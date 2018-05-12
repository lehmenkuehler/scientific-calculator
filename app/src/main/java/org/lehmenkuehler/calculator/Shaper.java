package org.lehmenkuehler.calculator;

import java.math.BigDecimal;

import org.lehmenkuehler.calculator.Mechanics.Fractions;
import org.lehmenkuehler.calculator.Mechanics.Result;

public class Shaper
{
    public static String getFinalOutput(Result answer, Preferences.NotationMode notationMode, Preferences.NotationMode alternativeNotationMode)
    {
        String real = "", imaginary = "", radius = "", angle;
        String output;
        if (Preferences.MODE_NUMERAL == Preferences.NumeralMode.DEC)
        {
            if (Preferences.MODE_COMPLEX == Preferences.ComplexMode.RECT)
            {
                if ((answer.getRe().signum() != 0) && (answer.getRe().abs().compareTo(
                        new BigDecimal("1E" + String.valueOf(Preferences.NOTATION_THRESHOLD))) > 0 ||
                        answer.getRe().abs().compareTo(new BigDecimal("1E-" + String.valueOf(Preferences.NOTATION_THRESHOLD))) < 0))
                {
                    if (alternativeNotationMode == Preferences.NotationMode.SCI)
                        real = buildNotationSCI(answer.getRe());
                    else if (alternativeNotationMode == Preferences.NotationMode.ENG)
                        real = buildNotationENG(answer.getRe());
                    else real = buildNotationDEC(answer.getRe());
                }
                if ((answer.getIm().signum() != 0) && (answer.getIm().abs().compareTo(
                        new BigDecimal("1E" + String.valueOf(Preferences.NOTATION_THRESHOLD))) > 0 ||
                        answer.getIm().abs().compareTo(new BigDecimal("1E-" + String.valueOf(Preferences.NOTATION_THRESHOLD))) < 0))
                {
                    if (alternativeNotationMode == Preferences.NotationMode.SCI)
                        imaginary = buildNotationSCI(answer.getRe());
                    else if (alternativeNotationMode == Preferences.NotationMode.ENG)
                        imaginary = buildNotationENG(answer.getRe());
                    else imaginary = buildNotationDEC(answer.getRe());
                }
                switch (notationMode)
                {
                    case DEC:
                        if (real.equals("")) real = buildNotationDEC(answer.getRe());
                        if (imaginary.equals("")) imaginary = buildNotationDEC(answer.getIm());
                        break;
                    case SCI:
                        real = buildNotationSCI(answer.getRe());
                        imaginary = buildNotationSCI(answer.getIm());
                        break;
                    case ENG:
                        real = buildNotationENG(answer.getRe());
                        imaginary = buildNotationENG(answer.getIm());
                        break;
                }
                output = buildRect(answer, real, imaginary);
            } else
            {
                if (answer.getRe().compareTo(new BigDecimal("1E12")) > 0 || answer.getIm().compareTo(new BigDecimal("1E12")) > 0)
                    radius = buildNotationENG(answer.getRadius());
                else
                {
                    switch (notationMode)
                    {
                        case DEC:
                            radius = buildNotationDEC(answer.getRadius());
                            break;
                        case SCI:
                            radius = buildNotationSCI(answer.getRadius());
                            break;
                        case ENG:
                            radius = buildNotationENG(answer.getRadius());
                            break;
                    }
                }
                if (answer.getAngle().compareTo(BigDecimal.ZERO) == 0) angle = "0";
                else
                    angle = answer.getAngle().setScale(Preferences.PRECISION, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
                output = buildPolar(answer, radius, angle);
            }
        } else output = buildSpecialNumeralSystem(answer);
        return output;
    }

    static private String buildNotationDEC(BigDecimal x)
    {
        return Fractions.tryConvert(x, Preferences.PRECISION);
    }

    static private String buildNotationSCI(BigDecimal x)
    {
        int power = 0;
        x = x.abs();
        if (x.compareTo(BigDecimal.ZERO) == 0)
        {
            return "0";
        } else if (x.compareTo(BigDecimal.ONE) < 0)
        {
            while (x.compareTo(BigDecimal.ONE) < 0)
            {
                x = x.movePointRight(1);
                power--;
            }
            return Fractions.tryConvert(x, Preferences.SCIENTIFIC_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        } else if (x.compareTo(BigDecimal.TEN) > 0)
        {
            do
            {
                x = x.movePointLeft(1);
                power++;
            } while (x.compareTo(BigDecimal.TEN) > 0);
            return Fractions.tryConvert(x, Preferences.SCIENTIFIC_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        } else
        {
            power = 0;
            return Fractions.tryConvert(x, Preferences.SCIENTIFIC_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        }
    }

    static private String buildNotationENG(BigDecimal x)
    {
        int power = 0;
        x = x.abs();
        if (x.compareTo(BigDecimal.ZERO) == 0)
        {
            return "0";
        } else if (x.compareTo(BigDecimal.ONE) < 0)
        {
            while (x.compareTo(BigDecimal.ONE) < 0)
            {
                x = x.movePointRight(1);
                power--;
            }
            while ((power * -1) % 3 != 0)
            {
                x = x.movePointRight(1);
                power--;
            }
            return Fractions.tryConvert(x, Preferences.ENGINEERING_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        } else if (x.compareTo(new BigDecimal("1000")) >= 0)
        {
            while (x.compareTo(new BigDecimal("1000")) >= 0)
            {
                x = x.movePointLeft(1);
                power++;
            }
            while (power % 3 != 0)
            {
                x = x.movePointLeft(1);
                power++;
            }
            return Fractions.tryConvert(x, Preferences.ENGINEERING_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        } else
        {
            power = 0;
            return Fractions.tryConvert(x, Preferences.ENGINEERING_PRECISION) + " × 10<sup><small>" + power + "</sup></small>";
        }
    }

    static private String buildRect(Result answer, String real, String imaginary)
    {
        String output = "";
        switch (answerStructure(answer))
        {
            case ZERO:
                if (answer.getRe().compareTo(BigDecimal.ZERO) < 0) output = "-";
                output += real;
                break;
            case ONLY_REAL:
                if (answer.getRe().compareTo(BigDecimal.ZERO) < 0) output = "-";
                output += real;
                break;
            case ONLY_COMPLEX:
                if (answer.getIm().compareTo(BigDecimal.ZERO) < 0) output += "-";
                if (imaginary.equals("1")) output += Preferences.IMAGINARY_CHARACTER;
                else output += imaginary + Preferences.IMAGINARY_CHARACTER;
                break;
            case REAL_AND_COMPLEX:
                if (answer.getRe().compareTo(BigDecimal.ZERO) < 0) output = "-";
                output += real;
                if (answer.getIm().compareTo(BigDecimal.ZERO) < 0) output += " - ";
                else output += " + ";
                if (imaginary.equals("1")) output += Preferences.IMAGINARY_CHARACTER;
                else output += imaginary + Preferences.IMAGINARY_CHARACTER;
                break;
        }
        return output;
    }

    private static String buildPolar(Result answer, String radius, String angle)
    {
        String output = "";
        if (answerStructure(answer) == Structure.ZERO) output = "0 ∠ 0";
        else
        {
            output += radius + " ∠ " + angle;
        }
        return output;
    }

    private static Structure answerStructure(Result answer)
    {
        if (answer.getRe().compareTo(BigDecimal.ZERO) == 0 && answer.getIm().compareTo(BigDecimal.ZERO) == 0)
            return Structure.ZERO;
        else if (answer.getRe().compareTo(BigDecimal.ZERO) != 0 && answer.getIm().compareTo(BigDecimal.ZERO) == 0)
            return Structure.ONLY_REAL;
        else if (answer.getRe().compareTo(BigDecimal.ZERO) == 0 && answer.getIm().compareTo(BigDecimal.ZERO) != 0)
            return Structure.ONLY_COMPLEX;
        else return Structure.REAL_AND_COMPLEX;
    }

    private static String buildSpecialNumeralSystem(Result answer)
    {
        String output = "";
        switch (answerStructure(answer))
        {
            case ZERO:
                output = "0";
                break;
            case ONLY_REAL:
                switch (Preferences.MODE_NUMERAL)
                {
                    case BIN:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.BIN);
                        break;
                    case OCT:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.OCT);
                        break;
                    case HEX:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.HEX);
                        break;
                    default:
                        break;
                }
                break;
            case ONLY_COMPLEX:
                switch (Preferences.MODE_NUMERAL)
                {
                    case BIN:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.BIN);
                        break;
                    case OCT:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.OCT);
                        break;
                    case HEX:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.HEX);
                        break;
                    default:
                        break;
                }
                break;
            case REAL_AND_COMPLEX:
                switch (Preferences.MODE_NUMERAL)
                {
                    case BIN:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.BIN);
                        break;
                    case OCT:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.OCT);
                        break;
                    case HEX:
                        output += Numeral.convertMode(answer.getRe(), Preferences.NumeralMode.HEX);
                        break;
                    default:
                        break;
                }
                if (answer.getIm().compareTo(BigDecimal.ZERO) < 0) output += " - ";
                else output += " + ";
                switch (Preferences.MODE_NUMERAL)
                {
                    case BIN:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.BIN);
                        break;
                    case OCT:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.OCT);
                        break;
                    case HEX:
                        output += Numeral.convertMode(answer.getIm(), Preferences.NumeralMode.HEX);
                        break;
                    default:
                        break;
                }
                break;
        }
        return output;
    }

    enum Structure
    {
        ZERO, ONLY_REAL, ONLY_COMPLEX, REAL_AND_COMPLEX
    }
}
