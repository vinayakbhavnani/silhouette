package me.silhouette;

import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchEventTranslator implements View.OnTouchListener
{
    Path mPath;
    public TouchEventTranslator(View view , Path path)
    {
        mPath = path;
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent)
    {
        int action = motionEvent.getAction();
        if (action == MotionEvent.ACTION_DOWN)
        {
            Log.d("doodle","action down"+action);
            mPath.moveTo(motionEvent.getX(),motionEvent.getY());
        }
        else if(action == MotionEvent.ACTION_UP)
        {
            Log.d("doodle","action up"+action);
        }
        else if(action == MotionEvent.ACTION_MOVE)
        {
            Log.d("doodle","action move"+action);
            updateHistoricalPath(motionEvent,mPath);
            mPath.lineTo(motionEvent.getX(),motionEvent.getY());
        }
        else
        {
            Log.d("doodle","action other"+action);
        }
        view.invalidate();
        return true;
    }

    private void updateHistoricalPath(MotionEvent event , Path path)
    {
        for(int i=0;i<event.getHistorySize();i++)
        {
            path.lineTo(event.getHistoricalX(i),event.getHistoricalY(i));
        }
    }
}
