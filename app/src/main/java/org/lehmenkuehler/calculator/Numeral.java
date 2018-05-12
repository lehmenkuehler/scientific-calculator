package org.lehmenkuehler.calculator;

import java.math.BigDecimal;

public class Numeral
{
    private static final int precision = Preferences.PRECISION;

    static String convertMode(BigDecimal value, Preferences.NumeralMode numSystem)
    {
        switch (numSystem)
        {
            case BIN:
                return decToBin(value);
            case OCT:
                return decToOct(value);
            case HEX:
                return decToHex(value);
            default:
                return value.toPlainString();
        }
    }

    public static BigDecimal parseToDec(String s)
    {
        switch (Preferences.MODE_NUMERAL)
        {
            case BIN:
                return binToDec(s);
            case OCT:
                return octToDec(s);
            case HEX:
                return hexToDec(s);
            default:
                return new BigDecimal(s);
        }
    }

    private static BigDecimal binToDec(String s)
    {
        BigDecimal result = BigDecimal.ZERO;
        int stringLength = s.length();
        boolean integer = true;
        int radixPos = stringLength;

        for (int i = 0; i < stringLength; i++)
        {
            if (s.charAt(i) == '.')
            {
                radixPos = i;
            }
        }

        for (int i = 0; i < stringLength; i++)
        {
            BigDecimal base;
            int n;

            if (integer)
            {
                base = new BigDecimal("2");
                n = radixPos - i - 1;

            } else
            {
                base = new BigDecimal("0.5");
                n = i - radixPos;
            }

            switch (s.charAt(i))
            {
                case '0':
                    break;
                case '1':
                    result = result.add(base.pow(n));
                    break;
                case '.':
                    integer = false;
                    radixPos = i;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private static BigDecimal hexToDec(String s)
    {
        BigDecimal result = BigDecimal.ZERO;
        int stringLength = s.length();
        boolean integer = true;
        int radixPos = stringLength;

        for (int i = 0; i < stringLength; i++)
        {
            if (s.charAt(i) == '.')
            {
                radixPos = i;
            }
        }

        for (int i = 0; i < stringLength; i++)
        {
            BigDecimal base;
            int n;

            if (integer)
            {
                base = new BigDecimal("16");
                n = radixPos - i - 1;

            } else
            {
                base = new BigDecimal("0.0625");
                n = i - radixPos;
            }

            switch (s.charAt(i))
            {
                case '0':
                    result = result.add(base.pow(n).multiply(BigDecimal.ZERO));
                    break;
                case '1':
                    result = result.add(base.pow(n).multiply(BigDecimal.ONE));
                    break;
                case '2':
                    result = result.add(base.pow(n).multiply(new BigDecimal("2")));
                    break;
                case '3':
                    result = result.add(base.pow(n).multiply(new BigDecimal("3")));
                    break;
                case '4':
                    result = result.add(base.pow(n).multiply(new BigDecimal("4")));
                    break;
                case '5':
                    result = result.add(base.pow(n).multiply(new BigDecimal("5")));
                    break;
                case '6':
                    result = result.add(base.pow(n).multiply(new BigDecimal("6")));
                    break;
                case '7':
                    result = result.add(base.pow(n).multiply(new BigDecimal("7")));
                    break;
                case '8':
                    result = result.add(base.pow(n).multiply(new BigDecimal("8")));
                    break;
                case '9':
                    result = result.add(base.pow(n).multiply(new BigDecimal("9")));
                    break;
                case 'A':
                    result = result.add(base.pow(n).multiply(BigDecimal.TEN));
                    break;
                case 'B':
                    result = result.add(base.pow(n).multiply(new BigDecimal("11")));
                    break;
                case 'C':
                    result = result.add(base.pow(n).multiply(new BigDecimal("12")));
                    break;
                case 'D':
                    result = result.add(base.pow(n).multiply(new BigDecimal("13")));
                    break;
                case 'E':
                    result = result.add(base.pow(n).multiply(new BigDecimal("14")));
                    break;
                case 'F':
                    result = result.add(base.pow(n).multiply(new BigDecimal("15")));
                    break;
                case '.':
                    integer = false;
                    radixPos = i;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private static BigDecimal octToDec(String s)
    {
        BigDecimal result = BigDecimal.ZERO;
        int stringLength = s.length();
        boolean integer = true;
        int radixPos = stringLength;

        for (int i = 0; i < stringLength; i++)
        {
            if (s.charAt(i) == '.')
            {
                radixPos = i;
            }
        }

        for (int i = 0; i < stringLength; i++)
        {
            BigDecimal base;
            int n;

            if (integer)
            {
                base = new BigDecimal("8");
                n = radixPos - i - 1;

            } else
            {
                base = new BigDecimal("0.125");
                n = i - radixPos;
            }

            switch (s.charAt(i))
            {
                case '0':
                    result = result.add(base.pow(n).multiply(BigDecimal.ZERO));
                    break;
                case '1':
                    result = result.add(base.pow(n).multiply(BigDecimal.ONE));
                    break;
                case '2':
                    result = result.add(base.pow(n).multiply(new BigDecimal("2")));
                    break;
                case '3':
                    result = result.add(base.pow(n).multiply(new BigDecimal("3")));
                    break;
                case '4':
                    result = result.add(base.pow(n).multiply(new BigDecimal("4")));
                    break;
                case '5':
                    result = result.add(base.pow(n).multiply(new BigDecimal("5")));
                    break;
                case '6':
                    result = result.add(base.pow(n).multiply(new BigDecimal("6")));
                    break;
                case '7':
                    result = result.add(base.pow(n).multiply(new BigDecimal("7")));
                    break;
                case '.':
                    integer = false;
                    radixPos = i;
                    break;
                default:
                    break;
            }
        }
        return result;
    }

    private static String decToHex(BigDecimal b)
    {
        String result = "";
        boolean startHasBeenFound = false;
        if (b.compareTo(BigDecimal.ZERO) == 0) return "0";
        if (b.compareTo(BigDecimal.ZERO) < 0)
        {
            result += "-";
            b = b.abs();
        }

        if (b.compareTo(BigDecimal.ONE) < 0) result += "0";
        else
        {
            for (int i = 32; i >= 0; i--)
            {
                if (new BigDecimal("16").pow(i).compareTo(b) <= 0)
                {
                    BigDecimal counter = BigDecimal.ONE;
                    startHasBeenFound = true;
                    while (new BigDecimal("16").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("16")) < 0)
                    {
                        counter = counter.add(BigDecimal.ONE);
                    }
                    counter = counter.subtract(BigDecimal.ONE);
                    b = b.subtract(new BigDecimal("16").pow(i).multiply(counter));
                    int q = counter.intValue();
                    switch (q)
                    {
                        case 1:
                            result += "1";
                            break;
                        case 2:
                            result += "2";
                            break;
                        case 3:
                            result += "3";
                            break;
                        case 4:
                            result += "4";
                            break;
                        case 5:
                            result += "5";
                            break;
                        case 6:
                            result += "6";
                            break;
                        case 7:
                            result += "7";
                            break;
                        case 8:
                            result += "8";
                            break;
                        case 9:
                            result += "9";
                            break;
                        case 10:
                            result += "A";
                            break;
                        case 11:
                            result += "B";
                            break;
                        case 12:
                            result += "C";
                            break;
                        case 13:
                            result += "D";
                            break;
                        case 14:
                            result += "E";
                            break;
                        case 15:
                            result += "F";
                            break;
                    }

                } else if (startHasBeenFound) result += "0";
            }
        }

        if (b.compareTo(BigDecimal.ZERO) == 0) return result;

        result += ".";

        for (int i = 1; i <= precision; i++)
        {
            if (new BigDecimal("0.0625").pow(i).compareTo(b) <= 0)
            {
                BigDecimal counter = BigDecimal.ONE;
                while (new BigDecimal("0.0625").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("16")) < 0)
                {
                    counter = counter.add(BigDecimal.ONE);
                }
                counter = counter.subtract(BigDecimal.ONE);
                b = b.subtract(new BigDecimal("0.0625").pow(i).multiply(counter));
                int q = counter.intValue();
                switch (q)
                {
                    case 1:
                        result += "1";
                        break;
                    case 2:
                        result += "2";
                        break;
                    case 3:
                        result += "3";
                        break;
                    case 4:
                        result += "4";
                        break;
                    case 5:
                        result += "5";
                        break;
                    case 6:
                        result += "6";
                        break;
                    case 7:
                        result += "7";
                        break;
                    case 8:
                        result += "8";
                        break;
                    case 9:
                        result += "9";
                        break;
                    case 10:
                        result += "A";
                        break;
                    case 11:
                        result += "B";
                        break;
                    case 12:
                        result += "C";
                        break;
                    case 13:
                        result += "D";
                        break;
                    case 14:
                        result += "E";
                        break;
                    case 15:
                        result += "F";
                        break;
                }
                if (b.compareTo(BigDecimal.ZERO) == 0) return result;
            } else result += "0";
        }
        return result;
    }

    private static String decToBin(BigDecimal b)
    {
        String result = "";
        boolean startHasBeenFound = false;
        if (b.compareTo(BigDecimal.ZERO) == 0) return "0";
        if (b.compareTo(BigDecimal.ZERO) < 0)
        {
            result += "-";
            b = b.abs();
        }

        if (b.compareTo(BigDecimal.ONE) < 0) result += "0";
        else
        {
            for (int i = 32; i >= 0; i--)
            {
                if (new BigDecimal("2").pow(i).compareTo(b) <= 0)
                {
                    BigDecimal counter = BigDecimal.ONE;
                    startHasBeenFound = true;
                    while (new BigDecimal("2").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("2")) < 0)
                    {
                        counter = counter.add(BigDecimal.ONE);
                    }
                    counter = counter.subtract(BigDecimal.ONE);
                    b = b.subtract(new BigDecimal("2").pow(i).multiply(counter));
                    int q = counter.intValue();
                    switch (q)
                    {
                        case 1:
                            result += "1";
                            break;
                    }

                } else if (startHasBeenFound) result += "0";
            }
        }

        if (b.compareTo(BigDecimal.ZERO) == 0) return result;

        result += ".";

        for (int i = 1; i <= precision; i++)
        {
            if (new BigDecimal("0.5").pow(i).compareTo(b) <= 0)
            {
                BigDecimal counter = BigDecimal.ONE;
                while (new BigDecimal("0.5").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("2")) < 0)
                {
                    counter = counter.add(BigDecimal.ONE);
                }
                counter = counter.subtract(BigDecimal.ONE);
                b = b.subtract(new BigDecimal("0.5").pow(i).multiply(counter));
                int q = counter.intValue();
                switch (q)
                {
                    case 1:
                        result += "1";
                        break;
                }
                if (b.compareTo(BigDecimal.ZERO) == 0) return result;
            } else result += "0";
        }
        return result;
    }

    private static String decToOct(BigDecimal b)
    {
        String result = "";
        boolean startHasBeenFound = false;
        if (b.compareTo(BigDecimal.ZERO) == 0) return "0";
        if (b.compareTo(BigDecimal.ZERO) < 0)
        {
            result += "-";
            b = b.abs();
        }

        if (b.compareTo(BigDecimal.ONE) < 0) result += "0";
        else
        {
            for (int i = 32; i >= 0; i--)
            {
                if (new BigDecimal("8").pow(i).compareTo(b) <= 0)
                {
                    BigDecimal counter = BigDecimal.ONE;
                    startHasBeenFound = true;
                    while (new BigDecimal("8").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("8")) < 0)
                    {
                        counter = counter.add(BigDecimal.ONE);
                    }
                    counter = counter.subtract(BigDecimal.ONE);
                    b = b.subtract(new BigDecimal("8").pow(i).multiply(counter));
                    int q = counter.intValue();
                    switch (q)
                    {
                        case 1:
                            result += "1";
                            break;
                        case 2:
                            result += "2";
                            break;
                        case 3:
                            result += "3";
                            break;
                        case 4:
                            result += "4";
                            break;
                        case 5:
                            result += "5";
                            break;
                        case 6:
                            result += "6";
                            break;
                        case 7:
                            result += "7";
                            break;
                    }

                } else if (startHasBeenFound) result += "0";
            }
        }

        if (b.compareTo(BigDecimal.ZERO) == 0) return result;

        result += ".";

        for (int i = 1; i <= precision; i++)
        {
            if (new BigDecimal("0.125").pow(i).compareTo(b) <= 0)
            {
                BigDecimal counter = BigDecimal.ONE;
                while (new BigDecimal("0.125").pow(i).multiply(counter).compareTo(b) <= 0 && counter.compareTo(new BigDecimal("8")) < 0)
                {
                    counter = counter.add(BigDecimal.ONE);
                }
                counter = counter.subtract(BigDecimal.ONE);
                b = b.subtract(new BigDecimal("0.125").pow(i).multiply(counter));
                int q = counter.intValue();
                switch (q)
                {
                    case 1:
                        result += "1";
                        break;
                    case 2:
                        result += "2";
                        break;
                    case 3:
                        result += "3";
                        break;
                    case 4:
                        result += "4";
                        break;
                    case 5:
                        result += "5";
                        break;
                    case 6:
                        result += "6";
                        break;
                    case 7:
                        result += "7";
                        break;
                }
                if (b.compareTo(BigDecimal.ZERO) == 0) return result;
            } else result += "0";
        }
        return result;
    }
}
