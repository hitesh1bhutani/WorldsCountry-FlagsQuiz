package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hitesh1bhutani on 07-01-2017.
 */
public class ChooseOcean extends ListActivity {

    private Bundle bundle;
    private static int quizType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ChooseOceanAdapter(getApplicationContext()));
        bundle=getIntent().getExtras();
        quizType=bundle.getInt(getResources().getString(R.string.quizType));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        final Intent newActivity;
        if(quizType==1) newActivity=new Intent(getApplicationContext(),QuizList.class);
        else newActivity=new Intent(getApplicationContext(),CountryToFlagQuiz.class);
        bundle=new Bundle();
        bundle.putInt(getResources().getString(R.string.ocean),position);
        newActivity.putExtras(bundle);
        startActivity(newActivity);
    }

    class SingleRow {
        final String _oceanName;
        final int _oceanFlagQuantity;
        SingleRow(String oceanName,int oceanFlagQuantity){
            this._oceanName=oceanName;
            this._oceanFlagQuantity=oceanFlagQuantity;
        }
    }

    static class ViewHolderItem {
        private TextView oceanName, oceanProgress;
        private ProgressBar bar;
    }

    class ChooseOceanAdapter extends BaseAdapter {

        private final Context _context;
        private LayoutInflater mInflater;
        private final ArrayList<SingleRow> list;
        private final Resources res;
        private final int[] oceanFlagQuantity;
        private final String[] oceans;
        private ViewHolderItem viewHolder;

        ChooseOceanAdapter(Context context) {
            list = new ArrayList<>();
            _context = context;
            res = _context.getResources();
            oceans=res.getStringArray(R.array.oceanList);
            oceanFlagQuantity=res.getIntArray(R.array.oceanFlagQuantity);
            addListObjects();
        }

        private void addListObjects() {
            for(int i=0;i<oceanFlagQuantity.length;i++) list.add(new SingleRow(oceans[i],oceanFlagQuantity[i]));
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view==null) {
                mInflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = mInflater.inflate(R.layout.chooseoceanitem, viewGroup, false);
                viewHolder = new ViewHolderItem();
                final Typeface typeface = Typeface.createFromAsset(getAssets(), "Candara.ttf");
                viewHolder.oceanName= (TextView) view.findViewById(R.id.oceanName);
                viewHolder.oceanName.setTypeface(typeface);
                viewHolder.bar= (ProgressBar) view.findViewById(R.id.pbar1);
                viewHolder.oceanProgress= (TextView) view.findViewById(R.id.oceanNameProgressReport);
                if(quizType==2) {
                    viewHolder.bar.setVisibility(View.GONE);
                    viewHolder.oceanProgress.setVisibility(View.GONE);
                }
                view.setTag(viewHolder);
            } else {
                viewHolder= (ViewHolderItem) view.getTag();
            }
            if(quizType==1){
                viewHolder.bar.setMax(getResources().getIntArray(R.array.oceanFlagQuantity)[i]);
                viewHolder.bar.setProgress(getProgressFromSharedPreferences(i));
            }
            viewHolder.oceanName.setText(oceans[i]);
            viewHolder.oceanProgress.setText(calculatePercentage(getProgressFromSharedPreferences(i), i));
            return view;
        }

        private String calculatePercentage(int progressFromSharedPreferences, int i) {
            final int percentageInt = (int)((((float) progressFromSharedPreferences) / ((float) getResources().getIntArray(R.array.oceanFlagQuantity)[i])) * 10000);
            final float percentage = (float) percentageInt / 100 ;
            return String.valueOf(percentage) + "%" ;
        }


        private int getProgressFromSharedPreferences(int oceanPosition){
            final SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            return sharedPreferences.getInt(
                    getResources().getStringArray(R.array.oceanList)[oceanPosition],0);
        }
    }
}
