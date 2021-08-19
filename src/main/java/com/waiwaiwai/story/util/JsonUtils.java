package com.waiwaiwai.story.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * @author mhwangzl
 * @date 2021/8/18 12:02
 * @description
 */
public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        // Bean中为NULL的不输出
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // Bean中不存在的字段不校验
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        OBJECT_MAPPER.setDateFormat(sdf);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_FORMAT)));

        OBJECT_MAPPER.registerModule(javaTimeModule);
    }

    /**
     * 将 Java 对象转为 JSON 字符串
     */
    public static String toJson(Object obj) {
        String jsonStr;
        try {
            jsonStr = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T toObject(String json, Class<T> type) {
        T obj;
        try {
            obj = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * 将 JSON 字符串转为 Java 对象
     */
    public static <T> T toObject(String json, TypeReference<T> valueTypeRef) {
        T obj;
        try {
            obj = OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    /**
     * @param json
     * @param clzz
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clzz) {
        List<T> list;
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(List.class, clzz);
            list = OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public static <T> Map<String, T> toMap(String json, Class<T> clzz) {
        Map<String, T> map;
        try {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(Map.class, String.class, clzz);
            map = OBJECT_MAPPER.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    /**
     * 从List<A> copy到List<B>
     *
     * @param list  List<B>
     * @param clazz B
     * @return List<B>
     */
    public static <T> List<T> copyList(List<?> list, Class<T> clazz) {
        if (list == null) {
            return null;
        }
        if (list.size() == 0) {
            return Lists.newArrayList();
        }
        return list.stream().map(l -> BeanUtils.transform(l, clazz)).collect(Collectors.toList());
    }
}
