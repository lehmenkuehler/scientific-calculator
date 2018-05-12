package org.lehmenkuehler.calculator;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences
{
    private static final SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
    private static final SharedPreferences log = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_LOG), Context.MODE_PRIVATE);

    public static int DISPLAY_TEXT_SIZE = settings.getInt("DISPLAY_TEXT_SIZE", 100);
    public static int KEYBOARD_WIDTH = settings.getInt("KEYBOARD_WIDTH", 1000);
    public static int BUTTON_SIZE = settings.getInt("BUTTON_SIZE", 1000);


    public static int PRECISION = settings.getInt("PRECISION", 8);
    public static int SCIENTIFIC_PRECISION = settings.getInt("SCIENTIFIC_PRECISION", 8);
    public static int ENGINEERING_PRECISION = settings.getInt("ENGINEERING_PRECISION", 8);
    public static int NOTATION_THRESHOLD = settings.getInt("NOTATION_THRESHOLD", 8);

    public static boolean WELCOMING_MESSAGE = settings.getBoolean("WELCOMING_MESSAGE", true);
    public static int USAGE_COUNTER = log.getInt("USAGE_COUNTER", 1);
    public static boolean PLEA_FOR_REVIEW = log.getBoolean("PLEA_FOR_REVIEW", true);

    public static boolean FIX_PRECISION = settings.getBoolean("FIX_PRECISION", false);

    public static int HISTORY_LENGTH = settings.getInt("HISTORY_LENGTH", 20);
    public static ComplexMode MODE_COMPLEX = ComplexMode.values()[settings.getInt("MODE_COMPLEX", 0)];
    public static AngularMode MODE_ANGULAR = AngularMode.values()[settings.getInt("MODE_ANGULAR", 0)];
    public static AngularMode MODE_ANGULAR_POLAR = AngularMode.values()[settings.getInt("MODE_ANGULAR_POLAR", 1)];
    public static NumeralMode MODE_NUMERAL = NumeralMode.values()[settings.getInt("MODE_NUMERAL", 2)];
    public static NotationMode MODE_NOTATION = NotationMode.values()[settings.getInt("MODE_NOTATION", 0)];
    public static NotationMode MODE_NOTATION_ALTERNATIVE = NotationMode.values()[settings.getInt("MODE_NOTATION", 1)];
    public static OutputMode MODE_OUTPUT = OutputMode.values()[settings.getInt("MODE_OUTPUT", 0)];
    public static String IMAGINARY_CHARACTER = settings.getString("IMAGINARY_CHARACTER", "i");
    static boolean INTERIM_RESULTS = settings.getBoolean("INTERIM_RESULTS", true);
    static boolean FULLSCREEN = settings.getBoolean("FULLSCREEN", true);
    static boolean VIBRATION = settings.getBoolean("FULLSCREEN", true);
    static boolean LOCK_PORTRAIT = settings.getBoolean("LOCK_PORTRAIT", false);

    static void updateValues()
    {

        DISPLAY_TEXT_SIZE = settings.getInt("DISPLAY_TEXT_SIZE", 100);
        KEYBOARD_WIDTH = settings.getInt("KEYBOARD_WIDTH", 1000);
        BUTTON_SIZE = settings.getInt("BUTTON_SIZE", 1000);

        PRECISION = settings.getInt("PRECISION", 8);
        SCIENTIFIC_PRECISION = settings.getInt("SCIENTIFIC_PRECISION", 8);
        ENGINEERING_PRECISION = settings.getInt("ENGINEERING_PRECISION", 8);
        NOTATION_THRESHOLD = settings.getInt("NOTATION_THRESHOLD", 8);

        WELCOMING_MESSAGE = settings.getBoolean("WELCOMING_MESSAGE", true);

        FIX_PRECISION = settings.getBoolean("FIX_PRECISION", false);

        HISTORY_LENGTH = settings.getInt("HISTORY_LENGTH", 20);

        INTERIM_RESULTS = settings.getBoolean("INTERIM_RESULTS", true);
        FULLSCREEN = settings.getBoolean("FULLSCREEN", true);
        VIBRATION = settings.getBoolean("FULLSCREEN", true);
        LOCK_PORTRAIT = settings.getBoolean("LOCK_PORTRAIT", false);

        MODE_COMPLEX = ComplexMode.values()[settings.getInt("MODE_COMPLEX", 0)];
        MODE_ANGULAR = AngularMode.values()[settings.getInt("MODE_ANGULAR", 0)];
        MODE_ANGULAR_POLAR = AngularMode.values()[settings.getInt("MODE_ANGULAR_POLAR", 1)];
        MODE_NUMERAL = NumeralMode.values()[settings.getInt("MODE_NUMERAL", 2)];
        MODE_NOTATION = NotationMode.values()[settings.getInt("MODE_NOTATION", 0)];
        MODE_NOTATION_ALTERNATIVE = NotationMode.values()[settings.getInt("MODE_NOTATION", 1)];
        MODE_OUTPUT = OutputMode.values()[settings.getInt("MODE_OUTPUT", 0)];

        IMAGINARY_CHARACTER = settings.getString("IMAGINARY_CHARACTER", "i");
    }

    public enum AngularMode
    {
        RAD("RAD"),
        DEG("DEG"),
        GRAD("GRAD");

        String modeBarText = "";

        AngularMode(String modeBarText)
        {
            this.modeBarText = modeBarText;
        }

        public String getModeBarText()
        {
            return modeBarText;
        }
    }

    public enum ComplexMode
    {
        RECT("RECT"),
        POLAR("POLAR");

        String modeBarText = "";

        ComplexMode(String modeBarText)
        {
            this.modeBarText = modeBarText;
        }

        public String getModeBarText()
        {
            return modeBarText;
        }
    }

    public enum NotationMode
    {
        DEC(""),
        ENG("ENG"),
        SCI("SCI");

        String modeBarText = "";

        NotationMode(String modeBarText)
        {
            this.modeBarText = modeBarText;
        }

        public String getModeBarText()
        {
            return modeBarText;
        }
    }

    public enum NumeralMode
    {
        BIN("BIN"),
        OCT("OCT"),
        DEC("DEC"),
        HEX("HEX");

        String modeBarText = "";

        NumeralMode(String modeBarText)
        {
            this.modeBarText = modeBarText;
        }

        public String getModeBarText()
        {
            return modeBarText;
        }
    }

    public enum OutputMode
    {
        DECIMAL("DEC"),
        REPEATING_DECIMAL("PER"),
        SIMPLE_FRACTION("FRAC"),
        PARTED_FRACTION("FRAC2");

        String modeBarText = "";

        OutputMode(String modeBarText)
        {
            this.modeBarText = modeBarText;
        }
    }
}

