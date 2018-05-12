package org.lehmenkuehler.calculator.Mechanics;

import android.content.Context;
import android.content.SharedPreferences;

import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public class History
{
    private final Context context;

    static private SharedPreferences history;

    private int index = 0;

    public History(Context context)
    {
        this.context = context;
    }

    public static int getNumberOfElements()
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        return history.getInt("index", 0);
    }

    static String getResultRe(int id)
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        return history.getString(id + "_resultRe", "0");
    }

    static String getResultIm(int id)
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        return history.getString(id + "_resultIm", "0");
    }

    public static String getInput(int id)
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        return history.getString(id + "_input", "");
    }

    public static String getOutput(int id)
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        return history.getString(id + "_output", "");
    }

    public static void clearHistory()
    {
        history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        history.edit().clear().apply();
    }

    public void addToHistory(String inputString, String finalAnswerString, Result result)
    {
        history = context.getSharedPreferences(context.getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
        index = history.getInt("index", 0);
        index++;
        if (index > Preferences.HISTORY_LENGTH)
        {
            history.edit().remove(index - Preferences.HISTORY_LENGTH + "_input").apply();
            history.edit().remove(index - Preferences.HISTORY_LENGTH + "_output").apply();
        }
        history.edit().putInt("index", index).apply();
        history.edit().putString(index + "_input", inputString).apply();
        history.edit().putString(index + "_output", finalAnswerString).apply();
        history.edit().putString(index + "_resultRe", result.getRe().toPlainString()).apply();
        history.edit().putString(index + "_resultIm", result.getIm().toPlainString()).apply();
    }

}
