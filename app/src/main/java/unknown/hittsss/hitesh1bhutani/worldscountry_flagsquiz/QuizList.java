package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hitesh1bhutani on 07-01-2017.
 */
public class QuizList extends Activity{

    private FlagIconAdapter adapter;
    private List<Flag> flagList;
    private static int _oceanFlag, _position;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flagiconlist);

        bundle=getIntent().getExtras();
        _oceanFlag=bundle.getInt(getResources().getString(R.string.ocean));

        final TextView oceanNameTitle = (TextView) findViewById(R.id.oceanNameTitle);
        final Typeface typeface = Typeface.createFromAsset(getAssets(), "Candara.ttf");
        oceanNameTitle.setTypeface(typeface);
        oceanNameTitle.setText(getResources().getStringArray(R.array.oceanList)[_oceanFlag]);

        setProgressOfTextView(_oceanFlag);

        final ImageView backButton= (ImageView) findViewById(R.id.topBackImageButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final RecyclerView recyclerView= (RecyclerView) findViewById(R.id.recycler_view);

        flagList=new ArrayList<>();

        adapter=new FlagIconAdapter(this, flagList , _oceanFlag);

        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(20), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), new RecyclerViewClickListener(){

            @Override
            public void onItemClick(View v, int position) {
                _position=position;
                final Intent newIntent=new Intent(getApplicationContext(),QuizMain.class);
                bundle=new Bundle();
                bundle.putInt(getResources().getString(R.string.ocean),_oceanFlag);
                bundle.putInt(getResources().getString(R.string.itemPosition),_position);
                newIntent.putExtras(bundle);
                startActivity(newIntent);
            }
        }));
        prepareFlags();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setProgressOfTextView(_oceanFlag);
    }

    private void prepareFlags(){

        final int[] covers=PrepareList.countryNameList(_oceanFlag);

        for(int i=0;i<covers.length;i++) {
            final Flag fi=new Flag(covers[i]);
            flagList.add(fi);
        }

        adapter.notifyDataSetChanged();
    }

    private int dpToPx(int dp){
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,getResources().getDisplayMetrics()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setProgressOfTextView(int oceanFlag) {
        final TextView progressBox = (TextView) findViewById(R.id.oceanProgressTitle);
        final Typeface type = Typeface.createFromAsset(getAssets(), "Candara.ttf");
        progressBox.setTypeface(type);
        final SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final int progressInt = sharedPreferences.getInt(getResources().getStringArray(R.array.oceanList)[oceanFlag],0);
        final String progress = String.valueOf(progressInt) + "/" + String.valueOf(getResources().getIntArray(R.array.oceanFlagQuantity)[oceanFlag]);
        progressBox.setText(progress);
    }

    private class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int _spanCount;
        private int _spacing;
        private boolean _includeEdge;

        private GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this._includeEdge=includeEdge;
            this._spacing=spacing;
            this._spanCount=spanCount;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            final int position=parent.getChildAdapterPosition(view);
            final int column=position % _spanCount;

            if(_includeEdge) {
                outRect.left= _spacing - column * _spacing / _spanCount;
                outRect.right= (column + 1) * _spacing / _spanCount;

                if(position < _spanCount) {
                    outRect.top=_spacing;
                }
                outRect.bottom=_spacing;
            } else {
                outRect.left = column * _spanCount / _spanCount;
                outRect.right = _spacing - (column + 1) * _spacing / _spanCount;
                if(position >= _spanCount) {
                    outRect.top = _spacing;
                }
            }
        }
    }
}
