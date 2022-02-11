package com.kashitkalaecom.brandmodelmgmt.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.*;

public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    //private static Gson gson = new Gson();
    private static Gson gson = new GsonBuilder()
            .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();


    private static ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) throws Exception {
        if (object == null) {
            return null;
        }

        String objectJson = gson.toJson(object);
        return objectJson;
    }

    public static String toJsonHtmlEscaping(Object object) throws Exception {
        if (object == null) {
            return null;
        }

        String objectJson = new GsonBuilder().disableHtmlEscaping().create().toJson(object);
        return objectJson;
    }

    public static String toJsonWithDateFormat(Object object) throws Exception {
        Gson gsonObj = new GsonBuilder()
//	    .setDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz").create();
                .setDateFormat("yyyy-MM-dd").create();
        if (object == null) {
            return null;
        }
        return gsonObj.toJson(object);
    }

    public static <T> T toObject(String jsonString, Class<T> classValue) throws Exception {

        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        return gson.fromJson(jsonString, classValue);
    }

    public static <T> T convertObject(Object basicObject, Class<T> classValue) throws Exception {

        if (basicObject == null) {
            return null;
        }
        String json = toJson(basicObject);

        return toObject(json, classValue);
    }

    @Deprecated
    public static <T> T toComplexObject(String jsonString, Type objectType) throws Exception {
        // System.out.println("Json String -----"+jsonString);

        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return gson.fromJson(jsonString, objectType);

    }

    public static <T> List<T> toList(String jsonString, Class<T> clazz) throws Exception {

        Type type = new TypeToken<List<T>>() {
        }.getType();

        if (StringUtils.isBlank(jsonString) || StringUtils.equals(jsonString, "null")) {
            return null;
        }
        List<T> list = JsonUtil.toComplexObject(jsonString, type);

        List<T> newList = new ArrayList<T>();
        for (T t : list) {
            if (t instanceof LinkedTreeMap) {
                String tempJson = gson.toJson(t);
                if (StringUtils.isBlank(tempJson)) {
                    continue;
                }
                T t_new = gson.fromJson(tempJson, clazz);
                newList.add(t_new);
            } else {
                newList.add(t);
            }
        }
        return newList;
    }

    public static <K, V> Map<K, V> toMap(String jsonString, Class<K> clazzK, Class<V> clazzV) throws Exception {
        Type type = new TypeToken<HashMap<K, V>>() {
        }.getType();

        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        Map<K, V> map = JsonUtil.toComplexObject(jsonString, type);
        Set<K> keys = map.keySet();

        for (K k : keys) {
            V v = map.get(k);
            if (v instanceof LinkedTreeMap) {
                String tempJson = gson.toJson(v);
                if (StringUtils.isBlank(tempJson)) {
                    continue;
                }
                V v_new = gson.fromJson(tempJson, clazzV);
                map.put(k, v_new);
            }
        }
        return map;
    }

    public static <T> T mapToObject(Map<String, Object> objectMap, Class<T> clazz) {
        String tempJson = gson.toJson(objectMap);
        return gson.fromJson(tempJson, clazz);

    }

    public static <T> Map<String, Object> objectToMap(T t, Object object) throws Exception {
        String tempJson = gson.toJson(t);
        return toMap(tempJson, String.class, Object.class);

    }

    public static void main(String[] args) throws Exception {
        List<String> xx = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            xx.add(UUID.randomUUID().toString());
        }

        System.out.println(toJson(xx));

        Timestamp timestamp = CustomClock.timestamp();
        String timeJson = toJson(timestamp);
        System.out.println(timeJson);

        System.out.println(toObject(timeJson, Timestamp.class));
    }


    public static String createJsonByMapper(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        String objectJson = JsonUtil.mapper.writeValueAsString(object);
        return objectJson;
    }

    public static <T> T createObject(String jsonString, TypeReference<T> typeReference) throws JsonMappingException, JsonProcessingException {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        return JsonUtil.mapper.readValue(jsonString, typeReference);

    }

}
