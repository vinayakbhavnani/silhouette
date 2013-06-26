package me.silhouette;

import android.content.Context;
import android.graphics.*;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DoodleView extends View
{
    File doodleFolder;
    Path path;
    Paint paint;

    public DoodleView(Context context)
    {
        super(context);
        setDrawingCacheEnabled(true);
        doodleFolder = setupMediaFolder();
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
    }

    public DoodleView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        setDrawingCacheEnabled(true);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        setDrawingCacheEnabled(true);
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.drawColor(Color.WHITE);
        canvas.drawLine(0, 0, 100, 100, paint);
        canvas.drawPath(path, paint);
        try
        {
            getDrawingCache().compress(Bitmap.CompressFormat.JPEG, 80, new FileOutputStream(new File(doodleFolder, "doodle.jpeg")));
        } catch (FileNotFoundException e)
        {
            Log.d("doodle", "failed");
        }
        setOnTouchListener(new TouchEventTranslator(this, path));
    }

    private File setupMediaFolder()
    {
        File path = new File(Environment.getExternalStorageDirectory(), "doodle");
        if (!path.exists())
        {
            path.mkdirs();
        }
        return path;
    }
}
