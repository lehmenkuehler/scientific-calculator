package org.lehmenkuehler.calculator.Menus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.lehmenkuehler.calculator.Enums.Converter;
import org.lehmenkuehler.calculator.Enums.Metrics;
import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.Mechanics.Result;
import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Scale;
import org.lehmenkuehler.calculator.Shaper;
import org.lehmenkuehler.calculator.Utility;

public class MenuConvert
{
    private final Context context;

    private final DarkBackground darkBackground;
    private final View layoutMenu;

    public PopupWindow popupWindow;
    private BigDecimal result = BigDecimal.ZERO;
    private List<ConvertedValue> values = new ArrayList<>();
    private Button ButtonClose, ButtonReturn;

    private TextView Headline;

    private LinearLayout ContentLayout, ButtonLayout;
    private ScrollView ScrollView;

    @SuppressLint("InflateParams")
    public MenuConvert(Context c)
    {
        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMenu = inflater.inflate(R.layout.menu_1, null);
        darkBackground = new DarkBackground(context);
    }

    public void setValue(BigDecimal value)
    {
        result = value;
    }

    public void initiatePopupWindow()
    {

        popupWindow = new PopupWindow(layoutMenu, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        popupWindow.setAnimationStyle(R.style.MenuAnimation);
        darkBackground.initiate();
        popupWindow.showAtLocation(layoutMenu, Gravity.CENTER, 0, 0);

        ContentLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);
        ButtonLayout = (LinearLayout) layoutMenu.findViewById(R.id.ButtonLayout);
        ScrollView = (ScrollView) layoutMenu.findViewById(R.id.ScrollView);

        ButtonReturn = new Button(context);
        ButtonReturn.setBackgroundResource(R.drawable.menu_button);
        ButtonReturn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonReturn.setText(context.getResources().getString(R.string.MENU_BUTTON_RETURN));
        ButtonReturn.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonReturn.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());

        ButtonClose = new Button(context);
        ButtonClose.setBackgroundResource(R.drawable.menu_button);
        ButtonClose.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonClose.setText(context.getResources().getString(R.string.MENU_BUTTON_EXIT));
        ButtonClose.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonClose.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonClose.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Utility.vibrate();
                darkBackground.dismiss();
                popupWindow.dismiss();
            }
        });

        Headline = (TextView) layoutMenu.findViewById(R.id.Headline);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();
        Headline.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());

        selectDimension();
    }

    private void selectDimension()
    {

        ContentLayout.removeAllViews();
        ButtonLayout.removeAllViews();
        ScrollView.scrollTo(0, 0);

        final String valueColor = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.MENU_CONVERT_HEADLINE_VALUE));
        String headlineText = context.getResources().getString(R.string.MENU_HEADLINE_CONVERT_1).toUpperCase()
                + "<br><big><b><font color=" + valueColor + ">"
                + Shaper.getFinalOutput(new Result(result), Preferences.NotationMode.DEC, Preferences.NotationMode.ENG)
                + "</font></b></big>";

        Headline.setText(Utility.fromHtml(headlineText));
        Headline.getLayoutParams().height = (int) (Metrics.MENU_HEADLINE_HEIGHT.getValue() * 1.6);

        ButtonLayout.addView(ButtonClose);

        Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), 7);

        ViewGroup.LayoutParams groupElementParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_ELEMENT_HEIGHT.getValue());

        for (final Converter.Tag tag : Converter.Tag.values())
        {
            TextView element = new TextView(context);
            element.setId(200 + tag.ordinal());
            element.setLayoutParams(groupElementParams);
            element.setBackgroundResource(R.drawable.menu_element_gradient);
            element.setTypeface(Typeface.DEFAULT_BOLD);
            element.setText(tag.getName());
            element.setGravity(Gravity.CENTER);
            element.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Utility.vibrate();
                    selectUnit(tag);
                }
            });
            ContentLayout.addView(element);
        }
    }


    private void selectUnit(Converter.Tag tag)
    {

        ButtonLayout.removeAllViews();
        ContentLayout.removeAllViews();
        ScrollView.scrollTo(0, 0);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();

        int elementCounter = 0;

        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_CONVERT_2).toUpperCase());

        ButtonLayout.addView(ButtonReturn);
        ButtonLayout.addView(ButtonClose);

        LinearLayout linearLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);

        ViewGroup.LayoutParams symbolParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams descriptionParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams wrapperParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Metrics.MENU_ELEMENT_HEIGHT.getValue());

        for (final Converter unit : Converter.values())
        {
            if (unit.getTag() == tag)
            {

                elementCounter++;

                LinearLayout wrapper = new LinearLayout(context);
                wrapper.setOrientation(LinearLayout.HORIZONTAL);
                TextView symbol = new TextView(context);
                TextView description = new TextView(context);

                wrapper.setId(300 + unit.ordinal());
                symbol.setId(400 + unit.ordinal());
                description.setId(500 + unit.ordinal());

                wrapper.setLayoutParams(wrapperParams);

                symbol.setLayoutParams(symbolParams);
                description.setLayoutParams(descriptionParams);

                symbol.setBackgroundResource(R.drawable.menu_element_solid);
                symbol.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                symbol.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue());
                symbol.setTypeface(Typeface.DEFAULT_BOLD);
                symbol.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_SECONDARY));

                description.setBackgroundResource(R.drawable.menu_element_gradient);

                symbol.setText(Utility.fromHtml(unit.getSymbol()));
                description.setText(Utility.fromHtml(unit.getDescription()));
                description.setGravity(Gravity.CENTER_VERTICAL);
                description.setPadding((int) (Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.getValue() * 0.07), 0, 0, 0);
                description.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
                description.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_PRIMARY));

                wrapper.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Utility.vibrate();
                        showResults(unit);
                    }
                });

                linearLayout.addView(wrapper);
                wrapper.addView(symbol);
                wrapper.addView(description);
            }

        }

        Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), elementCounter);

        ButtonReturn.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Utility.vibrate();
                selectDimension();
            }
        });

    }

    private void showResults(final Converter unit)
    {

        ButtonLayout.removeAllViews();
        ContentLayout.removeAllViews();

        int elementCounter = 0;

        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_CONVERT_3).toUpperCase());

        ButtonLayout.addView(ButtonReturn);
        ButtonLayout.addView(ButtonClose);

        LinearLayout linearLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);

        ViewGroup.LayoutParams symbolParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams descriptionParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams wrapperParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Metrics.MENU_ELEMENT_HEIGHT.getValue());

        Preferences.MODE_OUTPUT = Preferences.OutputMode.DECIMAL;

        for (final Converter units : Converter.values())
        {
            if (units.getTag() == unit.getTag())
            {

                elementCounter++;

                LinearLayout wrapper = new LinearLayout(context);
                wrapper.setOrientation(LinearLayout.HORIZONTAL);
                wrapper.setId(300 + units.ordinal());
                wrapper.setLayoutParams(wrapperParams);

                TextView symbol = new TextView(context);
                symbol.setLayoutParams(symbolParams);
                symbol.setBackgroundResource(R.drawable.menu_element_solid);
                symbol.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                symbol.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue());
                symbol.setTypeface(Typeface.DEFAULT_BOLD);
                symbol.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_SECONDARY));
                symbol.setText(Utility.fromHtml(units.getSymbol()));

                // CALCULATION (CONVERSION)
                BigDecimal res = result.divide(units.getValue(), 50, BigDecimal.ROUND_HALF_UP).multiply(unit.getValue()).stripTrailingZeros();
                if (res.signum() == 0) res = BigDecimal.ZERO;
                String stringValue = Shaper.getFinalOutput(new Result(res), Preferences.NotationMode.DEC, Preferences.NotationMode.ENG);
                values.add(new ConvertedValue(res, wrapper.getId()));

                TextView description = new TextView(context);
                description.setLayoutParams(descriptionParams);
                if (!unit.equals(units))
                    description.setBackgroundResource(R.drawable.menu_element_gradient);
                else description.setBackgroundResource(R.drawable.menu_element_special);

                description.setText(Utility.fromHtml("<b>" + units.getDescription() + "</b><br>" + stringValue));
                description.setGravity(Gravity.CENTER_VERTICAL);
                description.setPadding(20, 0, 0, 0);

                wrapper.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Utility.vibrate();
                        for (ConvertedValue cV : values)
                        {
                            if (cV.getId() == v.getId())
                            {
                                Main.convertedValue = cV.getValue();
                                values = new ArrayList<>();
                                break;
                            }
                        }
                        popupWindow.dismiss();
                        darkBackground.dismiss();
                    }
                });

                linearLayout.addView(wrapper);
                wrapper.addView(symbol);
                wrapper.addView(description);
            }
        }

        Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), elementCounter);

        ButtonReturn.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Utility.vibrate();
                selectUnit(unit.getTag());
            }
        });

    }

    private class ConvertedValue
    {

        final BigDecimal value;
        final int id;

        ConvertedValue(BigDecimal value, int id)
        {
            this.value = value;
            this.id = id;
        }

        public int getId()
        {
            return id;
        }

        public BigDecimal getValue()
        {
            return value;
        }
    }
}
