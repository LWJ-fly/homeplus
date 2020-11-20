package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
/**
 * @program: homeplus
 * @description: json的工具类
 * @author: ZEK
 * @create: 2019-04-18 13:37
 **/
public class JsonUtil {

    /**
     * 将json字符串转换为实体类对象
     *
     * @param json
     * @return
     */
    public static Object json2Obj(String json) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            Object entiy = mapper.readValue(json, Object.class);
            return entiy;
        } catch (com.fasterxml.jackson.core.JsonParseException e) {
            e.printStackTrace();
        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将List对象序列化为JSON文本
     */
    public static <T> String toJSONString(List<T> list) {
        String result= JSONObject.toJSONString(list);
        return result;
    }

    /**
     * 将对象序列化为JSON文本
     *
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        String result= JSONObject.toJSONString(object);
        return result;
    }

    /**
     * 将对象object转换为json字符串.
     *
     * @param object
     * @return
     */
    public static String obj2Json(Object object) {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            String json = mapper.writeValueAsString(object);
            return json;
        } catch (com.fasterxml.jackson.core.JsonGenerationException e) {
            e.printStackTrace();
        } catch (com.fasterxml.jackson.databind.JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 将JSON对象序列化为JSON文本
     *
     * @param jsonObject
     * @return
     */
    public static String toJSONString(JSONObject jsonObject) {
        return jsonObject.toString();
    }


     /**
     * json字符串转换成ArrayList集合
     * (需要实体类)
     * @param jsonString
     * @return
     */
    public static <T>ArrayList<T> json2ArrayList(String jsonString,Class cls){
        ArrayList<T> list = null;
        try {
            list = (ArrayList<T>) JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json字符串转换成List集合
     * (需要实体类)
     * @param jsonString
     * @return
     */
    public static <T>List<T> json2List(String jsonString,Class cls){
        List<T> list = null;
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * json转List
     * (不需要实体类)
     * @param jsonStr
     * @return
     */
    public static JSONArray json2List(String jsonStr){
        return JSON.parseArray(jsonStr);
    }
    
}
