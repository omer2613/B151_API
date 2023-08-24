package test_data;

import java.util.HashMap;
import java.util.Map;

public class JsonPlaceHolderTestData {

    public Map<String,Object> expectedDataMethod(Integer userId, String title, Boolean completed){
        Map<String,Object> expectedData = new HashMap<>();
        if (userId!=null){
            expectedData.put("userId",userId);
        }
        if (title!=null) {
            expectedData.put("title", title);
        }
        if (completed!=null){
            expectedData.put("completed",completed);
        }
        return expectedData;
    }
        //JsonPlaceHolder daki json datasini String e ceviren method
    public static String convertJsonToString(Integer userId,String title,Boolean completed){ //read value'yu kullanabilmek icin body yi bir String container a koyduk
      return   " {\n" +
                "                \"userId\": "+userId+",\n" +
                "                \"title\": \""+title+"\",\n" +
                "                \"completed\": "+completed+"\n" +
                "            }";
    }
}

/*
" {\n" +
                "                \"userId\": 10,\n" +
                "                \"id\": 198,\n" +
                "                \"title\": \"quis eius est sint explicabo\",\n" +
                "                \"completed\": true\n" +
                "            }";
 */