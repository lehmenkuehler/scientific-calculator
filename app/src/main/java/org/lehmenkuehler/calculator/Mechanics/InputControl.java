package org.lehmenkuehler.calculator.Mechanics;

import android.widget.Toast;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.Utility;

public class InputControl
{
    private static String toastMessage;

    public static boolean validSubmission(Component comp, Component prev)
    {
        toastMessage = "SYNTAX ERROR";
        if (analyze(comp, prev)) return true;
        else
        {
            Toast toast = Toast.makeText(Utility.getContext(), toastMessage, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
    }

    private static boolean analyze(Component comp, Component prev)
    {
        if (comp == Component.OPERATOR_FRACTION) return true;
        switch (comp.getType())
        {
            case FIGURE:
                return figure(comp, prev);
            case OPERATOR:
                return operator(prev);
            case CONSTANT:
                return constant(prev);
            case FUNCTION:
                return function(prev);
            case CONNECTIVE_FUNCTION:
                return connectiveFunction(prev);
            case ADVANCED_FUNCTION:
                return advancedFunction(prev);
            case BRACKET_OPEN:
                return bracketOpen(prev);
            case BRACKET_CLOSE:
                return bracketClose(prev);
            case VARIABLE:
                return variable(prev);
            case DOT:
                return dot(prev);
            case COMMA:
                return comma(prev);
            case SIGN:
                return sign(prev);
            default:
                return true;
        }
    }

    private static boolean figure(Component comp, Component prev)
    {

        if (Preferences.MODE_NUMERAL == Preferences.NumeralMode.DEC)
        {
            if (!Check.isFigureDEC(comp))
            {
                toastMessage = comp.getSymbol() + " DOES NOT EXIST IN DEC";
                return false;
            }
        } else if ((Preferences.MODE_NUMERAL == Preferences.NumeralMode.BIN))
        {
            if (!Check.isFigureBIN(comp))
            {
                toastMessage = comp.getSymbol() + " DOES NOT EXIST IN BIN";
                return false;
            }
        } else if ((Preferences.MODE_NUMERAL == Preferences.NumeralMode.OCT))
        {
            if (!Check.isFigureOCT(comp))
            {
                toastMessage = comp.getSymbol() + " DOES NOT EXIST IN OCT";
                return false;
            }
        }

        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return true;
            case COMMA:
                return true;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean operator(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return false;
            case FIGURE:
                return true;
            case OPERATOR:
                return false;
            case CONSTANT:
                return true;
            case FUNCTION:
                return false;
            case CONNECTIVE_FUNCTION:
                return false;
            case ADVANCED_FUNCTION:
                return false;
            case BRACKET_OPEN:
                return false;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

    private static boolean constant(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return true;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean function(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return true;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean advancedFunction(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return true;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean connectiveFunction(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return false;
            case FIGURE:
                return true;
            case OPERATOR:
                return false;
            case CONSTANT:
                return true;
            case FUNCTION:
                return false;
            case CONNECTIVE_FUNCTION:
                return false;
            case ADVANCED_FUNCTION:
                return false;
            case BRACKET_OPEN:
                return false;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

    private static boolean bracketOpen(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean bracketClose(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return false;
            case FIGURE:
                return true;
            case OPERATOR:
                return false;
            case CONSTANT:
                return true;
            case FUNCTION:
                return false;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return false;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

    private static boolean variable(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return true;
            case OPERATOR:
                return true;
            case CONSTANT:
                return true;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return true;
            case SIGN:
                return true;
            default:
                return true;
        }
    }

    private static boolean dot(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return false;
            case FIGURE:
                return true;
            case OPERATOR:
                return false;
            case CONSTANT:
                return false;
            case FUNCTION:
                return false;
            case CONNECTIVE_FUNCTION:
                return false;
            case ADVANCED_FUNCTION:
                return false;
            case BRACKET_OPEN:
                return false;
            case BRACKET_CLOSE:
                return false;
            case VARIABLE:
                return false;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

    private static boolean comma(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return false;
            case FIGURE:
                return true;
            case OPERATOR:
                return false;
            case CONSTANT:
                return true;
            case FUNCTION:
                return false;
            case CONNECTIVE_FUNCTION:
                return false;
            case ADVANCED_FUNCTION:
                return false;
            case BRACKET_OPEN:
                return false;
            case BRACKET_CLOSE:
                return true;
            case VARIABLE:
                return true;
            case DOT:
                return false;
            case COMMA:
                return false;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

    private static boolean sign(Component prev)
    {
        switch (prev.getType())
        {
            case VOID:
                return true;
            case FIGURE:
                return false;
            case OPERATOR:
                return true;
            case CONSTANT:
                return false;
            case FUNCTION:
                return true;
            case CONNECTIVE_FUNCTION:
                return true;
            case ADVANCED_FUNCTION:
                return true;
            case BRACKET_OPEN:
                return true;
            case BRACKET_CLOSE:
                return false;
            case VARIABLE:
                return false;
            case DOT:
                return false;
            case COMMA:
                return true;
            case SIGN:
                return false;
            default:
                return true;
        }
    }

}
