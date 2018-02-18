package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;
import java.util.List;
import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

  public static final String EXTRA_POSITION = "extra_position";
  private static final int DEFAULT_POSITION = -1;
  private static final String NOT_AVAILABLE = "N/A";

  Sandwich sandwich;
  private ImageView imageView;
  private TextView alsoKnownTv;
  private TextView descriptionTv;
  private TextView placeOfOriginTv;
  private TextView ingredientsTv;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    imageView = findViewById(R.id.image_iv);
    alsoKnownTv = findViewById(R.id.also_known_tv);
    descriptionTv = findViewById(R.id.description_tv);
    placeOfOriginTv = findViewById(R.id.origin_tv);
    ingredientsTv = findViewById(R.id.ingredients_tv);

    Intent intent = getIntent();
    if (intent == null) {
      closeOnError();
    }

    int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
    if (position == DEFAULT_POSITION) {
      // EXTRA_POSITION not found in intent
      closeOnError();
      return;
    }

    String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
    String json = sandwiches[position];

    try {
      sandwich = JsonUtils.parseSandwichJson(json);
    } catch (JSONException e) {
      e.printStackTrace();
    }
    if (sandwich == null) {
      // Sandwich data unavailable
      closeOnError();
      return;
    }

    populateUI();

    setTitle(sandwich.getMainName());
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      // Respond to the action bar's Up/Home button
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  private void populateUI() {
    Picasso.with(this)
        .load(sandwich.getImage())
        .into(imageView);
    populatePlaceOfOrigin(placeOfOriginTv);
    populateDescription(descriptionTv);
    populateAlsoKnownAs(alsoKnownTv);
    populateIngredients(ingredientsTv);
  }

  private void closeOnError() {
    finish();
    Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
  }

  private void populateIngredients(TextView ingredientsTv) {
    List<String> ingredients = sandwich.getIngredients();
    if (ingredients.isEmpty()) {
      ingredientsTv.setText(NOT_AVAILABLE);
    } else {
      ingredientsTv.setText(TextUtils.join(",", ingredients));
    }
  }

  private void populatePlaceOfOrigin(TextView placeOfOriginTv) {
    String placeOfOrigin = sandwich.getPlaceOfOrigin();
    if (placeOfOrigin == null || placeOfOrigin.isEmpty()) {
      placeOfOriginTv.setText(NOT_AVAILABLE);
    } else {
      placeOfOriginTv.setText(placeOfOrigin);
    }
  }

  private void populateAlsoKnownAs(TextView alsoKnownTv) {
    List<String> alsoKnownAs = sandwich.getAlsoKnownAs();
    if (alsoKnownAs.isEmpty()) {
      alsoKnownTv.setText(NOT_AVAILABLE);
    } else {
      alsoKnownTv.setText(TextUtils.join(",", alsoKnownAs));
    }

  }

  private void populateDescription(TextView descriptionTv) {
    String description = sandwich.getDescription();
    if (description == null) {
      descriptionTv.setText(NOT_AVAILABLE);
    } else {
      descriptionTv.setText(sandwich.getDescription());
    }

  }
}
