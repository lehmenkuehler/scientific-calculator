package org.lehmenkuehler.calculator.Menus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.text.method.LinkMovementMethod;
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
import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public class MenuReview {

    private final Context context;

    private final DarkBackground darkBackground;
    private final View layoutMenu;
    private PopupWindow menu;

    @SuppressLint("InflateParams")
    public MenuReview(Context c) {
        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMenu = inflater.inflate(R.layout.menu_1, null);
        darkBackground = new DarkBackground(context);
    }

    public void initiateMenu() {

        TextView Headline;
        LinearLayout ContentLayout, ButtonLayout;
        Button ButtonLater, ButtonNever;

        menu = new PopupWindow(layoutMenu, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        menu.setAnimationStyle(R.style.MenuAnimation);
        darkBackground.initiate();
        menu.showAtLocation(layoutMenu, Gravity.CENTER, 0, 0);

        ContentLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);
        ButtonLayout = (LinearLayout) layoutMenu.findViewById(R.id.ButtonLayout);

        ButtonLayout.removeAllViews();
        ButtonLater = new Button(context);
        ButtonLater.setBackgroundResource(R.drawable.menu_button);
        ButtonLater.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonLater.setText(context.getResources().getString(R.string.MENU_BUTTON_LATER));
        ButtonLater.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonLater.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonLater.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.vibrate();
                Preferences.PLEA_FOR_REVIEW = false;
                darkBackground.dismiss();
                menu.dismiss();
            }
        });
        ButtonLayout.addView(ButtonLater);

        ButtonNever = new Button(context);
        ButtonNever.setBackgroundResource(R.drawable.menu_button);
        ButtonNever.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonNever.setText(context.getResources().getString(R.string.MENU_BUTTON_NEVER));
        ButtonNever.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonNever.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonNever.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.vibrate();
                SharedPreferences log = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_LOG), Context.MODE_PRIVATE);
                SharedPreferences.Editor logEditor = log.edit();
                Preferences.PLEA_FOR_REVIEW = false;
                logEditor.putBoolean("PLEA_FOR_REVIEW", false);
                logEditor.apply();
                darkBackground.dismiss();
                menu.dismiss();
            }
        });
        ButtonLayout.addView(ButtonNever);

        Headline = (TextView) layoutMenu.findViewById(R.id.Headline);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();
        Headline.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());
        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_REVIEW).toUpperCase());

        TextView element = new TextView(context);
        element.setPadding(
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue(),
                Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue()
        );
        element.setBackgroundResource(R.drawable.menu_element_gradient);
        element.setLinksClickable(true);
        element.setMovementMethod(LinkMovementMethod.getInstance());
        element.setText(Utility.fromHtml(context.getResources().getString(R.string.REVIEW_PLEA)));
        element.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue());
        element.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));

        Button ButtonOpenPlayStore = new Button(context);
        ButtonOpenPlayStore.setBackgroundResource(R.drawable.menu_button);
        ButtonOpenPlayStore.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) (Metrics.MENU_BUTTONS_HEIGHT.getValue() * 1.5), 1f));
        ButtonOpenPlayStore.setText(context.getResources().getString(R.string.MENU_REVIEW_RATE_NOW));
        ButtonOpenPlayStore.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonOpenPlayStore.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue() * 1.5));
        ButtonOpenPlayStore.setTypeface(Typeface.DEFAULT_BOLD);
        ButtonOpenPlayStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darkBackground.dismiss();
                menu.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=org.lehmenkuehler.calculator"));
                context.startActivity(intent);
            }
        });

        ContentLayout.removeAllViews();
        ContentLayout.addView(element);
        ContentLayout.addView(ButtonOpenPlayStore);
    }


}