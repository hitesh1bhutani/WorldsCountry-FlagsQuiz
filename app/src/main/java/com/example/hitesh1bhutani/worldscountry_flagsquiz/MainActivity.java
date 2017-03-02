package com.example.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        getRating();
    }

    private void getRating(){
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sharedPreferences.getInt(getResources().getString(R.string.getRating), 0) == 1 || rating(sharedPreferences)){
            final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle(R.string.rateUs);
            alert.setMessage(R.string.feedBack);
            alert.setCancelable(false);
            alert.setPositiveButton(R.string.rateNow, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    launchMarket();
                }
            });
            alert.setNegativeButton(R.string.rateLater, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            increaseRatingNumber(sharedPreferences);
            alert.show();
        } else {
            increaseRatingNumber(sharedPreferences);
        }
    }

    private void launchMarket() {
        Uri uri = Uri.parse("market://details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, " unable to find market app", Toast.LENGTH_LONG).show();
        }
    }

    private void increaseRatingNumber(SharedPreferences sharedPreferences){
        final int rating = sharedPreferences.getInt(getResources().getString(R.string.getRating), 0) + 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(rating == 7)
            editor.putInt(getResources().getString(R.string.getRating), 2);
        else
            editor.putInt(getResources().getString(R.string.getRating), rating);
        editor.apply();
        editor.commit();
    }

    private boolean rating(SharedPreferences sharedPreferences){
        final int rating = sharedPreferences.getInt(getResources().getString(R.string.getRating), 0);
        return rating == 5;
    }

    private void initialize() {
        final Button flagToCountry = (Button) findViewById(R.id.flagToCountry);
        final Button countryToFlag = (Button) findViewById(R.id.countryToFlag);
        final Button exit = (Button) findViewById(R.id.exit);
        final Button rapidFire = (Button) findViewById(R.id.rapidFire);
        final Button highScore = (Button) findViewById(R.id.highScores);
        highScore.setTypeface(Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf"));
        rapidFire.setTypeface(Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf"));
        exit.setTypeface(Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf"));
        countryToFlag.setTypeface(Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf"));
        flagToCountry.setTypeface(Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf"));
        flagToCountry.setOnClickListener(this);
        countryToFlag.setOnClickListener(this);
        rapidFire.setOnClickListener(this);
        exit.setOnClickListener(this);
        highScore.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        final Intent newIntent;
        final Bundle bundle;
        switch (view.getId()){
            case R.id.flagToCountry:
                newIntent = new Intent(getApplicationContext(), ChooseOcean.class);
                bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.quizType),1);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
                break;
            case R.id.countryToFlag:
                newIntent = new Intent(getApplicationContext(), ChooseOcean.class);
                bundle = new Bundle();
                bundle.putInt(getResources().getString(R.string.quizType),2);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
                break;
            case R.id.rapidFire:
                newIntent = new Intent(getApplicationContext(), RapidFire.class);
                startActivity(newIntent);
                break;
            case R.id.highScores:
                newIntent = new Intent(getApplicationContext(), HighScore.class);
                startActivity(newIntent);
                break;
            case R.id.exit:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
