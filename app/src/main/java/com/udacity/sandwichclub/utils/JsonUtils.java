package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {
  private static final String NAME = "name";
  private static final String MAIN_NAME = "mainName";
  private static final String ALSO_KNOWN_AS = "alsoKnownAs";
  private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
  private static final String DESCRIPTION = "description";
  private static final String IMAGE = "image";
  private static final String INGREDIENTS = "ingredients";

  public static Sandwich parseSandwichJson(String json) throws JSONException {


    JSONObject sandwichJson = new JSONObject(json);
    Sandwich sandwich = new Sandwich();
    JSONObject sandwichName = sandwichJson.getJSONObject(NAME);
    String sandwichMainName = sandwichName.getString(MAIN_NAME);
    sandwich.setMainName(sandwichMainName);

    String placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN);
    sandwich.setPlaceOfOrigin(placeOfOrigin);

    String description = sandwichJson.getString(DESCRIPTION);
    sandwich.setDescription(description);

    JSONArray alsoKnownAsJsonArray = sandwichName.getJSONArray(ALSO_KNOWN_AS);
    List<String> alsoKnownAs = new ArrayList<>();
    for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
      alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
    }
    sandwich.setAlsoKnownAs(alsoKnownAs);

    JSONArray ingredientsJsonArray = sandwichJson.getJSONArray(INGREDIENTS);
    List<String> ingredients = new ArrayList<>();
    for (int i = 0; i < ingredientsJsonArray.length(); i++) {
      ingredients.add(ingredientsJsonArray.getString(i));
    }
    sandwich.setIngredients(ingredients);

    String image = sandwichJson.getString(IMAGE);
    sandwich.setImage(image);

    return sandwich;
  }
}
