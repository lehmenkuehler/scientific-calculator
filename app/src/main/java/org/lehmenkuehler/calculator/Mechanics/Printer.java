package org.lehmenkuehler.calculator.Mechanics;

import android.support.v4.content.ContextCompat;
import android.widget.EditText;

import java.util.List;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public class Printer
{
    private static final String colorFunctions = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_FUNCTIONS));
    private static final String colorConstants = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_CONSTANTS));
    private static final String colorFigures = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_FIGURES));
    private static final String colorPhantoms = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_PHANTOMS));
    private static final String colorOperators = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_OPERATORS));
    private static final String colorBrackets = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_BRACKETS));
    private static final String color = String.format("#%06X", 0xFFFFFF & ContextCompat.getColor(Utility.getContext(), R.color.DISPLAY_INPUT_GENERAL));

    private List<Element> script;
    private String finalOutput;

    public Printer()
    {
    }

    public List<Element> getScript()
    {
        return script;
    }


    public void setScript(List<Element> elements)
    {
        script = elements;
    }

    public String getFinalOutput()
    {
        return finalOutput;
    }

    public void printFinalOutput(EditText e)
    {
        buildFinalOutput();
        e.setText(Utility.fromHtml(finalOutput));
    }

    private void buildFinalOutput()
    {
        finalOutput = "";
        for (Element e : script)
        {

            switch (e.getOpticFeatureScript())
            {
                case SUBSCRIPT_START:
                    finalOutput += "<sub>";
                    break;
                case SUPERSCRIPT_START:
                    finalOutput += "<sup>";
                    break;
                default:
                    break;
            }

            if (e.getOpticFeatureSize() == Element.OpticFeatureSize.SMALL_START)
                finalOutput += "<small>";

            if (Check.isFunction(e.getComponent()) || Check.isConnectiveFunction(e.getComponent()))
            {
                finalOutput += "<font color=" + colorFunctions + ">" + e.getComponent().getSymbol() + "</font>";
            } else if (Check.isBracket(e.getComponent()))
            {
                if (Check.isPureBracket(e.getComponent()))
                {
                    finalOutput += "<font color=" + colorBrackets + ">" + e.getComponent().getSymbol() + "</font>";
                } else
                    finalOutput += "<font color=" + colorFunctions + ">" + e.getComponent().getSymbol() + "</font>";
            } else if (Check.isPhantom(e.getComponent()))
            {
                finalOutput += "<font color=" + colorPhantoms + ">" + e.getComponent().getSymbol() + "</font>";
            } else if (Check.isConstant(e.getComponent()) && e.getComponent() != Component.ANSWER)
            {
                finalOutput += "<font color=" + colorConstants + ">" + e.getComponent().getSymbol() + "</font>";
            } else if (Check.isOperator(e.getComponent()))
            {
                finalOutput += "<font color=" + colorOperators + ">" + e.getComponent().getSymbol() + "</font>";
            } else if (Check.isFigure(e.getComponent()))
            {
                finalOutput += "<font color=" + colorFigures + ">" + e.getComponent().getSymbol() + "</font>";
            } else
            {
                finalOutput += "<font color=" + color + ">" + e.getComponent().getSymbol() + "</font>";
                if (e.getComponent() == Component.ANSWER)
                {
                    String answerId = String.valueOf(e.getAnswerId());
                    if (e.getAnswerId() < 10) answerId = "0" + e.getAnswerId();
                    finalOutput += "<font color=" + color + "><sub><small>" + answerId + "</small></sub></font>";
                }
            }

            if (e.getOpticFeatureSize() == Element.OpticFeatureSize.SMALL_STOP)
                finalOutput += "</small>";

            switch (e.getOpticFeatureScript())
            {
                case SUBSCRIPT_STOP:
                    finalOutput += "</sub>";
                    break;
                case SUPERSCRIPT_STOP:
                    finalOutput += "</sup>";
                    break;
                default:
                    break;
            }
        }
    }
}
