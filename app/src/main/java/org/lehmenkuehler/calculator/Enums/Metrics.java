package org.lehmenkuehler.calculator.Enums;

public enum Metrics
{
    PADDING_ULTIMATE(0.01), // relative to the smaller screen side - backup value
    PADDING_SIDES(0.01), // relative to the smaller screen side
    PADDING_BOTTOM(0.01), // relative to the smaller screen side
    PADDING_TOP(0.02), // relative to the smaller screen side

    PADDING_SLOPE_FACTOR(0.08), // adding this much padding for a lower aspect ratio of one


    SCREEN_WIDTH,
    SCREEN_HEIGHT,

    // RELATIVE_VALUES

    LANDSCAPE_RATIO(0.49),

    // LARGE ITEMS

    CONTAINER_LARGE_COUNT_ROWS(4),
    CONTAINER_LARGE_COUNT_COLUMNS(5),

    CONTAINER_LARGE_WIDTH, // absolute width
    CONTAINER_LARGE_HEIGHT, // absolute height

    BUTTON_LARGE_ASPECT_RATIO(1.5217), // drawable aspect ratio
    BUTTON_LARGE_WIDTH(0.96), // relative width
    BUTTON_LARGE_HEIGHT,
    BUTTON_LARGE_MARGIN_SIDES,
    BUTTON_LARGE_MARGIN_TOP(0.01), // relative to the height
    BUTTON_LARGE_MARGIN_BOTTOM(0.01), // relative to the height
    BUTTON_LARGE_TEXT_SIZE(0.44), // relative to the height

    TEXTVIEW_LARGE_WIDTH, // equals the button's width
    TEXTVIEW_LARGE_HEIGHT(0.4), // relative to the button's height
    TEXTVIEW_LARGE_TEXT_SIZE(0.72), // relative to the height

    // SMALL ITEMS

    CONTAINER_SMALL_COUNT_ROWS(4),
    CONTAINER_SMALL_COUNT_COLUMNS(6),

    CONTAINER_SMALL_WIDTH, // absolute width
    CONTAINER_SMALL_HEIGHT, // absolute height

    BUTTON_SMALL_ASPECT_RATIO(1.3043), // drawable aspect ratio
    BUTTON_SMALL_WIDTH(0.96), // relative width
    BUTTON_SMALL_HEIGHT,
    BUTTON_SMALL_MARGIN_SIDES,
    BUTTON_SMALL_MARGIN_TOP(0.01), // relative to the height
    BUTTON_SMALL_MARGIN_BOTTOM(0.01), // relative to the height
    BUTTON_SMALL_TEXT_SIZE(0.30), // relative to the height

    TEXTVIEW_SMALL_WIDTH, // equals the button's width
    TEXTVIEW_SMALL_HEIGHT(0.4), // relative to the button's height
    TEXTVIEW_SMALL_TEXT_SIZE(0.66), // relative to the height

    // DISPLAYS

    DISPLAY_MARGIN_SIDES(0.1),  // relative to a large button's width

    DISPLAY_INPUT_HEIGHT,
    DISPLAY_INPUT_TEXT_SIZE(1.3), // relative to a large button's text size

    DISPLAY_OUTPUT_HEIGHT(1.0), // relative to a large button's height
    DISPLAY_OUTPUT_PADDING_SIDES(0.3), // relative to a large button's width
    DISPLAY_OUTPUT_TEXT_SIZE(0.4), // relative to the height
    DISPLAY_OUTPUT_MARGIN_BOTTOM(0.3), // relative to a large button's height

    // DISPLAY MODE

    TEXTVIEW_MODE_COUNT(6),
    TEXTVIEW_MODE_WIDTH,
    TEXTVIEW_MODE_HEIGHT(0.6), // relative to a large button's height
    TEXTVIEW_MODE_TEXT_SIZE(0.5), // relative to the height


    // MENU METRICS

    MENU_WIDTH(0.85), // relative to the screen width
    MENU_HEIGHT(0.8), // relative to the screen height

    MENU_ELEMENT_HEIGHT(1.3), // relative to a large button's height

    MENU_EDGE_HEIGHT(0.0), // relative to a large button's height
    MENU_SEPARATOR_HEIGHT(0.0), // relative to a large button's height
    MENU_HEADLINE_HEIGHT(1.0), // relative to a large button's height
    MENU_BUTTONS_HEIGHT(1.0), // relative to a large button's height

    MENU_HEADLINE_TEXT_SIZE(0.8), // relative to a large button's text size

    // CONSTANTS

    MENU_CONSTANTS_SYMBOL_WIDTH(0.3), // relative to the total width
    MENU_CONSTANTS_DESCRIPTION_WIDTH,

    MENU_CONSTANT_SYMBOL_TEXT_SIZE(1.1), // relative to a large button's text size
    MENU_CONSTANT_DESCRIPTION_TEXT_SIZE(0.75), // relative to a large button's text size

    // MODE

    MENU_MODE_ELEMENT1_HEIGHT(0.8), // relative to a large button's height
    MENU_MODE_ELEMENT2_HEIGHT(1.2), // relative to a large button's height
    MENU_MODE_BUTTON_HEIGHT(0.7), // relative to the element's height
    MENU_MODE_BUTTON_WIDTH(1.5), // relative to the button's height
    MENU_MODE_BUTTON_TEXT_SIZE(0.35), //  // relative to the button's height
    MENU_MODE_BUTTON_MARGIN_SIDES(0.10); // relative to the button's width

    double ratio = 1.0;
    int size = 0;

    Metrics(double ratio)
    {
        this.ratio = ratio;
    }

    Metrics(int size)
    {
        this.size = size;
    }

    Metrics()
    {
    }

    public double getRatio()
    {
        return ratio;
    }

    public int getValue()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }
}
