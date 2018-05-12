package org.lehmenkuehler.calculator.Mechanics;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Preferences;

class BigUniversal {
    private static final MathContext mc = new MathContext(200, RoundingMode.HALF_UP);
    private BigDecimal a = BigDecimal.ZERO;
    private BigDecimal b = BigDecimal.ZERO;

    BigUniversal() {
    }

    BigUniversal(BigDecimal re) {
        a = re;
    }

    BigUniversal(BigDecimal re, BigDecimal im) {
        a = re;
        b = im;
    }

    BigDecimal getRe() {
        return a;
    }

    void setRe(BigDecimal re) {
        a = re;
    }

    BigDecimal getIm() {
        return b;
    }

    void setIm(BigDecimal im) {
        b = im;
    }

    BigDecimal getAngle() {
        if (isReal() && a.signum() == 1) return BigDecimal.ZERO;
        else if (isReal() && a.signum() == -1)
            return Maths.convAM(Component.CONSTANT_PI.getValue(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR);
        else if (isComplex() && a.signum() == 0 && b.signum() == 1)
            return Maths.convAM(Component.CONSTANT_PI_HALF.getValue(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR);
        else if (isComplex() && a.signum() == 0 && b.signum() == -1)
            return Maths.convAM(Component.CONSTANT_PI_HALF.getValue().negate(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR);
        else if (a.signum() == 1)
            return Maths.convAM(Maths.arctan(b.divide(a, mc)), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR);
        else if (b.signum() == 1)
            return Maths.convAM(Component.CONSTANT_PI_HALF.getValue().add(Maths.arctan(a.abs().divide(b.abs(), mc))), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR);
        else if (b.signum() == -1)
            return (Maths.convAM(Component.CONSTANT_PI_HALF.getValue().add(Maths.arctan(a.abs().divide(b.abs(), mc))), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR_POLAR).negate());
        else return BigDecimal.ZERO;
    }

    private BigDecimal getAngleRAD() {
        if (a.compareTo(BigDecimal.ZERO) > 0) {
            return Maths.arctan(b.divide(a, mc));
        } else if (a.compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) >= 0) {
            return Maths.arctan(b.divide(a, mc)).add(Component.CONSTANT_PI.getValue());
        } else if (a.compareTo(BigDecimal.ZERO) < 0 && b.compareTo(BigDecimal.ZERO) < 0) {
            return Maths.arctan(b.divide(a, mc)).subtract(Component.CONSTANT_PI.getValue());
        } else if (a.equals(BigDecimal.ZERO) && b.compareTo(BigDecimal.ZERO) > 0) {
            return Component.CONSTANT_PI_HALF.getValue();
        } else {
            return Component.CONSTANT_PI_HALF.getValue().multiply(new BigDecimal("-1"));
        }
    }

    BigDecimal getRadius() {
        return Maths.sqrt(a.pow(2).add(b.pow(2)));
    }

    BigUniversal add(BigUniversal addend) {
        return new BigUniversal(a.add(addend.a), b.add(addend.b));
    }

    BigUniversal subtract(BigUniversal subtrahend) {
        return new BigUniversal(a.subtract(subtrahend.a), b.subtract(subtrahend.b));
    }

    BigUniversal multiply(BigUniversal factor) {
        if (isReal() && factor.isReal())
            return new BigUniversal(a.multiply(factor.a)).setScale(mc.getPrecision() - 5);
        else if (isReal() && factor.isComplex())
            return new BigUniversal(a.multiply(factor.a), a.multiply(factor.b)).setScale(mc.getPrecision() - 5);
        else if (isComplex() && factor.isReal())
            return new BigUniversal(a.multiply(factor.a), b.multiply(factor.a)).setScale(mc.getPrecision() - 5);
        else
            return new BigUniversal(a.multiply(factor.a).subtract(b.multiply(factor.b)), a.multiply(factor.b).add(factor.a.multiply(b))).setScale(mc.getPrecision() - 5);
    }

    BigUniversal divide(BigUniversal divisor) {
        if (isReal() && divisor.isReal()) {
            return new BigUniversal(a.divide(divisor.a, mc));
        } else if (isComplex() && divisor.isReal()) {
            return new BigUniversal(a.divide(divisor.a, mc), b.divide(divisor.a, mc));
        } else {
            BigDecimal denominator = divisor.a.pow(2).add(divisor.b.pow(2));
            return new BigUniversal((a.multiply(divisor.a).add(b.multiply(divisor.b))).divide(denominator, mc),
                    (b.multiply(divisor.a).subtract(a.multiply(divisor.b))).divide(denominator, mc));
        }
    }

    BigUniversal sin() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.sin(a));
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.sin(a).multiply(Maths.cosh(b)), Maths.cos(a).multiply(Maths.sinh(b)));
        }
    }

    BigUniversal cos() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.cos(a));
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.cos(a).multiply(Maths.cosh(b)), Maths.sin(a).multiply(Maths.sinh(b)).multiply(new BigDecimal("-1")));
        }
    }

    BigUniversal tan() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.tan(a), BigDecimal.ZERO);
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            BigDecimal numeratorRe = Maths.sin(a.add(a));
            BigDecimal numeratorIm = Maths.sinh(b.add(b));
            BigDecimal denominator = Maths.cos(a.add(a)).add(Maths.cosh(b.add(b)));
            return new BigUniversal(numeratorRe.divide(denominator, mc), numeratorIm.divide(denominator, mc));
        }
    }

    BigUniversal sec() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.sec(a));
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            BigDecimal numeratorRe = Maths.cos(a).multiply(Maths.cosh(b).multiply(new BigDecimal("2")));
            BigDecimal numeratorIm = Maths.sin(a).multiply(Maths.sinh(b).multiply(new BigDecimal("2")));
            BigDecimal denominator = Maths.cos(a.add(a)).add(Maths.cosh(b.add(b)));
            return new BigUniversal(numeratorRe.divide(denominator, mc), numeratorIm.divide(denominator, mc));
        }
    }

    BigUniversal csc() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.csc(a));
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            BigDecimal numeratorRe = Maths.sin(a).multiply(Maths.cosh(b).multiply(new BigDecimal("2"))).negate();
            BigDecimal numeratorIm = Maths.cos(a).multiply(Maths.sinh(b).multiply(new BigDecimal("2")));
            BigDecimal denominator = Maths.cos(a.add(a)).subtract(Maths.cosh(b.add(b)));
            return new BigUniversal(numeratorRe.divide(denominator, mc), numeratorIm.divide(denominator, mc));
        }
    }

    BigUniversal cot() {
        if (isReal()) {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.cot(a), BigDecimal.ZERO);
        } else {
            a = Maths.convAM(a, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            b = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            BigDecimal numeratorRe = Maths.sin(a.add(a)).negate();
            BigDecimal numeratorIm = Maths.sinh(b.add(b));
            BigDecimal denominator = Maths.cos(a.add(a)).subtract(Maths.cosh(b.add(b)));
            return new BigUniversal(numeratorRe.divide(denominator, mc), numeratorIm.divide(denominator, mc));
        }
    }

    BigUniversal arcsin() {
        if (isReal()) {
            BigUniversal interim = new BigUniversal(Maths.arcsin(a), BigDecimal.ZERO);
            interim.setRe(Maths.convAM(interim.getRe(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR));
            return interim;
        } else {
            BigDecimal root = Maths.sqrt((a.pow(2).add(b.pow(2)).subtract(BigDecimal.ONE)).pow(2).add(new BigDecimal("4").multiply(b.pow(2))));
            BigDecimal binominal = a.pow(2).add(b.pow(2));
            BigDecimal re = Maths.sgn(a).multiply(new BigDecimal("0.5")).multiply(Maths.arccos(root.subtract(binominal)));
            BigDecimal im = Maths.sgn(b).multiply(new BigDecimal("0.5")).multiply(Maths.arcosh(root.add(binominal)));
            re = Maths.convAM(re, Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR);
            im = Maths.convAM(im, Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR);
            return new BigUniversal(re, im);
        }
    }

    BigUniversal arccos() {
        if (isReal()) {
            BigUniversal interim = new BigUniversal(Maths.arccos(a), BigDecimal.ZERO);
            interim.setRe(Maths.convAM(interim.getRe(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR));
            return interim;
        } else {
            return new BigUniversal(Component.CONSTANT_PI_HALF.getValue()).subtract(new BigUniversal(a, b).arcsin());
        }
    }

    BigUniversal arctan() {
        if (isReal()) {
            BigUniversal interim = new BigUniversal(Maths.arctan(a), BigDecimal.ZERO);
            interim.setRe(Maths.convAM(interim.getRe(), Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR));
            return interim;
        } else {
            BigDecimal re, im;
            if (!a.equals(BigDecimal.ZERO)) {
                re = (a.pow(2).add(b.pow(2)).subtract(BigDecimal.ONE)).divide(a.multiply(new BigDecimal("2")), mc);
                re = Maths.arctan(re);
                re = re.add(Component.CONSTANT_PI_HALF.getValue().multiply(Maths.sgn(a)));
                re = re.multiply(new BigDecimal("0.5"));
            } else if (a.equals(BigDecimal.ZERO) && b.abs().compareTo(BigDecimal.ONE) > 0) {
                re = Component.CONSTANT_PI_HALF.getValue().multiply(Maths.sgn(b));
            } else re = BigDecimal.ZERO;
            im = b.multiply(new BigDecimal("2").divide(a.pow(2).add(b.pow(2)).add(BigDecimal.ONE), mc));
            im = Maths.artanh(im).multiply(new BigDecimal("0.5"));
            re = Maths.convAM(re, Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR);
            im = Maths.convAM(im, Preferences.AngularMode.RAD, Preferences.MODE_ANGULAR);
            return new BigUniversal(re, im);
        }
    }

    BigUniversal sinh() {
        if (isReal()) {
            return new BigUniversal(Maths.sinh(a));
        } else {
            return new BigUniversal(Maths.cos(b).multiply(Maths.sinh(a)), Maths.sin(b).multiply(Maths.cosh(a)));
        }
    }

    BigUniversal cosh() {
        if (isReal()) {
            return new BigUniversal(Maths.cosh(a));
        } else {
            return new BigUniversal(Maths.cos(b).multiply(Maths.cosh(a)), Maths.sin(b).multiply(Maths.sinh(a)));
        }
    }

    BigUniversal tanh() {
        if (isReal()) {
            return new BigUniversal(Maths.tanh(a));
        } else {
            BigDecimal numeratorRe = Maths.sinh(a.multiply(new BigDecimal("2")));
            BigDecimal numeratorIm = Maths.sin(b.multiply(new BigDecimal("2")));
            BigDecimal denominator = Maths.cosh(a.multiply(new BigDecimal("2"))).add(Maths.cos(b.multiply(new BigDecimal("2"))));
            return new BigUniversal(numeratorRe.divide(denominator, mc), numeratorIm.divide(denominator, mc));
        }
    }

    BigUniversal arsinh() {
        if (isReal()) return new BigUniversal(Maths.arsinh(a), BigDecimal.ZERO);
        else
            return (((this.pow(new BigUniversal(new BigDecimal("2"))).add(new BigUniversal(BigDecimal.ONE))).sqrt().add(this))).ln();
    }

    BigUniversal arcosh() {
        if (isReal()) return new BigUniversal(Maths.arcosh(a), BigDecimal.ZERO);
        else
            return (((this.pow(new BigUniversal(new BigDecimal("2"))).subtract(new BigUniversal(BigDecimal.ONE))).sqrt().add(this))).ln();
    }

    BigUniversal artanh() {
        if (isReal()) return new BigUniversal(Maths.artanh(a), BigDecimal.ZERO);
        else
            return (new BigUniversal(BigDecimal.ONE).add(this)).divide(new BigUniversal(BigDecimal.ONE).subtract(this)).ln().multiply(new BigUniversal(new BigDecimal("0.5")));
    }

    BigUniversal ln() {
        if (isReal()) return new BigUniversal(Maths.ln(a), BigDecimal.ZERO);
        else return new BigUniversal(Maths.ln(getRadius()), getAngleRAD());
    }

    BigUniversal log() {
        if (isReal()) return new BigUniversal(Maths.log(a), BigDecimal.ZERO);
        else return this.ln().divide(new BigUniversal(Maths.ln(BigDecimal.TEN)));
    }

    BigUniversal exp() {
        if (isReal()) {
            return new BigUniversal(Maths.exp(a), BigDecimal.ZERO);
        } else {
            BigDecimal im = Maths.convAM(b, Preferences.MODE_ANGULAR, Preferences.AngularMode.RAD);
            return new BigUniversal(Maths.cos(im), Maths.sin(im)).multiply(new BigUniversal(Maths.exp(a)));
        }
    }

    BigUniversal cis() {
        if (isReal()) {
            return new BigUniversal(Maths.cos(a), Maths.sin(a));
        } else {
            BigUniversal exponent = new BigUniversal(b.negate(), a);
            return exponent.exp();
        }
    }

    BigUniversal sqrt() {
        BigUniversal result;
        if (isReal()) {
            if (a.compareTo(BigDecimal.ZERO) >= 0) result = new BigUniversal(Maths.sqrt(a));
            else result = new BigUniversal(BigDecimal.ZERO, Maths.sqrt(a.abs()));
        } else {
            BigDecimal re = Maths.cos(getAngleRAD().divide(new BigDecimal("2"), mc));
            BigDecimal im = Maths.sin(getAngleRAD().divide(new BigDecimal("2"), mc));
            BigDecimal m = Maths.sqrt(getRadius());
            return new BigUniversal(re.multiply(m), im.multiply(m));
        }
        return result;
    }

    BigUniversal crt() {
        if (isReal()) return new BigUniversal(Maths.crt(a));
        else return new BigUniversal();
    } // Komplex fehlt

    BigUniversal abs() {
        BigUniversal result;
        if (isReal()) {
            result = new BigUniversal(a.abs(), BigDecimal.ZERO);
        } else {
            result = new BigUniversal(Maths.sqrt(a.pow(2).add(b.pow(2))));
        }
        return result;
    }

    BigUniversal inv() {
        BigUniversal result;
        if (isReal()) {
            result = new BigUniversal(BigDecimal.ONE.divide(a, mc), BigDecimal.ZERO);
        } else {
            result = conj().divide(new BigUniversal(getRadius().pow(2), BigDecimal.ZERO));
        }
        return result;
    }

    BigUniversal conj() {
        return new BigUniversal(a, b.multiply(new BigDecimal("-1")));
    }

    BigUniversal sgn() {
        if (isReal()) {
            return new BigUniversal(Maths.sgn(a), BigDecimal.ZERO);
        } else {
            if (a.compareTo(BigDecimal.ZERO) == 0 && b.compareTo(BigDecimal.ZERO) == 0)
                return new BigUniversal();
            else return new BigUniversal(a.divide(getRadius(), mc), b.divide(getRadius(), mc));
        }
    }

    BigUniversal fact() {
        return new BigUniversal(new BigDecimal(Maths.fact(a.toBigInteger())));
    }

    BigUniversal dfact() {
        return new BigUniversal(new BigDecimal(Maths.dfact(a.toBigInteger())));
    }

    BigUniversal GCD(BigDecimal b) {
        return new BigUniversal((new BigDecimal(a.toBigInteger().gcd(b.toBigInteger()))));
    }

    BigUniversal pow(BigUniversal n) {
        if (isReal() && n.isReal()) {
            return new BigUniversal(Maths.pow(a, n.getRe()), BigDecimal.ZERO);
        } else if (isComplex() && n.isReal()) {
            BigDecimal abs = Maths.pow(getRadius(), n.getRe());
            BigDecimal phi = getAngleRAD().multiply(n.getRe());
            return new BigUniversal(abs.multiply(Maths.cos(phi)), abs.multiply(Maths.sin(phi)));
        } else if (isReal() && n.isComplex()) {
            BigDecimal abs = Maths.pow(a, n.getRe());
            BigDecimal phi = Maths.ln(a).multiply(n.getIm());
            return new BigUniversal(abs.multiply(Maths.cos(phi)), abs.multiply(Maths.sin(phi)));
        } else {
            BigUniversal factor = new BigUniversal(Maths.ln(getRadius()), getAngleRAD());
            return (n.multiply(factor)).exp();
        }
    }

    BigUniversal mod(BigUniversal n) {
        return new BigUniversal(a.remainder(n.getRe()));
    }

    BigUniversal logn(BigUniversal n) {
        if (isReal() && n.isReal()) {
            if (n.a.equals(Component.CONSTANT_EULER_NUMBER.getValue()))
                return new BigUniversal(Maths.ln(a));
            else if (n.a.equals(BigDecimal.TEN)) return new BigUniversal(Maths.log(a));
            else return new BigUniversal(Maths.logn(a, n.a));
        } else if (isComplex() && n.isReal()) {
            if (n.a.equals(Component.CONSTANT_EULER_NUMBER.getValue()))
                return new BigUniversal(Maths.ln(getRadius()), getAngleRAD());
            else if (n.a.equals(BigDecimal.TEN))
                return new BigUniversal(Maths.log(getRadius()), getAngleRAD());
            else return this.ln().divide(new BigUniversal(Maths.ln(n.a)));
        } else return this.ln().divide(n.ln());
    }

    BigUniversal rt(BigUniversal base) {
        BigUniversal result;
        if (isReal() && base.isReal()) {
            result = new BigUniversal(Maths.rt(a, base.getRe()));
        } else if (isComplex() && base.isReal()) {
            result = new BigUniversal();
        } else {
            result = new BigUniversal();
        }
        return result;
    } // Komplex fehlt

    BigUniversal polar(BigUniversal angle) {
        angle.setRe(Maths.convAM(angle.getRe(), Preferences.MODE_ANGULAR_POLAR, Preferences.AngularMode.RAD));
        return new BigUniversal(a.multiply(Maths.cos(angle.getRe())), a.multiply(Maths.sin(angle.getRe())));
    }

    private BigUniversal setScale(int precision) {
        return new BigUniversal(a.setScale(precision, BigDecimal.ROUND_HALF_UP), b.setScale(precision, BigDecimal.ROUND_HALF_UP));
    }

    private boolean isComplex() {
        return (b.signum() != 0);
    }

    boolean isReal() {
        return (b.signum() == 0);
    }

}
