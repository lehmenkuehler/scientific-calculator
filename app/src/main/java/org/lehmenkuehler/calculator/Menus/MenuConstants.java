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
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Scale;
import org.lehmenkuehler.calculator.Utility;

public class MenuConstants {

    private final Context context;

    private final DarkBackground darkBackground;
    private final View layoutMenu;
    public PopupWindow menu;

    private TextView Headline;
    private LinearLayout ContentLayout, ButtonLayout;
    private ScrollView ScrollView;
    private Button ButtonClose, ButtonReturn;

    @SuppressLint("InflateParams")
    public MenuConstants(Context c) {
        context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutMenu = inflater.inflate(R.layout.menu_1, null);
        darkBackground = new DarkBackground(context);
    }

    public void initiateMenu() {

        menu = new PopupWindow(layoutMenu, Metrics.MENU_WIDTH.getValue(), Metrics.MENU_HEIGHT.getValue(), true);
        menu.setAnimationStyle(R.style.MenuAnimation);
        darkBackground.initiate();
        menu.showAtLocation(layoutMenu, Gravity.CENTER, 0, 0);

        ContentLayout = (LinearLayout) layoutMenu.findViewById(R.id.MenuLinearLayout);
        ButtonLayout = (LinearLayout) layoutMenu.findViewById(R.id.ButtonLayout);
        ScrollView = (ScrollView) layoutMenu.findViewById(R.id.ScrollView);

        ButtonReturn = new Button(context);
        ButtonReturn.setBackgroundResource(R.drawable.menu_button);
        ButtonReturn.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonReturn.setText(context.getResources().getString(R.string.MENU_BUTTON_RETURN));
        ButtonReturn.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonReturn.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonReturn.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.vibrate();
                openSelectGroup();
            }
        });

        ButtonClose = new Button(context);
        ButtonClose.setBackgroundResource(R.drawable.menu_button);
        ButtonClose.setLayoutParams(new TableRow.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_BUTTONS_HEIGHT.getValue(), 1f));
        ButtonClose.setText(context.getResources().getString(R.string.MENU_BUTTON_EXIT));
        ButtonClose.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_BUTTON_TEXT));
        ButtonClose.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
        ButtonClose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.vibrate();
                darkBackground.dismiss();
                menu.dismiss();
            }
        });

        Headline = (TextView) layoutMenu.findViewById(R.id.Headline);
        Headline.getLayoutParams().height = Metrics.MENU_HEADLINE_HEIGHT.getValue();
        Headline.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_HEADLINE_TEXT_SIZE.getValue());

        openSelectGroup();
    }

    private void openSelectGroup() {

        ContentLayout.removeAllViews();
        ButtonLayout.removeAllViews();
        ScrollView.scrollTo(0, 0);

        Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), 5);
        Headline.setText(context.getResources().getString(R.string.MENU_HEADLINE_CONSTANTS).toUpperCase());
        ButtonLayout.addView(ButtonClose);

        ViewGroup.LayoutParams groupElementParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Metrics.MENU_ELEMENT_HEIGHT.getValue());

        for (final Component.ConstantTag tag : Component.ConstantTag.values()) {

            if (tag.ordinal() < 2) continue;

            TextView element = new TextView(context);
            element.setId(200 + tag.ordinal());
            element.setLayoutParams(groupElementParams);
            element.setBackgroundResource(R.drawable.menu_element_gradient);
            element.setGravity(Gravity.CENTER);
            element.setText(tag.getName());
            element.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
            element.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_PRIMARY));
            element.setTypeface(Typeface.DEFAULT_BOLD);
            element.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Utility.vibrate();
                    openGroup(tag);
                }
            });
            ContentLayout.addView(element);
        }
    }

    private void openGroup(Component.ConstantTag tag) {

        ButtonLayout.removeAllViews();
        ContentLayout.removeAllViews();
        ScrollView.scrollTo(0, 0);

        int elementCounter = 0;

        Headline.setText(tag.getName().toUpperCase());

        ButtonLayout.addView(ButtonReturn);
        ButtonLayout.addView(ButtonClose);

        ViewGroup.LayoutParams symbolParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams descriptionParams = new TableRow.LayoutParams(Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.getValue(), ViewGroup.LayoutParams.MATCH_PARENT, 0f);
        ViewGroup.LayoutParams wrapperParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Metrics.MENU_ELEMENT_HEIGHT.getValue());

        for (final Component component : Component.values()) {
            if (component.getConstantTag() == tag) {

                elementCounter++;

                LinearLayout wrapper = new LinearLayout(context);
                wrapper.setId(300 + component.ordinal());
                wrapper.setOrientation(LinearLayout.HORIZONTAL);
                wrapper.setLayoutParams(wrapperParams);

                TextView symbol = new TextView(context);
                symbol.setBackgroundResource(R.drawable.menu_element_solid);
                symbol.setLayoutParams(symbolParams);
                symbol.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                symbol.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getValue());
                symbol.setTypeface(Typeface.DEFAULT_BOLD);
                symbol.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_SECONDARY));
                symbol.setText(Utility.fromHtml(component.getSymbol()));

                TextView description = new TextView(context);
                description.setBackgroundResource(R.drawable.menu_element_gradient);
                description.setLayoutParams(descriptionParams);
                description.setGravity(Gravity.CENTER_VERTICAL);
                description.setPadding((int) (Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.getValue() * 0.06), 0, 0, 0);
                description.setTextSize(TypedValue.COMPLEX_UNIT_PX, Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getValue());
                description.setTextColor(ContextCompat.getColor(Utility.getContext(), R.color.MENU_ELEMENT_TEXT_PRIMARY));
                description.setText(Utility.fromHtml("<b>" + component.getName() + "</b><br>" + component.getDescription()));

                wrapper.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Utility.vibrate();
                        Main.externallyAddedElement = component;
                        darkBackground.dismiss();
                        menu.dismiss();
                    }
                });

                ContentLayout.addView(wrapper);
                wrapper.addView(symbol);
                wrapper.addView(description);
            }
        }
        Scale.scaleMenuScrollView(ScrollView, Metrics.MENU_ELEMENT_HEIGHT.getValue(), elementCounter);
    }

}
