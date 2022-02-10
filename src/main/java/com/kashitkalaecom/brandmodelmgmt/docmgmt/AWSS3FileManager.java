package com.kashitkalaecom.brandmodelmgmt.docmgmt;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class AWSS3FileManager extends FileManager
{
    private static final Logger log = LoggerFactory.getLogger(AWSS3FileManager.class);

    public static AWSS3FileManager awsS3FileManager = new AWSS3FileManager();
    private AmazonS3 s3client;
    
    @Value("${aws.s3.accesskey}")
    private String awsS3AccessKey;
    
    @Value("${aws.s3.secretkey}")
    private String awsS3SecretKey;
    
    @Value("${aws.s3.region}")
    private String awsS3Region;

    private AWSS3FileManager()
    {

	AWSCredentials credentials = new BasicAWSCredentials(awsS3SecretKey, awsS3AccessKey);

	Regions regions = getRegion(awsS3Region);

	s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(regions).build();
    }

    protected static AWSS3FileManager getInstance()
    {
	return awsS3FileManager;
    }

    @Override
    public void addDocument(String bucketName, String fileName, File file)
    {
	s3client.putObject(bucketName, fileName, file);
    }

    @Override
    public void addDocument(String bucketName, String fileName, String content)
    {
	s3client.putObject(bucketName, fileName, content);
    }

    public void addDocument(String bucketName, String fileName, InputStream input, ObjectMetadata metadata)
    {

	s3client.putObject(bucketName, fileName, input, metadata);
    }

    @Override
    public void addDocument(String bucketName, String fileName, InputStream input)
    {
	s3client.putObject(bucketName, fileName, input, null);

    }

    @Override
    public Resource readFile(String location, String fileName) throws Exception
    {
	S3Object object = s3client.getObject(new GetObjectRequest(location, fileName));
	InputStream in = object.getObjectContent();

	ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	int nRead;
	byte[] data = new byte[32768];
	while ((nRead = in.read(data, 0, data.length)) != -1)
	{
	    buffer.write(data, 0, nRead);
	}

	buffer.flush();
	byte[] byteArray = buffer.toByteArray();
	in.close();
	ByteArrayResource byteArrayResource = new ByteArrayResource(byteArray);

	return byteArrayResource;

    }
    
    @Override
    public String getAsciiContent(String filePath, String fileName) throws Exception
    {
	S3Object object = s3client.getObject(new GetObjectRequest(filePath, fileName));
	S3ObjectInputStream inputStream =  object.getObjectContent();
	String content = IOUtils.toString(inputStream, Charset.defaultCharset());
	return content;
    }


    public void checkAndCreateLocation(String bucketName)
    {

	if (s3client.doesBucketExistV2(bucketName))
	{
	    log.info("Bucket name is not available." + " Try again with a different Bucket name.");
	    return;
	}

	s3client.createBucket(bucketName);
    }

    @Override
    public List<Object> getListofLocations()
    {
	return null;

    }

    public List<Bucket> getListofBuckets()
    {
	List<Bucket> buckets = s3client.listBuckets();
	return buckets;

    }

    @Override
    public void deleteLocation(String bucketName)
    {
	try
	{
	    s3client.deleteBucket(bucketName);
	} catch (AmazonServiceException e)
	{
	    System.err.println(e.getErrorMessage());
	    return;
	}

    }

    private Regions getRegion(String region)
    {
	if (StringUtils.equals(region, Regions.AF_SOUTH_1.name()))
	    return Regions.AF_SOUTH_1;

	if (StringUtils.equals(region, Regions.AP_EAST_1.name()))
	    return Regions.AP_EAST_1;

	if (StringUtils.equals(region, Regions.AP_NORTHEAST_1.name()))
	    return Regions.AP_NORTHEAST_1;

	if (StringUtils.equals(region, Regions.AP_NORTHEAST_2.name()))
	    return Regions.AP_NORTHEAST_2;

	if (StringUtils.equals(region, Regions.AP_SOUTH_1.name()))
	    return Regions.AP_SOUTH_1;

	if (StringUtils.equals(region, Regions.AP_SOUTHEAST_1.name()))
	    return Regions.AP_SOUTHEAST_1;

	if (StringUtils.equals(region, Regions.AP_SOUTHEAST_2.name()))
	    return Regions.AP_SOUTHEAST_2;

	if (StringUtils.equals(region, Regions.CA_CENTRAL_1.name()))
	    return Regions.CA_CENTRAL_1;

	if (StringUtils.equals(region, Regions.CN_NORTH_1.name()))
	    return Regions.CN_NORTH_1;

	if (StringUtils.equals(region, Regions.CN_NORTHWEST_1.name()))
	    return Regions.CN_NORTHWEST_1;

	if (StringUtils.equals(region, Regions.EU_CENTRAL_1.name()))
	    return Regions.EU_CENTRAL_1;

	if (StringUtils.equals(region, Regions.EU_NORTH_1.name()))
	    return Regions.EU_NORTH_1;

	if (StringUtils.equals(region, Regions.EU_SOUTH_1.name()))
	    return Regions.EU_SOUTH_1;

	if (StringUtils.equals(region, Regions.EU_WEST_1.name()))
	    return Regions.EU_WEST_1;

	if (StringUtils.equals(region, Regions.EU_WEST_2.name()))
	    return Regions.EU_WEST_2;

	if (StringUtils.equals(region, Regions.EU_WEST_3.name()))
	    return Regions.EU_WEST_3;

	if (StringUtils.equals(region, Regions.GovCloud.name()))
	    return Regions.GovCloud;

	if (StringUtils.equals(region, Regions.ME_SOUTH_1.name()))
	    return Regions.ME_SOUTH_1;

	if (StringUtils.equals(region, Regions.SA_EAST_1.name()))
	    return Regions.SA_EAST_1;

	if (StringUtils.equals(region, Regions.US_EAST_1.name()))
	    return Regions.US_EAST_1;

	if (StringUtils.equals(region, Regions.US_EAST_2.name()))
	    return Regions.US_EAST_2;

	if (StringUtils.equals(region, Regions.US_GOV_EAST_1.name()))
	    return Regions.US_GOV_EAST_1;

	if (StringUtils.equals(region, Regions.US_WEST_1.name()))
	    return Regions.US_WEST_1;

	if (StringUtils.equals(region, Regions.US_WEST_2.name()))
	    return Regions.US_WEST_2;

	return null;
    }

    
}
