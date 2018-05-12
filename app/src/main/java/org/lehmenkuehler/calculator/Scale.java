package org.lehmenkuehler.calculator;

import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.lehmenkuehler.calculator.Enums.Metrics;

public class Scale
{
    public static void everything(RelativeLayout relativeLayout)
    {
        relativeLayout.setPadding(
                Metrics.PADDING_SIDES.getValue(),
                Metrics.PADDING_TOP.getValue(),
                Metrics.PADDING_SIDES.getValue(),
                Metrics.PADDING_BOTTOM.getValue()
        );
    }

    public static void largeButton(Button button)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Metrics.BUTTON_LARGE_WIDTH.getValue(),
                Metrics.BUTTON_LARGE_HEIGHT.getValue()
        );
        params.setMargins(
                Metrics.BUTTON_LARGE_MARGIN_SIDES.getValue(),
                Metrics.BUTTON_LARGE_MARGIN_TOP.getValue(),
                Metrics.BUTTON_LARGE_MARGIN_SIDES.getValue(),
                Metrics.BUTTON_LARGE_MARGIN_BOTTOM.getValue()
        );
        button.setLayoutParams(params);
        button.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.BUTTON_LARGE_TEXT_SIZE.getValue()
        );
    }

    public static void smallButton(Button button)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Metrics.BUTTON_SMALL_WIDTH.getValue(),
                Metrics.BUTTON_SMALL_HEIGHT.getValue()
        );
        params.setMargins(
                Metrics.BUTTON_SMALL_MARGIN_SIDES.getValue(),
                Metrics.BUTTON_SMALL_MARGIN_TOP.getValue(),
                Metrics.BUTTON_SMALL_MARGIN_SIDES.getValue(),
                Metrics.BUTTON_SMALL_MARGIN_BOTTOM.getValue()
        );
        button.setLayoutParams(params);
        button.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.BUTTON_SMALL_TEXT_SIZE.getValue()
        );
    }

    public static void largeTextView(TextView textView)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Metrics.TEXTVIEW_LARGE_WIDTH.getValue(),
                Metrics.TEXTVIEW_LARGE_HEIGHT.getValue()
        );
        textView.setLayoutParams(params);
        textView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.TEXTVIEW_LARGE_TEXT_SIZE.getValue()
        );
    }

    public static void smallTextView(TextView textView)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Metrics.TEXTVIEW_SMALL_WIDTH.getValue(),
                Metrics.TEXTVIEW_SMALL_HEIGHT.getValue()
        );
        textView.setLayoutParams(params);
        textView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.TEXTVIEW_SMALL_TEXT_SIZE.getValue()
        );
    }

    public static void ouputDisplay(TextView textView)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                Metrics.DISPLAY_OUTPUT_HEIGHT.getValue()
        );
        params.setMargins(
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                0,
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                0
        );
        textView.setLayoutParams(params);
        textView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.DISPLAY_OUTPUT_TEXT_SIZE.getValue()
        );
    }

    public static void outputLinearLayout(LinearLayout linearLayout)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Metrics.DISPLAY_OUTPUT_HEIGHT.getValue()
        );
        params.setMargins(
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                0,
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                Metrics.DISPLAY_OUTPUT_MARGIN_BOTTOM.getValue()
        );
        linearLayout.setPadding(Metrics.DISPLAY_OUTPUT_PADDING_SIDES.getValue(), 0, Metrics.DISPLAY_OUTPUT_PADDING_SIDES.getValue(), 0);
        linearLayout.setLayoutParams(params);
    }

    public static void modeDisplay(TextView textView)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                Metrics.TEXTVIEW_MODE_WIDTH.getValue(),
                Metrics.TEXTVIEW_MODE_HEIGHT.getValue()
        );
        textView.setLayoutParams(params);
        textView.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.TEXTVIEW_MODE_TEXT_SIZE.getValue()
        );
    }

    public static void inputDisplay(EditText editText)
    {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                Metrics.DISPLAY_INPUT_HEIGHT.getValue()
        );
        params.setMargins(
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                0,
                Metrics.DISPLAY_MARGIN_SIDES.getValue(),
                0
        );
        editText.setLayoutParams(params);
        editText.setPadding(30, 10, 0, 0);
        editText.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                Metrics.DISPLAY_INPUT_TEXT_SIZE.getValue()
        );
    }

    public static void scaleMenuScrollView(ScrollView scrollView, int elementHeight, int elementCount)
    {
        int availableHeight = Metrics.MENU_HEIGHT.getValue() - Metrics.MENU_HEADLINE_HEIGHT.getValue() - Metrics.MENU_BUTTONS_HEIGHT.getValue();
        if (elementCount * elementHeight < availableHeight)
        {
            scrollView.getLayoutParams().height = elementCount * elementHeight;
        } else
        {
            int possibleElements = availableHeight / elementHeight;
            scrollView.getLayoutParams().height = possibleElements * elementHeight;
        }
    }
}
