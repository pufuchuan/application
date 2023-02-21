package com.ly.application.utils;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.NumberSerializers;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class JsonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        setMapperProperties(objectMapper);
    }

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    private static void setMapperProperties(ObjectMapper mapper) {
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        // GMT+8
        mapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        // Include.NON_NULL 属性为NULL 不序列化
        // ALWAYS // 默认策略，任何情况都执行序列化
        // NON_EMPTY // null、集合数组等没有内容、空字符串等，都不会被序列化
        // NON_DEFAULT // 如果字段是默认值，就不会被序列化
        // NON_ABSENT // null的不会序列化，但如果类型是AtomicReference，依然会被序列化
        //mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // 允许字段名没有引号（可以进一步减小json体积）：
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号：
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 允许出现特殊字符和转义符
        // mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true); 这个已经过时
        mapper.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        // 允许C和C++样式注释：
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        // 枚举输出成字符串
        // WRITE_ENUMS_USING_INDEX：输出索引
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        // 空对象不要抛出异常：
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // Date、Calendar等序列化为时间格式的字符串(如果不执行以下设置，就会序列化成时间戳格式)：
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // 反序列化时，遇到未知属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 反序列化时，遇到忽略属性不要抛出异常：
        mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
        // 反序列化时，空字符串对于的实例属性为null：
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);


        SimpleModule simpleModule = new SimpleModule(
                "SimpleModule",
                new Version(1, 0, 0, null, null, null));
        simpleModule.addSerializer(new NumberSerializers.LongSerializer(Long.class));

        mapper.registerModule(simpleModule);


        JavaTimeModule timeModule = new JavaTimeModule();

        timeModule.addSerializer(new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addSerializer(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));


//        timeModule.addSerializer(CustomInstantSerializer.newInstance(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS")));
//        timeModule.addDeserializer(Instant.class, CustomInstantDeserializer.newInstance(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSSSSS")));
        mapper.registerModule(timeModule);
    }

    public static <T> T toBean(String var1, Class<T> var2) {
        if (StrUtil.isBlank(var1) || null == var2) {
            return null;
        }
        try {
            return (T) objectMapper.readValue(var1, var2);
        } catch (JsonProcessingException e) {
            log.error("异常原因:" + ExceptionUtil.stacktraceToString(e));
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T toList(String var1, Class<T> var2) {
        if (StrUtil.isBlank(var1) || null == var2) {
            return null;
        }
        try {
            return (T) objectMapper.readValue(var1, getJavaType(List.class, var2));
        } catch (JsonProcessingException e) {
            log.error("异常原因:" + ExceptionUtil.stacktraceToString(e));
            e.printStackTrace();
        }
        return null;
    }

    protected static JavaType getJavaType(Class<?> var1, Class<?>... var2) {
        return objectMapper.getTypeFactory().constructParametricType(var1, var2);
    }

    public static String toJson(Object var1) {
        if (var1 == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(var1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static <K, V> Map<K, V> toMap(Object var1) {
//
//        return objectMapper.readValue(to)
//    }


}
