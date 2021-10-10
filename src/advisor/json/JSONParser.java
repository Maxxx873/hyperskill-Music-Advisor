package advisor.gson;
import com.google.gson.*;
import java.util.Iterator;


public class JSONParser {

    public static void parseCategories(String json) {

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray categoriesObjArr = jsonObject
                .getAsJsonObject("categories")
                .getAsJsonArray("items");

        Iterator categoriesItr = categoriesObjArr.iterator();
        while (categoriesItr.hasNext()) {
            JsonObject nameObj = (JsonObject) categoriesItr.next();
            System.out.println(nameObj.get("name").getAsString());
        }

    }


}
