package org.lehmenkuehler.calculator.Mechanics;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Enums.Error;
import org.lehmenkuehler.calculator.Main;
import org.lehmenkuehler.calculator.Numeral;

public class Parser {

    private final List<Element> script = new ArrayList<>();
    private List<Component> componentList = new ArrayList<>();
    private List<Data> data = new ArrayList<>();
    private final List<Integer> answerIds = new ArrayList<>();

    private int index = 0;

    public List<Data> getData() {
        buildData();
        return data;
    }

    String getComponentIdString() {
        String s = "";
        for (Element e : script) {
            s += e.getComponent().ordinal() + ",";
        }
        return s;
    }

    public List<Element> getScript() {
        return script;
    }

    /**
     * Returns the component of an element.
     *
     * @param pos Position of the called element
     * @return Component at a certain position
     */
    public Component getComponent(int pos) {
        if (pos >= 0 && pos < script.size()) return script.get(pos).getComponent();
        else return Component.VOID;
    }

    public void insertElement(Element element, int pos) {

        if (pos >= 0 && pos < script.size() && Check.isPhantom(script.get(pos).getComponent()))
            script.set(pos, element);
        else script.add(pos, element);
    }

    public int[] findConversionRange(int pos) {

        if (script.size() == 0) return new int[0];
        if (pos == script.size()) pos = script.size() - 1;

        // conversion at end of input
        if (pos >= 0 && pos < script.size()) {
            int start, stop;

            if (Check.isConvertable(script.get(pos).getComponent())) {
                start = pos;
                stop = pos;
            } else if (pos > 0 && Check.isConvertable(script.get(pos - 1).getComponent())) {
                start = pos - 1;
                stop = pos - 1;
            } else return new int[0];

            while (start >= 0) {
                if (start > 0 && !Check.isConvertable(script.get(start - 1).getComponent())) break;
                if (start >= 0 && !Check.isConvertable(script.get(start).getComponent())) break;
                start--;
            }
            while (stop < script.size()) {
                if (stop < script.size() && !Check.isConvertable(script.get(stop).getComponent())) break;
                if (stop < script.size() - 1 && !Check.isConvertable(script.get(stop + 1).getComponent())) break;
                stop++;
            }

            return new int[]{start, stop};

        } else return new int[0];
    }

    /**
     * Checks if the numerator of a certain fraction is defined.
     *
     * @param fractionSignPos Position of the fraction sign.
     * @return Numerator defined or not defined.
     */
    public boolean emptyNumerator(int fractionSignPos) {
        return (script.get(fractionSignPos - 2).getComponent().getType() == Component.Type.PHANTOM
                || script.get(fractionSignPos - 2).getComponent() == Component.BRACKET_OPEN_FRACTION1);
    }

    /**
     * Removes an element from the input list.
     * Removes several elements if their root component is deleted.
     * Possibly adds a phantom.
     * Skips elements that are linked to eg. a function.
     *
     * @param pos position to be removed
     */
    public void removeElement(int pos) {
        // return if impossible index
        if (pos < 0) return;

        // skip commas
        if (script.get(pos).getComponent() == Component.SEPARATOR_COMMA) return;

        // delete all elements that are associated with a possible root element
        if (Check.isRootObject(script.get(pos).getComponent()) || script.get(pos).getComponent() == Component.OPERATOR_FRACTION) {
            int id = script.get(pos).getId();
            for (int i = script.size() - 1; i >= 0; i--) {
                if (script.get(i).getId() == id) script.remove(i);
            }
            //pos++;
        } else if (Check.isLinkedObject(script.get(pos).getComponent())) {
            return;
        } else {
            script.remove(pos);
        }

        // insert phantom for empty brackets, between exponential function and closing bracket
        if (pos > 0 && pos < script.size() && script.get(pos - 1).getComponent() == Component.FUNCTION_POW && Check.isBracketClose(script.get(pos).getComponent())) {
            script.add(pos, new Element(script.get(pos - 1).getComponent().getPhantom().get(0), script.get(pos - 1).getId()));
        }

        // insert phantom for empty brackets, between function and closing bracket
        if (pos > 0 && pos < script.size() && Check.isFunction(script.get(pos - 1).getComponent()) && Check.isBracketClose(script.get(pos).getComponent())) {
            script.add(pos, new Element(script.get(pos - 1).getComponent().getPhantom().get(0), script.get(pos - 1).getId()));
        }

        // insert phantom for empty brackets, between function and comma
        if (pos > 0 && pos < script.size() && Check.isFunction(script.get(pos - 1).getComponent()) && script.get(pos).getComponent() == Component.SEPARATOR_COMMA) {
            script.add(pos, new Element(script.get(pos - 1).getComponent().getPhantom().get(0), script.get(pos - 1).getId()));
        }

        // insert phantom for empty brackets, between comma and comma - HAS NOT BEEN TESTED
        if (pos > 0 && pos < script.size() && script.get(pos - 1).getComponent() == Component.SEPARATOR_COMMA && script.get(pos).getComponent() == Component.SEPARATOR_COMMA) {
            int index = pos;
            int commas = 1;
            while (index > 0 && !Check.isFunction(script.get(index - 1).getComponent())) {
                index--;
                if (script.get(pos).getComponent() == Component.SEPARATOR_COMMA) commas++;
            }
            script.add(pos, new Element(script.get(index - 1).getComponent().getPhantom().get(commas), script.get(index - 1).getId()));
        }

        // insert phantom for empty brackets, between comma and closing bracket
        if (pos > 0 && pos < script.size() && script.get(pos - 1).getComponent() == Component.SEPARATOR_COMMA && Check.isBracketClose(script.get(pos).getComponent())) {
            int index = pos;
            int commas = 1;
            while (index > 0 && !Check.isFunction(script.get(index - 1).getComponent())) {
                index--;
                if (script.get(pos).getComponent() == Component.SEPARATOR_COMMA) commas++;
            }
            script.add(pos, new Element(script.get(index - 1).getComponent().getPhantom().get(commas), script.get(index - 1).getId()));
        }

    }

    /**
     * Finds the nearest function's index before the entered position
     *
     * @param pos Position to start from
     * @return The first function found before the position
     */
    public int getPreviousFunctionIndex(int pos) {
        int functionIndex = pos;

        // prevent out of range index
        if (functionIndex >= script.size()) functionIndex = script.size() - 1;

        // loop through the script until a function is found or the script ends
        while (functionIndex > 0 && (script.get(functionIndex).getComponent().getType() != Component.Type.FUNCTION)) {
            functionIndex--;
        }
        return functionIndex;
    }

    /**
     * Finds the nearest operator before the entered position
     *
     * @param pos Position to start from
     * @return The first operator found before the position
     */
    public int getFractionStart(int pos) {

        if (pos == 0) return 0;


        // previous component is an operator
        if (Check.isOperator(script.get(pos - 1).getComponent())) return pos;

        // previous component is a function
        if (Check.isFunction(script.get(pos - 1).getComponent())) return pos;

        // previous component is a comma
        if (script.get(pos - 1).getComponent() == Component.SEPARATOR_COMMA) return pos;

        // previous component is a closing bracket
        if (Check.isBracketClose(script.get(pos - 1).getComponent())) {

            // count brackets
            int bracketCount = 1;
            int index = pos;
            if (index >= script.size()) index = script.size();
            while (index >= 0) {
                index--;
                if (index == 0) return 0;
                if (Check.isBracketClose(script.get(index).getComponent())) bracketCount++;
                if (Check.isFunction(script.get(index).getComponent())) bracketCount--;
                if (Check.isBracketOpen(script.get(index).getComponent())) bracketCount--;
                if (bracketCount == 0) break;
            }
            return index + 1;
        }

        // current component is a closing bracket
        if (pos < script.size() && Check.isBracketClose(script.get(pos).getComponent())) {

            // count brackets
            int bracketCount = 1;
            int index = pos;
            while (index >= 0) {
                index--;
                if (Check.isBracketClose(script.get(index).getComponent())) bracketCount++;
                if (Check.isFunction(script.get(index).getComponent())) bracketCount--;
                if (Check.isBracketOpen(script.get(index).getComponent())) bracketCount--;
                if (bracketCount == 0) break;
            }
            return index + 1;
        }

        int index = pos;
        while (index > 0 &&
                !Check.isOperator(script.get(index - 1).getComponent()) &&
                !Check.isFunction(script.get(index - 1).getComponent())) {
            index--;
        }
        return index;
    }


    /**
     * Convert the script to a list containing components only
     */
    private void elementListToComponentList() {
        for (Element e : script) {
            if (e.getComponent() == Component.ANSWER || e.getComponent() == Component.ANSWER_LAST) answerIds.add(e.getAnswerId());
            if (e.getComponent().getType() == Component.Type.PHANTOM) {
                Main.globalError = Error.MISSING_ARGUMENT;
                throw new RuntimeException();
            }
            componentList.add(e.getComponent());
        }
    }


    private void arrangeMultiplicationSigns() {
        for (int i = 0; i < componentList.size(); i++) {
            if (i + 1 < componentList.size() && componentList.get(i).getType() == Component.Type.FIGURE) {
                if (componentList.get(i + 1).getType() == Component.Type.CONSTANT
                        || componentList.get(i + 1).getType() == Component.Type.VARIABLE
                        || componentList.get(i + 1).getType() == Component.Type.BRACKET_OPEN
                        || componentList.get(i + 1).getType() == Component.Type.FUNCTION) {
                    componentList.add(i + 1, Component.OPERATOR_MULTIPLY);
                    i++;
                }
            } else if (i + 1 < componentList.size() && componentList.get(i).getType() == Component.Type.CONSTANT || componentList.get(i).getType() == Component.Type.VARIABLE) {
                if (componentList.get(i + 1).getType() == Component.Type.CONSTANT
                        || componentList.get(i + 1).getType() == Component.Type.VARIABLE
                        || componentList.get(i + 1).getType() == Component.Type.BRACKET_OPEN
                        || componentList.get(i + 1).getType() == Component.Type.FIGURE
                        || componentList.get(i + 1).getType() == Component.Type.FUNCTION) {
                    componentList.add(i + 1, Component.OPERATOR_MULTIPLY);
                    i++;
                }
            } else if (i + 1 < componentList.size() && componentList.get(i).getType() == Component.Type.BRACKET_CLOSE) {
                if (componentList.get(i + 1).getType() == Component.Type.CONSTANT
                        || componentList.get(i + 1).getType() == Component.Type.VARIABLE
                        || componentList.get(i + 1).getType() == Component.Type.BRACKET_OPEN
                        || componentList.get(i + 1).getType() == Component.Type.FIGURE
                        || componentList.get(i + 1).getType() == Component.Type.FUNCTION) {
                    componentList.add(i + 1, Component.OPERATOR_MULTIPLY);
                    i++;
                }
            }
        }
    }

    private void buildData() {

        componentList = new ArrayList<>();
        data = new ArrayList<>();

        elementListToComponentList();
        arrangeMultiplicationSigns();

        int level = 0;
        int autoCloseLevel = 0;
        int diffToOldCloseLevel = 0;
        String numberTemp = "";

        newDataElement();
        index = 0;
        int answerCount = 0;

        for (int n = 0; n < componentList.size(); n++) {

            Component c = componentList.get(n);

            // FIGURE
            if (Check.isFigure(c) || c.equals(Component.SEPARATOR_DOT)) {
                if (n + 1 < componentList.size() && (Check.isFigure(componentList.get(n + 1)) || componentList.get(n + 1).equals(Component.SEPARATOR_DOT)))
                    numberTemp += c.getSymbol();
                else {
                    numberTemp += c.getSymbol();
                    data.get(index).setValueRe(Numeral.parseToDec(numberTemp));
                    numberTemp = "";
                }
            }
            // VARIABLE
            else if (Check.isVariable(c)) {
                data.get(index).declareToVariable();
            }
            // CONSTANT
            else if (Check.isConstant(c)) {
                if (c.equals(Component.CONSTANT_IMAGINARY))
                    data.get(index).setValueIm(BigDecimal.ONE);
                    // ANSWER
                else if (c == Component.ANSWER || c == Component.ANSWER_LAST) {
                    data.get(index).setPriorityUp();
                    level++;
                    newDataElement();
                    data.get(index).setValueRe(Numeral.parseToDec(History.getResultRe(answerIds.get(answerCount))));
                    newDataElement();
                    data.get(index).setValueIm(Numeral.parseToDec(History.getResultIm(answerIds.get(answerCount))));
                    answerCount++;
                    newDataElement();
                    data.get(index).setPriorityDown();
                    level--;
                }
                else data.get(index).setValueRe(c.getValue());
            }
            // FUNCTION
            else if (Check.isFunction(c) || Check.isAdvancedFunction(c)) {
                data.get(index).setFunction(c);

                data.get(index).setPriorityUp();
                level++;
                newDataElement();

                data.get(index).setPriorityUp();
                data.get(index).makeArgument();
                if (Check.isAdvancedFunction(c)) {
                    data.get(index).declareToVariableSection();
                }
                level++;
                diffToOldCloseLevel = level - autoCloseLevel;
                autoCloseLevel = level;
                newDataElement();
            }
            // CONNECTIVE FUNCTION
            else if (Check.isConnectiveFunction(c)) {
                newDataElement();
                data.get(index).setFunction(c);
                newDataElement();
                if (c == Component.FUNCTION_POW) {
                    data.get(index).setPriorityUp();
                    level++;
                    newDataElement();
                }
            }
            // OPERATOR
            else if (Check.isOperator(c) || c == Component.NEGATIVE_SIGN) {
                newDataElement();
                if (c.equals(Component.NEGATIVE_SIGN))
                    data.get(index).setOperation(Component.OPERATOR_SUBTRACT);
                else data.get(index).setOperation(c);
            }
            // BRACKET OPEN
            else if (Check.isBracketOpen(c)) {
                data.get(index).setPriorityUp();
                level++;
                newDataElement();
            }
            // BRACKET CLOSE
            else if (Check.isBracketClose(c)) {
                if (level == autoCloseLevel) {
                    autoCloseLevel = autoCloseLevel - diffToOldCloseLevel;
                    newDataElement();
                    data.get(index).setPriorityDown();
                    level--;
                }
                newDataElement();
                data.get(index).setPriorityDown();
                level--;
            }
            // COMMA
            else if (c.equals(Component.SEPARATOR_COMMA)) {
                newDataElement();
                data.get(index).setPriorityDown();
                level--;
                newDataElement();
                data.get(index).setPriorityUp();
                level++;
                data.get(index).makeArgument();
                newDataElement();
            }
            // PHANTOM
            else if (c.getType() == Component.Type.PHANTOM) {
                Main.globalError = Error.MISSING_ARGUMENT;
            }
        }
    }

    private void newDataElement() {
        data.add(new Data());
        index++;
    }


}
