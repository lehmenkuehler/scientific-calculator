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

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Enums.Metrics;
import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.Mechanics.History;
import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Scale;
import org.lehmenkuehler.calculator.Utility;

public class MenuHistory
{
    private final Context context;

    private final DarkBackground darkBackground;
    private final View layoutMenu;
    public PopupWindow menu;

    private TextView Headline;
    private LinearLayout ContentLayout, ButtonLayout;
    private android.widget.ScrollView ScrollView;
    private Button ButtonClose, ButtonClearHistory;

    @SuppressLint("InflateParams")
    public MenuHistory(Context c)
    {
        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMenu = inflater.inflate(R.layout.menu_1, null);
        darkBackground = new DarkBackground(context);
    }

    public void initiateMenu()
    {

        menu = new PopupWindow(layoutMenu, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        menu.setAnimationStyle(R.style.MenuAnimation);
        darkBackground.initiate();
        menu.showAtLocation(layoutMenu, Gravity.CENTER, 0, 0);

        ContentLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);
        ButtonLayout = (LinearLayout) layoutMenu.findViewById(R.id.ButtonLayout);
        ScrollView = (ScrollView) layoutMenu.findViewById(R.id.ScrollView);

        ContentLayout.setBackgroundResource(R.drawable.menu_element_history_background);

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
                menu.dismiss();
            }
        });

        ButtonClearHistory = new Button(context);
        ButtonClearHistory.setBackgroundResource(R.drawable.menu_button);
        ButtonClearHistory.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonClearHistory.setText(context.getResources().getString(R.string.MENU_BUTTON_CLEAR));
        ButtonClearHistory.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonClearHistory.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonClearHistory.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Utility.vibrate();
                ContentLayout.removeAllViews();
                History.clearHistory();
                loadHistory();
            }
        });

        ButtonLayout.removeAllViews();
        ButtonLayout.addView(ButtonClose);
        ButtonLayout.addView(ButtonClearHistory);

        Headline = (TextView) layoutMenu.findViewById(R.id.Headline);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();
        Headline.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());
        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_HISTORY).toUpperCase());

        loadHistory();
    }

    private void loadHistory()
    {

        ContentLayout.removeAllViews();
        ContentLayout.setPadding(0, 0, 0, 0);
        ScrollView.scrollTo(0, 0);

        int historyLength = History.getNumberOfElements();
        if (historyLength > Preferences.HISTORY_LENGTH)
            historyLength = Preferences.HISTORY_LENGTH;

        if (historyLength == 0)
        {
            Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), 1);
            ViewGroup.LayoutParams messageParams = new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_ELEMENT_HEIGHT.getValue(), 0f);
            TextView message = new TextView(context);
            message.setLayoutParams(messageParams);
            message.setGravity(Gravity.CENTER);
            message.setTypeface(Typeface.DEFAULT_BOLD);
            message.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_SECONDARY));
            message.setBackgroundResource(R.drawable.menu_element_gradient);
            message.setText(R.string.MENU_HISTORY_EMPTY);
            ContentLayout.addView(message);
        } else
        {
            ContentLayout.setPadding((int) (Metrics.MENU_WIDTH.getValue() * 0.02), 0, (int) (Metrics.MENU_WIDTH.getValue() * 0.02), 0);

            ViewGroup.LayoutParams equationParams = new TableRow.LayoutParams((int) (Metrics.MENU_WIDTH.getValue() * 0.96), (int) (Metrics.MENU_ELEMENT_HEIGHT.getValue() * 0.6), 0f);
            ViewGroup.LayoutParams resultParams = new TableRow.LayoutParams((int) (Metrics.MENU_WIDTH.getValue() * 0.96), (int) (Metrics.MENU_ELEMENT_HEIGHT.getValue() * 0.8), 0f);

            Scale.scaleMenuScrollView(ScrollView, equationParams.height + resultParams.height, historyLength);

            for (int i = History.getNumberOfElements(); i > History.getNumberOfElements() - historyLength; i--)
            {

                TextView equation = new TextView(context);
                equation.setLayoutParams(equationParams);
                equation.setGravity(Gravity.CENTER_VERTICAL);
                equation.setBackgroundResource(R.drawable.menu_element_history_input);
                equation.setPadding(20, 0, 0, 0);
                equation.setText(Utility.fromHtml(History.getInput(i)));

                TextView result = new TextView(context);
                result.setId(i);
                result.setLayoutParams(resultParams);
                result.setBackgroundResource(R.drawable.menu_element_history_result);
                result.setPadding(20, 0, 0, 0);
                result.setGravity(Gravity.CENTER_VERTICAL);
                result.setText(Utility.fromHtml(History.getOutput(i)));
                result.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue());
                result.setTypeface(Typeface.DEFAULT_BOLD);
                result.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_SECONDARY));
                result.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Main.externallyAddedElement = Component.ANSWER;
                        Main.answerId = v.getId();
                        Utility.vibrate();
                        darkBackground.dismiss();
                        menu.dismiss();
                    }
                });
                ContentLayout.addView(equation);
                ContentLayout.addView(result);
            }
        }
    }
}
