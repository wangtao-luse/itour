package com.itour.util;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.CollectionType;
import org.codehaus.jackson.map.type.TypeFactory;
/**
 * jackson帮助类
 * @author wwang
 *
 */
public class JacksonUtil {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static Object readJson2Entity(String jsonStr, Class<?> clazz) {
		Object entityBean = null;
		try {
			if (jsonStr != null)
				entityBean = objectMapper.readValue(jsonStr, clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityBean;
	}

	public static Object[] readJson2Array(String jsonStr, Class<?> clazz) {
		try {
			if (jsonStr != null)
				return ((Object[]) (Object[]) objectMapper.readValue(jsonStr, clazz));
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return null;
	}

	public static List<?> readJson2List(String returnJSON, Class<?> clazz) {
		CollectionType collectionType = TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
		try {
			return ((List<?>) objectMapper.readValue(returnJSON, collectionType));
		} catch (JsonParseException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		return Collections.EMPTY_LIST;
	}

	public static JsonNode jsonStr2JsonNode(String jsonStr) {
		JsonNode node = null;
		try {
			if (jsonStr != null)
				node = objectMapper.readTree(jsonStr);
		} catch (JsonProcessingException e) {
		} catch (IOException e) {
		}
		return node;
	}

	public static String pojo2JsonStr(Object obj) {
		StringWriter sw = new StringWriter();
		try {
			objectMapper.writeValue(sw, obj);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (sw != null) {
			try {
				sw.flush();
				sw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return sw.toString();
	}
}
