package me.silhouette;

import android.app.Activity;
import android.os.Bundle;

public class Doodle extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(new DoodleView(this));
    }
}
