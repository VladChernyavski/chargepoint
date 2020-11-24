package by.cniitu.chargepoint.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String getJsonString(Object object){

        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return jsonString;
    }

    public static Object[] getObjectByJson(String jsonString){
        try {
            return objectMapper.readValue(jsonString, Object[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static DataTransferUtilObject getDataTransferObject(String jsonString){
        try {
            return objectMapper.readValue(jsonString, DataTransferUtilObject.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
