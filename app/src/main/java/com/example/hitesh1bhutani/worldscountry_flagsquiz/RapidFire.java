package com.example.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by hitesh1bhutani on 18-02-2017.
 */
public class RapidFire extends Activity implements View.OnClickListener {

    private ArrayList<String> optionsList;
    private int _answerNumber, finalTotalScore=0;
    private Button optionOne, optionTwo, optionThree, optionFour;
    private Random random;
    private ArrayList<Button> buttonList;
    private ArrayList<Integer> numberList;
    private String answer;
    private ProgressBar progressBar;
    private CountDownTimer count;
    private TextView totalScore;
    private int totalScoreInstance;
    private String names[];
    private int score[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rapidfire);
        progressBar= (ProgressBar) findViewById(R.id.progressBarRF);
        addSharedPrefs(-1);
        setCountDownTimer();
        createOptionsList();
        setButtons();
        setButtonsText();
        prepareCovers(_answerNumber);
        totalScore= (TextView) findViewById(R.id.totalScore);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Candara.ttf");
        totalScore.setTypeface(typeface);
        final String totalScoreString=getResources().getString(R.string.totalScore);
        setTotalScore(totalScoreString, finalTotalScore, 50);
    }

    private void setCountDownTimer() {
        count=new CountDownTimer(10000,1) {
            @Override
            public void onTick(long millisUntilFinished) {
                final int progress = (int) (millisUntilFinished);
                progressBar.setProgress(progress);
                setTotalScore(getResources().getString(R.string.totalScore), finalTotalScore, (int)Math.floor(millisUntilFinished/200));
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                showDialogBox(getResources().getString(R.string.timeIsUp), getResources().getString(R.string.anotherShot));
            }
        };
        count.start();
    }

    private void prepareCovers(int answerNumber) {
        final Integer[] coverInt= new Integer[]{
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
                R.drawable.tuvalu_main, R.drawable.argentina_main, R.drawable.bolivia_main,
                R.drawable.brazil_main, R.drawable.chile_main,
                R.drawable.colombia_main, R.drawable.dominican_republic_main,
                R.drawable.ecuador_main, R.drawable.guyana_main,
                R.drawable.panama_main, R.drawable.paraguay_main,
                R.drawable.peru_main, R.drawable.saint_lucia_main,
                R.drawable.saint_vincent_and_the_grenadines_main, R.drawable.suriname_main,
                R.drawable.trinidad_and_tobago_main, R.drawable.uruguay_main,
                R.drawable.venezuela_main
        };
        final ArrayList<Integer> covers=new ArrayList<>();
        covers.addAll(Arrays.asList(coverInt));
        final ImageView iv= (ImageView) findViewById(R.id.countryFlagRF);
        iv.setImageResource(covers.get(answerNumber));
    }

    private void setButtonsText() {
        createButtonList();
        for(int i=0;i<optionsList.size();i++){
            random=new Random();
            final String nextCountry=optionsList.get(random.nextInt(optionsList.size()));
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

    private void setButtons() {
        optionOne= (Button) findViewById(R.id.optionOneRF);
        optionTwo= (Button) findViewById(R.id.optionTwoRF);
        optionThree= (Button) findViewById(R.id.optionThreeRF);
        optionFour= (Button) findViewById(R.id.optionFourRF);
        optionOne.setOnClickListener(this);
        optionTwo.setOnClickListener(this);
        optionThree.setOnClickListener(this);
        optionFour.setOnClickListener(this);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "palatino-linotype.ttf");
        optionOne.setTypeface(typeface);
        optionTwo.setTypeface(typeface);
        optionThree.setTypeface(typeface);
        optionFour.setTypeface(typeface);
    }

    private void createOptionsList() {
        optionsList=new ArrayList<>();
        final ArrayList<String> optionsToChooseFrom=new ArrayList<>();
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.africa_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.asia_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.europe_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.north_america_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.oceania_flag_list)));
        optionsToChooseFrom.addAll(Arrays.asList(getResources().getStringArray(R.array.south_america_flag_list)));
        optionsList.add(getAnswer(optionsToChooseFrom));
        optionsToChooseFrom.remove(optionsList.get(0));
        for(int i=1;i<4;i++){
            random=new Random();
            optionsList.add(optionsToChooseFrom.get(random.nextInt(optionsToChooseFrom.size())));
            optionsToChooseFrom.remove(optionsList.get(i));
        }
    }

    private String getAnswer(ArrayList<String> optionsToChooseFrom) {
        random=new Random();
        _answerNumber=random.nextInt(numberList.size());
        answer = optionsToChooseFrom.get(_answerNumber);
        numberList.remove(Integer.valueOf(_answerNumber));
        return answer;
    }

    @Override
    public void onClick(View v) {
        count.cancel();
        if(((Button) v).getText().toString().equalsIgnoreCase(answer)) {
            if(numberList.size()!=0){
                finalTotalScore = totalScoreInstance;
                setCountDownTimer();
                createOptionsList();
                setButtonsText();
                prepareCovers(_answerNumber);
                setTotalScore(getResources().getString(R.string.totalScore), finalTotalScore, 50);
            } else showDialogBox(getResources().getString(R.string.fullHouse), getResources().getString(R.string.competeWithYourself));
        } else showDialogBox(getResources().getString(R.string.gotItWrong), getResources().getString(R.string.anotherShot));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        count.cancel();
        finish();
    }

    private void showDialogBox(final String title, final String message) {
        final SoundPool soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        soundPool.play(soundPool.load(this, R.raw.cheer, 1), 1, 1, 0, 0, 1);
        final AlertDialog.Builder dialog = new AlertDialog.Builder(RapidFire.this);
        dialog.setTitle(title);
        dialog.setCancelable(false);
        if(checkHighScore()){
            dialog.setMessage(R.string.scoredHighScore);
            final LinearLayout layout=new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            final EditText name = new EditText(getApplicationContext());
            name.setHint(R.string.name);
            name.setSingleLine();
            name.setTextColor(Color.BLACK);
            layout.addView(name);
            dialog.setView(layout);
            dialog.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    submitHighScore(name.getText().toString());
                    dialog.cancel();
                    dialogBox(title, message);
                }
            });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    submitHighScore(getResources().getString(R.string.name));
                    dialog.cancel();
                    dialogBox(title, message);
                }
            });
        } else {
            dialog.setMessage(message);
            dialog.setPositiveButton(R.string.start, new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finalTotalScore = 0;
                    setCountDownTimer();
                    createOptionsList();
                    setButtonsText();
                    prepareCovers(_answerNumber);
                    setTotalScore(getResources().getString(R.string.totalScore), finalTotalScore, 50);
                    dialog.cancel();
                }
            });
            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
        }
        dialog.show();
    }

    private void dialogBox(String title, String message) {
        final AlertDialog.Builder di = new AlertDialog.Builder(RapidFire.this);
        di.setTitle(title);
        di.setMessage(message);
        di.setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finalTotalScore = 0;
                setCountDownTimer();
                createOptionsList();
                setButtonsText();
                prepareCovers(_answerNumber);
                setTotalScore(getResources().getString(R.string.totalScore), finalTotalScore, 50);
                dialog.cancel();
            }
        });
        di.setCancelable(false);
        di.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        di.show();
    }

    private void submitHighScore(String s) {
        final SharedPreferences preferences=getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.highScores), MODE_PRIVATE);
        int position=0, temp;
        for(temp=0; temp<10; temp++) {
            score[temp] = preferences.getInt("score" + temp, 0);
            if(score[temp] <= finalTotalScore) {
                position=temp;
                break;
            }
        }
        for(int y=9;y>position;y--){
            score[y] = score[y-1];
            names[y] = names[y-1];
        }
        names[position] = s;
        score[position] = finalTotalScore;
        final SharedPreferences.Editor editor = preferences.edit();
        for (int x=0; x<10; x++)
        {
            editor.putString("name"+x, this.names[x]);
            editor.putInt("score"+x, this.score[x]);
        }
        editor.apply();
        editor.commit();
    }

    private boolean checkHighScore() {
        if(finalTotalScore==0) {
            return false;
        }
        final SharedPreferences preferences=getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.highScores), MODE_PRIVATE);
        names=new String[10];
        score=new int[10];
        for (int x=0; x<10; x++)
        {
            names[x]=preferences.getString("name"+x, "-");
            score[x] = preferences.getInt("score"+x, 0);
            if(score[x]==0) return true;
            if(score[x]<finalTotalScore) return true;
        }
        return false;
    }

    private void addSharedPrefs(int number){
        if(number==-1) {
            numberList=new ArrayList<>();
            for(int i=0;i<194;i++){
                numberList.add(i,i);
            }
        }
    }

    private void setTotalScore(String totalScoreString, int Score, int currentScore) {
        final int finalTotalScoreIteration = Score + currentScore;
        totalScore.setText(totalScoreString + Score + " + " + currentScore + " = " + finalTotalScoreIteration);
        totalScoreInstance = finalTotalScoreIteration;
    }
}