package org.lehmenkuehler.calculator;

import org.lehmenkuehler.calculator.Enums.Metrics;

class ComputeMetrics {


    static void computeMetrics() {

        // THIS ORDER NEEDS TO BE KEPT

        adjustSidePadding();

        computeAvailableSpace();
        computeLargeButtons();
        computeLargeTextViews();
        computeSmallButtons();
        computeSmallTextViews();

        scaleOutputDisplay();
        scaleModeDisplay();

        scaleInputDisplay();


        scaleGeneralMenu();
        scaleMenuConstants();
        scaleMenuMode();
    }

    private static void adjustSidePadding() {
        double screenRatio = (double) Main.screenHeight / (double) Main.screenWidth;

        double customRatio = Metrics.PADDING_ULTIMATE.getRatio() + (1000 - Preferences.KEYBOARD_WIDTH) * 0.0005;

        if (screenRatio > 1) {
            if (screenRatio < 1.7776) {
                double diff = 1.7776 - screenRatio;
                Metrics.PADDING_SIDES.setRatio(customRatio + diff * Metrics.PADDING_SLOPE_FACTOR.getRatio());
            }
            else Metrics.PADDING_SIDES.setRatio(customRatio);
        } else {
            Metrics.PADDING_SIDES.setRatio(customRatio);
        }
    }


    private static void computeAvailableSpace() {
        if (Main.screenWidth > Main.screenHeight) {

            Metrics.PADDING_SIDES.setSize((int) (Main.screenWidth * Metrics.PADDING_SIDES.getRatio()));
            Metrics.PADDING_TOP.setSize((int) (Main.screenWidth * Metrics.PADDING_TOP.getRatio()));
            Metrics.PADDING_BOTTOM.setSize((int) (Main.screenWidth * Metrics.PADDING_BOTTOM.getRatio()));

            Metrics.SCREEN_WIDTH.setSize(Main.screenWidth - Metrics.PADDING_SIDES.getValue() * 2);
            Metrics.SCREEN_HEIGHT.setSize(Main.screenHeight - Metrics.PADDING_TOP.getValue() - Metrics.PADDING_BOTTOM.getValue());

            Metrics.SCREEN_WIDTH.setSize((int) (Main.screenWidth * Metrics.LANDSCAPE_RATIO.getRatio()));
        } else {
            Metrics.PADDING_SIDES.setSize((int) (Main.screenHeight * Metrics.PADDING_SIDES.getRatio()));
            Metrics.PADDING_TOP.setSize((int) (Main.screenHeight * Metrics.PADDING_TOP.getRatio()));
            Metrics.PADDING_BOTTOM.setSize((int) (Main.screenHeight * Metrics.PADDING_BOTTOM.getRatio()));

            Metrics.SCREEN_WIDTH.setSize(Main.screenWidth - Metrics.PADDING_SIDES.getValue() * 2);
            Metrics.SCREEN_HEIGHT.setSize(Main.screenHeight - Metrics.PADDING_TOP.getValue() - Metrics.PADDING_BOTTOM.getValue());
        }
    }

    private static void computeLargeButtons() {
        Metrics.CONTAINER_LARGE_WIDTH.setSize(Metrics.SCREEN_WIDTH.getValue() / Metrics.CONTAINER_LARGE_COUNT_COLUMNS.getValue());
        Metrics.BUTTON_LARGE_WIDTH.setSize((int) (Metrics.CONTAINER_LARGE_WIDTH.getValue() * Metrics.BUTTON_LARGE_WIDTH.getRatio() * Preferences.BUTTON_SIZE * 0.001));
        Metrics.BUTTON_LARGE_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_WIDTH.getValue() / Metrics.BUTTON_LARGE_ASPECT_RATIO.getRatio()));
        Metrics.BUTTON_LARGE_MARGIN_SIDES.setSize((Metrics.CONTAINER_LARGE_WIDTH.getValue() - Metrics.BUTTON_LARGE_WIDTH.getValue()) / 2);
        Metrics.BUTTON_LARGE_MARGIN_TOP.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.BUTTON_LARGE_MARGIN_TOP.getRatio()));
        Metrics.BUTTON_LARGE_MARGIN_BOTTOM.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.BUTTON_LARGE_MARGIN_BOTTOM.getRatio()));
        Metrics.BUTTON_LARGE_TEXT_SIZE.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.BUTTON_LARGE_TEXT_SIZE.getRatio()));
    }

    private static void computeLargeTextViews() {
        Metrics.TEXTVIEW_LARGE_WIDTH.setSize(Metrics.CONTAINER_LARGE_WIDTH.getValue());
        Metrics.TEXTVIEW_LARGE_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.TEXTVIEW_LARGE_HEIGHT.getRatio()));
        Metrics.CONTAINER_LARGE_HEIGHT.setSize(Metrics.BUTTON_LARGE_HEIGHT.getValue() + Metrics.BUTTON_LARGE_MARGIN_TOP.getValue() + Metrics.BUTTON_LARGE_MARGIN_BOTTOM.getValue() + Metrics.TEXTVIEW_LARGE_HEIGHT.getValue());
        Metrics.TEXTVIEW_LARGE_TEXT_SIZE.setSize((int) (Metrics.TEXTVIEW_LARGE_HEIGHT.getValue() * Metrics.TEXTVIEW_LARGE_TEXT_SIZE.getRatio()));
    }

    private static void computeSmallButtons() {
        Metrics.CONTAINER_SMALL_WIDTH.setSize(Metrics.SCREEN_WIDTH.getValue() / Metrics.CONTAINER_SMALL_COUNT_COLUMNS.getValue());
        Metrics.BUTTON_SMALL_WIDTH.setSize((int) (Metrics.CONTAINER_SMALL_WIDTH.getValue() * Metrics.BUTTON_SMALL_WIDTH.getRatio() * Preferences.BUTTON_SIZE * 0.001));
        Metrics.BUTTON_SMALL_HEIGHT.setSize((int) (Metrics.BUTTON_SMALL_WIDTH.getValue() / Metrics.BUTTON_SMALL_ASPECT_RATIO.getRatio()));
        Metrics.BUTTON_SMALL_MARGIN_SIDES.setSize((Metrics.CONTAINER_SMALL_WIDTH.getValue() - Metrics.BUTTON_SMALL_WIDTH.getValue()) / 2);
        Metrics.BUTTON_SMALL_MARGIN_TOP.setSize((int) (Metrics.BUTTON_SMALL_HEIGHT.getValue() * Metrics.BUTTON_SMALL_MARGIN_TOP.getRatio()));
        Metrics.BUTTON_SMALL_MARGIN_BOTTOM.setSize((int) (Metrics.BUTTON_SMALL_HEIGHT.getValue() * Metrics.BUTTON_SMALL_MARGIN_BOTTOM.getRatio()));
        Metrics.BUTTON_SMALL_TEXT_SIZE.setSize((int) (Metrics.BUTTON_SMALL_HEIGHT.getValue() * Metrics.BUTTON_SMALL_TEXT_SIZE.getRatio()));
    }

    private static void computeSmallTextViews() {
        Metrics.TEXTVIEW_SMALL_WIDTH.setSize(Metrics.CONTAINER_SMALL_WIDTH.getValue());
        Metrics.TEXTVIEW_SMALL_HEIGHT.setSize((int) (Metrics.BUTTON_SMALL_HEIGHT.getValue() * Metrics.TEXTVIEW_SMALL_HEIGHT.getRatio()));
        Metrics.CONTAINER_SMALL_HEIGHT.setSize(Metrics.BUTTON_SMALL_HEIGHT.getValue() + Metrics.BUTTON_SMALL_MARGIN_TOP.getValue() + Metrics.BUTTON_SMALL_MARGIN_BOTTOM.getValue() + Metrics.TEXTVIEW_SMALL_HEIGHT.getValue());
        Metrics.TEXTVIEW_SMALL_TEXT_SIZE.setSize((int) (Metrics.TEXTVIEW_SMALL_HEIGHT.getValue() * Metrics.TEXTVIEW_SMALL_TEXT_SIZE.getRatio()));
    }

    private static void scaleOutputDisplay() {
        Metrics.DISPLAY_OUTPUT_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.DISPLAY_OUTPUT_HEIGHT.getRatio()));
        Metrics.DISPLAY_OUTPUT_TEXT_SIZE.setSize((int) (Metrics.DISPLAY_OUTPUT_HEIGHT.getValue() * Metrics.DISPLAY_OUTPUT_TEXT_SIZE.getRatio()));
        Metrics.DISPLAY_OUTPUT_PADDING_SIDES.setSize((int) (Metrics.BUTTON_LARGE_WIDTH.getValue() * Metrics.DISPLAY_OUTPUT_PADDING_SIDES.getRatio()));
        Metrics.DISPLAY_OUTPUT_MARGIN_BOTTOM.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.DISPLAY_OUTPUT_MARGIN_BOTTOM.getRatio()));

        Metrics.DISPLAY_MARGIN_SIDES.setSize((int) (Metrics.BUTTON_LARGE_WIDTH.getValue() * Metrics.DISPLAY_MARGIN_SIDES.getRatio()));
    }

    private static void scaleModeDisplay() {
        if (Main.screenWidth > Main.screenHeight) {
            Metrics.TEXTVIEW_MODE_WIDTH.setSize((Main.screenWidth  - Metrics.PADDING_SIDES.getValue() * 2) / Metrics.TEXTVIEW_MODE_COUNT.getValue());
        } else {
            Metrics.TEXTVIEW_MODE_WIDTH.setSize(Metrics.SCREEN_WIDTH.getValue() / Metrics.TEXTVIEW_MODE_COUNT.getValue());
        }
        Metrics.TEXTVIEW_MODE_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.TEXTVIEW_MODE_HEIGHT.getRatio()));
        Metrics.TEXTVIEW_MODE_TEXT_SIZE.setSize((int) (Metrics.TEXTVIEW_MODE_HEIGHT.getValue() * Metrics.TEXTVIEW_MODE_TEXT_SIZE.getRatio()));
    }

    private static void scaleInputDisplay() {
        int usedSpace = 0;

        if (Main.screenWidth > Main.screenHeight) {
            usedSpace += Metrics.PADDING_BOTTOM.getValue();
            usedSpace += Metrics.CONTAINER_LARGE_HEIGHT.getValue() * Metrics.CONTAINER_LARGE_COUNT_ROWS.getValue();
            usedSpace += Metrics.DISPLAY_OUTPUT_HEIGHT.getValue();
            usedSpace += Metrics.DISPLAY_OUTPUT_MARGIN_BOTTOM.getValue();
            usedSpace += Metrics.TEXTVIEW_MODE_HEIGHT.getValue();
        } else {
            usedSpace += Metrics.PADDING_BOTTOM.getValue();
            usedSpace += Metrics.CONTAINER_LARGE_HEIGHT.getValue() * Metrics.CONTAINER_LARGE_COUNT_ROWS.getValue();
            usedSpace += Metrics.CONTAINER_SMALL_HEIGHT.getValue() * Metrics.CONTAINER_SMALL_COUNT_ROWS.getValue() - Metrics.TEXTVIEW_SMALL_HEIGHT.getValue();
            usedSpace += Metrics.DISPLAY_OUTPUT_HEIGHT.getValue();
            usedSpace += Metrics.DISPLAY_OUTPUT_MARGIN_BOTTOM.getValue();
            usedSpace += Metrics.TEXTVIEW_MODE_HEIGHT.getValue();
        }

        Metrics.DISPLAY_INPUT_HEIGHT.setSize(Metrics.SCREEN_HEIGHT.getValue() - usedSpace);
        if (Metrics.DISPLAY_INPUT_HEIGHT.getValue() < Metrics.DISPLAY_OUTPUT_HEIGHT.getValue())
            Metrics.DISPLAY_INPUT_HEIGHT.setSize(Metrics.DISPLAY_OUTPUT_HEIGHT.getValue());

        Metrics.DISPLAY_INPUT_TEXT_SIZE.setSize((int) (Metrics.BUTTON_LARGE_TEXT_SIZE.getValue() * Metrics.DISPLAY_INPUT_TEXT_SIZE.getRatio() * ((double) Preferences.DISPLAY_TEXT_SIZE / 100)));
    }

    private static void scaleGeneralMenu() {

        Metrics.MENU_WIDTH.setSize((int) (Main.screenWidth * Metrics.MENU_WIDTH.getRatio()));
        Metrics.MENU_HEIGHT.setSize((int) (Main.screenHeight * Metrics.MENU_HEIGHT.getRatio()));

        Metrics.MENU_EDGE_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_EDGE_HEIGHT.getRatio()));
        Metrics.MENU_SEPARATOR_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_SEPARATOR_HEIGHT.getRatio()));

        Metrics.MENU_ELEMENT_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_ELEMENT_HEIGHT.getRatio()));
        Metrics.MENU_HEADLINE_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_HEADLINE_HEIGHT.getRatio()));
        Metrics.MENU_BUTTONS_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_BUTTONS_HEIGHT.getRatio()));

        Metrics.MENU_HEADLINE_TEXT_SIZE.setSize((int) (Metrics.BUTTON_LARGE_TEXT_SIZE.getValue() * Metrics.MENU_HEADLINE_TEXT_SIZE.getRatio()));


        Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.setSize((int) (Metrics.BUTTON_LARGE_TEXT_SIZE.getValue() * Metrics.MENU_CONSTANT_SYMBOL_TEXT_SIZE.getRatio()));
        Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.setSize((int) (Metrics.BUTTON_LARGE_TEXT_SIZE.getValue() * Metrics.MENU_CONSTANT_DESCRIPTION_TEXT_SIZE.getRatio()));
    }

    private static void scaleMenuConstants() {
        Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.setSize((int) (Metrics.MENU_WIDTH.getValue() * Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.getRatio()));
        Metrics.MENU_CONSTANTS_DESCRIPTION_WIDTH.setSize(Metrics.MENU_WIDTH.getValue() - Metrics.MENU_CONSTANTS_SYMBOL_WIDTH.getValue());
    }

    private static void scaleMenuMode() {
        Metrics.MENU_MODE_ELEMENT1_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_MODE_ELEMENT1_HEIGHT.getRatio()));
        Metrics.MENU_MODE_ELEMENT2_HEIGHT.setSize((int) (Metrics.BUTTON_LARGE_HEIGHT.getValue() * Metrics.MENU_MODE_ELEMENT2_HEIGHT.getRatio()));

        Metrics.MENU_MODE_BUTTON_HEIGHT.setSize((int) (Metrics.MENU_MODE_ELEMENT2_HEIGHT.getValue() * Metrics.MENU_MODE_BUTTON_HEIGHT.getRatio()));
        Metrics.MENU_MODE_BUTTON_WIDTH.setSize((int) (Metrics.MENU_MODE_BUTTON_HEIGHT.getValue() * Metrics.MENU_MODE_BUTTON_WIDTH.getRatio()));
        Metrics.MENU_MODE_BUTTON_TEXT_SIZE.setSize((int) (Metrics.MENU_MODE_BUTTON_HEIGHT.getValue() * Metrics.MENU_MODE_BUTTON_TEXT_SIZE.getRatio()));
        Metrics.MENU_MODE_BUTTON_MARGIN_SIDES.setSize((int) (Metrics.MENU_MODE_BUTTON_WIDTH.getValue() * Metrics.MENU_MODE_BUTTON_MARGIN_SIDES.getRatio()));
    }

}