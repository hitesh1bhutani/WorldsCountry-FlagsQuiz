package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by hitesh1bhutani on 09-01-2017.
 */
public class QuizMain extends Activity implements View.OnClickListener {

    private static int _oceanFlag, _countryPosition, isShowAd;
    private ArrayList<String> optionsList;
    private Button optionOne, optionTwo, optionThree, optionFour;
    private String actualCountryName;
    private String[] actualCountryNameId;
    private ArrayList<Button> buttonList;
    private Bundle bundle;
    private CountDownTimer countDownTimer;
    private InterstitialAd mInterstitialAd;
    private SharedPreferences sharedPreferences;
    private boolean nextOrPrevious;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizmain);
        if(checkWhetherToLoadAddOrNot()) loadAd();
        bundle=getIntent().getExtras();
        _oceanFlag=bundle.getInt(getResources().getString(R.string.ocean));
        _countryPosition=bundle.getInt(getResources().getString(R.string.itemPosition));
        setImageResource(_countryPosition);
        createOptionList();
        setButtons();
        setButtonText(optionsList);
    }

    private void loadAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.interstitial_ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                nextQuestion();
            }
        });
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }

    private boolean checkWhetherToLoadAddOrNot() {
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isShowAd = sharedPreferences.getInt(getResources().getString(R.string.displayInterstitialAd), 1);
        return isShowAd >= 5;
    }

    private void setButtons(){
        optionOne= (Button) findViewById(R.id.optionOne);
        optionTwo= (Button) findViewById(R.id.optionTwo);
        optionThree= (Button) findViewById(R.id.optionThree);
        optionFour= (Button) findViewById(R.id.optionFour);
        final Button next= (Button) findViewById(R.id.next);
        final Button previous= (Button) findViewById(R.id.previous);
        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf");
        optionOne.setTypeface(typeface);
        optionTwo.setTypeface(typeface);
        optionThree.setTypeface(typeface);
        optionFour.setTypeface(typeface);
        next.setTypeface(typeface);
        previous.setTypeface(typeface);
        next.setOnClickListener(this);
        previous.setOnClickListener(this);
    }

    private void setButtonText(ArrayList optionsList) {
        createButtonList();
        for(int i=0;i<optionsList.size();i++){
            final Random nextOption=new Random();
            final String nextCountry=optionsList.get(nextOption.nextInt(optionsList.size())).toString();
            buttonList.get(i).setText(nextCountry);
            buttonList.remove(i);
            optionsList.remove(nextCountry);
            i--;
        }
    }

    private void createButtonList() {
        buttonList=new ArrayList<>(4);
        buttonList.add(optionOne);
        buttonList.add(optionTwo);
        buttonList.add(optionThree);
        buttonList.add(optionFour);
    }

    private void createOptionList() {
        optionsList=new ArrayList<>();
        optionsList.add(getAnswer(_countryPosition));
        final ArrayList<String> optionsToChooseFrom=new ArrayList<>();
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.africa_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.asia_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.europe_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.north_america_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.oceania_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.south_america_flag_list)));
        optionsToChooseFrom.remove(optionsList.get(0));
        for(int i=1;i<4;i++){
            final Random newNumber=new Random();
            optionsList.add(optionsToChooseFrom.get(newNumber.nextInt(optionsToChooseFrom.size())));
            optionsToChooseFrom.remove(optionsList.get(i));
        }
    }

    private String getAnswer(int countryPosition) {
        actualCountryName=null;
        if(_oceanFlag==0){
            actualCountryName=getResources().getStringArray(R.array.africa_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.africa_flag_list);
        } else if(_oceanFlag==1){
            actualCountryName=getResources().getStringArray(R.array.asia_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.asia_flag_list);
        } else if(_oceanFlag==2){
            actualCountryName=getResources().getStringArray(R.array.europe_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.europe_flag_list);
        } else if(_oceanFlag==3){
            actualCountryName=getResources().getStringArray(R.array.north_america_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.north_america_flag_list);
        } else if(_oceanFlag==4){
            actualCountryName=getResources().getStringArray(R.array.oceania_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.oceanFlagQuantity);
        } else if(_oceanFlag==5){
            actualCountryName=getResources().getStringArray(R.array.south_america_flag_list)[countryPosition];
            actualCountryNameId=getResources().getStringArray(R.array.south_america_flag_list);
        }
        return actualCountryName;
    }

    private void setImageResource(int countryPosition) {
        final ImageView iv= (ImageView) findViewById(R.id.countryFlag);
        final int[] covers=PrepareList.countryNameListMain(_oceanFlag);
        iv.setImageResource(covers[countryPosition]);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        try{
            countDownTimer.cancel();
        } catch (NullPointerException n){
            n.getMessage();
        }
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.optionOne:
                checkAnswer(((Button) v).getText().toString(), R.id.optionOne);
                break;
            case R.id.optionTwo:
                checkAnswer(((Button) v).getText().toString(), R.id.optionTwo);
                break;
            case R.id.optionThree:
                checkAnswer(((Button) v).getText().toString(), R.id.optionThree);
                break;
            case R.id.optionFour:
                checkAnswer(((Button) v).getText().toString(), R.id.optionFour);
                break;
            case R.id.next:
                nextOrPrevious = true;
                nextQuestion();
                break;
            case R.id.previous:
                nextOrPrevious = false;
                nextQuestion();
                break;
        }
    }

    private void nextQuestion() {
        try{
            countDownTimer.cancel();
        } catch (Exception e){
            e.getMessage();
        }
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        if (mInterstitialAd!=null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            isShowAd = 0;
            edit.putInt(getResources().getString(R.string.displayInterstitialAd), isShowAd);
        } else {
            isShowAd = isShowAd + 1;
            edit.putInt(getResources().getString(R.string.displayInterstitialAd), isShowAd);
            Intent newIntent=new Intent(getApplicationContext(),QuizMain.class);
            bundle=new Bundle();
            bundle.putInt(getResources().getString(R.string.ocean),_oceanFlag);
            if(nextOrPrevious){
                if(actualCountryNameId.length<=_countryPosition+1){
                    bundle.putInt(getResources().getString(R.string.itemPosition),0);
                } else {
                    bundle.putInt(getResources().getString(R.string.itemPosition),_countryPosition+1);
                }
            } else{
                if(_countryPosition-1<0){
                    bundle.putInt(getResources().getString(R.string.itemPosition),actualCountryNameId.length-1);
                } else {
                    bundle.putInt(getResources().getString(R.string.itemPosition),_countryPosition-1);
                }
            }
            newIntent.putExtras(bundle);
            startActivity(newIntent);
            finish();
        }
        edit.apply();
    }

    private void checkAnswer(String userAnswer, int ButtonId) {
        final Button btn = (Button) findViewById(ButtonId);
        if(userAnswer.equalsIgnoreCase(actualCountryName)) {
            createButtonList();
            for(int i=0;i<buttonList.size();i++){
                buttonList.get(i).setClickable(false);
                buttonList.get(i).setFocusable(false);
            }
            addToSharedPreferences(_countryPosition);
            final AnimationDrawable animationDrawable=new AnimationDrawable();
            animationDrawable.addFrame(new ColorDrawable(btn.getSolidColor()), 500);
            animationDrawable.setOneShot(false);
            animationDrawable.addFrame(new ColorDrawable(Color.GREEN), 500);
            btn.setBackground(animationDrawable);
            animationDrawable.start();
            if(mInterstitialAd!=null && mInterstitialAd.isLoaded()){
                nextQuestion();
            } else {
                final Random random = new Random();
                final String winner =
                        getResources().getStringArray(R.array.win)[random.nextInt(5)] + getResources().getString(R.string.nextQuestionIn);
                countDownTimer = new CountDownTimer(4000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        TextView win = (TextView) findViewById(R.id.win);
                        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Candara.ttf");
                        win.setTypeface(typeface);
                        String won = winner + String.valueOf(Math.ceil(millisUntilFinished / 1000));
                        win.setVisibility(View.VISIBLE);
                        win.setText(won);
                    }

                    @Override
                    public void onFinish() {
                        nextOrPrevious = false;
                        nextQuestion();
                    }
                };
                countDownTimer.start();
            }
        } else {
            btn.setBackgroundColor(Color.RED);
            final Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
            btn.startAnimation(shake);
            Vibrator vibe= (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
            vibe.vibrate(100);
        }
    }

    private void addToSharedPreferences(int countryPosition) {
        final boolean trueOrFalse = sharedPreferences.getBoolean(
                getResources().getStringArray(StringArray.getStringArrayFromResources(_oceanFlag))[countryPosition],false);
        final SharedPreferences.Editor edit=sharedPreferences.edit();
        edit.putBoolean(getResources().getStringArray(StringArray.getStringArrayFromResources(_oceanFlag))[countryPosition],true);
        edit.apply();
        if(!trueOrFalse){
            final int number=sharedPreferences.getInt(
                getResources().getStringArray(R.array.oceanList)[_oceanFlag],0)+1;
            edit.putInt(
                getResources().getStringArray(R.array.oceanList)[_oceanFlag],number);
            edit.apply();
        }
    }
}