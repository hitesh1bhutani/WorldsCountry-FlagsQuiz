package com.example.hitesh1bhutani.worldscountry_flagsquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hitesh1bhutani on 18-02-2017.
 */

public class HighScore extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.highscore);
        final SharedPreferences sharedPreferences =
                getApplicationContext().getSharedPreferences(getApplicationContext().getString(R.string.highScores), MODE_PRIVATE);
        final ArrayList<HashMap<String, String>> menuItems=new ArrayList<>();
        for(int i=0;i<10;i++){
            final HashMap<String, String> map=new HashMap<>();
            map.put(getResources().getString(R.string.name), sharedPreferences.getString("name"+i, "-"));
            map.put(getResources().getString(R.string.highScores), String.valueOf(sharedPreferences.getInt("score"+i, 0)));
            menuItems.add(map);
        }
        final ListView listView = (ListView) findViewById(R.id.listView);
        MyHighScoreAdapter adapter = new MyHighScoreAdapter(this, menuItems);
        listView.setAdapter(adapter);
        if (menuItems.size() == 0) {
            final AlertDialog.Builder alert = new AlertDialog.Builder(HighScore.this);
            alert.setMessage(R.string.noHighScore);
            alert.setPositiveButton(R.string.rapidFire, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent newIntent = new Intent(getApplicationContext(), RapidFire.class);
                    startActivity(newIntent);
                    finish();
                }
            });
            alert.setNegativeButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.setCancelable(false);
            alert.show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private class MyHighScoreAdapter extends BaseAdapter{

        private ArrayList<HashMap<String, String>> _menuItems;
        private Context _context;
        private View row;

        MyHighScoreAdapter(Context context, ArrayList<HashMap<String, String>> menuItems) {
            _context = context;
            _menuItems = menuItems;
        }

        @Override
        public int getCount() {
            return _menuItems.size();
        }

        @Override
        public Object getItem(int position) {
            return _menuItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            row = convertView;
            if (row==null){
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.highscorelayout, parent, false);
            }
            final MyHighScoreViewHolder viewHolder = new MyHighScoreViewHolder(row);
            String name = _menuItems.get(position).get(getResources().getString(R.string.name));
            if(name.length()>15){
                name = name.substring(0, 14) + "..." ;
            }
            viewHolder.name.setText(name);
            viewHolder.highScore.setText(_menuItems.get(position).get(getResources().getString(R.string.highScores)));
            return row;
        }

        private class MyHighScoreViewHolder extends RecyclerView.ViewHolder{

            private TextView name, highScore;

            MyHighScoreViewHolder(View itemView) {
                super(itemView);
                final Typeface typeface = Typeface.createFromAsset(getAssets(), "SHOWG.TTF");
                name = (TextView) itemView.findViewById(R.id.nameTable);
                name.setTypeface(typeface);
                highScore = (TextView) itemView.findViewById(R.id.scoreTable);
                highScore.setTypeface(typeface);
            }
        }
    }
}