package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        changeLanguage(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), null));
        setContentView(R.layout.activity_main);
        initialize();
        getRating();
        loadAd();
        Log.d("onCreate 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private void changeLanguage(String language) {
        Log.d( "changeLanguage 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        final String tempLanguage = language;
        Resources resources = getBaseContext().getResources();
        for(int i=0; i<resources.getStringArray(R.array.langList1).length; i++){
            final String lang = resources.getStringArray(R.array.langList2)[i];
            final String lang1 = resources.getStringArray(R.array.langList1)[i];
            if(Locale.getDefault().getDisplayLanguage().equalsIgnoreCase(lang)) {
                language = lang;
                break;
            }
            if(Locale.getDefault().getDisplayLanguage().equalsIgnoreCase(lang1)) {
                language = lang;
                break;
            }
        }
        if (language==null) {
            language = "en";
        }
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(tempLanguage == null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(resources.getString(R.string.language), language);
            editor.apply();
        } else {
            language = sharedPreferences.getString(resources.getString(R.string.language), "en");
//            Log.d("bbbb");
        }



        final Locale locale = new Locale(language);
        final Configuration config = new Configuration();
        config.locale = locale;
        Log.d( "changeLanguage 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        Log.d( "changeLanguage 2", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(resources.getString(R.string.language), language);
        editor.apply();
        Log.d( "changeLanguage 3", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private void loadAd() {
        Log.d("Load Ad 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                finish();
            }
        });
        requestNewInterstitial();
        Log.d("Load Ad 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private void requestNewInterstitial() {
        Log.d("RequestNewInter 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        AdRequest adRequest = new AdRequest.Builder()
                .build();

        mInterstitialAd.loadAd(adRequest);
        Log.d("RequestNewInter 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private void getRating(){
        Log.d("getRating 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sharedPreferences.getBoolean(getResources().getString(R.string.isRatingProvided), true)){
            if(sharedPreferences.getInt(getResources().getString(R.string.getRating), 0) == 1 || rating(sharedPreferences)){
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(R.string.rateUs);
                alert.setMessage(R.string.feedBack);
                alert.setCancelable(false);
                alert.setPositiveButton(R.string.rateNow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        launchMarket();
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(getResources().getString(R.string.isRatingProvided), false);
                        editor.apply();
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
            Log.d("getRating 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
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

    private void shareApp() {
        final String appPackageName = getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        sendIntent.putExtra(Intent.EXTRA_TEXT, getApplicationContext().getString(R.string.shareApplicationString) + appPackageName);
        sendIntent.setType("text/plain");
        sendIntent.createChooser(sendIntent, "Choose client");
        startActivity(sendIntent);
    }

    private void increaseRatingNumber(SharedPreferences sharedPreferences){
        Log.d("Increase Rating 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
        final int rating = sharedPreferences.getInt(getResources().getString(R.string.getRating), 0) + 1;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(rating == 7)
            editor.putInt(getResources().getString(R.string.getRating), 2);
        else
            editor.putInt(getResources().getString(R.string.getRating), rating);
        editor.apply();
        editor.commit();
        Log.d("Increase Rating 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private boolean rating(SharedPreferences sharedPreferences){
        final int rating = sharedPreferences.getInt(getResources().getString(R.string.getRating), 0);
        return rating == 5;
    }

    private void initialize() {
        Log.d("Initialize 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
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
        final ImageView share = (ImageView) findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp();
            }
        });
        final ImageView rate = (ImageView) findViewById(R.id.rating);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMarket();
            }
        });
        final ImageView chooseLanguage = (ImageView) findViewById(R.id.chooseLanguage);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        chooseLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertLanguage = new AlertDialog.Builder(MainActivity.this);
                alertLanguage.setTitle(R.string.language);
                final RelativeLayout fullLayout = new RelativeLayout(MainActivity.this);
                final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                fullLayout.setLayoutParams(lp);

                final RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp1.addRule(RelativeLayout.CENTER_IN_PARENT);
                lp1.setMargins(((int) getResources().getDimension(R.dimen.space)),(int)getResources().getDimension(R.dimen.space),(int)getResources().getDimension(R.dimen.space),(int)getResources().getDimension(R.dimen.space));
                final ImageView lang1 = new ImageView(MainActivity.this);
                lang1.setLayoutParams(lp1);
                lang1.setImageResource(R.drawable.united_kingdom);
                fullLayout.addView(lang1);

                final RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                lp2.setMargins(((int) getResources().getDimension(R.dimen.space)),(int)getResources().getDimension(R.dimen.space),(int)getResources().getDimension(R.dimen.space),(int)getResources().getDimension(R.dimen.space));
                lp2.addRule(RelativeLayout.BELOW, lang1.getId());
                final ImageView lang2 = new ImageView(MainActivity.this);
                lang2.setLayoutParams(lp2);
                lang2.setImageResource(R.drawable.spain);
                fullLayout.addView(lang2);

                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final SharedPreferences.Editor edit = sharedPreferences.edit();
                final String la = sharedPreferences.getString(getResources().getString(R.string.language), "en");

                lang1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit.putString(getResources().getString(R.string.language), "en");
                    }
                });

                lang2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edit.putString(getResources().getString(R.string.language), "es");
                    }
                });

                alertLanguage.setView(fullLayout);
                alertLanguage.setCancelable(false);
                alertLanguage.setPositiveButton(getResources().getString(R.string.save), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit.apply();
                    }
                });
                alertLanguage.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edit.putString(getResources().getString(R.string.language), la);
                        edit.apply();
                    }
                });
                alertLanguage.show();
            }
        });
        final ImageView sound = (ImageView) findViewById(R.id.volume);
        drawableChange(sound, sharedPreferences.getBoolean(getResources().getString(R.string.isVolumeEnabled), true));
        sound.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if(sharedPreferences.getBoolean(getResources().getString(R.string.isVolumeEnabled), true)){
                    editor.putBoolean(getResources().getString(R.string.isVolumeEnabled), false);
                    drawableChange(sound, false);
                }
                else {
                    editor.putBoolean(getResources().getString(R.string.isVolumeEnabled), true);
                    drawableChange(sound, true);
                }
                editor.apply();
            }
        });

        Log.d("Initialize 1", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    private void drawableChange(ImageView sound, boolean indicator) {
        if(indicator)
            sound.setImageResource(R.drawable.ic_volume_up_indicator);
        else
            sound.setImageResource(R.drawable.ic_volume_off_indicator);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Destroy", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
    }

    @Override
    public void onClick(View view) {
        Log.d("onClick 0", PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString(getResources().getString(R.string.language), "true123"));
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
                if(mInterstitialAd!=null && mInterstitialAd.isLoaded()) mInterstitialAd.show();
                else finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mInterstitialAd!=null && mInterstitialAd.isLoaded()) mInterstitialAd.show();
        else finish();
    }
}
