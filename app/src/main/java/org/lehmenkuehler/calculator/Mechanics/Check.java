package org.lehmenkuehler.calculator.Mechanics;

import org.lehmenkuehler.calculator.Enums.Component;

public class Check {


    public static boolean isLinkedObject(Component comp) {
        return (comp == Component.BRACKET_OPEN_FRACTION1 || comp == Component.BRACKET_CLOSE_FRACTION1
                || comp == Component.BRACKET_OPEN_FRACTION2 || comp == Component.BRACKET_CLOSE_FRACTION2
                || comp == Component.BRACKET_CLOSE_POWER_FUNCTION
                || comp == Component.BRACKET_CLOSE_FUNCTION || comp.getType().equals(Component.Type.PHANTOM));
    }

    public static boolean isRootObject(Component comp) {
        return (isFunction(comp) || comp == Component.FUNCTION_POW);
    }

    public static boolean isFunction(Component comp) {
        return comp.getType().equals(Component.Type.FUNCTION);
    }

    public static boolean isAdvancedFunction(Component comp) {
        return comp.getType().equals(Component.Type.ADVANCED_FUNCTION);
    }

    public static boolean isConnectiveFunction(Component comp) {
        return comp.getType().equals(Component.Type.CONNECTIVE_FUNCTION);
    }

    public static boolean isPhantom(Component comp) {
        return comp.getType().equals(Component.Type.PHANTOM);
    }

    public static boolean isOperator(Component comp) {
        return comp.getType().equals(Component.Type.OPERATOR);
    }

    public static boolean isFigure(Component comp) {
        return comp.getType().equals(Component.Type.FIGURE);
    }

    public static boolean isFigureBIN(Component comp) {
        return (comp == Component.FIGURE_ZERO || comp == Component.FIGURE_ONE);
    }

    public static boolean isFigureOCT(Component comp) {
        return (comp == Component.FIGURE_ZERO || comp == Component.FIGURE_ONE
                || comp == Component.FIGURE_TWO || comp == Component.FIGURE_THREE
                || comp == Component.FIGURE_FOUR || comp == Component.FIGURE_FIVE
                || comp == Component.FIGURE_SIX || comp == Component.FIGURE_SEVEN);
    }

    public static boolean isFigureDEC(Component comp) {
        return (comp == Component.FIGURE_ZERO || comp == Component.FIGURE_ONE
                || comp == Component.FIGURE_TWO || comp == Component.FIGURE_THREE
                || comp == Component.FIGURE_FOUR || comp == Component.FIGURE_FIVE
                || comp == Component.FIGURE_SIX || comp == Component.FIGURE_SEVEN
                || comp == Component.FIGURE_EIGHT || comp == Component.FIGURE_NINE);
    }

    public static boolean isConvertable(Component comp) {
        return isFigureDEC(comp) || comp == Component.SEPARATOR_DOT;
    }

    public static boolean isBracket(Component comp) {
        return isBracketOpen(comp) || isBracketClose(comp);
    }

    public static boolean isPureBracket(Component comp) {
        return comp == Component.BRACKET_OPEN || comp == Component.BRACKET_CLOSE;
    }

    public static boolean isConstant(Component comp) {
        return comp.getType().equals(Component.Type.CONSTANT) || comp.getType().equals(Component.Type.VARIABLE);
    }

    public static boolean isBracketOpen(Component comp) {
        return comp.getType().equals(Component.Type.BRACKET_OPEN);
    }

    public static boolean isBracketClose(Component comp) {
        return comp.getType().equals(Component.Type.BRACKET_CLOSE);
    }

    public static boolean isVariable(Component comp) {
        return comp.getType().equals(Component.Type.VARIABLE);
    }
}
