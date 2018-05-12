package org.lehmenkuehler.calculator.Menus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.R;

class DarkBackground {

    private final View view;
    private final PopupWindow popupWindow;

    @SuppressLint("InflateParams")
    DarkBackground(Context c) {
        LayoutInflater inflater;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.menu_background, null);
        popupWindow = new PopupWindow(view, Main.screenWidth, Main.screenHeight, true);
        popupWindow.setAnimationStyle(R.style.MenuBackgroundAnimation);
    }


    void initiate() {
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
    }

    void dismiss() {
        popupWindow.dismiss();
    }


}
