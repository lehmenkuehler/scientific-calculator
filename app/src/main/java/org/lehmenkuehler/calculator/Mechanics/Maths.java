package org.lehmenkuehler.calculator.Mechanics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Enums.Error;
import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.Preferences;

class Maths
{
    private static final MathContext mc = new MathContext(30, RoundingMode.HALF_UP);

    static BigDecimal sgn(BigDecimal x)
    {
        if (x.compareTo(BigDecimal.ZERO) > 0) return BigDecimal.ONE;
        else if (x.equals(BigDecimal.ZERO)) return BigDecimal.ZERO;
        else return new BigDecimal("-1");
    }

    static BigDecimal acoth(BigDecimal x)
    {
        return new BigDecimal("0.5").multiply(ln(x.add(BigDecimal.ONE).divide(x.subtract(BigDecimal.ONE), mc)));
    }

    static BigDecimal artanh(BigDecimal x)
    {
        return new BigDecimal("0.5").multiply(ln(BigDecimal.ONE.add(x).divide(BigDecimal.ONE.subtract(x), mc)));
    }

    static BigDecimal arcosh(BigDecimal x)
    {
        return ln(x.add(sqrt(x.pow(2).subtract(BigDecimal.ONE))));
    }

    static BigDecimal arsinh(BigDecimal x)
    {
        return ln(x.add(sqrt(x.pow(2).add(BigDecimal.ONE))));
    }

    static BigDecimal tanh(BigDecimal x)
    {
        return sinh(x).divide(cosh(x), mc);
    }

    static BigDecimal coth(BigDecimal x)
    {
        return cosh(x).divide(sinh(x), mc);
    }

    static BigDecimal cosh(BigDecimal x)
    {
        BigDecimal result = BigDecimal.ZERO;
        int limit = mc.getPrecision();
        for (int k = 0; k < limit; k++)
        {
            BigDecimal sumElement;
            sumElement = x.pow(2 * k);
            sumElement = sumElement.divide(new BigDecimal(fact(new BigInteger(String.valueOf(2 * k)))), mc);
            result = result.add(sumElement);
        }
        return result;
    }

    static BigDecimal sinh(BigDecimal x)
    {
        BigDecimal result = BigDecimal.ZERO;
        int limit = mc.getPrecision() * 3;
        for (int k = 0; k < limit; k++)
        {
            BigDecimal sumElement;
            sumElement = x.pow(2 * k + 1);
            sumElement = sumElement.divide(new BigDecimal(fact(new BigInteger(String.valueOf(2 * k + 1)))), mc);
            result = result.add(sumElement);
        }
        return result;
    }

    static BigDecimal arccot(BigDecimal x)
    {
        BigDecimal piHalf = new BigDecimal("1.57079632679489661923132169163975144209858469968755291048747229615390820314310449"
                + "93140174126710585339910740432566411533235469223047752911158626797040642405587251420513509692605527798223114"
                + "744774651909822144054878329667230642378");
        return piHalf.subtract(arctan(x));
    }

    static BigDecimal arctan(BigDecimal x)
    {
        BigDecimal result = BigDecimal.ZERO;
        boolean signed = x.compareTo(BigDecimal.ZERO) < 0;
        x = x.abs();
        if (x.compareTo(new BigDecimal(0.8)) > 0)
        {
            return arctan(x.divide(BigDecimal.ONE.add(sqrt(BigDecimal.ONE.add(x.pow(2)))), mc)).multiply(new BigDecimal("2"));
        } else
        {
            int limit = mc.getPrecision();
            for (int k = 0; k < limit; k++)
            {
                BigDecimal sumElement = BigDecimal.ONE.negate().pow(k);
                sumElement = sumElement.multiply(x.pow(2 * k + 1));
                sumElement = sumElement.divide(new BigDecimal(2 * k + 1), mc);
                result = result.add(sumElement);
            }
            if (signed) result = result.negate();
            return result;
        }
    }

    static BigDecimal arccos(BigDecimal x)
    {
        return Component.CONSTANT_PI_HALF.getValue().subtract(arcsin(x));
    }

    static BigDecimal arcsin(BigDecimal x)
    {

        BigDecimal sign = sgn(x);
        x = x.abs();
        // avoid invalid argument
        if (x.equals(BigDecimal.ONE)) return Component.CONSTANT_PI_HALF.getValue().multiply(sign);
        if (x.subtract(BigDecimal.ONE).signum() == 1)
        {
            Main.globalError = Error.ARGUMENT_OUT_OF_RANGE;
            throw new RuntimeException();
        }

        if (x.doubleValue() < 0.7)
        {
            BigDecimal result = BigDecimal.ZERO;
            int limit = mc.getPrecision() * 5;
            BigDecimal sumElement;
            for (int k = 0; k < limit; k++)
            {
                sumElement = new BigDecimal(comb(new BigInteger(String.valueOf(2 * k)), new BigInteger(String.valueOf(k))));
                sumElement = sumElement.multiply(x.pow(2 * k + 1));
                sumElement = sumElement.divide(new BigDecimal("4").pow(k).multiply(new BigDecimal(2 * k + 1)), mc);
                result = result.add(sumElement);
            }
            return result.multiply(sign);
        } else
        {
            return arctan(sqrt(x.pow(2).divide(BigDecimal.ONE.subtract(x.pow(2)), mc))).multiply(sgn(x)).multiply(sign);
        }

    }

    static BigDecimal tan(BigDecimal x)
    {
        return (sin(x).divide(cos(x), mc));
    }

    static BigDecimal cos(BigDecimal x)
    {
        BigDecimal result = BigDecimal.ZERO;
        x = x.remainder(Component.CONSTANT_PI.getValue().multiply(new BigDecimal("2")));
        int limit = mc.getPrecision();
        for (int k = 0; k < limit; k++)
        {
            BigDecimal sumElement;
            sumElement = new BigDecimal("-1").pow(k);
            sumElement = sumElement.divide(new BigDecimal(fact(new BigInteger(String.valueOf(2 * k)))), mc);
            sumElement = sumElement.multiply(x.pow(2 * k));
            result = result.add(sumElement);
        }
        result = result.setScale(Preferences.PRECISION, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        return result;
    }

    static BigDecimal sin(BigDecimal x)
    {
        BigDecimal result = BigDecimal.ZERO;
        x = x.remainder(Component.CONSTANT_PI.getValue().multiply(new BigDecimal("2")));
        int limit = mc.getPrecision();
        for (int k = 0; k < limit; k++)
        {
            BigDecimal sumElement;
            sumElement = new BigDecimal("-1").pow(k);
            sumElement = sumElement.divide(new BigDecimal(fact(new BigInteger(String.valueOf(2 * k + 1)))), mc);
            sumElement = sumElement.multiply(x.pow(2 * k + 1));
            result = result.add(sumElement);
        }
        result = result.setScale(Preferences.PRECISION, BigDecimal.ROUND_HALF_UP).stripTrailingZeros();
        return result;
    }

    static BigDecimal sec(BigDecimal x)
    {
        return BigDecimal.ONE.divide(cos(x), mc);
    }

    static BigDecimal csc(BigDecimal x)
    {
        return BigDecimal.ONE.divide(sin(x), mc);
    }

    static BigDecimal cot(BigDecimal x)
    {
        return BigDecimal.ONE.divide(tan(x), mc);
    }

    static BigDecimal crt(BigDecimal x)
    {
        return rt(x, new BigDecimal("3"));
    }

    static BigDecimal sqrt(BigDecimal x)
    {
        if (x.signum() == 0) return BigDecimal.ZERO;

        BigDecimal result;

        if (x.subtract(new BigDecimal(Double.MAX_VALUE)).signum() == -1)
        {
            result = new BigDecimal(Math.sqrt(x.doubleValue()));
        } else result = pow(x, new BigDecimal("0.5"));

        BigDecimal oneHalf = new BigDecimal("0.5");
        for (int n = 0; n < 10; n++)
        {
            result = oneHalf.multiply(result.add(x.abs().divide(result, mc)));
        }
        return result;
    }

    static BigDecimal rt(BigDecimal x, BigDecimal n)
    {
        if (n.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0)
        {
            BigDecimal result = new BigDecimal(Math.exp(Math.log(x.doubleValue()) / n.intValue()));
            int limit = mc.getPrecision();
            for (int i = 0; i < limit; i++)
            {
                result = ((n.subtract(BigDecimal.ONE).multiply(result.pow(n.intValue())).add(x)).divide(n.multiply(result.pow(n.intValue() - 1)), mc));
            }
            return result;
        } else return exp(ln(x).divide(n, mc));
    }

    static BigDecimal pow(BigDecimal x, BigDecimal n)
    {
        if (n.compareTo(BigDecimal.ONE) == 0) return x;
        if (n.compareTo(BigDecimal.ZERO) < 0)
        {
            x = BigDecimal.ONE.divide(x.abs(), mc);
            n = n.abs();
        }
        if (n.remainder(BigDecimal.ONE).signum() == 0) return x.pow(n.intValue());
        return (exp(ln(x).multiply(n)));
    }

    static BigDecimal exp(BigDecimal x)
    {

        boolean signed = x.signum() == -1;
        x = x.abs();

        if (x.remainder(BigDecimal.ONE).signum() == 0)
        {
            if (!signed) return Component.CONSTANT_EULER_NUMBER.getValue().pow(x.intValue());
            else
                return BigDecimal.ONE.divide(Component.CONSTANT_EULER_NUMBER.getValue().pow(x.intValue()), 150, BigDecimal.ROUND_HALF_UP);
        }

        // reducing x

        BigDecimal LN100K = new BigDecimal("11.512925464970228420089957273421821038005507443143864880166639504837863048386762401179986025447991491709838920211");
        BigDecimal LN1P1 = new BigDecimal("0.0953101798043248600439521232807650922206053653086441991852398081630010142358842328390575029130364930727479418458");

        BigDecimal k, l;

        k = x.divide(LN100K, 0, BigDecimal.ROUND_FLOOR);
        x = x.subtract(LN100K.multiply(k));
        l = x.divide(LN1P1, 0, BigDecimal.ROUND_FLOOR);
        x = x.subtract(LN1P1.multiply(l));

        BigDecimal result = BigDecimal.ZERO;
        int limit = mc.getPrecision();
        for (int n = 0; n < limit; n++)
        {
            result = result.add(x.pow(n).divide(new BigDecimal(fact(new BigInteger(String.valueOf(n)))), mc));
        }

        result = result.multiply(new BigDecimal("100000").pow(k.intValue())).multiply(new BigDecimal("1.1").pow(l.intValue()));

        if (signed) result = BigDecimal.ONE.divide(result, 150, BigDecimal.ROUND_HALF_UP);

        return result;
    }

    static BigInteger fact(BigInteger n)
    {
        BigInteger result = BigInteger.ONE;
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
        {
            result = BigInteger.ONE;
        } else
        {
            for (long i = n.longValue(); i > 0; i--)
            {
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
        }
        return result;
    }

    static BigInteger dfact(BigInteger n)
    {
        BigInteger result = BigInteger.ONE;
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
        {
            result = BigInteger.ONE;
        } else if (n.remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0)
        {
            for (long i = n.longValue(); i > 0; i = i - 2)
            {
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
        } else
        {
            for (long i = n.longValue(); i > 0; i = i - 2)
            {
                result = result.multiply(new BigInteger(String.valueOf(i)));
            }
        }
        return result;
    }

    static BigDecimal logn(BigDecimal x, BigDecimal n)
    {
        return ln(x).divide(ln(n), mc);
    }

    static BigDecimal log(BigDecimal x)
    {
        return ln(x).divide(ln(BigDecimal.TEN), mc);
    }

    static BigDecimal ln(BigDecimal x)
    {
        BigDecimal result, remainder, interim = BigDecimal.ZERO;
        BigDecimal argument = x;
        boolean signed = false;
        BigDecimal lnSqrtTwo = new BigDecimal("0.346573590279972654708616060729088284037750067180127627060340004746696810");

        if (x.compareTo(BigDecimal.ONE) < 0)
        {
            argument = BigDecimal.ONE.divide(x, mc);
            signed = true;
        }

        int m = (int) (Math.log(argument.doubleValue()) / Math.log(2));
        argument = argument.divide(new BigDecimal("2").pow(m), mc);
        int limit = mc.getPrecision();

        for (int k = 0; k < limit; k++)
        {
            BigDecimal sumElement;
            sumElement = (argument.subtract(BigDecimal.ONE)).divide(argument.add(BigDecimal.ONE), mc);
            sumElement = sumElement.pow(2 * k + 1);
            sumElement = sumElement.multiply(new BigDecimal("2").divide(new BigDecimal("2").multiply(new BigDecimal(k)).add(BigDecimal.ONE), mc));
            interim = interim.add(sumElement);
        }

        remainder = (argument.subtract(BigDecimal.ONE)).pow(2);
        remainder = remainder.divide((new BigDecimal("4").multiply(new BigDecimal(limit)).add(new BigDecimal("6"))).multiply(argument.abs()), mc);
        remainder = remainder.multiply(((argument.subtract(BigDecimal.ONE)).divide(argument.add(BigDecimal.ONE), mc)).abs().pow(2 * limit + 1));

        interim = interim.add(remainder);
        result = new BigDecimal("2").multiply(lnSqrtTwo).multiply(new BigDecimal(m));
        result = result.add(interim);

        if (signed) result = result.multiply(new BigDecimal("-1"));
        return result;
    }

    static BigInteger comb(BigInteger n, BigInteger c)
    {
        return fact(n).divide(fact(c).multiply(fact(n.subtract(c))));
    }

    static BigInteger perm(BigInteger n, BigInteger c)
    {
        if (c.compareTo(n) > 0) return BigInteger.ZERO;
        return fact(n).divide(fact(n.subtract(c)));
    }

    // least multiple
    private static long lm(long n)
    {
        long root = sqrt(new BigDecimal(n)).longValue() + 1;
        long prime = 5;
        if (n == 1) return 1;
        if (n % 2 == 0) return 2;
        if (n % 3 == 0) return 3;
        while (n % prime != 0)
        {
            prime += 2;
            if (n % prime == 0) return prime;
            prime += 4;
            if (prime > root) return n;
        }
        return prime;
    }


    // prime factorization
    private static List<Long> pf(long n)
    {
        if (n == 0)
        {
            Main.globalError = Error.ARGUMENT_MUST_BE_POSITIVE_INTEGER;
            throw new RuntimeException();
        }
        long leastMultiple;
        List<Long> primes = new ArrayList<>();
        for (int i = 0; ; i++)
        {
            leastMultiple = lm(n);
            primes.add(leastMultiple);
            n = n / leastMultiple;
            if (n == 1) return primes;
        }
    }

    static BigInteger GCD(BigInteger a, BigInteger b)
    {
        return a.gcd(b);
    }

    // least common multiple
    static BigInteger lcm(BigInteger a, BigInteger b)
    {
        long result = 1;
        List<Long> primesA = pf(a.longValue()), primesB = pf(b.longValue());
        boolean[] ignore = new boolean[primesB.size()];
        for (long element1 : primesA)
        {
            for (int i = 0; i < primesB.size(); i++)
            {
                if (element1 == primesB.get(i) && !ignore[i])
                {
                    ignore[i] = true;
                    break;
                }
            }
        }
        for (long element : primesA)
        {
            result *= element;
        }
        int i = 0;
        for (long element : primesB)
        {
            if (!ignore[i]) result *= element;
            i++;
        }
        return BigInteger.valueOf(result);
    }

    static BigDecimal convAM(BigDecimal x, Preferences.AngularMode input, Preferences.AngularMode output)
    {
        if (input == output) return x;
        BigDecimal xRAD = x;
        switch (input)
        {
            case DEG:
                xRAD = x.divide(new BigDecimal(180), mc).multiply(Component.CONSTANT_PI.getValue());
                break;
            case GRAD:
                xRAD = x.divide(new BigDecimal(200), mc).multiply(Component.CONSTANT_PI.getValue());
                break;
        }
        x = x.remainder(new BigDecimal("2").multiply(Component.CONSTANT_PI.getValue()));
        switch (output)
        {
            case RAD:
                return xRAD;
            case DEG:
                return xRAD.divide(Component.CONSTANT_PI.getValue(), mc).multiply(new BigDecimal(180));
            case GRAD:
                return xRAD.divide(Component.CONSTANT_PI.getValue(), mc).multiply(new BigDecimal(200));
            default:
                return x;
        }
    }

    static BigDecimal rand(BigDecimal min, BigDecimal max)
    {
        return min.add(max.subtract(min).multiply(new BigDecimal(String.valueOf(Math.random()))));
    }

    static BigDecimal randInt(BigDecimal min, BigDecimal max)
    {
        max = max.add(BigDecimal.ONE);
        return min.add(max.subtract(min).multiply(new BigDecimal(String.valueOf(Math.random())))).setScale(0, BigDecimal.ROUND_FLOOR).stripTrailingZeros();
    }

}

