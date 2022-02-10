package com.kashitkalaecom.brandmodelmgmt.docmgmt;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;

public abstract class FileManager {
    private static String FILE_SYSTEM = "AWS_S3";

    public static FileManager getArtthaFileManager() {
     
    	
    	String fileSystem = FILE_SYSTEM;
    	if (StringUtils.equals(fileSystem, "AWS_S3")) {
            return AWSS3FileManager.getInstance();
        }
        return null;

    }

    public abstract void addDocument(String location, String fileName, File file) throws Exception;

    public abstract void addDocument(String location, String fileName, String content) throws Exception;

    public abstract void addDocument(String location, String fileName, InputStream input) throws Exception;

    public abstract Resource readFile(String location, String fileName) throws Exception;

    public abstract void checkAndCreateLocation(String location) throws Exception;

    public abstract List<Object> getListofLocations();

    public abstract void deleteLocation(String location) throws Exception;

    public abstract String getAsciiContent(String filePath, String fileName) throws Exception;

    public void addDocument(String bucketName, String dirPath, String fileName, InputStream stream) throws Exception {
        if (StringUtils.equals(FILE_SYSTEM, "ALI_OSS")) {
            addDocument(bucketName, dirPath + File.separator + fileName, stream);
        } else {
            addDocument(bucketName + File.separator + dirPath, fileName, stream);
        }
    }

    public Resource readFile(String bucketName, String dirPath, String fileName) throws Exception {
         Resource resource=null;
        if (StringUtils.equals(FILE_SYSTEM, "ALI_OSS")) {
            resource=readFile(bucketName, dirPath + File.separator + fileName);
        } else {
            resource=readFile(bucketName + File.separator + dirPath, fileName);
        }
        return resource;
    }



}
