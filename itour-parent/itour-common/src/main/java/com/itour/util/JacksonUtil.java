package com.itour.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.map.SerializationConfig;

/**
 * bean转json格式或者json转bean格式, 项目中我们通常使用这个工具类进行json---java互相转化
 */
public class JacksonUtil {
    /**
     * java 对象转换为 json 字符串
     *
     * @param obj
     *            对象
     * @return json
     */
    public static String toJSON(Object obj) {
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, obj);
        } catch (JsonGenerationException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String result = writer.toString();
        return (null == result) ? "" : result.replaceAll("null", "\"\"");
    }

    private static ObjectMapper mapper = new ObjectMapper();

    static{
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    /**
     * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType构造类型,然后调用本函数.
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }

        try {
            return (T) mapper.readValue(jsonString, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 构造的Collection Type如:
     * ArrayList<Bean>, 则调用constructCollectionType(ArrayList.class,Bean.class)
     * HashMap<String,Bean>, 则调用(HashMap.class,String.class, Bean.class)
     */
    public static JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * json字符串转换为对象
     *
     * @param <T>
     * @param json
     *            json字符串
     * @param clazz
     *            要转换对象的class
     * @return 对象
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {

        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json的字节流转换为对象
     *
     * @param <T>
     * @param request
     *            HttpServletRequest
     * @param clazz
     *            要转换对象的class
     * @return 对象
     */
    public static <T> T getRequestFromObject(HttpServletRequest request,
                                             Class<T> clazz) {
        try {
            return fromJSON(request.getInputStream(), clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * json的字节流转换为对象
     *
     * @param <T>
     * @param json
     *            json的字节流
     * @param clazz
     *            要转换对象的class
     * @return 对象
     */
    public static <T> T fromJSON(InputStream json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object> getJsonList(String jstr, List<Object> li) {
        char[] cstr = jstr.toCharArray();
        boolean bend = false;
        int istart = 0;
        int iend = 0;
        for (int i = 0; i < cstr.length; i++) {
            if ((cstr[i] == '{') && !bend) {
                istart = i;
            }
            if (cstr[i] == '}' && !bend) {
                iend = i;
                bend = true;
            }
        }

        if (istart != 0) {
            String substr = jstr.substring(istart, iend + 1);
            jstr = jstr.substring(0, istart - 1)
                    + jstr.substring(iend + 1, jstr.length());
            substr = substr.replace(",\"children\":", "");
            substr = substr.replace("]", "");
            substr = substr.replace("[", "");
            li.add(substr);
            getJsonList(jstr, li);
        }
        return li;
    }

    /**
     * 封装将json对象转换为java集合对象
     *
     * @param <T>
     * @param clazz
     * @param jsons
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getJavaCollection(T clazz, String jsons) {
        List<T> objs = null;
        JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(jsons);
        if (jsonArray != null) {
            objs = new ArrayList<T>();
            List list = (List) JSONSerializer.toJava(jsonArray);
            for (Object o : list) {
                JSONObject jsonObject = JSONObject.fromObject(o);
                T obj = (T) JSONObject.toBean(jsonObject, clazz.getClass());
                objs.add(obj);
            }
        }
        return objs;
    }
}
