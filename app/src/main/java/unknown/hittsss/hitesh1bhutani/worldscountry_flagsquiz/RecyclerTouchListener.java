package unknown.hittsss.hitesh1bhutani.worldscountry_flagsquiz;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hitesh1bhutani on 19-01-2017.
 */
class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    private RecyclerViewClickListener _mListener;
    private GestureDetector gestureDetector;
    private static int b=0;

    RecyclerTouchListener(Context context, final RecyclerViewClickListener recyclerViewClickListener){
        this._mListener=recyclerViewClickListener;
        gestureDetector=new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if(e.getAction()==e.ACTION_SCROLL){
            b=1;
        }
        if(b==0&&childView != null && _mListener != null && gestureDetector.onTouchEvent(e)){
            _mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
