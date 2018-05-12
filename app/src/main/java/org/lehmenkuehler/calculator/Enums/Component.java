package org.lehmenkuehler.calculator.Enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lehmenkuehler.calculator.Preferences;
import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public enum Component
{
    VOID,

    ANSWER(Type.CONSTANT, "ans", 3 + 2),
    ANSWER_LAST(Type.CONSTANT, "ans", 3),

    FIGURE_ZERO(Type.FIGURE, "0"),
    FIGURE_ONE(Type.FIGURE, "1"),
    FIGURE_TWO(Type.FIGURE, "2"),
    FIGURE_THREE(Type.FIGURE, "3"),
    FIGURE_FOUR(Type.FIGURE, "4"),
    FIGURE_FIVE(Type.FIGURE, "5"),
    FIGURE_SIX(Type.FIGURE, "6"),
    FIGURE_SEVEN(Type.FIGURE, "7"),
    FIGURE_EIGHT(Type.FIGURE, "8"),
    FIGURE_NINE(Type.FIGURE, "9"),
    FIGURE_A(Type.FIGURE, "A"),
    FIGURE_B(Type.FIGURE, "B"),
    FIGURE_C(Type.FIGURE, "C"),
    FIGURE_D(Type.FIGURE, "D"),
    FIGURE_E(Type.FIGURE, "E"),
    FIGURE_F(Type.FIGURE, "F"),

    SEPARATOR_DOT(Type.DOT, "."),
    SEPARATOR_COMMA(Type.COMMA, ", "),

    VARIABLE_X(Type.VARIABLE, "x"),

    OPERATOR_ADD(Type.OPERATOR, " + "),
    OPERATOR_SUBTRACT(Type.OPERATOR, " - "),
    OPERATOR_MULTIPLY(Type.OPERATOR, " × "),
    OPERATOR_DIVIDE(Type.OPERATOR, " ÷ "),
    NEGATIVE_SIGN(Type.SIGN, "-"),
    OPERATOR_FRACTION(Type.OPERATOR, "/"),

    BRACKET_OPEN(Type.BRACKET_OPEN, "("),
    BRACKET_CLOSE(Type.BRACKET_CLOSE, ")"),
    BRACKET_CLOSE_FUNCTION(Type.BRACKET_CLOSE, ")"),
    BRACKET_CLOSE_POWER_FUNCTION(Type.BRACKET_CLOSE, "&ThinSpace;", 1), // &ThinSpace;
    BRACKET_OPEN_FRACTION1(Type.BRACKET_OPEN, "&ThinSpace;", 1),
    BRACKET_CLOSE_FRACTION1(Type.BRACKET_CLOSE, "&ZeroWidthSpace", 1),
    BRACKET_OPEN_FRACTION2(Type.BRACKET_OPEN, "&ZeroWidthSpace", 1),
    BRACKET_CLOSE_FRACTION2(Type.BRACKET_CLOSE, "&ThinSpace;", 1),

    PHANTOM_N(Type.PHANTOM, "n"),
    PHANTOM_A(Type.PHANTOM, "a"),
    PHANTOM_B(Type.PHANTOM, "b"),
    PHANTOM_C(Type.PHANTOM, "c"),
    PHANTOM_R(Type.PHANTOM, "r"),
    PHANTOM_X(Type.PHANTOM, "x"),
    PHANTOM_X1(Type.PHANTOM, "x1"),
    PHANTOM_X2(Type.PHANTOM, "x2"),
    PHANTOM_Z(Type.PHANTOM, "z"),
    PHANTOM_F_OF_X(Type.PHANTOM, "f(x)"),
    PHANTOM_MIN(Type.PHANTOM, "x<sub><small>min</small></sub>", 4),
    PHANTOM_MAX(Type.PHANTOM, "x<sub><small>max</small></sub>", 4),


    FUNCTION_SINE(
            Type.FUNCTION,
            "sin(",
            "sin(x)",
            Utility.getContext().getString(R.string.FUNCTION_SINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_COSINE(
            Type.FUNCTION,
            "cos(",
            "cos(x)",
            Utility.getContext().getString(R.string.FUNCTION_COSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_TANGENT(
            Type.FUNCTION,
            "tan(",
            "tan(x)",
            Utility.getContext().getString(R.string.FUNCTION_COSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_COSECANT(
            Type.FUNCTION,
            "csc(",
            "csc(x)",
            Utility.getContext().getString(R.string.FUNCTION_COSECANT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_SECANT(
            Type.FUNCTION,
            "sec(",
            "sec(x)",
            Utility.getContext().getString(R.string.FUNCTION_SECANT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_COTANGENT(
            Type.FUNCTION,
            "cot(",
            "cot(x)",
            Utility.getContext().getString(R.string.FUNCTION_COTANGENT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_ARCSINE(
            Type.FUNCTION,
            "arcsin(",
            "arcsin(x)",
            Utility.getContext().getString(R.string.FUNCTION_ARCSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_ARCCOSINE(
            Type.FUNCTION,
            "arccos(",
            "arccos(x)",
            Utility.getContext().getString(R.string.FUNCTION_ARCCOSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_ARCTANGENT(
            Type.FUNCTION,
            "arctan(",
            "arctan(x)",
            Utility.getContext().getString(R.string.FUNCTION_ARCTANGENT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_SINE(
            Type.FUNCTION,
            "sinh(",
            "sinh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_SINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_COSINE(
            Type.FUNCTION,
            "cosh(",
            "cosh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_COSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_TANGENT(
            Type.FUNCTION,
            "tanh(",
            "tanh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_TANGENT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_AREASINE(
            Type.FUNCTION,
            "arsinh(",
            "arsinh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_AREASINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_AREACOSINE(
            Type.FUNCTION,
            "arcosh(",
            "arcosh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_AREACOSINE),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_HYPERBOLIC_AREATANGENT(
            Type.FUNCTION,
            "artanh(",
            "artanh(x)",
            Utility.getContext().getString(R.string.FUNCTION_HYPERBOLIC_AREATANGENT),
            FunctionTag.TRIGONOMETRIC_AND_HYPERBOLIC,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_SQUARE_ROOT(
            Type.FUNCTION,
            "√(",
            "√(x)",
            Utility.getContext().getString(R.string.FUNCTION_SQUARE_ROOT),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_CUBIC_ROOT(
            Type.FUNCTION,
            "<sup><small><small>3</small></small></sup>√(", 3,
            "<sup><small><small>3</small></small></sup>√(x)",
            Utility.getContext().getString(R.string.FUNCTION_CUBIC_ROOT),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_NTH_ROOT(
            Type.FUNCTION,
            "√(",
            "√(n, x)",
            Utility.getContext().getString(R.string.FUNCTION_NTH_ROOT),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Arrays.asList(PHANTOM_N, PHANTOM_X)
    ),
    FUNCTION_NATURAL_LOGARITHM(
            Type.FUNCTION,
            "ln(",
            "ln(x)",
            Utility.getContext().getString(R.string.FUNCTION_NATURAL_LOGARITHM),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_COMMON_LOGARITHM(
            Type.FUNCTION,
            "log(",
            "log(x)",
            Utility.getContext().getString(R.string.FUNCTION_COMMON_LOGARITHM),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_NTH_LOGARITHM(
            Type.FUNCTION,
            "log<sub><small>n</small></sub>(", 5,
            "log<sub><small>n</small></sub>(n, x)",
            Utility.getContext().getString(R.string.FUNCTION_NTH_LOGARITHM),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Arrays.asList(PHANTOM_N, PHANTOM_X)
    ),
    FUNCTION_EXPONENTIAL(
            Type.FUNCTION,
            "exp(",
            "exp(x)",
            Utility.getContext().getString(R.string.FUNCTION_EXPONENTIAL),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_INVERSE(
            Type.FUNCTION,
            "inv(",
            "inv(x)",
            Utility.getContext().getString(R.string.FUNCTION_INVERSE),
            FunctionTag.EXPONENTS_AND_LOGARITHMS,
            Collections.singletonList(PHANTOM_X)
    ),
    FUNCTION_SIGNUM(
            Type.FUNCTION,
            "sgn(",
            Collections.singletonList(PHANTOM_X)),
    FUNCTION_ABSOLUTE(Type.FUNCTION, "abs(", Collections.singletonList(PHANTOM_X)),
    FUNCTION_FACTORIAL_1(Type.FUNCTION, "fact(", Collections.singletonList(PHANTOM_N)),
    FUNCTION_COMBINATION(Type.FUNCTION, "C(", Arrays.asList(PHANTOM_N, PHANTOM_R)),
    FUNCTION_PERMUTATION(Type.FUNCTION, "P(", Arrays.asList(PHANTOM_N, PHANTOM_R)),
    FUNCTION_LEAST_COMMON_MULTIPLE(Type.FUNCTION, "LCM(", Arrays.asList(PHANTOM_N, PHANTOM_C)),
    FUNCTION_GREATEST_COMMON_DIVISOR(Type.FUNCTION, "GCD(", Arrays.asList(PHANTOM_N, PHANTOM_C)),

    FUNCTION_REAL_PART(Type.FUNCTION, "re(", Collections.singletonList(PHANTOM_Z)),
    FUNCTION_IMAGINARY_PART(Type.FUNCTION, "im(", Collections.singletonList(PHANTOM_Z)),
    FUNCTION_CONJUGATE(Type.FUNCTION, "conj(", Collections.singletonList(PHANTOM_Z)),
    FUNCTION_CIS(Type.FUNCTION, "cis(", Collections.singletonList(PHANTOM_X)),

    FUNCTION_RANDOM_NUMBER(Type.FUNCTION, "rand(", Arrays.asList(PHANTOM_MIN, PHANTOM_MAX)),
    FUNCTION_RANDOM_INTEGER(Type.FUNCTION, "randInt(", Arrays.asList(PHANTOM_MIN, PHANTOM_MAX)),

    FUNCTION_POW(Type.CONNECTIVE_FUNCTION, "&ZeroWidthSpace", 1, Collections.singletonList(PHANTOM_X)), // &ZeroWidthSpace
    FUNCTION_POLAR(Type.CONNECTIVE_FUNCTION, " ∠ ", 3),
    FUNCTION_FACTORIAL_CONNECTIVE(Type.CONNECTIVE_FUNCTION, "!"),
    FUNCTION_DOUBLE_FACTORIAL_CONNECTIVE(Type.CONNECTIVE_FUNCTION, "!!"),
    FUNCTION_MODULO(Type.CONNECTIVE_FUNCTION, " mod "),
    FUNCTION_PERCENTAGE_CHANGE(Type.CONNECTIVE_FUNCTION, " Δ% "),

    FUNCTION_LIMES(Type.ADVANCED_FUNCTION, "lim("),
    FUNCTION_DERIVATION(Type.ADVANCED_FUNCTION, "<sup><small>d</small></sup>/<sub><small>dx</small></sub>(", 5, Arrays.asList(PHANTOM_F_OF_X, PHANTOM_X)),
    FUNCTION_INTEGRATION(Type.ADVANCED_FUNCTION, "∫(", Arrays.asList(PHANTOM_F_OF_X, PHANTOM_X1, PHANTOM_X2)),

    CONSTANT_IMAGINARY(Type.CONSTANT, Preferences.IMAGINARY_CHARACTER),
    CONSTANT_VARIABLE(Type.VARIABLE, "x"),

    CONSTANT_PERCENT(
            Type.CONSTANT,
            "%",
            "0.01",
            Utility.getContext().getString(R.string.CONSTANT_PERCENT),
            ConstantTag.VOID,
            new BigDecimal("0.01")
    ),
    CONSTANT_YOCTO(
            Type.CONSTANT,
            "y",
            "10<sup><small>-24</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_YOCTO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-24")
    ),
    CONSTANT_ZEPTO(
            Type.CONSTANT,
            "z",
            "10<sup><small>-21</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ZEPTO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-21")
    ),
    CONSTANT_ATTO(
            Type.CONSTANT,
            "a",
            "10<sup><small>-18</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ATTO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-18")
    ),
    CONSTANT_FEMTO(
            Type.CONSTANT,
            "f",
            "10<sup><small>-15</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_FEMTO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-15")
    ),
    CONSTANT_PICO(
            Type.CONSTANT,
            "p",
            "10<sup><small>-12</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_PICO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-12")
    ),
    CONSTANT_NANO(
            Type.CONSTANT,
            "n",
            "10<sup><small>-9</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_NANO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-9")
    ),
    CONSTANT_MICRO(
            Type.CONSTANT,
            "μ",
            "10<sup><small>-6</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MICRO),
            ConstantTag.PREFIX,
            new BigDecimal("1E-6")
    ),
    CONSTANT_MILLI(
            Type.CONSTANT,
            "m",
            "0.001",
            Utility.getContext().getString(R.string.CONSTANT_MILLI),
            ConstantTag.PREFIX,
            new BigDecimal("0.001")
    ),
    CONSTANT_CENTI(
            Type.CONSTANT,
            "c",
            "0.01",
            Utility.getContext().getString(R.string.CONSTANT_CENTI),
            ConstantTag.PREFIX,
            new BigDecimal("0.01")
    ),
    CONSTANT_DECI(
            Type.CONSTANT,
            "d",
            "0.1",
            Utility.getContext().getString(R.string.CONSTANT_DECI),
            ConstantTag.PREFIX,
            new BigDecimal("0.1")
    ),
    CONSTANT_DEKA(
            Type.CONSTANT,
            "da",
            "10",
            Utility.getContext().getString(R.string.CONSTANT_DEKA),
            ConstantTag.PREFIX,
            new BigDecimal("10")
    ),
    CONSTANT_HECTO(
            Type.CONSTANT,
            "h",
            "100",
            Utility.getContext().getString(R.string.CONSTANT_HECTO),
            ConstantTag.PREFIX,
            new BigDecimal("100")
    ),
    CONSTANT_KILO(
            Type.CONSTANT,
            "k",
            "1000",
            Utility.getContext().getString(R.string.CONSTANT_KILO),
            ConstantTag.PREFIX,
            new BigDecimal("1000")
    ),
    CONSTANT_MEGA(
            Type.CONSTANT,
            "M",
            "10<sup><small>6</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MEGA),
            ConstantTag.PREFIX,
            new BigDecimal("1E6")
    ),
    CONSTANT_GIGA(
            Type.CONSTANT,
            "G",
            "10<sup><small>9</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_GIGA),
            ConstantTag.PREFIX,
            new BigDecimal("1E9")
    ),
    CONSTANT_TERA(
            Type.CONSTANT,
            "T",
            "10<sup><small>12</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_TERA),
            ConstantTag.PREFIX,
            new BigDecimal("1E12")
    ),
    CONSTANT_PETA(
            Type.CONSTANT,
            "P",
            "10<sup><small>15</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_PETA),
            ConstantTag.PREFIX,
            new BigDecimal("1E15")
    ),
    CONSTANT_EXA(
            Type.CONSTANT,
            "E",
            "10<sup><small>18</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_EXA),
            ConstantTag.PREFIX,
            new BigDecimal("1E18")
    ),
    CONSTANT_ZETTA(
            Type.CONSTANT,
            "Z",
            "10<sup><small>21</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ZETTA),
            ConstantTag.PREFIX,
            new BigDecimal("1E21")
    ),
    CONSTANT_YOTTA(
            Type.CONSTANT,
            "Y",
            "10<sup><small>24</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_YOTTA),
            ConstantTag.PREFIX,
            new BigDecimal("1E24")
    ),
    CONSTANT_PI(
            Type.CONSTANT,
            "π",
            "3.141 592 653 589...",
            Utility.getContext().getString(R.string.CONSTANT_PI),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679821480865132823066470938446095505822317253594081")
    ),
    CONSTANT_PI_HALF(
            Type.CONSTANT,
            "π/2",
            "1.570 796 326 794...",
            Utility.getContext().getString(R.string.CONSTANT_PI_HALF),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("1.5707963267948966192313216916397514420985846996875529104874722961539082031431044993140174126710585339910740432566411533235469223047752911158626797040")
    ),
    CONSTANT_EULER_NUMBER(
            Type.CONSTANT,
            "<i>e</i>", 1,
            "2.718 281 828 459...",
            Utility.getContext().getString(R.string.CONSTANT_EULER_NUMBER),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("2.7182818284590452353602874713526624977572470936999595749669676277240766303535475945713821785251664274274663919320030599218174135966290435729003342953")
    ),
    CONSTANT_EULER_MASCHERONI_CONSTANT(
            Type.CONSTANT,
            "<i>γ</i>", 1,
            "0.577 215 664 901...",
            Utility.getContext().getString(R.string.CONSTANT_EULER_MASCHERONI_CONSTANT),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("0.5772156649015328606065120900824024310421593359399235988057672348848677267776646709369470632917467495")
    ),
    CONSTANT_GOLDEN_RATIO(
            Type.CONSTANT,
            "<i>Φ</i>", 1,
            "1.618 033 988 749...",
            Utility.getContext().getString(R.string.CONSTANT_GOLDEN_RATIO),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("1.618033988749894848204586834365638117720309179805762862135448622705260462818902449707207204189391137484754088075")
    ),
    CONSTANT_INVERSE_GOLDEN_RATIO(
            Type.CONSTANT,
            "<i>φ</i>", 1,
            "0.618 033 988 749...",
            Utility.getContext().getString(R.string.CONSTANT_INVERSE_GOLDEN_RATIO),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("0.618033988749894848204586834365638117720309179805762862135448622705260462818902449707207204189391137484754088075")
    ),
    CONSTANT_SILVER_RATIO(
            Type.CONSTANT,
            "δ<sub><small>S</small></sub>", 2,
            "2.414 213 562 373...",
            Utility.getContext().getString(R.string.CONSTANT_SILVER_RATIO),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("2.41421356237309504880168872420969807856967187537694807317667973799073247846210703885038753432764157273501384623")
    ),
    CONSTANT_PLASTIC_CONSTANT(
            Type.CONSTANT,
            "ρ",
            "1.324 717 957 244...",
            Utility.getContext().getString(R.string.CONSTANT_PLASTIC_CONSTANT),
            ConstantTag.MATHEMATICAL,
            new BigDecimal("1.324717957244746025960908854478097340734404056901733364534015050302827851245547594054699347981787280329910920994")
    ),
    CONSTANT_SPEED_OF_LIGHT(
            Type.CONSTANT,
            "<i>c</i>", 1,
            "299 792 458 m s<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_SPEED_OF_LIGHT),
            ConstantTag.UNIVERSAL,
            new BigDecimal("299792458")
    ),
    CONSTANT_MAGNETIC(
            Type.CONSTANT,
            "<i>μ</i><sub><small>0</small></sub>", 2,
            "4π × 10<sup><small>-7</small></sup> = 12.566 370 614... × 10<sup><small>-7</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MAGNETIC),
            ConstantTag.UNIVERSAL,
            new BigDecimal("0.000001256637061435917295385057353311801153678867759750042328389977836923126562514483599451213930136846827192859234605312922658837537843820232892690143")
    ),
    CONSTANT_ELECTRIC(
            Type.CONSTANT,
            "<i>ε</i><sub><small>0</small></sub>", 2,
            "8.854 187 817... × 10<sup><small>-12</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRIC),
            ConstantTag.UNIVERSAL,
            new BigDecimal("0.0000000000088541878176203898505365630317107502606083701665994498081024171524053950954599821142852891607182008932867329183837820510568392933049328130732")
    ),
    CONSTANT_CHARACTERISTIC_IMPEDANCE(
            Type.CONSTANT,
            "<i>Z</i><sub><small>0</small></sub>", 2,
            "376.730 313 461... Ω",
            Utility.getContext().getString(R.string.CONSTANT_CHARACTERISTIC_IMPEDANCE),
            ConstantTag.UNIVERSAL,
            new BigDecimal("376.730313461770655468198400420319308268623508352418655232074638296707269221307698880166875199565586693648509990325420943056800892866887729032028431158505")
    ),
    CONSTANT_NEWTONIAN_CONSTANT_OF_GRAVITATION(
            Type.CONSTANT,
            "<i>G</i>", 1,
            "6.674 08(31) × 10<sup><small>-11</small></sup> m³ kg<sup><small>-1</small></sup> s<sup><small>-2</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_NEWTONIAN_CONSTANT_OF_GRAVITATION),
            ConstantTag.UNIVERSAL,
            new BigDecimal("0.0000000000667408")
    ),
    CONSTANT_PLANCK_CONSTANT(
            Type.CONSTANT,
            "<i>h</i>", 1,
            "6.626 070 040(81) × 10<sup><small>-34</small></sup> J s",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_CONSTANT),
            ConstantTag.UNIVERSAL,
            new BigDecimal("6.626070040E-34")
    ),
    CONSTANT_PLANCK_CONSTANT_REDUCED(
            Type.CONSTANT,
            "<i>ħ</i>", 1,
            "1.054 571 800(13) × 10<sup><small>-34</small></sup> J s",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_CONSTANT_REDUCED),
            ConstantTag.UNIVERSAL,
            new BigDecimal("1.054571800E-34")
    ),
    CONSTANT_PLANCK_MASS(
            Type.CONSTANT,
            "<i>m</i><sub><small>P</small></sub>", 2,
            "2.176 470(51) × 10<sup><small>-8</small></sup> kg",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_MASS),
            ConstantTag.UNIVERSAL,
            new BigDecimal("2.176470E-8")
    ),
    CONSTANT_PLANCK_TEMPERATURE(
            Type.CONSTANT,
            "<i>T</i><sub><small>P</small></sub>", 2,
            "1.416 808(33) × 10<sup><small>32</small></sup> K",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_TEMPERATURE),
            ConstantTag.UNIVERSAL,
            new BigDecimal("1.416808E32")
    ),
    CONSTANT_PLANCK_LENGTH(
            Type.CONSTANT,
            "<i>l</i><sub><small>P</small></sub>", 2,
            "1.616 229(38) × 10<sup><small>-35</small></sup> m",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_LENGTH),
            ConstantTag.UNIVERSAL,
            new BigDecimal("1.616229E-35")
    ),
    CONSTANT_PLANCK_TIME(
            Type.CONSTANT,
            "<i>t</i><sub><small>P</small></sub>", 2,
            "5.391 16(13) × 10<sup><small>-44</small></sup> s",
            Utility.getContext().getString(R.string.CONSTANT_PLANCK_TIME),
            ConstantTag.UNIVERSAL,
            new BigDecimal("5.39116E-44")
    ),
    CONSTANT_ELEMENTARY_CHARGE(
            Type.CONSTANT,
            "<i>e</i>", 1,
            "1.602 176 6208(98) × 10<sup><small>-19</small></sup> C",
            Utility.getContext().getString(R.string.CONSTANT_ELEMENTARY_CHARGE),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("1.6021766208E-19")
    ),
    CONSTANT_MAGNETIC_FLUX_QUANTUM(
            Type.CONSTANT,
            "<i>Φ</i><sub><small>0</small></sub>", 2,
            "2.067 833 831(13) × 10<sup><small>-15</small></sup> Wb",
            Utility.getContext().getString(R.string.CONSTANT_MAGNETIC_FLUX_QUANTUM),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("2.067833831E-15")
    ),
    CONSTANT_CONDUCTANCE_QUANTUM(
            Type.CONSTANT,
            "<i>G</i><sub><small>0</small></sub>", 2,
            "7.748 091 7310(18) × 10<sup><small>-5</small></sup> S",
            Utility.getContext().getString(R.string.CONSTANT_CONDUCTANCE_QUANTUM),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("7.7480917310E-5")
    ),
    CONSTANT_JOSEPHON_CONSTANT(
            Type.CONSTANT,
            "<i>K</i><sub><small>J</small></sub>", 2,
            "483 597.8525(30) × 10<sup><small>9</small></sup> Hz V<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_JOSEPHON_CONSTANT),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("483597.8525E9")
    ),
    CONSTANT_VON_KLITZING_CONSTANT(
            Type.CONSTANT,
            "<i>R</i><sub><small>K</small></sub>", 2,
            "25 812.807 4555(59) Ω",
            Utility.getContext().getString(R.string.CONSTANT_VON_KLITZING_CONSTANT),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("25812.8074555")
    ),
    CONSTANT_BOHR_MAGNETON(
            Type.CONSTANT,
            "<i>μ</i><sub><small>B</small></sub>", 2,
            "927.400 9994(57) × 10<sup><small>-26</small></sup> J T<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_BOHR_MAGNETON),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("927.4009994E-26")
    ),
    CONSTANT_NUCLEAR_MAGNETON(
            Type.CONSTANT,
            "<i>μ</i><sub><small>N</small></sub>", 2,
            "5.050 783 699(31) × 10<sup><small>-27</small></sup> J T<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_NUCLEAR_MAGNETON),
            ConstantTag.ELECTROMAGNETIC,
            new BigDecimal("5.050783699E-27")
    ),
    CONSTANT_FINE_STRUCTURE_CONSTANT(
            Type.CONSTANT,
            "α",
            "7.297 352 5664(17) × 10<sup><small>-3</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_FINE_STRUCTURE_CONSTANT),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("7.2973525664E-3")
    ),
    CONSTANT_RYDBERG_CONSTANT(
            Type.CONSTANT,
            "<i>R</i><sub><small>∞</small></sub>", 2,
            "10 973 731.568 508(65) m<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_RYDBERG_CONSTANT),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("10973731.568508")
    ),
    CONSTANT_BOHR_RADIUS(
            Type.CONSTANT,
            "<i>a</i><sub><small>0</small></sub>", 2,
            "0.529 177 210 67(12) × 10<sup><small>-10</small></sup> m",
            Utility.getContext().getString(R.string.CONSTANT_BOHR_RADIUS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("0.52917721067E-10")
    ),
    CONSTANT_HARTREE_ENERGY(
            Type.CONSTANT,
            "<i>E</i><sub><small>h</small></sub>", 2,
            "4.359 744 650(54) × 10<sup><small>-18</small></sup> J",
            Utility.getContext().getString(R.string.CONSTANT_HARTREE_ENERGY),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("4.359744650E-18")
    ),
    CONSTANT_QUANTUM_OF_CIRCULATION(
            Type.CONSTANT,
            "<i>h</i>/2<i>m</i><sub><small>e</small></sub>", 5,
            "3.636 947 5486(17) × 10<sup><small>-4</small></sup> m² s<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_QUANTUM_OF_CIRCULATION),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("3.6369475486E-4")
    ),
    CONSTANT_FERMI_COUPLING_CONSTANT(
            Type.CONSTANT,
            "<i>G</i><i>F</i>/(<i>ħc</i>)³", 8,
            "1.166 3787(6) × 10<sup><small>-5</small></sup> Ge V<sup><small>-2</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_FERMI_COUPLING_CONSTANT),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("1.1663787E-5")
    ),
    CONSTANT_WEAK_MIXING_ANGLE(
            Type.CONSTANT,
            "<i>s</i>²<sub><small>W</small></sub>", 3,
            "0.2223(21)",
            Utility.getContext().getString(R.string.CONSTANT_WEAK_MIXING_ANGLE),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("0.2223")
    ),
    CONSTANT_ELECTRON_MASS(
            Type.CONSTANT,
            "<i>m</i><sub><small>e</small></sub>", 2,
            "9.109 383 56(11) × 10<sup><small>-31</small></sup> kg",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_MASS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("9.10938356E-31")
    ),
    CONSTANT_ELECTRON_REST_ENERGY(
            Type.CONSTANT,
            "<i>m</i><sub><small>e</small></sub><i>c</i>²", 4,
            "8.187 105 65(10) × 10<sup><small>-14</small></sup> kg",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_REST_ENERGY),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("8.18710565E-14")
    ),
    CONSTANT_ELECTRON_MOLAR_MASS(
            Type.CONSTANT,
            "<i>M</i><sub><small>e</small></sub>", 2,
            "5.485 799 090 70(16) × 10<sup><small>-7</small></sup> kg mol<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_MOLAR_MASS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("5.48579909070E-7")
    ),
    CONSTANT_COMPTON_WAVELENGTH(
            Type.CONSTANT,
            "<i>λ</i><sub><small>C</small></sub>", 2,
            "2.426 310 2367(11) × 10<sup><small>-12</small></sup> m",
            Utility.getContext().getString(R.string.CONSTANT_COMPTON_WAVELENGTH),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("2.4263102367E-12")
    ),
    CONSTANT_CLASSICAL_ELECTRON_RADIUS(
            Type.CONSTANT,
            "<i>r</i><sub><small>e</small></sub>", 2,
            "2.817 940 3227(19) × 10<sup><small>-15</small></sup> m",
            Utility.getContext().getString(R.string.CONSTANT_CLASSICAL_ELECTRON_RADIUS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("2.8179403227E-15")
    ),
    CONSTANT_THOMSON_CROSS_SECTION(
            Type.CONSTANT,
            "<i>σ</i><sub><small>e</small></sub>", 2,
            "0.665 245 871 58(91) × 10<sup><small>-28</small></sup> m²",
            Utility.getContext().getString(R.string.CONSTANT_THOMSON_CROSS_SECTION),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("0.66524587158E-28")
    ),
    CONSTANT_ELECTRON_MAGNETIC_MOMENT(
            Type.CONSTANT,
            "<i>μ</i><sub><small>e</small></sub>", 2,
            "−928.476 4620(57) × 10<sup><small>-26</small></sup> J T<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_MAGNETIC_MOMENT),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("928.4764620E-26").negate()
    ),
    CONSTANT_ELECTRON_MAGNETIC_MOMENT_ANOMALY(
            Type.CONSTANT,
            "<i>a</i><sub><small>e</small></sub>", 2,
            "1.15965218091(26) × 10<sup><small>-3</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_MAGNETIC_MOMENT_ANOMALY),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("1.15965218091E-3")
    ),
    CONSTANT_ELECTRON_G_FACTOR(
            Type.CONSTANT,
            "<i>g</i><sub><small>e</small></sub>", 2,
            "−2.002 319 304 361 82(52)",
            Utility.getContext().getString(R.string.CONSTANT_ELECTRON_G_FACTOR),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("2.00231930436182").negate()
    ),
    CONSTANT_MUON_MASS(
            Type.CONSTANT,
            "<i>m</i><sub><small>μ</small></sub>", 2,
            "1.883 531 594(48) × 10<sup><small>-28</small></sup> kg",
            Utility.getContext().getString(R.string.CONSTANT_MUON_MASS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("1.883531594E-28")
    ),
    CONSTANT_MUON_REST_ENERGY(
            Type.CONSTANT,
            "<i>m</i><sub><small>μ</small></sub><i>c</i>²", 4,
            "1.692 833 774(43) × 10<sup><small>-11</small></sup> J",
            Utility.getContext().getString(R.string.CONSTANT_MUON_REST_ENERGY),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("1.692833774E-11")
    ),
    CONSTANT_MUON_MOLAR_MASS(
            Type.CONSTANT,
            "<i>M</i><sub><small>μ</small>", 2,
            "0.113 428 9257(25) × 10<sup><small>-3</small></sup> kg mol<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MUON_MOLAR_MASS),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("0.1134289257E-3")
    ),
    CONSTANT_MUON_COMPTON_WAVELENGTH(
            Type.CONSTANT,
            "<i>λ</i><sub><small>C,μ</small></sub>", 4,
            "11.734 441 11(26) × 10<sup><small>-15</small></sup> m",
            Utility.getContext().getString(R.string.CONSTANT_MUON_COMPTON_WAVELENGTH),
            ConstantTag.ATOMIC_AND_NUCLEAR,
            new BigDecimal("11.73444111E-15")
    ),
    CONSTANT_AVOGADRO_CONSTANT(
            Type.CONSTANT,
            "<i>N</i><sub><small>A</small></sub>", 2,
            "6.022 140 857(74) × 10<sup><small>23</small></sup> mol<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_AVOGADRO_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("6.022140857E23")
    ),
    CONSTANT_ATOMIC_MASS_CONSTANT(
            Type.CONSTANT,
            "<i>m</i><sub><small>u</small></sub>", 2,
            "1.660 539 040(20) × 10<sup><small>-27</small></sup> kg",
            Utility.getContext().getString(R.string.CONSTANT_ATOMIC_MASS_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("1.660539040E-27")
    ),
    CONSTANT_FARADAY_CONSTANT(
            Type.CONSTANT,
            "<i>F</i>", 1,
            "96 485.332 89(59) C mol<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_FARADAY_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("96485.33289")
    ),
    CONSTANT_MOLAR_PLANCK_CONSTANT(
            Type.CONSTANT,
            "<i>N</i><sub><small>A</small></sub><i>h</i>", 3,
            "3.990 312 7110(18) × 10<sup><small>-10</small></sup> J s mol<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MOLAR_PLANCK_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("3.9903127110E-10")
    ),
    CONSTANT_MOLAR_GAS_CONSTANT(
            Type.CONSTANT,
            "<i>R</i>", 1,
            "8.314 4598(48) J mol<sup><small>-1</small></sup> K<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_MOLAR_GAS_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("8.3144598")
    ),
    CONSTANT_BOLTZMANN_CONSTANT(
            Type.CONSTANT,
            "<i>k</i>", 1,
            "1.380 648 52(79) × 10<sup><small>-23</small></sup> J K<sup><small>-1</small></sup>",
            Utility.getContext().getString(R.string.CONSTANT_BOLTZMANN_CONSTANT),
            ConstantTag.PHYSICOCHEMICAL,
            new BigDecimal("1.38064852E-23")
    );


    private Type type = Type.VOID;
    private String symbol = "";
    private String name = "";
    private String description = "";
    private ConstantTag tag;
    private FunctionTag functionTag;
    private BigDecimal value = BigDecimal.ZERO;
    private List<Component> phantom = new ArrayList<>();
    private int id = 0;
    private int length = 0;

    Component()
    {
    }

    Component(Type type, String symbol)
    {
        this.type = type;
        this.symbol = symbol;
    }

    Component(Type type, String symbol, int length)
    {
        this.type = type;
        this.symbol = symbol;
        this.length = length;
    }


    Component(Type type, String symbol, String description, String name, FunctionTag functionTag, List<Component> phantom)
    {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.functionTag = functionTag;
        this.phantom = phantom;
    }

    Component(Type type, String symbol, String description, String name, ConstantTag tag, BigDecimal value)
    {
        this.type = type;
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.value = value;
    }

    Component(Type type, String symbol, int length, String description, String name, ConstantTag tag, BigDecimal value)
    {
        this.type = type;
        this.symbol = symbol;
        this.length = length;
        this.name = name;
        this.description = description;
        this.tag = tag;
        this.value = value;
    }

    Component(Type type, String symbol, int length, String description, String name, FunctionTag functionTag, List<Component> phantom)
    {
        this.type = type;
        this.symbol = symbol;
        this.length = length;
        this.name = name;
        this.description = description;
        this.functionTag = functionTag;
        this.phantom = phantom;
    }

    //DELETE WHEN DONE
    Component(Type type, String symbol, List<Component> phantom)
    {
        this.type = type;
        this.symbol = symbol;
        this.phantom = phantom;
    }

    Component(Type type, String symbol, int length, List<Component> phantom)
    {
        this.type = type;
        this.symbol = symbol;
        this.length = length;
        this.phantom = phantom;
    }

    public Type getType()
    {
        return type;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public ConstantTag getConstantTag()
    {
        return tag;
    }

    public BigDecimal getValue()
    {
        return value;
    }

    public List<Component> getPhantom()
    {
        return phantom;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public FunctionTag getFunctionTag()
    {
        return functionTag;
    }

    public int getLength()
    {
        if (length == 0) return symbol.length();
        else return length;
    }

    public enum Type
    {
        VOID, FIGURE, OPERATOR, CONSTANT, FUNCTION, CONNECTIVE_FUNCTION, ADVANCED_FUNCTION, BRACKET_OPEN, BRACKET_CLOSE, VARIABLE, DOT, COMMA, PHANTOM, SIGN
    }

    public enum ConstantTag
    {
        VOID("VOID"),
        PREFIX("PREFIX"),
        UNIVERSAL(Utility.getContext().getString(R.string.CONSTANTS_UNIVERSAL)),
        ELECTROMAGNETIC(Utility.getContext().getString(R.string.CONSTANTS_ELECTROMAGNETIC)),
        ATOMIC_AND_NUCLEAR(Utility.getContext().getString(R.string.CONSTANTS_ATOMIC_AND_NUCLEAR)),
        MATHEMATICAL(Utility.getContext().getString(R.string.CONSTANTS_MATHEMATICAL)),
        PHYSICOCHEMICAL(Utility.getContext().getString(R.string.CONSTANTS_PHYSICOCHEMICAL));

        String name = "";

        ConstantTag(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }

    public enum FunctionTag
    {
        VOID("VOID"),
        TRIGONOMETRIC_AND_HYPERBOLIC(Utility.getContext().getString(R.string.FUNCTIONS_TRIGONOMETRIC_AND_HYPERBOLIC)),
        EXPONENTS_AND_LOGARITHMS(Utility.getContext().getString(R.string.FUNCTIONS_EXPONENTS_AND_LOGARITHMS));

        String name = "";

        FunctionTag(String name)
        {
            this.name = name;
        }

        public String getName()
        {
            return name;
        }
    }

}
