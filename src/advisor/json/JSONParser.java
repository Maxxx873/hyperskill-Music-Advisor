package advisor.json;
import advisor.entitie.Category;
import advisor.entitie.Release;
import com.google.gson.*;
import java.util.Iterator;
import java.util.List;


public class JSONParser {

    public static void parseCategories(String json, List<Category> categories) {

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray categoriesObjArr = jsonObject
                .getAsJsonObject("categories")
                .getAsJsonArray("items");

        Iterator categoriesItr = categoriesObjArr.iterator();
        while (categoriesItr.hasNext()) {
            JsonObject nameObj = (JsonObject) categoriesItr.next();
            categories.add(new Category(nameObj.get("id").getAsString(), nameObj.get("name").getAsString()));
        }

    }

    public static void parseFeatures(String json) {

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray categoriesObjArr = jsonObject
                .getAsJsonObject("playlists")
                .getAsJsonArray("items");

        Iterator categoriesItr = categoriesObjArr.iterator();
        while (categoriesItr.hasNext()) {
            JsonObject nameObj = (JsonObject) categoriesItr.next();
            System.out.println(nameObj.get("name").getAsString());
            System.out.println(nameObj.getAsJsonObject("external_urls")
                    .get("spotify")
                    .getAsString() + "\n");
        }

    }

    public static void parseNewReleases(String json, List<Release> releases) {
        String artist = new String();

        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        JsonArray categoriesObjArr = jsonObject
                .getAsJsonObject("albums")
                .getAsJsonArray("items");

        Iterator categoriesItr = categoriesObjArr.iterator();
        while (categoriesItr.hasNext()) {
            StringBuilder stringBuilder = new StringBuilder();
            JsonObject nameObj = (JsonObject) categoriesItr.next();
            JsonArray artistsArr = nameObj.getAsJsonArray("artists");
            Iterator artistsItr = artistsArr.iterator();
            stringBuilder.append("[");
            while (artistsItr.hasNext()) {
                JsonObject artistObj = (JsonObject) artistsItr.next();
                stringBuilder.append(artistObj.get("name").getAsString());
                if(artistsItr.hasNext()) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("]");
            releases.add(new Release(nameObj.get("name").getAsString(),
                    stringBuilder.toString(),
                    nameObj.getAsJsonObject("external_urls")
                            .get("spotify")
                            .getAsString()));

        }

        releases.stream().forEach(Release::print);
    }

    public static void parsePlayLists(String json) {
        JsonObject jsonObject = JsonParser.parseString(json).getAsJsonObject();
        if(json.contains("message")) {
            System.out.println(jsonObject);
            return;
        }
        JsonArray categoriesObjArr = jsonObject
                .getAsJsonObject("playlists")
                .getAsJsonArray("items");

        Iterator categoriesItr = categoriesObjArr.iterator();
        while (categoriesItr.hasNext()) {
            JsonObject nameObj = (JsonObject) categoriesItr.next();
            System.out.println(nameObj.get("name").getAsString());
            System.out.println(nameObj.getAsJsonObject("external_urls")
                    .get("spotify")
                    .getAsString() + "\n");
        }
    }

}
