package com.wolf.springboot.utils.json;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * <p>
 * Title: JsonUtil
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author wyx
 * @date 2019年4月16日
 */
public class JsonUtil {

	/**
	 * ObjectMapper是JSON操作的核心，Jackson的所有JSON操作都是在ObjectMapper中实现。
	 * ObjectMapper有多个JSON序列化的方法，可以把JSON字符串保存File、OutputStream等不同的介质中。
	 * writeValue(File arg0, Object arg1)把arg1转成json序列，并保存到arg0文件中。
	 * writeValue(OutputStream arg0, Object arg1)把arg1转成json序列，并保存到arg0输出流中。
	 * writeValueAsBytes(Object arg0)把arg0转成json序列，并把结果输出成字节数组。
	 * writeValueAsString(Object arg0)把arg0转成json序列，并把结果输出成字符串。
	 */
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		// 解决实体未包含字段反序列化时抛出异常
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		// 对于空的对象转json的时候不抛出错误
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		// 允许属性名称没有引号
		mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
		// 允许单引号
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
	}

	public static String objectToJson(Object obj) {
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T jsonToClass(String json, Class<T> beanType) {
		T t = null;
		try {
			t = mapper.readValue(json, beanType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = null;
		try {
			map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public static <T> List<T> jsonToList(String json, Class<T> beanType) {
		List<T> list = null;
		try {
			JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
			list = mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @Title: findValue
	 * @Description: 获取json对象数据的属性
	 * @param resData- 请求的数据
	 * @param resPro- 请求的属性
	 * @return
	 */
	public static String findValue(String resData, String resPro) {
		String result = null;
		try {
			JsonNode node = mapper.readTree(resData);
			JsonNode resProNode = node.get(resPro);
			result = JsonUtil.objectToJson(resProNode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
