package org.lehmenkuehler.calculator.Mechanics;

import android.widget.Toast;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public class Fractions {


    public static void toggleOutput() {
        switch (Preferences.MODE_OUTPUT) {
            case DECIMAL:
                Preferences.MODE_OUTPUT = Preferences.OutputMode.REPEATING_DECIMAL;
                break;
            case REPEATING_DECIMAL:
                Preferences.MODE_OUTPUT = Preferences.OutputMode.SIMPLE_FRACTION;
                break;
            case SIMPLE_FRACTION:
                Preferences.MODE_OUTPUT = Preferences.OutputMode.PARTED_FRACTION;
                break;
            default:
                Preferences.MODE_OUTPUT = Preferences.OutputMode.DECIMAL;
                break;
        }
    }

    public static String tryConvert(BigDecimal x, int precision) {
        x = x.abs();
        try {
            switch (Preferences.MODE_OUTPUT) {
                case DECIMAL:
                    return decimal(x, precision);
                case REPEATING_DECIMAL:
                    return repeatingDecimal(x);
                case SIMPLE_FRACTION:
                    return simpleFraction(x);
                case PARTED_FRACTION:
                    return partedFraction(x);
                default:
                    return decimal(x, precision);
            }
        } catch (RuntimeException e) {
            return "ERROR";
        }
    }

    private static String decimal(BigDecimal x, int precision) {
        if (Preferences.FIX_PRECISION) {
            return x.abs().setScale(precision, BigDecimal.ROUND_HALF_UP).toPlainString();
        } else {
            if (x.signum() == 0) return "0";
            return x.abs().setScale(precision, BigDecimal.ROUND_HALF_UP).stripTrailingZeros().toPlainString();
        }
    }

    private static String repeatingDecimal(BigDecimal x) {

        if (x.remainder(BigDecimal.ONE).signum() == 0) {
            throw new RuntimeException();
        }

        String value = x.toPlainString();
        String period = repeatingDecimals(value);
        String prePoint = x.toBigInteger().toString();

        if (period.length() == 0) {
            throw new RuntimeException();
        }
        int uniqueDecimalsStart = value.indexOf(".") + 1;
        int periodStart = value.indexOf(period);

        String uniqueDecimals = "";

        if (periodStart > uniqueDecimalsStart) {
            uniqueDecimals = value.substring(uniqueDecimalsStart, periodStart);
        }

        return prePoint + "." + uniqueDecimals + "‾" + period;
    }

    private static String simpleFraction(BigDecimal x) {
        if (x.remainder(BigDecimal.ONE).signum() == 0) {
            throw new RuntimeException();
        } else try {
            return convertToFraction(x, Preferences.OutputMode.SIMPLE_FRACTION);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    private static String partedFraction(BigDecimal x) {
        if (x.remainder(BigDecimal.ONE).signum() == 0 || x.abs().compareTo(BigDecimal.ONE) < 0) {
            throw new RuntimeException();
        } else try {
            return convertToFraction(x, Preferences.OutputMode.PARTED_FRACTION);
        } catch (RuntimeException e) {
            throw new RuntimeException();
        }
    }

    private static String convertToFraction(BigDecimal x, Preferences.OutputMode output) {
        BigInteger numerator, denominator, prePoint;

        // separate integer and decimal value
        prePoint = x.toBigInteger();
        x = x.subtract(new BigDecimal(prePoint));

        // create non canceled fraction
        String string = x.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        int pow = string.length() - index - 1;

        // check for integer or surd
        if (string.substring(index + 1).length() < 60) {
            numerator = x.movePointRight(pow).toBigInteger();
            denominator = BigInteger.TEN.pow(pow);

            BigInteger gcd = numerator.gcd(denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);

        } else {
            String value = x.toPlainString();
            String period = repeatingDecimals(value);
            if (period.length() == 0) {
                throw new RuntimeException();
            }
            int uniqueDecimals = value.indexOf(period);

            // equation one - decimal power
            BigInteger x1 = BigInteger.TEN.pow(uniqueDecimals);
            BigInteger num1 = (x.multiply(new BigDecimal(x1)).toBigInteger());

            // equation two - decimal power
            BigInteger x2 = BigInteger.TEN.pow(uniqueDecimals + period.length());
            BigInteger num2 = (x.multiply(new BigDecimal(x2)).toBigInteger());

            numerator = num2.subtract(num1);
            denominator = x2.subtract(x1);

            BigInteger gcd = numerator.gcd(denominator);
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }

        String res = "";
        if (output == Preferences.OutputMode.PARTED_FRACTION) {
            res += prePoint.toString() + " <sup><small>" + numerator.toString() + "</small></sup>&frasl;<sub><small>" + denominator.toString() + "</small></sub>";
        } else {
            numerator = numerator.add(denominator.multiply(prePoint));
            res += "<sup><small>" + numerator.toString() + "</small></sup>&frasl;<sub><small>" + denominator.toString() + "</small></sub>";
        }
        return res;
    }

    private static String repeatingDecimals(String x) {
        int index = x.indexOf(".");
        String decimals = x.substring(index + 1);

        if (decimals.length() < 50) return "";

        String collected = "";
        for (int i = 0; i < decimals.length(); i++) {

            if (i == 120) return "";

            boolean falseAlarm = false;
            collected += decimals.charAt(i);
            int nextStartOfCollected = decimals.indexOf(collected, i + 1);
            if (nextStartOfCollected < 0) collected = "";
            if (nextStartOfCollected == i + 1) {
                for (int j = i + 1; j < decimals.length() - collected.length() - 30; j += collected.length()) {
                    int startAgain = decimals.indexOf(collected, j);
                    if (startAgain != j) {
                        falseAlarm = true;
                        collected = "";
                        break;
                    }
                }
                if (!falseAlarm) break;
            }
        }
        return collected;
    }
}
