package org.lehmenkuehler.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Enums.Metrics;
import org.lehmenkuehler.calculator.Enums.Error;
import org.lehmenkuehler.calculator.Mechanics.Check;
import org.lehmenkuehler.calculator.Mechanics.Cursor;
import org.lehmenkuehler.calculator.Mechanics.Element;
import org.lehmenkuehler.calculator.Mechanics.Engine;
import org.lehmenkuehler.calculator.Mechanics.Fractions;
import org.lehmenkuehler.calculator.Mechanics.History;
import org.lehmenkuehler.calculator.Mechanics.InputControl;
import org.lehmenkuehler.calculator.Mechanics.Parser;
import org.lehmenkuehler.calculator.Mechanics.Printer;
import org.lehmenkuehler.calculator.Menus.MenuConstants;
import org.lehmenkuehler.calculator.Menus.MenuConvert;
import org.lehmenkuehler.calculator.Menus.MenuFunctions;
import org.lehmenkuehler.calculator.Menus.MenuHistory;
import org.lehmenkuehler.calculator.Menus.MenuMode;
import org.lehmenkuehler.calculator.Menus.MenuPrefix;
import org.lehmenkuehler.calculator.Menus.MenuReview;
import org.lehmenkuehler.calculator.Menus.MenuWelcome;

public class Main extends Activity implements View.OnClickListener, View.OnLongClickListener
{
    public static Component externallyAddedElement = Component.VOID;
    public static int answerId = 0;

    public static Error globalError = Error.VOID;
    public static BigDecimal convertedValue;

    public static int screenHeight, screenWidth;
    private final History historyManager = new History(this);
    private Context context;
    private Cursor cursorManager = new Cursor();
    private Engine Calculation = new Engine();
    private Parser parseManager = new Parser();
    private Printer printManager = new Printer();

    private MenuConstants mConst;
    private MenuFunctions mFunc;
    private MenuHistory mHist;
    private MenuPrefix mPref;
    private MenuConvert mConv;
    private MenuMode mMode;


    private boolean shift = false;
    private boolean alpha = false;
    private boolean hyperbolic = false;
    private boolean executed = false;

    private int inputId = 0;


    private Button ButtonZero;
    private Button ButtonOne;
    private Button ButtonTwo;
    private Button ButtonThree;
    private Button ButtonFour;
    private Button ButtonFive;
    private Button ButtonSix;
    private Button ButtonSeven;
    private Button ButtonEight;
    private Button ButtonNine;
    private Button ButtonDecimalPower;
    private Button ButtonBracketOpen;
    private Button ButtonBracketClose;
    private Button ButtonAdd;
    private Button ButtonSubtract;
    private Button ButtonMultiply;
    private Button ButtonDivide;
    private Button ButtonSquare;
    private Button ButtonRoot;
    private Button ButtonPower;
    private Button ButtonEnter;
    private Button ButtonClear;
    private Button ButtonBackspace;
    private Button ButtonToggleSign;
    private Button ButtonDot;
    private Button ButtonSine;
    private Button ButtonCosine;
    private Button ButtonTangent;
    private Button ButtonPi;
    private Button ButtonNaturalLogarithm;
    private Button ButtonCommonLogarithm;
    private Button ButtonLogarithm;
    private Button ButtonImaginary;
    private Button ButtonShift;
    private Button ButtonAlpha;
    private Button ButtonMode;
    private Button ButtonToggleHyperbolic;
    private Button ButtonCursorForward;
    private Button ButtonCursorBackward;
    private Button ButtonConstants;
    private Button ButtonFunctions;
    private Button ButtonHistory;
    private Button ButtonSettingsMenu;
    private Button ButtonToggleFraction;

    private TextView TextViewZero;
    private TextView TextViewOne;
    private TextView TextViewTwo;
    private TextView TextViewThree;
    private TextView TextViewFour;
    private TextView TextViewFive;
    private TextView TextViewSix;
    private TextView TextViewSeven;
    private TextView TextViewEight;
    private TextView TextViewNine;
    private TextView TextViewDecimalPower;
    private TextView TextViewBracketOpen;
    private TextView TextViewBracketClose;
    private TextView TextViewAdd;
    private TextView TextViewSubtract;
    private TextView TextViewMultiply;
    private TextView TextViewDivide;
    private TextView TextViewSquare;
    private TextView TextViewRoot;
    private TextView TextViewPower;
    private TextView TextViewEnter;
    private TextView TextViewToggleSign;
    private TextView TextViewDot;
    private TextView TextViewSine;
    private TextView TextViewCosine;
    private TextView TextViewTangent;
    private TextView TextViewPi;
    private TextView TextViewNaturalLogarithm;
    private TextView TextViewCommonLogarithm;
    private TextView TextViewLogarithm;
    private TextView TextViewImaginary;
    private TextView TextViewMode;
    private TextView TextViewToggleHyperbolic;
    private TextView TextViewConstants;
    private TextView TextViewFunctions;
    private TextView TextViewHistory;
    private TextView TextViewOptions;
    private TextView TextViewToggleFraction;

    private EditText displayInput;
    private TextView displayOutput;
    private LinearLayout LinearLayoutOutput;

    private LinearLayout linearLayoutKeyboard;
    private LinearLayout LinearLayoutModes;
    private RelativeLayout relativeLayout;

    private TextView barAngularMode;
    private TextView barNumeralMode;
    private TextView barFunction;
    private TextView barStructure;
    private TextView barNotation;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleFullScreen();
        computeScreenMetrics();

        context = this;

        if (Preferences.LOCK_PORTRAIT)
        {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        displayInput = (EditText) findViewById(R.id.display_input);
        displayOutput = (TextView) findViewById(R.id.display_result);
        displayOutput.setMovementMethod(new ScrollingMovementMethod());

        barAngularMode = (TextView) findViewById(R.id.TextViewModeAngular);
        barNumeralMode = (TextView) findViewById(R.id.TextViewModeNumeral);
        barFunction = (TextView) findViewById(R.id.TextViewModeFunction);
        barStructure = (TextView) findViewById(R.id.TextViewModeComplex);
        barNotation = (TextView) findViewById(R.id.TextViewModeNotation);

        setModeBarText();

        ComputeMetrics.computeMetrics();

        mConst = new MenuConstants(this);
        mFunc = new MenuFunctions(this);
        mHist = new MenuHistory(this);
        mPref = new MenuPrefix(this);
        mConv = new MenuConvert(this);
        mMode = new MenuMode(this);

        linearLayoutKeyboard = (LinearLayout) findViewById(R.id.linearLayoutKeyboard);
        LinearLayoutModes = (LinearLayout) findViewById(R.id.LinearLayoutModes);

        LinearLayoutOutput = (LinearLayout) findViewById(R.id.LinearLayoutOutput);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        // ZERO
        ButtonZero = (Button) findViewById(R.id.ButtonZero);
        Color.labelButton(ButtonZero, "0");
        TextViewZero = (TextView) findViewById(R.id.TextViewZero);
        // DOT
        ButtonDot = (Button) findViewById(R.id.ButtonDot);
        Color.labelButton(ButtonDot, ".");
        TextViewDot = (TextView) findViewById(R.id.TextViewDot);
        Color.labelTextView(TextViewDot, "");
        // NEGATIVE SIGN
        ButtonToggleSign = (Button) findViewById(R.id.ButtonToggleSign);
        Color.labelButton(ButtonToggleSign, "&plusmn");
        TextViewToggleSign = (TextView) findViewById(R.id.TextViewToggleSign);
        // DECIMAL POWER
        ButtonDecimalPower = (Button) findViewById(R.id.ButtonDecimalPower);
        Color.labelButton(ButtonDecimalPower, "× 10<sup><small>n</small></sup>");
        TextViewDecimalPower = (TextView) findViewById(R.id.TextViewDecimalPower);
        Color.labelTextView(TextViewDecimalPower, "prfx");
        // ENTER
        ButtonEnter = (Button) findViewById(R.id.ButtonEnter);
        Color.labelButton(ButtonEnter, "=");
        TextViewEnter = (TextView) findViewById(R.id.TextViewEnter);
        Color.labelTextView(TextViewEnter, "ans");
        // ONE
        ButtonOne = (Button) findViewById(R.id.ButtonOne);
        Color.labelButton(ButtonOne, "1");
        TextViewOne = (TextView) findViewById(R.id.TextViewOne);
        Color.labelTextView(TextViewOne, "A");
        // TWO
        ButtonTwo = (Button) findViewById(R.id.ButtonTwo);
        Color.labelButton(ButtonTwo, "2");
        TextViewTwo = (TextView) findViewById(R.id.TextViewTwo);
        Color.labelTextView(TextViewTwo, "B");
        // THREE
        ButtonThree = (Button) findViewById(R.id.ButtonThree);
        Color.labelButton(ButtonThree, "3");
        TextViewThree = (TextView) findViewById(R.id.TextViewThree);
        Color.labelTextView(TextViewThree, "C");
        // BRACKET OPEN
        ButtonBracketOpen = (Button) findViewById(R.id.ButtonBracketOpen);
        Color.labelButton(ButtonBracketOpen, "(");
        TextViewBracketOpen = (TextView) findViewById(R.id.TextViewBracketOpen);
        Color.labelTextView(TextViewBracketOpen, "sgn");
        // BRACKET CLOSE
        ButtonBracketClose = (Button) findViewById(R.id.ButtonBracketClose);
        Color.labelButton(ButtonBracketClose, ")");
        TextViewBracketClose = (TextView) findViewById(R.id.TextViewBracketClose);
        Color.labelTextView(TextViewBracketClose, "ran#", "int");
        // FOUR
        ButtonFour = (Button) findViewById(R.id.ButtonFour);
        Color.labelButton(ButtonFour, "4");
        TextViewFour = (TextView) findViewById(R.id.TextViewFour);
        Color.labelTextView(TextViewFour, "LCM", "D");
        // FIVE
        ButtonFive = (Button) findViewById(R.id.ButtonFive);
        Color.labelButton(ButtonFive, "5");
        TextViewFive = (TextView) findViewById(R.id.TextViewFive);
        Color.labelTextView(TextViewFive, "GCD", "E");
        // SIX
        ButtonSix = (Button) findViewById(R.id.ButtonSix);
        Color.labelButton(ButtonSix, "6");
        TextViewSix = (TextView) findViewById(R.id.TextViewSix);
        Color.labelTextView(TextViewSix, "F");
        // DIVIDE
        ButtonDivide = (Button) findViewById(R.id.ButtonDivide);
        Color.labelButton(ButtonDivide, "÷");
        TextViewDivide = (TextView) findViewById(R.id.TextViewDivide);
        Color.labelTextView(TextViewDivide, "%", "Δ%");
        // MULTIPLY
        ButtonMultiply = (Button) findViewById(R.id.ButtonMultiply);
        Color.labelButton(ButtonMultiply, "×");
        TextViewMultiply = (TextView) findViewById(R.id.TextViewMultiply);
        Color.labelTextView(TextViewMultiply, "d/dx", "∫dx");
        // SEVEN
        ButtonSeven = (Button) findViewById(R.id.ButtonSeven);
        Color.labelButton(ButtonSeven, "7");
        TextViewSeven = (TextView) findViewById(R.id.TextViewSeven);
        Color.labelTextView(TextViewSeven, "n!", "n!!");
        // EIGHT
        ButtonEight = (Button) findViewById(R.id.ButtonEight);
        Color.labelButton(ButtonEight, "8");
        TextViewEight = (TextView) findViewById(R.id.TextViewEight);
        Color.labelTextView(TextViewEight, "nCr", "nPr");
        // NINE
        ButtonNine = (Button) findViewById(R.id.ButtonNine);
        Color.labelButton(ButtonNine, "9");
        TextViewNine = (TextView) findViewById(R.id.TextViewNine);
        Color.labelTextView(TextViewNine, "re", "im");
        // ADD
        ButtonAdd = (Button) findViewById(R.id.ButtonAdd);
        Color.labelButton(ButtonAdd, "+");
        TextViewAdd = (TextView) findViewById(R.id.TextViewAdd);
        Color.labelTextView(TextViewAdd, "abs", "conj");
        // SUBTRACT
        ButtonSubtract = (Button) findViewById(R.id.ButtonSubtract);
        Color.labelButton(ButtonSubtract, "-");
        TextViewSubtract = (TextView) findViewById(R.id.TextViewSubtract);
        Color.labelTextView(TextViewSubtract, "mod", "x");
        // SQUARE
        ButtonSquare = (Button) findViewById(R.id.ButtonSquare);
        Color.labelButton(ButtonSquare, "x<sup><small>2</small></sup>");
        TextViewSquare = (TextView) findViewById(R.id.TextViewSquare);
        Color.labelTextView(TextViewSquare, "x<sup><small>3</small></sup>");
        // CUBE
        ButtonRoot = (Button) findViewById(R.id.ButtonRoot);
        Color.labelButton(ButtonRoot, "√x");
        TextViewRoot = (TextView) findViewById(R.id.TextViewCube);
        Color.labelTextView(TextViewRoot, "<sup><small>3</small></sup>√x");
        // POWER
        ButtonPower = (Button) findViewById(R.id.ButtonPower);
        Color.labelButton(ButtonPower, "x<sup><small>n</small></sup>");
        TextViewPower = (TextView) findViewById(R.id.TextViewPower);
        Color.labelTextView(TextViewPower, "<sup><small>n</small></sup>√x");
        // NATURAL LOGARITHM
        ButtonNaturalLogarithm = (Button) findViewById(R.id.ButtonNaturalLogarithm);
        Color.labelButton(ButtonNaturalLogarithm, "ln");
        TextViewNaturalLogarithm = (TextView) findViewById(R.id.TextViewNaturalLogarithm);
        Color.labelTextView(TextViewNaturalLogarithm, "e<sup><small>x</small></sup>");
        // COMMON LOGARITHM
        ButtonCommonLogarithm = (Button) findViewById(R.id.ButtonCommonLogarithm);
        Color.labelButton(ButtonCommonLogarithm, "<sup><small>a</sup></small>/<sub><small>b</sub></small>");
        TextViewCommonLogarithm = (TextView) findViewById(R.id.TextViewCommonLogarithm);
        Color.labelTextView(TextViewCommonLogarithm, "log");
        // NTH-LOGARITHM
        ButtonLogarithm = (Button) findViewById(R.id.ButtonLogarithm);
        Color.labelButton(ButtonLogarithm, "log<sub><small>n</small></sub>");
        TextViewLogarithm = (TextView) findViewById(R.id.TextViewLogarithm);
        Color.labelTextView(TextViewLogarithm, "x<sup><small>-1</small></sup>");
        // HYPERBOLIC
        ButtonToggleHyperbolic = (Button) findViewById(R.id.ButtonToggleHyperbolic);
        Color.labelButton(ButtonToggleHyperbolic, "hyp");
        TextViewToggleHyperbolic = (TextView) findViewById(R.id.TextViewToggleHyperbolic);
        // SINE
        ButtonSine = (Button) findViewById(R.id.ButtonSine);
        Color.labelButton(ButtonSine, "sin");
        TextViewSine = (TextView) findViewById(R.id.TextViewSine);
        Color.labelTextView(TextViewSine, "arcsin");
        // COSINE
        ButtonCosine = (Button) findViewById(R.id.ButtonCosine);
        Color.labelButton(ButtonCosine, "cos");
        TextViewCosine = (TextView) findViewById(R.id.TextViewCosine);
        Color.labelTextView(TextViewCosine, "arccos");
        // TANGENT
        ButtonTangent = (Button) findViewById(R.id.ButtonTangent);
        Color.labelButton(ButtonTangent, "tan");
        TextViewTangent = (TextView) findViewById(R.id.TextViewTangent);
        Color.labelTextView(TextViewTangent, "arctan");
        // PI
        ButtonPi = (Button) findViewById(R.id.ButtonPi);
        Color.labelButton(ButtonPi, "π");
        TextViewPi = (TextView) findViewById(R.id.TextViewPi);
        Color.labelTextView(TextViewPi, "e");
        // IMAGINARY
        ButtonImaginary = (Button) findViewById(R.id.ButtonImaginary);
        Color.labelButton(ButtonImaginary, Preferences.IMAGINARY_CHARACTER);
        TextViewImaginary = (TextView) findViewById(R.id.TextViewImaginary);
        Color.labelTextView(TextViewImaginary, "∠", "cis");
        // TOGGLE FRACTION
        ButtonToggleFraction = (Button) findViewById(R.id.ButtonToggleFraction);
        Color.labelButton(ButtonToggleFraction, "");
        TextViewToggleFraction = (TextView) findViewById(R.id.TextViewToggleFraction);
        // MODE
        ButtonMode = (Button) findViewById(R.id.ButtonMode);
        Color.labelButton(ButtonMode, "mode");
        TextViewMode = (TextView) findViewById(R.id.TextViewMode);
        // CONSTANTS
        ButtonConstants = (Button) findViewById(R.id.ButtonConstants);
        Color.labelButton(ButtonConstants, "cnst");
        TextViewConstants = (TextView) findViewById(R.id.TextViewConstants);
        Color.labelTextView(TextViewConstants, "conv");
        // FUNCTIONS
        ButtonFunctions = (Button) findViewById(R.id.ButtonFunctions);
        Color.labelButton(ButtonFunctions, "func");
        TextViewFunctions = (TextView) findViewById(R.id.TextViewFunctions);
        // HISTORY
        ButtonHistory = (Button) findViewById(R.id.ButtonHistory);
        Color.labelButton(ButtonHistory, "hist");
        TextViewHistory = (TextView) findViewById(R.id.TextViewHistory);
        Color.labelTextView(TextViewHistory, "");
        // OPTIONS
        ButtonSettingsMenu = (Button) findViewById(R.id.ButtonSettingsMenu);
        Color.labelButton(ButtonSettingsMenu, "");
        TextViewOptions = (TextView) findViewById(R.id.TextViewOptions);
        // 2ND
        ButtonShift = (Button) findViewById(R.id.ButtonShift);
        Color.labelButton(ButtonShift, "2nd");
        // 3RD
        ButtonAlpha = (Button) findViewById(R.id.ButtonAlpha);
        Color.labelButton(ButtonAlpha, "3rd");
        // CURSOR BACKWARD
        ButtonCursorBackward = (Button) findViewById(R.id.ButtonCursorBackward);
        Color.labelButton(ButtonCursorBackward, "");
        // CURSOR FORWARD
        ButtonCursorForward = (Button) findViewById(R.id.ButtonCursorForward);
        Color.labelButton(ButtonCursorForward, "");
        // BACKSPACE
        ButtonBackspace = (Button) findViewById(R.id.ButtonBackspace);
        Color.labelButton(ButtonBackspace, "");
        // CLEAR
        ButtonClear = (Button) findViewById(R.id.ButtonClear);
        Color.labelButton(ButtonClear, "AC");

        setScale();

        ButtonDecimalPower.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (ButtonDecimalPower.getTextSize() * 0.7));
        ButtonToggleFraction.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) (ButtonDecimalPower.getTextSize() * 0.9));

        SharedPreferences log = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_LOG), Context.MODE_PRIVATE);
        SharedPreferences.Editor logEditor = log.edit();
        logEditor.putInt("USAGE_COUNTER", Preferences.USAGE_COUNTER + 1);
        logEditor.apply();

        if ((Preferences.USAGE_COUNTER % 10 == 0 && Preferences.PLEA_FOR_REVIEW) || Preferences.USAGE_COUNTER == 150)
        {
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable()
            {
                @Override
                public void run()
                {
                    MenuReview menuReview = new MenuReview(context);
                    menuReview.initiateMenu();
                }
            }, 600);
        }


        displayInput.setOnTouchListener(new OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                v.onTouchEvent(event);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        cursorManager.synchronizeAfterTouch(displayInput.getSelectionStart());
                        cursorManager.updateCursor(displayInput);
                    }
                }, 300);
                InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                return true;
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                if (Preferences.WELCOMING_MESSAGE)
                {
                    MenuWelcome menuWelcome = new MenuWelcome(context);
                    menuWelcome.initiateMenu();

                    SharedPreferences settings = Utility.getContext().getSharedPreferences(Utility.getContext().getResources().getString(R.string.SHARED_PREFERENCES_SETTINGS), Context.MODE_PRIVATE);
                    SharedPreferences.Editor settingEditor = settings.edit();
                    Preferences.WELCOMING_MESSAGE = false;
                    settingEditor.putBoolean("WELCOMING_MESSAGE", false);
                    settingEditor.apply();
                }
            }
        }, 600);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        if (requestCode == 1)
        {
            if (resultCode == Activity.RESULT_CANCELED)
            {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        }
    }

    @Override
    public boolean onLongClick(View view)
    {
        shift = true;
        view.performClick();
        return true;
    }

    @Override
    public void onClick(View view)
    {
        Utility.vibrate();
        inputId++;
        switch (view.getId())
        {
            case R.id.ButtonZero:
                if (!shift) apply(Component.FIGURE_ZERO);
                break;
            case R.id.ButtonOne:
                if (!shift) apply(Component.FIGURE_ONE);
                else apply(Component.FIGURE_A);
                break;
            case R.id.ButtonTwo:
                if (!shift) apply(Component.FIGURE_TWO);
                else apply(Component.FIGURE_B);
                break;
            case R.id.ButtonThree:
                if (!shift && !alpha) apply(Component.FIGURE_THREE);
                else if (shift) apply(Component.FIGURE_C);
                break;
            case R.id.ButtonFour:
                if (!shift && !alpha) apply(Component.FIGURE_FOUR);
                else if (shift) apply(Component.FUNCTION_LEAST_COMMON_MULTIPLE);
                else apply(Component.FIGURE_D);
                break;
            case R.id.ButtonFive:
                if (!shift && !alpha) apply(Component.FIGURE_FIVE);
                else if (shift) apply(Component.FUNCTION_GREATEST_COMMON_DIVISOR);
                else apply(Component.FIGURE_E);
                break;
            case R.id.ButtonSix:
                if (!shift) apply(Component.FIGURE_SIX);
                else apply(Component.FIGURE_F);
                break;
            case R.id.ButtonSeven:
                if (!shift && !alpha) apply(Component.FIGURE_SEVEN);
                else if (shift) apply(Component.FUNCTION_FACTORIAL_CONNECTIVE);
                else apply(Component.FUNCTION_DOUBLE_FACTORIAL_CONNECTIVE);
                break;
            case R.id.ButtonEight:
                if (!shift && !alpha) apply(Component.FIGURE_EIGHT);
                else if (shift) apply(Component.FUNCTION_COMBINATION);
                else apply(Component.FUNCTION_PERMUTATION);
                break;
            case R.id.ButtonNine:
                if (!shift && !alpha) apply(Component.FIGURE_NINE);
                else if (shift) apply(Component.FUNCTION_REAL_PART);
                else apply(Component.FUNCTION_IMAGINARY_PART);
                break;
            case R.id.ButtonDot:
                if (!shift) apply(Component.SEPARATOR_DOT);
                //if (shift) apply(Component.SEPARATOR_COMMA);
                break;
            case R.id.ButtonAdd:
                if (!shift && !alpha) apply(Component.OPERATOR_ADD);
                else if (shift) apply(Component.FUNCTION_ABSOLUTE);
                else apply(Component.FUNCTION_CONJUGATE);
                break;
            case R.id.ButtonSubtract:
                if (!shift && !alpha) apply(Component.OPERATOR_SUBTRACT);
                else if (shift) apply(Component.FUNCTION_MODULO);
                else apply(Component.CONSTANT_VARIABLE);
                break;
            case R.id.ButtonMultiply:
                if (!shift && !alpha) apply(Component.OPERATOR_MULTIPLY);
                else if (shift) apply(Component.FUNCTION_DERIVATION);
                else apply(Component.FUNCTION_INTEGRATION);
                break;
            case R.id.ButtonDivide:
                if (!shift && !alpha) apply(Component.OPERATOR_DIVIDE);
                else if (shift) apply(Component.CONSTANT_PERCENT);
                else apply(Component.FUNCTION_PERCENTAGE_CHANGE);
                break;
            case R.id.ButtonSine:
                if (!shift && !hyperbolic) apply(Component.FUNCTION_SINE);
                if (shift && !hyperbolic) apply(Component.FUNCTION_ARCSINE);
                if (!shift && hyperbolic) apply(Component.FUNCTION_HYPERBOLIC_SINE);
                if (shift && hyperbolic) apply(Component.FUNCTION_HYPERBOLIC_AREASINE);
                break;
            case R.id.ButtonCosine:
                if (!shift && !hyperbolic) apply(Component.FUNCTION_COSINE);
                if (shift && !hyperbolic) apply(Component.FUNCTION_ARCCOSINE);
                if (!shift && hyperbolic) apply(Component.FUNCTION_HYPERBOLIC_COSINE);
                if (shift && hyperbolic)
                    apply(Component.FUNCTION_HYPERBOLIC_AREACOSINE);
                break;
            case R.id.ButtonTangent:
                if (!shift && !hyperbolic) apply(Component.FUNCTION_TANGENT);
                if (shift && !hyperbolic) apply(Component.FUNCTION_ARCTANGENT);
                if (!shift && hyperbolic) apply(Component.FUNCTION_HYPERBOLIC_TANGENT);
                if (shift && hyperbolic)
                    apply(Component.FUNCTION_HYPERBOLIC_AREATANGENT);
                break;
            case R.id.ButtonSquare:
                if (!shift)
                {
                    apply(Component.FUNCTION_POW, Component.FIGURE_TWO);
                    cursorManager.cursorPositionForward();
                    cursorManager.updateCursor(displayInput);
                }
                if (shift)
                {
                    apply(Component.FUNCTION_POW, Component.FIGURE_THREE);
                    cursorManager.cursorPositionForward();
                    cursorManager.updateCursor(displayInput);
                }
                break;
            case R.id.ButtonRoot:
                if (!shift) apply(Component.FUNCTION_SQUARE_ROOT);
                if (shift) apply(Component.FUNCTION_CUBIC_ROOT);
                break;
            case R.id.ButtonPower:
                if (!shift)
                {
                    apply(Component.FUNCTION_POW);
                }
                if (shift) apply(Component.FUNCTION_NTH_ROOT);
                break;
            case R.id.ButtonNaturalLogarithm:
                if (!shift) apply(Component.FUNCTION_NATURAL_LOGARITHM);
                if (shift)
                {
                    apply(Component.CONSTANT_EULER_NUMBER);
                    inputId++;
                    apply(Component.FUNCTION_POW);
                }
                break;
            case R.id.ButtonCommonLogarithm:
                if (!shift) apply(Component.OPERATOR_FRACTION);
                else apply(Component.FUNCTION_COMMON_LOGARITHM);
                break;
            case R.id.ButtonLogarithm:
                if (!shift) apply(Component.FUNCTION_NTH_LOGARITHM);
                if (shift)
                    apply(Component.FUNCTION_POW);
                break;
            case R.id.ButtonImaginary:
                if ((!shift && !alpha)) apply(Component.CONSTANT_IMAGINARY);
                else if (shift) apply(Component.FUNCTION_POLAR);
                else apply(Component.FUNCTION_CIS);
                break;
            case R.id.ButtonPi:
                if (!shift) apply(Component.CONSTANT_PI);
                if (shift) apply(Component.CONSTANT_EULER_NUMBER);
                break;
            case R.id.ButtonBracketOpen:
                if (!shift) apply(Component.BRACKET_OPEN);
                else apply(Component.FUNCTION_SIGNUM);
                break;
            case R.id.ButtonBracketClose:
                if (!shift && !alpha) apply(Component.BRACKET_CLOSE);
                else if (shift) apply(Component.FUNCTION_RANDOM_NUMBER);
                else apply(Component.FUNCTION_RANDOM_INTEGER);
                break;
            case R.id.ButtonDecimalPower:
                if (!shift)
                {
                    apply(Component.OPERATOR_MULTIPLY);
                    inputId++;
                    apply(Component.FIGURE_ONE);
                    inputId++;
                    apply(Component.FIGURE_ZERO);
                    inputId++;
                    apply(Component.FUNCTION_POW);
                } else
                {
                    mPref.initiateMenu();
                    mPref.menu.setOnDismissListener(new PopupWindow.OnDismissListener()
                    {
                        public void onDismiss()
                        {
                            if (externallyAddedElement != Component.VOID)
                            {
                                apply(externallyAddedElement);
                                externallyAddedElement = Component.VOID;
                            }
                        }
                    });
                }
                clearModes();
                break;
            case R.id.ButtonToggleSign:
                if (!shift)
                {
                    if (parseManager.getComponent(cursorManager.getElementPosition() - 1).equals(Component.NEGATIVE_SIGN))
                    {
                        cursorManager.cursorPositionBackward();
                        cursorManager.updateCursor(displayInput);
                        parseManager.removeElement(cursorManager.getElementPosition());
                    } else
                        apply(Component.NEGATIVE_SIGN);
                }
                break;
            case R.id.ButtonShift:
                shiftOn();
                break;
            case R.id.ButtonAlpha:
                alphaOn();
                break;
            case R.id.ButtonToggleHyperbolic:
                if (!hyperbolic) hyperbolicOn();
                else hyperbolicOff();
                break;
            case R.id.ButtonEnter:
                if (!shift)
                {
                    try
                    {
                        Calculation.setData(parseManager.getData());
                        Calculation.executeCalculation();
                        if (globalError == Error.VOID)
                        {
                            printResult();
                        } else displayOutput.setText(globalError.getMessage());

                    } catch (Exception e)
                    {
                        displayOutput.setText(globalError.getMessage());
                    }
                    displayInput.setEnabled(false);
                    historyManager.addToHistory(printManager.getFinalOutput(), Shaper.getFinalOutput(Calculation.getResult(), Preferences.MODE_NOTATION, Preferences.MODE_NOTATION_ALTERNATIVE), Calculation.getResult());
                    executed = true;
                } else
                {
                    answerId = History.getNumberOfElements();
                    apply(Component.ANSWER_LAST);
                }
                break;
            case R.id.ButtonBackspace:
                backspace();
                performInterimCalculation();
                break;
            case R.id.ButtonClear:
                clear();
                break;
            case R.id.ButtonCursorForward:
                cursorManager.cursorPositionForward();
                cursorManager.updateCursor(displayInput);
                break;
            case R.id.ButtonCursorBackward:
                cursorManager.cursorPositionBackward();
                cursorManager.updateCursor(displayInput);
                break;
            case R.id.ButtonMode:
                mMode.initiatePopupWindow();
                mMode.popupMode.setOnDismissListener(new PopupWindow.OnDismissListener()
                {
                    public void onDismiss()
                    {
                        setModeBarText();
                        if (!displayOutput.getText().equals(""))
                            displayOutput.setText(Utility.fromHtml(Shaper.getFinalOutput(Calculation.getResult(), Preferences.MODE_NOTATION, Preferences.MODE_NOTATION_ALTERNATIVE)));
                    }
                });
                break;
            case R.id.ButtonToggleFraction:
                if (!displayOutput.getText().equals("")) toggleFraction();
                break;
            case R.id.ButtonConstants:
                if (!shift)
                {
                    mConst.initiateMenu();
                    mConst.menu.setOnDismissListener(new PopupWindow.OnDismissListener()
                    {
                        public void onDismiss()
                        {
                            if (externallyAddedElement != Component.VOID)
                            {
                                apply(externallyAddedElement);
                                externallyAddedElement = Component.VOID;
                            }
                        }
                    });
                } else
                {

                    // find to be converted value
                    final int[] startAndStop = parseManager.findConversionRange(cursorManager.getElementPosition());

                    BigDecimal value = findConversion(startAndStop);

                    mConv.setValue(value);
                    mConv.initiatePopupWindow();
                    mConv.popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
                    {
                        public void onDismiss()
                        {
                            if (convertedValue != null)
                            {
                                replaceWithConversion(convertedValue, startAndStop);
                                convertedValue = null;
                            }
                        }
                    });
                }
                clearModes();
                break;
            case R.id.ButtonFunctions:
                mFunc.initiateMenu();
                mFunc.menu.setOnDismissListener(new PopupWindow.OnDismissListener()
                {
                    public void onDismiss()
                    {
                        if (externallyAddedElement != Component.VOID)
                        {
                            apply(externallyAddedElement);
                            externallyAddedElement = Component.VOID;
                        }
                    }
                });
                clearModes();
                break;
            case R.id.ButtonHistory:
                if (!shift)
                {
                    mHist.initiateMenu();
                    mHist.menu.setOnDismissListener(new PopupWindow.OnDismissListener()
                    {
                        public void onDismiss()
                        {
                            if (externallyAddedElement != Component.VOID)
                            {
                                apply(externallyAddedElement);
                                externallyAddedElement = Component.VOID;
                            }
                        }
                    });
                }
                clearModes();
                break;
            case R.id.ButtonSettingsMenu:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, 1);
                break;
            default:
                break;
        }
    }

    private void shiftOn()
    {
        if (shift)
        {
            shift = false;
            barFunction.setText("");
        } else
        {
            alpha = false;
            shift = true;
            barFunction.setText("2nd");
        }
    }

    private void alphaOn()
    {
        if (alpha)
        {
            alpha = false;
            barFunction.setText("");
        } else
        {
            shift = false;
            alpha = true;
            barFunction.setText("3rd");
        }
    }

    private void clearModes()
    {
        alpha = false;
        shift = false;
        hyperbolicOff();
        barFunction.setText("");
    }

    private void hyperbolicOn()
    {
        Color.labelButton(ButtonSine, "sinh");
        Color.labelTextView(TextViewSine, "arsinh");
        Color.labelButton(ButtonCosine, "cosh");
        Color.labelTextView(TextViewCosine, "arcosh");
        Color.labelButton(ButtonTangent, "tanh");
        Color.labelTextView(TextViewTangent, "artanh");
        hyperbolic = true;
    }

    private void hyperbolicOff()
    {
        Color.labelButton(ButtonSine, "sin");
        Color.labelTextView(TextViewSine, "arcsin");
        Color.labelButton(ButtonCosine, "cos");
        Color.labelTextView(TextViewCosine, "arccos");
        Color.labelButton(ButtonTangent, "tan");
        Color.labelTextView(TextViewTangent, "arctan");
        hyperbolic = false;
    }

    private void apply(Component... component)
    {

        if (executed) clear();

        if (Check.isOperator(component[0]) && cursorManager.getElementPosition() == 0 && component[0] != Component.OPERATOR_FRACTION)
        {
            answerId = History.getNumberOfElements();
            apply(Component.ANSWER_LAST);
        }

        if (InputControl.validSubmission(component[0], parseManager.getComponent(cursorManager.getElementPosition() - 1)))
        {

            for (Component c : component)
            {
                if (c == Component.FUNCTION_POW)
                {
                    completeExponentialFunction();
                    inputId++;
                } else if (c == Component.OPERATOR_FRACTION)
                {
                    createFraction();
                    inputId++;
                } else
                {

                    Element element = new Element(c, inputId);
                    if (c == Component.ANSWER || c == Component.ANSWER_LAST)
                        element.setAnswerId(answerId);
                    parseManager.insertElement(element, cursorManager.getElementPosition());

                    printManager.setScript(parseManager.getScript());
                    printManager.printFinalOutput(displayInput);

                    cursorManager.updateElementList(printManager.getScript());
                    cursorManager.cursorPositionOneForward();

                    if (Check.isFunction(c) || Check.isAdvancedFunction(c))
                    {
                        completeFunction(c);
                        cursorManager.setElementPositionManually(parseManager.getPreviousFunctionIndex(cursorManager.getElementPosition()) + 1);
                    }
                }
            }

            cursorManager.updateCursor(displayInput);
            performInterimCalculation();

        }
        clearModes();
    }


    private void performInterimCalculation()
    {
        if (Preferences.INTERIM_RESULTS)
        {
            try
            {
                Calculation.setData(parseManager.getData());
                Calculation.executeCalculation();
                printResult();
                if (displayInput.getText().length() == 0) clear();
            } catch (Exception e)
            {
                displayOutput.setText(null);
            }
            globalError = Error.VOID;
        }
    }

    private void createFraction()
    {

        // declare position as it remains untouched from outside
        int pos = cursorManager.getElementPosition();
        // find out if the numerator has already been entered

        /*
        boolean numeratorExists = pos > 0
                && !Check.isOperator(parseManager.getComponent(pos - 1))
                && !Check.isFunction(parseManager.getComponent(pos - 1))
                && parseManager.getComponent((pos - 1)) != Component.SEPARATOR_COMMA;
                */

        // set fraction start position
        int fractionStart = pos;
        //if (numeratorExists) fractionStart = parseManager.getFractionStart(pos);

        // set cursor position (will be set after all components are added)
        int cursorElementIndex = pos + 1;
        //if (numeratorExists) cursorElementIndex = pos + 4;


        // first opening bracket
        Element firstOpeningBracket = new Element(Component.BRACKET_OPEN_FRACTION1, inputId, Element.OpticFeatureScript.SUPERSCRIPT_START, Element.OpticFeatureSize.SMALL_START);
        parseManager.insertElement(firstOpeningBracket, fractionStart);
        pos++;

        //if (!numeratorExists) {
        // phantom one
        Element phantomOne = new Element(Component.PHANTOM_A, inputId);
        parseManager.insertElement(phantomOne, pos);
        pos++;
        //}

        // first closing bracket
        Element firstClosingBracket = new Element(Component.BRACKET_CLOSE_FRACTION1, inputId, Element.OpticFeatureScript.SUPERSCRIPT_STOP, Element.OpticFeatureSize.SMALL_STOP);
        parseManager.insertElement(firstClosingBracket, pos);
        pos++;

        // operator
        Element operator = new Element(Component.OPERATOR_FRACTION, inputId);
        parseManager.insertElement(operator, pos);
        pos++;

        // second opening bracket
        Element secondOpeningBracket = new Element(Component.BRACKET_OPEN_FRACTION2, inputId, Element.OpticFeatureScript.SUBSCRIPT_START, Element.OpticFeatureSize.SMALL_START);
        parseManager.insertElement(secondOpeningBracket, pos);
        pos++;

        // phantom one
        Element phantomTwo = new Element(Component.PHANTOM_B, inputId);
        parseManager.insertElement(phantomTwo, pos);
        pos++;

        // second closing bracket
        Element secondClosingBracket = new Element(Component.BRACKET_CLOSE_FRACTION2, inputId, Element.OpticFeatureScript.SUBSCRIPT_STOP, Element.OpticFeatureSize.SMALL_STOP);
        parseManager.insertElement(secondClosingBracket, pos);

        printManager.setScript(parseManager.getScript());
        printManager.printFinalOutput(displayInput);

        cursorManager.updateElementList(printManager.getScript());
        cursorManager.setElementPositionManually(cursorElementIndex);
    }

    /**
     * Removes the object before the cursor.
     */
    private void backspace()
    {

        // set position of the element that is to be removed, return if pos is zero
        int pos = cursorManager.getElementPosition();
        if (pos > 0) pos--;
        else return;

        cursorManager.cursorPositionBackward();

        /*
         * Special case: Fractions
         * Check for opening fraction bracket and the fraction component before it.
         *
         *
         */
        if (pos > 0 && parseManager.getComponent(pos) == Component.BRACKET_OPEN_FRACTION2)
        {
            pos--;

            boolean emptyNumerator = parseManager.emptyNumerator(pos);
            if (emptyNumerator) cursorManager.setElementPositionManually(pos - 3);
            else cursorManager.setElementPositionManually(pos - 2);
        }

        /*
         * Special case: Exponent
         * Cursor needs to be incremented by one if an exponent is deleted.
         */
        if (pos > 0 && parseManager.getComponent(pos) == Component.FUNCTION_POW)
        {
            parseManager.removeElement(pos);
            cursorManager.cursorPositionForward();
        } else parseManager.removeElement(pos); // default case

        printManager.setScript(parseManager.getScript());
        printManager.printFinalOutput(displayInput);
        cursorManager.updateCursor(displayInput);
    }

    private void completeFunction(Component comp)
    {

        // skip to the previous function's phantom
        printManager.setScript(parseManager.getScript());
        cursorManager.updateElementList(printManager.getScript());

        List<Component> phantomList = comp.getPhantom();
        int phantomListSize = phantomList.size();

        // loop through the function's phantom list
        for (int i = 0; i < comp.getPhantom().size(); i++)
        {

            // insert phantom by index
            apply(phantomList.get(i));

            // insert comma if there are phantoms to come
            if (i < phantomListSize - 1)
            {
                apply(Component.SEPARATOR_COMMA);
            }
        }
        apply(Component.BRACKET_CLOSE_FUNCTION);
    }

    private void completeExponentialFunction()
    {
        Element element1 = new Element(Component.FUNCTION_POW, inputId, Element.OpticFeatureScript.SUPERSCRIPT_START, Element.OpticFeatureSize.SMALL_START);
        parseManager.insertElement(element1, cursorManager.getElementPosition());
        printManager.setScript(parseManager.getScript());
        printManager.printFinalOutput(displayInput);
        cursorManager.updateElementList(printManager.getScript());
        cursorManager.cursorPositionOneForward();

        Element element2 = new Element(Component.FUNCTION_POW.getPhantom().get(0), inputId);
        parseManager.insertElement(element2, cursorManager.getElementPosition());
        printManager.setScript(parseManager.getScript());
        printManager.printFinalOutput(displayInput);
        cursorManager.updateElementList(printManager.getScript());
        cursorManager.cursorPositionOneForward();

        Element element3 = new Element(Component.BRACKET_CLOSE_POWER_FUNCTION, inputId, Element.OpticFeatureScript.SUPERSCRIPT_STOP, Element.OpticFeatureSize.SMALL_STOP);
        parseManager.insertElement(element3, cursorManager.getElementPosition());
        printManager.setScript(parseManager.getScript());
        printManager.printFinalOutput(displayInput);
        cursorManager.updateElementList(printManager.getScript());
        cursorManager.cursorPositionOneForward();

        cursorManager.cursorPositionBackward();
    }

    private void toggleFraction()
    {
        if (parseManager.getScript().size() != 0)
        {
            Fractions.toggleOutput();
            printResult();
        } else
        {
            Toast toast = Toast.makeText(this, getResources().getString(R.string.TOAST_TOGGLE_OUTPUT_ERROR1), Toast.LENGTH_SHORT);
            toast.show();
        }

    }

    private void clear()
    {
        Calculation = new Engine();
        parseManager = new Parser();
        printManager = new Printer();
        cursorManager = new Cursor();
        displayInput.setText(null);
        displayInput.setEnabled(true);
        displayOutput.setText(null);
        globalError = Error.VOID;
        externallyAddedElement = Component.VOID;
        answerId = 0;
        executed = false;
    }

    private void printResult()
    {
        String output = Shaper.getFinalOutput(Calculation.getResult(), Preferences.MODE_NOTATION, Preferences.MODE_NOTATION_ALTERNATIVE);
        if (output.contains("ERROR"))
        {
            toggleFraction();
        } else displayOutput.setText(Utility.fromHtml(output));
    }

    private void setScale()
    {
        ArrayList<View> keys = childrenArray(linearLayoutKeyboard);
        ArrayList<View> modi = childrenArray(LinearLayoutModes);

        Scale.everything(relativeLayout);

        for (View child : keys)
        {
            if (child.getTag() != null)
            {
                switch (child.getTag().toString())
                {
                    case "bigButton":
                        Scale.largeButton((Button) child);
                        child.setOnClickListener(this);
                        child.setOnLongClickListener(this);
                        ((Button) child).setShadowLayer(10, 0, 0, ContextCompat.getColor(this, R.color.GLOW));
                        break;
                    case "bigTextView":
                        Scale.largeTextView((TextView) child);
                        break;
                    case "smallButton":
                        Scale.smallButton((Button) child);
                        child.setOnClickListener(this);
                        child.setOnLongClickListener(this);
                        ((Button) child).setShadowLayer(10, 0, 0, ContextCompat.getColor(this, R.color.GLOW));
                        break;
                    case "smallTextView":
                        Scale.smallTextView((TextView) child);
                        break;
                    default:
                        break;
                }
            }
        }

        for (View child : modi)
        {
            if (child instanceof TextView) Scale.modeDisplay((TextView) child);
        }

        Scale.outputLinearLayout(LinearLayoutOutput);
        Scale.ouputDisplay(displayOutput);
        Scale.inputDisplay(displayInput);

        // COMPENSATE FOR ROUNDING

        if (screenWidth > screenHeight)
        {
            barAngularMode.getLayoutParams().width = screenWidth
                    - Metrics.PADDING_SIDES.getValue() * 2
                    - Metrics.DISPLAY_MARGIN_SIDES.getValue() * 2
                    - (Metrics.TEXTVIEW_MODE_COUNT.getValue() - 1) * Metrics.TEXTVIEW_MODE_WIDTH.getValue();
        } else
        {
            barAngularMode.getLayoutParams().width = Metrics.SCREEN_WIDTH.getValue()
                    - Metrics.DISPLAY_MARGIN_SIDES.getValue() * 2
                    - (Metrics.TEXTVIEW_MODE_COUNT.getValue() - 1) * Metrics.TEXTVIEW_MODE_WIDTH.getValue();
        }
    }

    private ArrayList<View> childrenArray(View v)
    {
        if (!(v instanceof ViewGroup))
        {
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++)
        {
            View child = vg.getChildAt(i);
            ArrayList<View> viewArrayList = new ArrayList<>();
            viewArrayList.add(v);
            viewArrayList.addAll(childrenArray(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

    void computeScreenMetrics()
    {
        int statusBarHeight = 0;
        if (!Preferences.FULLSCREEN)
        {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0)
            {
                statusBarHeight = getResources().getDimensionPixelSize(resourceId);
            }
        }

        // get screen measurements
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels - statusBarHeight;
    }

    void toggleFullScreen()
    {
        if (Preferences.FULLSCREEN)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        } else
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void setModeBarText()
    {
        barAngularMode.setText(Preferences.MODE_ANGULAR.getModeBarText());
        barNumeralMode.setText(Preferences.MODE_NUMERAL.getModeBarText());
        barStructure.setText(Preferences.MODE_COMPLEX.getModeBarText());
        barNotation.setText(Preferences.MODE_NOTATION.getModeBarText());
    }

    private BigDecimal findConversion(int[] startAndStop)
    {
        if (startAndStop.length == 0) return BigDecimal.ZERO;
        else if (startAndStop[0] == startAndStop[1])
            return new BigDecimal(parseManager.getComponent(startAndStop[0]).getSymbol());
        else
        {
            String val = "";
            for (int i = startAndStop[0]; i <= startAndStop[1]; i++)
            {
                val += parseManager.getComponent(i).getSymbol();
            }
            return new BigDecimal(val);
        }
    }

    private void replaceWithConversion(BigDecimal value, int[] startAndStop)
    {
        if (startAndStop.length == 2)
        {
            cursorManager.setElementPositionManually(startAndStop[1] + 1);
            for (int i = 0; i < startAndStop[1] - startAndStop[0]; i++)
            {
                backspace();
            }
        }


        if (value.signum() == -1) apply(Component.NEGATIVE_SIGN);
        value = value.abs();

        boolean engineeringString = false;
        String s;
        value = value.round(new MathContext(14, RoundingMode.HALF_UP)).stripTrailingZeros();
        if (value.compareTo(new BigDecimal("1E-5")) < 0 || value.compareTo(new BigDecimal("1E5")) > 0)
        {
            s = value.toEngineeringString();
            engineeringString = true;
        } else s = value.toPlainString();

        for (int i = 0; i < s.length() && s.charAt(i) != 'E'; i++)
        {
            switch (s.charAt(i))
            {
                case '0':
                    apply(Component.FIGURE_ZERO);
                    break;
                case '1':
                    apply(Component.FIGURE_ONE);
                    break;
                case '2':
                    apply(Component.FIGURE_TWO);
                    break;
                case '3':
                    apply(Component.FIGURE_THREE);
                    break;
                case '4':
                    apply(Component.FIGURE_FOUR);
                    break;
                case '5':
                    apply(Component.FIGURE_FIVE);
                    break;
                case '6':
                    apply(Component.FIGURE_SIX);
                    break;
                case '7':
                    apply(Component.FIGURE_SEVEN);
                    break;
                case '8':
                    apply(Component.FIGURE_EIGHT);
                    break;
                case '9':
                    apply(Component.FIGURE_NINE);
                    break;
                case '.':
                    apply(Component.SEPARATOR_DOT);
                    break;
                default:
                    break;
            }
        }

        // possible second run
        if (engineeringString)
        {
            apply(Component.OPERATOR_MULTIPLY, Component.FIGURE_ONE, Component.FIGURE_ZERO, Component.FUNCTION_POW);
            int index = s.indexOf("E") + 1;
            if (s.charAt(index) == '-')
            {
                apply(Component.NEGATIVE_SIGN);
                index++;
            }
            for (int i = index; i < s.length(); i++)
            {
                switch (s.charAt(i))
                {
                    case '0':
                        apply(Component.FIGURE_ZERO);
                        break;
                    case '1':
                        apply(Component.FIGURE_ONE);
                        break;
                    case '2':
                        apply(Component.FIGURE_TWO);
                        break;
                    case '3':
                        apply(Component.FIGURE_THREE);
                        break;
                    case '4':
                        apply(Component.FIGURE_FOUR);
                        break;
                    case '5':
                        apply(Component.FIGURE_FIVE);
                        break;
                    case '6':
                        apply(Component.FIGURE_SIX);
                        break;
                    case '7':
                        apply(Component.FIGURE_SEVEN);
                        break;
                    case '8':
                        apply(Component.FIGURE_EIGHT);
                        break;
                    case '9':
                        apply(Component.FIGURE_NINE);
                        break;
                    default:
                        break;
                }
            }
            cursorManager.cursorPositionForward();
            cursorManager.updateCursor(displayInput);
        }
    }
}

