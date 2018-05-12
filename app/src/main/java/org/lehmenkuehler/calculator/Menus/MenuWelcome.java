package org.lehmenkuehler.calculator.Menus;

import android.annotation.SuppressLint;
import android.content.Context;
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

import org.lehmenkuehler.calculator.Enums.Metrics;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Scale;
import org.lehmenkuehler.calculator.Utility;

public class MenuWelcome
{
    private final Context context;

    private final DarkBackground darkBackground;
    private final View layoutMenu;
    private PopupWindow menu;

    @SuppressLint("InflateParams")
    public MenuWelcome(Context c)
    {
        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMenu = inflater.inflate(R.layout.menu_1, null);
        darkBackground = new DarkBackground(context);
    }

    public void initiateMenu()
    {

        TextView Headline;
        LinearLayout ContentLayout, ButtonLayout;
        Button ButtonClose;
        ScrollView scrollView = (ScrollView) layoutMenu.findViewById(R.id.ScrollView);

        menu = new PopupWindow(layoutMenu, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        menu.setAnimationStyle(R.style.MenuAnimation);
        darkBackground.initiate();
        menu.showAtLocation(layoutMenu, Gravity.CENTER, 0, 0);

        ContentLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);
        ButtonLayout = (LinearLayout) layoutMenu.findViewById(R.id.ButtonLayout);

        ButtonLayout.removeAllViews();
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
        ButtonLayout.addView(ButtonClose);

        Headline = (TextView) layoutMenu.findViewById(R.id.Headline);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();
        Headline.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());
        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_VERSION_DATA).toUpperCase());

        TextView element = new TextView(context);
        element.setPadding(
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue()
        );
        element.setBackgroundResource(R.drawable.menu_element_gradient);
        element.setText(Utility.fromHtml(context.getResources().getString(R.string.WELCOMING_MESSAGE)));
        element.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        element.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));

        scrollView.getLayoutParams().height = (int) (Metrics.MENU_HEIGHT.getValue() * 0.7);

        ContentLayout.removeAllViews();
        ContentLayout.addView(element);
    }
}