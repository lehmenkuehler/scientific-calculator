package org.lehmenkuehler.calculator.Mechanics;

import java.math.BigDecimal;

import org.lehmenkuehler.calculator.Enums.Component;

class Data
{
    private BigUniversal value1 = new BigUniversal();
    private BigUniversal value2 = new BigUniversal();
    private BigUniversal value3 = new BigUniversal();
    private BigUniversal value4 = new BigUniversal();

    private Component operation = Component.OPERATOR_ADD;
    private Component function = Component.VOID;
    private Component priority = Component.VOID;
    private boolean isArgument = false;
    private boolean variableSection = false;
    private int variableSectionId = 0;
    private boolean isVariable = false;

    Data()
    {
    }

    Data(Data data)
    {
        this.value1 = data.value1;
        this.value2 = data.value2;
        this.value3 = data.value3;
        this.value4 = data.value4;
        this.operation = data.operation;
        this.function = data.function;
        this.priority = data.priority;
        this.isArgument = data.isArgument;
        this.variableSection = data.variableSection;
        this.variableSectionId = data.variableSectionId;
        this.isVariable = data.isVariable;
    }

    void makeArgument()
    {
        isArgument = true;
    }

    void declareToVariable()
    {
        isVariable = true;
    }

    boolean isVariable()
    {
        return isVariable;
    }

    void revokeVariableStatus()
    {
        isVariable = false;
    }

    void declareToVariableSection()
    {
        variableSection = true;
    }

    boolean isVariableSectionStart()
    {
        return variableSection;
    }

    int getVariableSectionId()
    {
        return variableSectionId;
    }

    void setVariableSectionId(int id)
    {
        variableSectionId = id;
    }

    void revokeVariableSectionStatus()
    {
        variableSection = false;
    }

    boolean argumentStatus()
    {
        return isArgument;
    }

    void setValue1(BigUniversal v)
    {
        value1 = v;
    }

    void setValue2(BigUniversal v)
    {
        value2 = v;
    }

    void setValue3(BigUniversal v)
    {
        value3 = v;
    }

    void setValue4(BigUniversal v)
    {
        value4 = v;
    }

    void setValueRe(BigDecimal re)
    {
        value1.setRe(re);
    }

    void setValueIm(BigDecimal im)
    {
        value1.setIm(im);
    }

    void setFunction(Component comp)
    {
        function = comp;
    }

    void setOperation(Component comp)
    {
        operation = comp;
    }

    void setPriorityUp()
    {
        priority = Component.BRACKET_OPEN;
    }

    void setPriorityDown()
    {
        priority = Component.BRACKET_CLOSE;
    }

    BigUniversal getValue1()
    {
        return value1;
    }

    BigUniversal getValue2()
    {
        return value2;
    }

    BigUniversal getValue3()
    {
        return value3;
    }

    BigUniversal getValue4()
    {
        return value4;
    }

    Component getFunction()
    {
        return function;
    }

    boolean operationAdd()
    {
        return (operation.equals(Component.OPERATOR_ADD));
    }

    boolean operationSubtract()
    {
        return (operation.equals(Component.OPERATOR_SUBTRACT));
    }

    boolean operationMultiply()
    {
        return (operation.equals(Component.OPERATOR_MULTIPLY));
    }

    boolean operationDivide()
    {
        return (operation.equals(Component.OPERATOR_DIVIDE) || operation.equals(Component.OPERATOR_FRACTION));
    }

    boolean priorityStart()
    {
        return (priority.equals(Component.BRACKET_OPEN));
    }

    boolean priorityStop()
    {
        return (priority.equals(Component.BRACKET_CLOSE));
    }

    boolean functionExistent()
    {
        return (!function.equals(Component.VOID));
    }

    boolean equalsZero()
    {
        return (value1.getRe().equals(BigDecimal.ZERO) && value1.getIm().equals(BigDecimal.ZERO));
    }

    boolean equalsPiHalf()
    {
        return (value1.getRe().subtract(Component.CONSTANT_PI_HALF.getValue()).abs().compareTo(new BigDecimal("1E-15")) < 0);
    }

    boolean isPositiveInteger()
    {
        return (value1.isReal() && value1.getRe().remainder(BigDecimal.ONE).equals(BigDecimal.ZERO) && value1.getRe().compareTo(BigDecimal.ZERO) >= 0);
    }


    void clearValues()
    {
        value1 = new BigUniversal();
        value2 = new BigUniversal();
        value3 = new BigUniversal();
        value4 = new BigUniversal();
    }

    void clearFunction()
    {
        function = Component.VOID;
    }

    void clearOperation()
    {
        operation = Component.VOID;
    }

    void clearPriority()
    {
        priority = Component.VOID;
    }

    void clear()
    {
        clearValues();
        clearFunction();
        clearOperation();
        clearPriority();
    }
}
