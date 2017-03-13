package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by hitesh1bhutani on 19-01-2017.
 */
class FlagIconAdapter extends RecyclerView.Adapter<FlagIconAdapter.MyViewHolder>{

    private final int _oceanFlag;
    private Context _context;
    private List<Flag> _flagList;

    FlagIconAdapter(Context context, List<Flag> flagList, int oceanFlag) {
        this._context=context;
        this._flagList=flagList;
        this._oceanFlag=oceanFlag;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView= LayoutInflater.from(_context).inflate(R.layout.flagiconview,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Flag flag=_flagList.get(position);
        Glide.with(_context).load(flag.get_flagThumbnail()).into(holder.thumbnail);
        holder.markCheckedImageView.setVisibility(View.GONE);
        if(getBooleanValue(position)){
            holder.markCheckedImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return _flagList.size();
    }

    private boolean getBooleanValue(int position) {
        final SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(_context);
        return sharedPreferences.getBoolean(_context.getResources().getStringArray(StringArray.getStringArrayFromResources(_oceanFlag))[position],false);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView thumbnail, markCheckedImageView;

        MyViewHolder(View itemView) {
            super(itemView);
            thumbnail= (ImageView) itemView.findViewById(R.id.thumbnail);
            markCheckedImageView = (ImageView) itemView.findViewById(R.id.checkMark);
        }
    }
}


