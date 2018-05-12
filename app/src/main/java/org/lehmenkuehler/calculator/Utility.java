package org.lehmenkuehler.calculator;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Vibrator;
import android.text.Html;
import android.text.Spanned;

public class Utility extends Application
{
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext()
    {
        return context;
    }

    public static void vibrate()
    {
        Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (Preferences.VIBRATION) v.vibrate(30);
    }

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html)
    {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
        {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else
        {
            result = Html.fromHtml(html);
        }
        return result;
    }
}
