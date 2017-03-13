package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hitesh1bhutani on 17-01-2017.
 */
public class CountryToFlagQuiz extends Activity implements View.OnClickListener {

    private static String[] countryNameList;
    private static int[] _covers;
    private ArrayList<Integer> _allCoversList, _optionsList;
    private ImageView[] _optionPane;
    private static int _position, _oceanName, isShowAd;
    private String _questionCountryName;
    private Random random;
    private Bundle bundle;
    private CountDownTimer countDownTimer;
    private ImageView _optionOne, _optionTwo, _optionThree, _optionFour;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countrytoflagquiz);
        if(checkWhetherToLoadAddOrNot()) loadAd();
        bundle = getIntent().getExtras();
        _oceanName = bundle.getInt(getResources().getString(R.string.ocean));
        initializeImageViews();
        _questionCountryName = getCountryName(_oceanName);
        final TextView questionField = (TextView) findViewById(R.id.questionCountryName);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Georgia.ttf");
        questionField.setTypeface(typeface);
        questionField.setText(_questionCountryName);
        prepareCovers(_oceanName);
        prepareOptionsList(_allCoversList);
        setOptions(_optionPane, _optionsList, _questionCountryName, _covers, _position);
    }

    private void loadAd() {
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
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
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("D9XXXXXXXXXXXXXXXXXXXXXXXXXXXXX")
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    private boolean checkWhetherToLoadAddOrNot() {
        final SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        isShowAd = sharedPreferences.getInt(getResources().getString(R.string.displayInterstitialAdCTF), 1);
        return isShowAd >= 5;
    }

    private void setOptions(ImageView[] optionPane, ArrayList<Integer> optionsList, String questionCountryName, int[] covers, int position) {
        int j=0;
        random=new Random();
        final int firstOption=random.nextInt(optionPane.length);
        optionPane[firstOption].setImageResource(covers[position]);
        optionPane[firstOption].setTag(questionCountryName);
        for (int i = 0; i< optionsList.size(); j++){
            if(j==firstOption) continue;
            final int nextOption=random.nextInt(optionsList.size());
            optionPane[j].setImageResource(optionsList.get(nextOption));
            optionsList.remove(nextOption);
        }
    }

    private void initializeImageViews() {
        _optionOne= (ImageView) findViewById(R.id.optionOneMain);
        _optionTwo= (ImageView) findViewById(R.id.optionTwoMain);
        _optionThree= (ImageView) findViewById(R.id.optionThreeMain);
        _optionFour= (ImageView) findViewById(R.id.optionFourMain);
        _optionOne.setOnClickListener(this);
        _optionTwo.setOnClickListener(this);
        _optionThree.setOnClickListener(this);
        _optionFour.setOnClickListener(this);
        _optionPane=new ImageView[]{_optionOne, _optionTwo, _optionThree, _optionFour};
        final Button next= (Button) findViewById(R.id.nextCTF);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf");
        next.setOnClickListener(this);
        next.setTypeface(typeface);
        final Button previous= (Button) findViewById(R.id.previousCTF);
        previous.setOnClickListener(this);
        previous.setTypeface(typeface);
    }

    private void prepareOptionsList(ArrayList<Integer> allCoversList) {
        _optionsList=new ArrayList<>();
        for(int i=0;i<3;i++){
            random=new Random();
            final int newNumber=random.nextInt(allCoversList.size());
            _optionsList.add(allCoversList.get(newNumber));
            allCoversList.remove(newNumber);
        }
    }

    private void prepareCovers(int oceanName) {
        _covers=PrepareList.countryNameListMain(oceanName);
        final int[] allCovers= new int[]{
                R.drawable.algeria_main, R.drawable.angola_main, R.drawable.burkina_faso_main,
                R.drawable.burundi_main, R.drawable.cameroon_main, R.drawable.cape_verde_main,
                R.drawable.central_african_republic_main, R.drawable.chad_main, R.drawable.comoros_main,
                R.drawable.cote_d_ivoire_main, R.drawable.democratic_republic_of_congo_main, R.drawable.djiboti_main,
                R.drawable.egypt_main, R.drawable.equatorial_guinea_main, R.drawable.eritrea_main,
                R.drawable.ethiopia_main, R.drawable.gabon_main, R.drawable.gambia_main,
                R.drawable.ghana_main, R.drawable.guinea_main, R.drawable.guinea_bissau_main,
                R.drawable.kenya_main, R.drawable.lesotho_main, R.drawable.liberia_main,
                R.drawable.libya_main, R.drawable.madagascar_main, R.drawable.malawi_main,
                R.drawable.mali_main, R.drawable.mauritania_main, R.drawable.mauritius_main,
                R.drawable.morocco_main, R.drawable.mozambique_main, R.drawable.namibia_main,
                R.drawable.niger_main, R.drawable.nigeria_main, R.drawable.republic_of_the_congo_main,
                R.drawable.rwanda_main, R.drawable.sao_tome_and_principe_main, R.drawable.senegal_main,
                R.drawable.seychelles_main, R.drawable.sierra_leone_main, R.drawable.somalia_main,
                R.drawable.south_sudan_main, R.drawable.south_africa_main, R.drawable.sudan_main, R.drawable.swaziland_main,
                R.drawable.tanzania_main, R.drawable.togo_main, R.drawable.tunisia_main,
                R.drawable.uganda_main, R.drawable.zambia_main, R.drawable.zimbabwe_main,
                R.drawable.afghanistan_main, R.drawable.armenia_main,
                R.drawable.azerbaijan_main, R.drawable.bahrain_main,
                R.drawable.bangladesh_main, R.drawable.bhutan_main,
                R.drawable.brunei_main, R.drawable.cambodia_main,
                R.drawable.china_main, R.drawable.cyprus_main,
                R.drawable.india_main, R.drawable.indonesia_main,
                R.drawable.iran_main, R.drawable.iraq_main,
                R.drawable.israel_main, R.drawable.japan_main,
                R.drawable.jordan_main, R.drawable.kazakhstan_main,
                R.drawable.kuwait_main, R.drawable.kyrgyzstan_main,
                R.drawable.laos_main, R.drawable.lebanon_main,
                R.drawable.malaysia_main, R.drawable.maldives_main,
                R.drawable.mongolia_main, R.drawable.myanmar_main,
                R.drawable.nepal_main, R.drawable.north_korea_main,
                R.drawable.oman_main, R.drawable.pakistan_main,
                R.drawable.philippines_main, R.drawable.qatar_main,
                R.drawable.russia_main, R.drawable.saudi_arabia_main,
                R.drawable.singapore_main, R.drawable.south_korea_main,
                R.drawable.sri_lanka_main, R.drawable.syria_main,
                R.drawable.taiwan_main, R.drawable.tajikistan_main,
                R.drawable.thailand_main, R.drawable.turkey_main,
                R.drawable.turkmenistan_main, R.drawable.united_arab_emirates_main,
                R.drawable.vietnam_main, R.drawable.yemen_main,
                R.drawable.albania_main, R.drawable.andorra_main,
                R.drawable.austria_main, R.drawable.belarus_main,
                R.drawable.belgium_main, R.drawable.bosnia_and_herzegovina_main,
                R.drawable.botswana_main, R.drawable.bulgaria_main,
                R.drawable.croatia_main, R.drawable.czech_republic_main,
                R.drawable.denmark_main, R.drawable.estonia_main,
                R.drawable.finland_main, R.drawable.france_main,
                R.drawable.georgia_main, R.drawable.germany_main,
                R.drawable.greece_main, R.drawable.hungary_main,
                R.drawable.iceland_main, R.drawable.ireland_main,
                R.drawable.italy_main, R.drawable.kosovo_main,
                R.drawable.latvia_main, R.drawable.liechtenstein_main,
                R.drawable.lithuania_main, R.drawable.luxembourg_main,
                R.drawable.macedonia_main, R.drawable.malta_main,
                R.drawable.moldova_main, R.drawable.monaco_main,
                R.drawable.montenegro_main, R.drawable.netherlands_main,
                R.drawable.norway_main, R.drawable.poland_main,
                R.drawable.portugal_main, R.drawable.romania_main,
                R.drawable.san_marino_main, R.drawable.serbia_main,
                R.drawable.slovakia_main, R.drawable.slovenia_main,
                R.drawable.spain_main, R.drawable.sweden_main,
                R.drawable.switzerland_main, R.drawable.ukraine_main,
                R.drawable.united_kingdom_main, R.drawable.vatican_city_main,
                R.drawable.antigua_and_barbuda_main, R.drawable.bahamas_main,
                R.drawable.barbados_main, R.drawable.belize_main,
                R.drawable.canada_main, R.drawable.costa_rica_main,
                R.drawable.cuba_main, R.drawable.dominica_main,
                R.drawable.el_salvador_main, R.drawable.greneda_main,
                R.drawable.guatemala_main, R.drawable.haiti_main,
                R.drawable.honduras_main, R.drawable.jamaica_main,
                R.drawable.mexico_main, R.drawable.nicaragua_main,
                R.drawable.saint_kitts_and_nevis_main, R.drawable.united_states_of_america_main,
                R.drawable.australia_main, R.drawable.east_timor_main,
                R.drawable.fiji_main, R.drawable.kiribati_main,
                R.drawable.marshall_islands_main, R.drawable.micronesia_main,
                R.drawable.nauru_main, R.drawable.new_zealand_main,
                R.drawable.niue_main, R.drawable.palau_main,
                R.drawable.papua_new_guinea_main, R.drawable.samoa_main,
                R.drawable.solomon_islands_main, R.drawable.tonga_main,
                R.drawable.tuvalu_main,
                R.drawable.argentina_main, R.drawable.bolivia_main,
                R.drawable.brazil_main, R.drawable.chile_main,
                R.drawable.colombia_main, R.drawable.dominican_republic_main,
                R.drawable.ecuador_main, R.drawable.guyana_main,
                R.drawable.panama_main, R.drawable.paraguay_main,
                R.drawable.peru_main, R.drawable.saint_lucia_main,
                R.drawable.saint_vincent_and_the_grenadines_main, R.drawable.suriname_main,
                R.drawable.trinidad_and_tobago_main, R.drawable.uruguay_main,
                R.drawable.venezuela_main };
        _allCoversList=new ArrayList<>();
        for(int i=0;i<allCovers.length;i++){
            _allCoversList.add(allCovers[i]);
        }
    }

    private String getCountryName(int oceanName) {
        if(oceanName==0) countryNameList=getResources().getStringArray(R.array.africa_flag_list);
        else if(oceanName==1) countryNameList=getResources().getStringArray(R.array.asia_flag_list);
        else if(oceanName==2) countryNameList=getResources().getStringArray(R.array.europe_flag_list);
        else if(oceanName==3) countryNameList=getResources().getStringArray(R.array.north_america_flag_list);
        else if(oceanName==4) countryNameList=getResources().getStringArray(R.array.oceania_flag_list);
        else if(oceanName==5) countryNameList=getResources().getStringArray(R.array.south_america_flag_list);
        random=new Random();
        _position=random.nextInt(countryNameList.length);
        return countryNameList[_position];
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
            case R.id.optionOneMain:
                checker(v.getTag(), R.id.optionOneMain);
                break;
            case R.id.optionTwoMain:
                checker(v.getTag(), R.id.optionTwoMain);
                break;
            case R.id.optionThreeMain:
                checker(v.getTag(), R.id.optionThreeMain);
                break;
            case R.id.optionFourMain:
                checker(v.getTag(), R.id.optionFourMain);
                break;
            case R.id.previousCTF:
                nextQuestion();
                break;
            case R.id.nextCTF:
                nextQuestion();
                break;
        }
    }

    private void checker(Object tag, int optionOneMain) {
        if(tag==_questionCountryName) {
            final ImageView btn= (ImageView) findViewById(optionOneMain);
            final Animation animation=new AlphaAnimation(0,1);
            animation.setInterpolator(new LinearInterpolator());
            animation.setRepeatCount(Animation.INFINITE);
            animation.setDuration(500);
            animation.setRepeatMode(Animation.REVERSE);
            btn.startAnimation(animation);
            if(mInterstitialAd!=null && mInterstitialAd.isLoaded()) nextQuestion();
            else {
                final String winner =
                        getResources().getStringArray(R.array.win)[random.nextInt(5)] + getResources().getString(R.string.nextQuestionIn);
                countDownTimer = new CountDownTimer(4000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        final TextView win = (TextView) findViewById(R.id.winCTF);
                        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Candara.ttf");
                        win.setTypeface(typeface);
                        final String won = winner + String.valueOf(Math.ceil(millisUntilFinished / 1000));
                        win.setVisibility(View.VISIBLE);
                        win.setText(won);
                    }

                    @Override
                    public void onFinish() {
                        nextQuestion();
                    }
                };
                countDownTimer.start();
            }
            _optionOne.setClickable(false);
            _optionTwo.setClickable(false);
            _optionThree.setClickable(false);
            _optionFour.setClickable(false);
        } else {
            final Vibrator vibe= (Vibrator) getSystemService(getApplicationContext().VIBRATOR_SERVICE);
            vibe.vibrate(100);
            final Animation shake=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.shake);
            final ImageView image= (ImageView) findViewById(optionOneMain);
            image.startAnimation(shake);
        }
    }

    private void nextQuestion() {
        try{
            countDownTimer.cancel();
        } catch (Exception e){
            e.getMessage();
        }
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final SharedPreferences.Editor edit = sharedPreferences.edit();
        if (mInterstitialAd!=null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            isShowAd = 0;
            edit.putInt(getResources().getString(R.string.displayInterstitialAd), isShowAd);
        } else {
            isShowAd = isShowAd + 1;
            edit.putInt(getResources().getString(R.string.displayInterstitialAdCTF), isShowAd);
            Intent newActivity = new Intent(getApplicationContext(), CountryToFlagQuiz.class);
            bundle = new Bundle();
            bundle.putInt(getResources().getString(R.string.ocean), _oceanName);
            newActivity.putExtras(bundle);
            startActivity(newActivity);
            finish();
        }
        edit.apply();
    }
}
