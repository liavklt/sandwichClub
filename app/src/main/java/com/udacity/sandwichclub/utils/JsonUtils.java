package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonUtils {

  public static Sandwich parseSandwichJson(String json) throws JSONException {
    final String NAME = "name";
    final String MAIN_NAME = "mainName";
    final String ALSO_KNOWN_AS = "alsoKnownAs";
    final String PLACE_OF_ORIGIN = "placeOfOrigin";
    final String DESCRIPTION = "description";
    final String IMAGE = "image";
    final String INGREDIENTS = "ingredients";

    JSONObject sandwichJson = new JSONObject(json);
    Sandwich sandwich = new Sandwich();
    JSONObject sandwichDetails = sandwichJson.getJSONObject(NAME);
    String sandwichMainName = sandwichDetails.getString(MAIN_NAME);
    String placeOfOrigin = sandwichJson.getString(PLACE_OF_ORIGIN);
    String description = sandwichJson.getString(DESCRIPTION);

    JSONArray alsoKnownAsJsonArray = sandwichDetails.getJSONArray(ALSO_KNOWN_AS);
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
    sandwich.setMainName(sandwichMainName);
    sandwich.setPlaceOfOrigin(placeOfOrigin);
    sandwich.setDescription(description);
    sandwich.setImage(image);

    return sandwich;
  }
}
