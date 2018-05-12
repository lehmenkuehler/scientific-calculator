package org.lehmenkuehler.calculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.math.BigDecimal;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity implements View.OnClickListener
{
    boolean visualUpdate;

    Button ButtonAlternativeModeOff, ButtonAlternativeModeSCI, ButtonAlternativeModeENG;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        visualUpdate = false;

        if (Preferences.FULLSCREEN)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // INTERIM RESULTS
        Switch SwitchInterimResults = (Switch) findViewById(R.id.SwitchInterimResults);
        SwitchInterimResults.setChecked(Preferences.INTERIM_RESULTS);
        SwitchInterimResults.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.INTERIM_RESULTS = b;
                settingEditor.putBoolean("INTERIM_RESULTS", b);
                settingEditor.apply();
            }
        });

        // FULLSCREEN
        Switch SwitchFullscreen = (Switch) findViewById(R.id.SwitchFullscreen);
        SwitchFullscreen.setChecked(Preferences.FULLSCREEN);
        SwitchFullscreen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.FULLSCREEN = b;
                settingEditor.putBoolean("FULLSCREEN", b);
                settingEditor.apply();
                visualUpdate = true;
            }
        });

        // LOCK PORTRAIT MODE
        Switch SwitchLockPortrait = (Switch) findViewById(R.id.SwitchLockPortrait);
        SwitchLockPortrait.setChecked(Preferences.LOCK_PORTRAIT);
        SwitchLockPortrait.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.LOCK_PORTRAIT = b;
                settingEditor.putBoolean("LOCK_PORTRAIT", b);
                settingEditor.apply();
                visualUpdate = true;
            }
        });

        // VIBRATION
        Switch SwitchVibration = (Switch) findViewById(R.id.SwitchVibration);
        SwitchVibration.setChecked(Preferences.VIBRATION);
        SwitchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.VIBRATION = b;
                settingEditor.putBoolean("FULLSCREEN", b);
                settingEditor.apply();
            }
        });

        // DECIMAL PLACES
        Switch SwitchFixedDecimalPlaces = (Switch) findViewById(R.id.SwitchFixedDecimalPlaces);
        SwitchFixedDecimalPlaces.setChecked(Preferences.FIX_PRECISION);
        SwitchFixedDecimalPlaces.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.FIX_PRECISION = b;
                settingEditor.putBoolean("FIX_PRECISION", b);
                settingEditor.apply();
            }
        });

        // PRECISION DEC
        SeekBar SeekBarPrecisionDEC = (SeekBar) findViewById(R.id.SeekBarPrecisionDEC);
        final TextView TextViewPrecisionDEC = (TextView) findViewById(R.id.TextViewPrecisionDEC);

        SeekBarPrecisionDEC.setMax(30);
        SeekBarPrecisionDEC.setProgress(Preferences.PRECISION);
        TextViewPrecisionDEC.setText(String.valueOf(Preferences.PRECISION));
        SeekBarPrecisionDEC.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.PRECISION = i;
                settingEditor.putInt("PRECISION", i);
                settingEditor.apply();
                TextViewPrecisionDEC.setText(String.valueOf(Preferences.PRECISION));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // PRECISION SCI
        SeekBar SeekBarPrecisionSCI = (SeekBar) findViewById(R.id.SeekBarPrecisionSCI);
        final TextView TextViewPrecisionSCI = (TextView) findViewById(R.id.TextViewPrecisionSCI);

        SeekBarPrecisionSCI.setMax(10);
        SeekBarPrecisionSCI.setProgress(Preferences.SCIENTIFIC_PRECISION);
        TextViewPrecisionSCI.setText(String.valueOf(Preferences.SCIENTIFIC_PRECISION));
        SeekBarPrecisionSCI.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.SCIENTIFIC_PRECISION = i;
                settingEditor.putInt("SCIENTIFIC_PRECISION", i);
                settingEditor.apply();
                TextViewPrecisionSCI.setText(String.valueOf(Preferences.SCIENTIFIC_PRECISION));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // PRECISION ENG
        SeekBar SeekBarPrecisionENG = (SeekBar) findViewById(R.id.SeekBarPrecisionENG);
        final TextView TextViewPrecisionENG = (TextView) findViewById(R.id.TextViewPrecisionENG);

        SeekBarPrecisionENG.setMax(10);
        SeekBarPrecisionENG.setProgress(Preferences.ENGINEERING_PRECISION);
        TextViewPrecisionENG.setText(String.valueOf(Preferences.ENGINEERING_PRECISION));
        SeekBarPrecisionENG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.ENGINEERING_PRECISION = i;
                settingEditor.putInt("ENGINEERING_PRECISION", i);
                settingEditor.apply();
                TextViewPrecisionENG.setText(String.valueOf(Preferences.ENGINEERING_PRECISION));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // SET ALTERNATIVE NOTATION
        ButtonAlternativeModeOff = (Button) findViewById(R.id.ButtonAlternativeModeOff);
        ButtonAlternativeModeSCI = (Button) findViewById(R.id.ButtonAlternativeModeSCI);
        ButtonAlternativeModeENG = (Button) findViewById(R.id.ButtonAlternativeModeENG);
        refreshAlternativeNotation();

        ButtonAlternativeModeOff.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.DEC;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                refreshAlternativeNotation();
            }
        });
        ButtonAlternativeModeSCI.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.SCI;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                refreshAlternativeNotation();
            }
        });
        ButtonAlternativeModeENG.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.ENG;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                refreshAlternativeNotation();
            }
        });

        // THRESHOLD FOR ALTERNATIVE NOTATION
        SeekBar SeekBarThreshold = (SeekBar) findViewById(R.id.SeekBarThreshold);
        final TextView TextViewThreshold = (TextView) findViewById(R.id.TextViewThreshold);

        SeekBarThreshold.setMax(30);
        SeekBarThreshold.setProgress(Preferences.NOTATION_THRESHOLD);
        TextViewThreshold.setText(String.valueOf(Preferences.NOTATION_THRESHOLD));
        SeekBarThreshold.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.NOTATION_THRESHOLD = i;
                settingEditor.putInt("NOTATION_THRESHOLD", i);
                settingEditor.apply();
                TextViewThreshold.setText(String.valueOf(Preferences.NOTATION_THRESHOLD));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // KEYBOARD WIDTH
        SeekBar SeekBarKeyboardWidth = (SeekBar) findViewById(R.id.SeekBarKeyboardWidth);
        final TextView TextViewKeyboardWidth = (TextView) findViewById(R.id.TextViewKeyboardWidth);

        SeekBarKeyboardWidth.setMax(100);
        SeekBarKeyboardWidth.setProgress(Preferences.KEYBOARD_WIDTH - 900);
        String percentage = new BigDecimal(String.valueOf(Preferences.KEYBOARD_WIDTH * 0.1)).setScale(1, BigDecimal.ROUND_FLOOR).toPlainString() + "%";
        TextViewKeyboardWidth.setText(percentage);
        SeekBarKeyboardWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.KEYBOARD_WIDTH = i + 900;
                settingEditor.putInt("KEYBOARD_WIDTH", i + 900);
                settingEditor.apply();
                String percentage = new BigDecimal(String.valueOf(Preferences.KEYBOARD_WIDTH * 0.1)).setScale(1, BigDecimal.ROUND_FLOOR).toPlainString() + "%";
                TextViewKeyboardWidth.setText(percentage);
                visualUpdate = true;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // BUTTON SIZE
        SeekBar SeekBarButtonSize = (SeekBar) findViewById(R.id.SeekBarButtonSize);
        final TextView TextViewButtonSize = (TextView) findViewById(R.id.TextViewButtonSize);

        SeekBarButtonSize.setMax(200);
        SeekBarButtonSize.setProgress(Preferences.BUTTON_SIZE - 800);
        String percentage2 = new BigDecimal(String.valueOf(Preferences.BUTTON_SIZE * 0.1)).setScale(1, BigDecimal.ROUND_FLOOR).toPlainString() + "%";
        TextViewButtonSize.setText(percentage2);
        SeekBarButtonSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.BUTTON_SIZE = i + 800;
                settingEditor.putInt("BUTTON_SIZE", i + 800);
                settingEditor.apply();
                String percentage2 = new BigDecimal(String.valueOf(Preferences.BUTTON_SIZE * 0.1)).setScale(1, BigDecimal.ROUND_FLOOR).toPlainString() + "%";
                TextViewButtonSize.setText(percentage2);
                visualUpdate = true;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // DISPLAY TEXT SIZE
        SeekBar SeekBarDisplayTextSize = (SeekBar) findViewById(R.id.SeekBarDisplayTextSize);
        final TextView TextViewDisplayTextSize = (TextView) findViewById(R.id.TextViewDisplayTextSize);

        SeekBarDisplayTextSize.setMax(100);
        SeekBarDisplayTextSize.setProgress(Preferences.DISPLAY_TEXT_SIZE - 50);
        TextViewDisplayTextSize.setText(String.valueOf(Preferences.DISPLAY_TEXT_SIZE + "%"));
        SeekBarDisplayTextSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingEditor = settings.edit();
                Preferences.DISPLAY_TEXT_SIZE = i + 50;
                settingEditor.putInt("DISPLAY_TEXT_SIZE", i + 50);
                settingEditor.apply();
                TextViewDisplayTextSize.setText(String.valueOf(Preferences.DISPLAY_TEXT_SIZE + "%"));
                visualUpdate = true;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        // CONTACT THE DEVELOPER
        Button ButtonContact = (Button) findViewById(R.id.ButtonContact);
        ButtonContact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "leander.lehmenkuehler@gmail.com", null));
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        // RESET CALCULATOR
        Button ButtonReset = (Button) findViewById(R.id.ButtonReset);
        ButtonReset.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences history = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_HISTORY), Context.MODE_PRIVATE);
                SharedPreferences.Editor historyEditor = history.edit();
                historyEditor.clear();
                historyEditor.apply();

                SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                SharedPreferences.Editor settingsEditor = settings.edit();
                settingsEditor.clear();
                settingsEditor.apply();

                Preferences.updateValues();

                finish();
                startActivity(getIntent());
            }
        });


    }

    @Override
    public void onClick(View view)
    {
        Utility.vibrate();
        SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
        SharedPreferences.Editor settingEditor = settings.edit();

        switch (view.getId())
        {
            case R.id.ButtonAlternativeModeOff:
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.DEC;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                Toast toast1 = Toast.makeText(Utility.getContext(), "off", Toast.LENGTH_SHORT);
                toast1.show();
                refreshAlternativeNotation();
                break;
            case R.id.ButtonAlternativeModeSCI:
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.SCI;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                Toast toast2 = Toast.makeText(Utility.getContext(), "sci", Toast.LENGTH_SHORT);
                toast2.show();
                refreshAlternativeNotation();
                break;
            case R.id.ButtonAlternativeModeENG:
                Preferences.MODE_NOTATION_ALTERNATIVE = Preferences.NotationMode.ENG;
                settingEditor.putInt("MODE_NOTATION_ALTERNATIVE", Preferences.MODE_NOTATION_ALTERNATIVE.ordinal());
                settingEditor.apply();
                Toast toast3 = Toast.makeText(Utility.getContext(), "eng", Toast.LENGTH_SHORT);
                toast3.show();
                refreshAlternativeNotation();
                break;
            default:
                break;
        }


    }

    private void refreshAlternativeNotation()
    {
        switch (Preferences.MODE_NOTATION_ALTERNATIVE)
        {
            case DEC:
                ButtonAlternativeModeOff.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAlternativeModeSCI.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAlternativeModeENG.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case SCI:
                ButtonAlternativeModeOff.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAlternativeModeSCI.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAlternativeModeENG.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case ENG:
                ButtonAlternativeModeOff.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAlternativeModeSCI.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAlternativeModeENG.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy()
    {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        super.onDestroy();
    }
}
