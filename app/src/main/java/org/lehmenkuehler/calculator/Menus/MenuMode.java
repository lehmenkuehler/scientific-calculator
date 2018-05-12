package org.lehmenkuehler.calculator.Menus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TableRow;
import android.widget.TextView;

import org.lehmenkuehler.calculator.Enums.Metrics;
import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public class MenuMode implements View.OnClickListener
{
    public PopupWindow popupMode;
    private final Context context;
    private final DarkBackground darkBackground;

    private final View LinearLayout;

    private final TextView HeadlineAngularMode;
    private final TextView HeadlineAngularModePolar;
    private final TextView HeadlineNumeralMode;
    private final TextView HeadlineComplexMode;
    private final TextView HeadlineNotationMode;

    private final LinearLayout ElementAngularMode;
    private final LinearLayout ElementAngularModePolar;
    private final LinearLayout ElementNumeralMode;
    private final LinearLayout ElementComplexMode;
    private final LinearLayout ElementNotationMode;

    private final Button ButtonAngularModeRAD;
    private final Button ButtonAngularModeDEG;
    private final Button ButtonAngularModeGRAD;
    private final Button ButtonAngularModePolarRAD;
    private final Button ButtonAngularModePolarDEG;
    private final Button ButtonAngularModePolarGRAD;
    private final Button ButtonNumeralModeDEC;
    private final Button ButtonNumeralModeHEX;
    private final Button ButtonNumeralModeBIN;
    private final Button ButtonNumeralModeOCT;
    private final Button ButtonComplexModeRECT;
    private final Button ButtonComplexModePOLAR;
    private final Button ButtonNotationModeDEC;
    private final Button ButtonNotationModeSCI;
    private final Button ButtonNotationModeENG;

    private SharedPreferences settings;

    @SuppressLint("InflateParams")
    public MenuMode(Context c)
    {

        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout = inflater.inflate(R.layout.menu_mode, null);
        darkBackground = new DarkBackground(context);

        settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);

        // HEADLINES

        HeadlineAngularMode = (TextView) LinearLayout.findViewById(R.id.HeadlineAngularMode);
        HeadlineAngularModePolar = (TextView) LinearLayout.findViewById(R.id.HeadlineAngularModePolar);
        HeadlineNumeralMode = (TextView) LinearLayout.findViewById(R.id.HeadlineNumeralMode);
        HeadlineComplexMode = (TextView) LinearLayout.findViewById(R.id.HeadlineComplexMode);
        HeadlineNotationMode = (TextView) LinearLayout.findViewById(R.id.HeadlineNotationMode);

        HeadlineAngularMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue();
        HeadlineAngularModePolar.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue();
        HeadlineNumeralMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue();
        HeadlineComplexMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue();
        HeadlineNotationMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue();

        // BUTTON ELEMENTS

        ElementAngularMode = (LinearLayout) LinearLayout.findViewById(R.id.ElementAngularMode);
        ElementAngularModePolar = (LinearLayout) LinearLayout.findViewById(R.id.ElementAngularModePolar);
        ElementNumeralMode = (LinearLayout) LinearLayout.findViewById(R.id.ElementNumeralMode);
        ElementComplexMode = (LinearLayout) LinearLayout.findViewById(R.id.ElementComplexMode);
        ElementNotationMode = (LinearLayout) LinearLayout.findViewById(R.id.ElementNotationMode);

        ElementAngularMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue();
        ElementAngularModePolar.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue();
        ElementNumeralMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue();
        ElementComplexMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue();
        ElementNotationMode.getLayoutParams().height = Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue();

        // BUTTONS

        ButtonAngularModeRAD = (Button) LinearLayout.findViewById(R.id.ButtonAngularModeRAD);
        ButtonAngularModeDEG = (Button) LinearLayout.findViewById(R.id.ButtonAngularModeDEG);
        ButtonAngularModeGRAD = (Button) LinearLayout.findViewById(R.id.ButtonAngularModeGRAD);
        ButtonAngularModePolarRAD = (Button) LinearLayout.findViewById(R.id.ButtonAngularModePolarRAD);
        ButtonAngularModePolarDEG = (Button) LinearLayout.findViewById(R.id.ButtonAngularModePolarDEG);
        ButtonAngularModePolarGRAD = (Button) LinearLayout.findViewById(R.id.ButtonAngularModePolarGRAD);
        ButtonNumeralModeDEC = (Button) LinearLayout.findViewById(R.id.ButtonNumeralModeDEC);
        ButtonNumeralModeHEX = (Button) LinearLayout.findViewById(R.id.ButtonNumeralModeHEX);
        ButtonNumeralModeBIN = (Button) LinearLayout.findViewById(R.id.ButtonNumeralModeBIN);
        ButtonNumeralModeOCT = (Button) LinearLayout.findViewById(R.id.ButtonNumeralModeOCT);
        ButtonComplexModeRECT = (Button) LinearLayout.findViewById(R.id.ButtonComplexModeRECT);
        ButtonComplexModePOLAR = (Button) LinearLayout.findViewById(R.id.ButtonComplexModePOLAR);
        ButtonNotationModeDEC = (Button) LinearLayout.findViewById(R.id.ButtonNotationModeDEC);
        ButtonNotationModeSCI = (Button) LinearLayout.findViewById(R.id.ButtonNotationModeSCI);
        ButtonNotationModeENG = (Button) LinearLayout.findViewById(R.id.ButtonNotationModeENG);

        ButtonAngularModeRAD.setOnClickListener(this);
        ButtonAngularModeDEG.setOnClickListener(this);
        ButtonAngularModeGRAD.setOnClickListener(this);
        ButtonAngularModePolarRAD.setOnClickListener(this);
        ButtonAngularModePolarDEG.setOnClickListener(this);
        ButtonAngularModePolarGRAD.setOnClickListener(this);
        ButtonNumeralModeDEC.setOnClickListener(this);
        ButtonNumeralModeHEX.setOnClickListener(this);
        ButtonNumeralModeBIN.setOnClickListener(this);
        ButtonNumeralModeOCT.setOnClickListener(this);
        ButtonComplexModeRECT.setOnClickListener(this);
        ButtonComplexModePOLAR.setOnClickListener(this);
        ButtonNotationModeDEC.setOnClickListener(this);
        ButtonNotationModeSCI.setOnClickListener(this);
        ButtonNotationModeENG.setOnClickListener(this);

        scaleButton(ButtonAngularModeRAD);
        scaleButton(ButtonAngularModeDEG);
        scaleButton(ButtonAngularModeGRAD);
        scaleButton(ButtonAngularModePolarRAD);
        scaleButton(ButtonAngularModePolarDEG);
        scaleButton(ButtonAngularModePolarGRAD);
        scaleButton(ButtonNumeralModeDEC);
        scaleButton(ButtonNumeralModeHEX);
        scaleButton(ButtonNumeralModeBIN);
        scaleButton(ButtonNumeralModeOCT);
        scaleButton(ButtonComplexModeRECT);
        scaleButton(ButtonComplexModePOLAR);
        scaleButton(ButtonNotationModeDEC);
        scaleButton(ButtonNotationModeSCI);
        scaleButton(ButtonNotationModeENG);

        Button ButtonClose = (Button) LinearLayout.findViewById(R.id.ButtonClose);
        ButtonClose.getLayoutParams().height = Metrics.MENU_BUTTONS_HEIGHT.getValue();
        ButtonClose.setText(context.getResources().getString(R.string.MENU_BUTTON_EXIT));
        ButtonClose.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonClose.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Utility.vibrate();
                popupMode.dismiss();
                darkBackground.dismiss();
            }
        });

    }

    private void scaleButton(Button button)
    {
        LinearLayout.LayoutParams params = new TableRow.LayoutParams(Metrics.MENU_MODE_BUTTON_WIDTH.getValue(), Metrics.MENU_MODE_BUTTON_HEIGHT.getValue());
        params.setMargins(Metrics.MENU_MODE_BUTTON_MARGIN_SIDES.getValue(), 0, Metrics.MENU_MODE_BUTTON_MARGIN_SIDES.getValue(), 0);
        button.setLayoutParams(params);
        button.setAllCaps(true);
        button.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_HEADLINE_TEXT));
        button.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_MODE_BUTTON_TEXT_SIZE.getValue());
    }

    private void scaleHeadline(TextView textView)
    {
        ViewGroup.LayoutParams params = new TableRow.LayoutParams(Metrics.MENU_WIDTH.getValue(), Metrics.MENU_MODE_ELEMENT1_HEIGHT.getValue(), 0f);
        textView.setLayoutParams(params);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());
    }

    public void initiatePopupWindow()
    {

        darkBackground.initiate();

        popupMode = new PopupWindow(LinearLayout, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        popupMode.setAnimationStyle(R.style.MenuAnimation);
        popupMode.showAtLocation(LinearLayout, Gravity.CENTER, 0, 0);

        scaleHeadline(HeadlineAngularMode);
        scaleHeadline(HeadlineAngularModePolar);
        scaleHeadline(HeadlineNumeralMode);
        scaleHeadline(HeadlineComplexMode);
        scaleHeadline(HeadlineNotationMode);

        refreshAngularMode();
        refreshAngularModePolar();
        refreshNumeralMode();
        refreshComplexMode();
        refreshNotationMode();

        settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
    }

    private void refreshAngularMode()
    {
        switch (Preferences.MODE_ANGULAR)
        {
            case RAD:
                ButtonAngularModeRAD.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAngularModeDEG.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModeGRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case DEG:
                ButtonAngularModeRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModeDEG.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAngularModeGRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case GRAD:
                ButtonAngularModeRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModeDEG.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModeGRAD.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    private void refreshAngularModePolar()
    {
        switch (Preferences.MODE_ANGULAR_POLAR)
        {
            case RAD:
                ButtonAngularModePolarRAD.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAngularModePolarDEG.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModePolarGRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case DEG:
                ButtonAngularModePolarRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModePolarDEG.setBackgroundResource(R.drawable.mode_button_active);
                ButtonAngularModePolarGRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case GRAD:
                ButtonAngularModePolarRAD.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModePolarDEG.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonAngularModePolarGRAD.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    private void refreshNumeralMode()
    {
        switch (Preferences.MODE_NUMERAL)
        {
            case BIN:
                ButtonNumeralModeBIN.setBackgroundResource(R.drawable.mode_button_active);
                ButtonNumeralModeOCT.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeDEC.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeHEX.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case OCT:
                ButtonNumeralModeBIN.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeOCT.setBackgroundResource(R.drawable.mode_button_active);
                ButtonNumeralModeDEC.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeHEX.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case DEC:
                ButtonNumeralModeBIN.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeOCT.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeDEC.setBackgroundResource(R.drawable.mode_button_active);
                ButtonNumeralModeHEX.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case HEX:
                ButtonNumeralModeBIN.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeOCT.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeDEC.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNumeralModeHEX.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    private void refreshComplexMode()
    {
        switch (Preferences.MODE_COMPLEX)
        {
            case RECT:
                ButtonComplexModeRECT.setBackgroundResource(R.drawable.mode_button_active);
                ButtonComplexModePOLAR.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case POLAR:
                ButtonComplexModeRECT.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonComplexModePOLAR.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    private void refreshNotationMode()
    {
        switch (Preferences.MODE_NOTATION)
        {
            case DEC:
                ButtonNotationModeDEC.setBackgroundResource(R.drawable.mode_button_active);
                ButtonNotationModeSCI.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNotationModeENG.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case SCI:
                ButtonNotationModeDEC.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNotationModeSCI.setBackgroundResource(R.drawable.mode_button_active);
                ButtonNotationModeENG.setBackgroundResource(R.drawable.mode_button_disabled);
                break;
            case ENG:
                ButtonNotationModeDEC.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNotationModeSCI.setBackgroundResource(R.drawable.mode_button_disabled);
                ButtonNotationModeENG.setBackgroundResource(R.drawable.mode_button_active);
                break;
            default:
                break;
        }
    }

    private void updateModeBar()
    {
        ((TextView) ((Main) context).findViewById(R.id.TextViewModeAngular)).setText(Preferences.MODE_ANGULAR.getModeBarText());
        ((TextView) ((Main) context).findViewById(R.id.TextViewModeNumeral)).setText(Preferences.MODE_NUMERAL.getModeBarText());
        ((TextView) ((Main) context).findViewById(R.id.TextViewModeComplex)).setText(Preferences.MODE_COMPLEX.getModeBarText());
        ((TextView) ((Main) context).findViewById(R.id.TextViewModeNotation)).setText(Preferences.MODE_NOTATION.getModeBarText());
    }

    @Override
    public void onClick(View view)
    {

        Utility.vibrate();
        SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
        SharedPreferences.Editor settingEditor = settings.edit();

        switch (view.getId())
        {

            // ANGULAR MODE
            case R.id.ButtonAngularModeRAD:
                Preferences.MODE_ANGULAR = Preferences.AngularMode.RAD;
                settingEditor.putInt("MODE_ANGULAR", Preferences.MODE_ANGULAR.ordinal());
                settingEditor.apply();
                refreshAngularMode();
                break;
            case R.id.ButtonAngularModeDEG:
                Preferences.MODE_ANGULAR = Preferences.AngularMode.DEG;
                settingEditor.putInt("MODE_ANGULAR", Preferences.MODE_ANGULAR.ordinal());
                settingEditor.apply();
                refreshAngularMode();
                break;
            case R.id.ButtonAngularModeGRAD:
                Preferences.MODE_ANGULAR = Preferences.AngularMode.GRAD;
                settingEditor.putInt("MODE_ANGULAR", Preferences.MODE_ANGULAR.ordinal());
                settingEditor.apply();
                refreshAngularMode();
                break;

            // ANGULAR MODE POLAR
            case R.id.ButtonAngularModePolarRAD:
                Preferences.MODE_ANGULAR_POLAR = Preferences.AngularMode.RAD;
                settingEditor.putInt("MODE_ANGULAR_POLAR", Preferences.MODE_ANGULAR_POLAR.ordinal());
                settingEditor.apply();
                refreshAngularModePolar();
                break;
            case R.id.ButtonAngularModePolarDEG:
                Preferences.MODE_ANGULAR_POLAR = Preferences.AngularMode.DEG;
                settingEditor.putInt("MODE_ANGULAR_POLAR", Preferences.MODE_ANGULAR_POLAR.ordinal());
                settingEditor.apply();
                refreshAngularModePolar();
                break;
            case R.id.ButtonAngularModePolarGRAD:
                Preferences.MODE_ANGULAR_POLAR = Preferences.AngularMode.GRAD;
                settingEditor.putInt("MODE_ANGULAR_POLAR", Preferences.MODE_ANGULAR_POLAR.ordinal());
                settingEditor.apply();
                refreshAngularModePolar();
                break;

            // NUMERAL MODE
            case R.id.ButtonNumeralModeDEC:
                Preferences.MODE_NUMERAL = Preferences.NumeralMode.DEC;
                settingEditor.putInt("MODE_NUMERAL", Preferences.MODE_NUMERAL.ordinal());
                settingEditor.apply();
                refreshNumeralMode();
                break;
            case R.id.ButtonNumeralModeHEX:
                Preferences.MODE_NUMERAL = Preferences.NumeralMode.HEX;
                settingEditor.putInt("MODE_NUMERAL", Preferences.MODE_NUMERAL.ordinal());
                settingEditor.apply();
                refreshNumeralMode();
                break;
            case R.id.ButtonNumeralModeBIN:
                Preferences.MODE_NUMERAL = Preferences.NumeralMode.BIN;
                settingEditor.putInt("MODE_NUMERAL", Preferences.MODE_NUMERAL.ordinal());
                settingEditor.apply();
                refreshNumeralMode();
                break;
            case R.id.ButtonNumeralModeOCT:
                Preferences.MODE_NUMERAL = Preferences.NumeralMode.OCT;
                settingEditor.putInt("MODE_NUMERAL", Preferences.MODE_NUMERAL.ordinal());
                settingEditor.apply();
                refreshNumeralMode();
                break;

            // COMPLEX MODE
            case R.id.ButtonComplexModeRECT:
                Preferences.MODE_COMPLEX = Preferences.ComplexMode.RECT;
                settingEditor.putInt("MODE_COMPLEX", Preferences.MODE_COMPLEX.ordinal());
                settingEditor.apply();
                refreshComplexMode();
                break;
            case R.id.ButtonComplexModePOLAR:
                Preferences.MODE_COMPLEX = Preferences.ComplexMode.POLAR;
                settingEditor.putInt("MODE_COMPLEX", Preferences.MODE_COMPLEX.ordinal());
                settingEditor.apply();
                refreshComplexMode();
                break;

            // NOTATION MODE
            case R.id.ButtonNotationModeDEC:
                Preferences.MODE_NOTATION = Preferences.NotationMode.DEC;
                settingEditor.putInt("MODE_NOTATION", Preferences.MODE_NOTATION.ordinal());
                settingEditor.apply();
                refreshNotationMode();
                break;
            case R.id.ButtonNotationModeSCI:
                Preferences.MODE_NOTATION = Preferences.NotationMode.SCI;
                settingEditor.putInt("MODE_NOTATION", Preferences.MODE_NOTATION.ordinal());
                settingEditor.apply();
                refreshNotationMode();
                break;
            case R.id.ButtonNotationModeENG:
                Preferences.MODE_NOTATION = Preferences.NotationMode.ENG;
                settingEditor.putInt("MODE_NOTATION", Preferences.MODE_NOTATION.ordinal());
                settingEditor.apply();
                refreshNotationMode();
                break;
            default:
                break;
        }
        updateModeBar();
    }
}
