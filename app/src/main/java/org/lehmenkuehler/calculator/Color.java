package org.lehmenkuehler.calculator;

import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.Spanned;
import android.widget.Button;
import android.widget.TextView;

class Color {

    private static final String buttonTextColor = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.BUTTON_TEXT));
    private static final String buttonTextColorShift = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.BUTTON_TEXT_2ND));
    private static final String buttonTextColorAlpha = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.BUTTON_TEXT_3RD));
    private static final String textViewTextColor1 = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.TEXTVIEW_TEXT_1));
    private static final String textViewTextColor2 = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.TEXTVIEW_TEXT_2));


    static void labelButton (Button button, String text) {
        String s;
        if (text.equals("2nd")) {
            s = "<font color=" + buttonTextColorShift + ">" + text + "</font>";
        } else if (text.equals("3rd")) {
            s = "<font color=" + buttonTextColorAlpha + ">" + text + "</font>";
        } else {
            s = "<font color=" + buttonTextColor + ">" + text + "</font>";
        }
        button.setText(fromHtml(s));
    }

    static void labelTextView(TextView textView, String... text) {
        if (text.length == 1) {
            String s1 = "<font color=" + textViewTextColor1 + ">" + text[0] + "</font>";
            textView.setText(fromHtml(s1));
        }
        else {
            String s1 = "<font color=" + textViewTextColor1 + ">" + text[0] + "</font>";
            String s2 = "<font color=" + textViewTextColor2 + ">" + text[1] + "</font>";
            textView.setText(fromHtml(s1 + " " + s2));
        }


    }


    @SuppressWarnings("deprecation")
    private static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

}
