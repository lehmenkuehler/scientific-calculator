package org.lehmenkuehler.calculator.Mechanics;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.lehmenkuehler.calculator.Enums.Component;
import org.lehmenkuehler.calculator.Enums.Error;
import org.lehmenkuehler.calculator.Main;

public class Engine
{

    private final int precision = 100;
    private final Result result = new Result();
    private List<Data> mainData = new ArrayList<>();
    //private final List<Data> variableSection1 = new ArrayList<>();
    //private final List<Data> variableSection2 = new ArrayList<>();
    private BigUniversal variable = new BigUniversal();

    private List<List<Data>> variableSection = new ArrayList<List<Data>>();

    public Engine()
    {

    }


    public Result getResult()
    {
        return result;
    }

    public void executeCalculation()
    {
        result.setValue(calculate(mainData));
    }

    public void setData(List<Data> data)
    {
        mainData = data;
    }

    private BigUniversal calculate(List<Data> data)
    {

        boolean standardRun = !data.get(0).isVariableSectionStart();

        // add result object
        data.add(0, new Data());

        // Search for Advanced functions / variable section parts

        if (standardRun && true)
        {
            int advancedFunctionCount = 0;
            List<Integer> startPoints = new ArrayList<>();
            int index1 = 0;
            for (Data d : data)
            {
                if (Check.isAdvancedFunction(d.getFunction()))
                {
                    variableSection.add(new ArrayList<Data>());
                    advancedFunctionCount++;
                    startPoints.add(index1);
                }
                index1++;
            }
            while (advancedFunctionCount != 0)
            {
                int priorityLevel = 0;
                int start = startPoints.get(startPoints.size() - 1);
                data.get(start).setVariableSectionId(advancedFunctionCount);
                start++;
                data.get(start).setVariableSectionId(advancedFunctionCount);
                for (; ; )
                {
                    if (data.get(start).priorityStart()) priorityLevel++;
                    if (data.get(start).priorityStop()) priorityLevel--;

                    variableSection.get(advancedFunctionCount - 1).add(new Data(data.get(start)));
                    data.remove(start);

                    if (priorityLevel == 0)
                    {
                        advancedFunctionCount--;
                        break;
                    }
                }
            }
        }


        int rounds = roundsNeeded(data);
        for (int round = 1; round <= rounds; round++)
        {
            int argumentNumber = 1;
            int start = findStart(data);
            int stop = findStop(data);

            /*
            if (standardRun &&  data.get(start - 1).isVariableSectionStart()) {
                for (int i = start - 1; i <= stop + 1; i++) {
                    variableSection1.add(new Data(data.get(i)));
                    variableSection2.add(new Data(data.get(i)));
                }
                for (int i = 0; i <= stop - start + 2; i++) {
                    data.remove(start - 1);
                }
                continue;
            }*/

            if (!standardRun)
            {
                for (int n = start; n <= stop; n++)
                {
                    if (data.get(n).isVariable())
                    {
                        data.get(n).setValue1(variable);
                        data.get(n).revokeVariableStatus();
                    }
                }
            }

            for (int n = start; n <= stop; n++)
            {
                if (data.get(n).argumentStatus())
                {
                    switch (argumentNumber)
                    {
                        case 1:
                            data.get(start - 1).setValue1(data.get(n).getValue1());
                            break;
                        case 2:
                            data.get(start - 1).setValue2(data.get(n).getValue1());
                            break;
                        case 3:
                            data.get(start - 1).setValue3(data.get(n).getValue1());
                            break;
                        case 4:
                            data.get(start - 1).setValue4(data.get(n).getValue1());
                            break;
                    }
                    argumentNumber++;
                    data.get(n).clear();
                }
            }

            for (int n = start; n <= stop; n++)
            {
                if (data.get(n).functionExistent())
                {
                    switch (data.get(n).getFunction())
                    {
                        case FUNCTION_SINE:
                            data.get(n).setValue1(data.get(n).getValue1().sin());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_COSINE:
                            data.get(n).setValue1(data.get(n).getValue1().cos());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_TANGENT:
                            data.get(n).setValue1(data.get(n).getValue1().tan());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_COSECANT:
                            data.get(n).setValue1(data.get(n).getValue1().csc());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_SECANT:
                            data.get(n).setValue1(data.get(n).getValue1().sec());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_COTANGENT:
                            data.get(n).setValue1(data.get(n).getValue1().cot());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_ARCSINE:
                            data.get(n).setValue1(data.get(n).getValue1().arcsin());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_ARCCOSINE:
                            data.get(n).setValue1(data.get(n).getValue1().arccos());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_ARCTANGENT:
                            data.get(n).setValue1(data.get(n).getValue1().arctan());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_SINE:
                            data.get(n).setValue1(data.get(n).getValue1().sinh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_COSINE:
                            data.get(n).setValue1(data.get(n).getValue1().cosh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_TANGENT:
                            data.get(n).setValue1(data.get(n).getValue1().tanh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_AREASINE:
                            data.get(n).setValue1(data.get(n).getValue1().arsinh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_AREACOSINE:
                            data.get(n).setValue1(data.get(n).getValue1().arcosh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_HYPERBOLIC_AREATANGENT:
                            data.get(n).setValue1(data.get(n).getValue1().artanh());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_NATURAL_LOGARITHM:
                            data.get(n).setValue1(data.get(n).getValue1().ln());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_COMMON_LOGARITHM:
                            data.get(n).setValue1(data.get(n).getValue1().log());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_EXPONENTIAL:
                            data.get(n).setValue1(data.get(n).getValue1().exp());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_SQUARE_ROOT:
                            data.get(n).setValue1(data.get(n).getValue1().sqrt());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_CUBIC_ROOT:
                            data.get(n).setValue1(data.get(n).getValue1().crt());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_ABSOLUTE:
                            data.get(n).setValue1(data.get(n).getValue1().abs());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_INVERSE:
                            data.get(n).setValue1(data.get(n).getValue1().inv());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_FACTORIAL_1:
                            data.get(n).setValue1(data.get(n).getValue1().fact());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_NTH_ROOT:
                            data.get(n).setValue1(data.get(n).getValue2().rt(data.get(n).getValue1()));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_NTH_LOGARITHM:
                            data.get(n).setValue1(data.get(n).getValue2().logn(data.get(n).getValue1()));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_PERMUTATION:
                            data.get(n).setValue1(new BigUniversal(new BigDecimal(Maths.perm(data.get(n).getValue1().getRe().toBigInteger(), data.get(n).getValue2().getRe().toBigInteger()))));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_COMBINATION:
                            data.get(n).setValue1(new BigUniversal(new BigDecimal(Maths.comb(data.get(n).getValue1().getRe().toBigInteger(), data.get(n).getValue2().getRe().toBigInteger()))));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_LIMES:
                            variable = data.get(n).getValue1();
                            data.get(n).setValue1(calculate(variableSection.get(data.get(n).getVariableSectionId())));
                            data.get(n).clearFunction();
                            data.get(n).setValue2(new BigUniversal());
                            break;
                        case FUNCTION_DERIVATION:
                            variable = data.get(n).getValue1().add(new BigUniversal(new BigDecimal("1E-20")));
                            List<Data> variableSectionAlternative = new ArrayList<>();
                            for (Data d : variableSection.get(data.get(n).getVariableSectionId() - 1))
                            {
                                variableSectionAlternative.add(new Data(d));
                            }
                            BigUniversal f_of_x_plus_h = calculate(variableSection.get(data.get(n).getVariableSectionId() - 1));
                            variable = data.get(n).getValue1();
                            BigUniversal f_of_x = calculate(variableSectionAlternative);
                            data.get(n).setValue1(f_of_x_plus_h.subtract(f_of_x).divide(new BigUniversal(new BigDecimal("1E-20"))));
                            data.get(n).clearFunction();
                            data.get(n).setValue2(new BigUniversal());
                            break;
                        case FUNCTION_INTEGRATION:
                            int steps = 40;
                            BigUniversal a = data.get(n).getValue1();
                            BigUniversal step = data.get(n).getValue2().subtract(a).divide(new BigUniversal(new BigDecimal(steps)));
                            BigUniversal stepHalf = step.divide(new BigUniversal(new BigDecimal("2")));
                            BigUniversal res = new BigUniversal();

                            List<BigUniversal> heightList = new ArrayList<>();

                            List<Data> tempList = new ArrayList<>();

                            for (Data d : variableSection.get(data.get(n).getVariableSectionId() - 1))
                            {
                                tempList.add(new Data(d));
                            }

                            variable = a;
                            heightList.add(calculate(tempList));
                            for (int i = 0; i < steps; i++)
                            {
                                variable = variable.add(step);
                                tempList.clear();
                                for (Data d : variableSection.get(data.get(n).getVariableSectionId() - 1))
                                {
                                    tempList.add(new Data(d));
                                }
                                heightList.add(calculate(tempList));
                            }
                            variable = data.get(n).getValue2();
                            tempList.clear();
                            for (Data d : variableSection.get(data.get(n).getVariableSectionId() - 1))
                            {
                                tempList.add(new Data(d));
                            }
                            heightList.add(calculate(tempList));

                            heightList.add(new BigUniversal(BigDecimal.ZERO));

                            for (int i = 0; i < steps; i++)
                            {
                                res = res.add(stepHalf.multiply(heightList.get(i).add(heightList.get(i + 1))));
                            }
                            data.get(n).setValue1(res);
                            data.get(n).clearFunction();
                            data.get(n).setValue2(new BigUniversal());
                            break;
                        case FUNCTION_SIGNUM:
                            data.get(n).setValue1(data.get(n).getValue1().sgn());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_REAL_PART:
                            data.get(n).setValue1(new BigUniversal(data.get(n).getValue1().getRe()));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_IMAGINARY_PART:
                            data.get(n).setValue1(new BigUniversal(BigDecimal.ZERO, data.get(n).getValue1().getIm()));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_CONJUGATE:
                            data.get(n).setValue1(data.get(n).getValue1().conj());
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_LEAST_COMMON_MULTIPLE:
                            data.get(n).setValue1(new BigUniversal(new BigDecimal(Maths.lcm(data.get(n).getValue1().getRe().toBigInteger(), data.get(n).getValue2().getRe().toBigInteger()))));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_GREATEST_COMMON_DIVISOR:
                            data.get(n).setValue1(new BigUniversal(new BigDecimal(Maths.GCD(data.get(n).getValue1().getRe().toBigInteger(), data.get(n).getValue2().getRe().toBigInteger()))));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_RANDOM_NUMBER:
                            data.get(n).setValue1(new BigUniversal(Maths.rand(data.get(n).getValue1().getRe(), data.get(n).getValue2().getRe())));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_RANDOM_INTEGER:
                            data.get(n).setValue1(new BigUniversal(Maths.randInt(data.get(n).getValue1().getRe(), data.get(n).getValue2().getRe())));
                            data.get(n).clearFunction();
                            break;
                        case FUNCTION_CIS:
                            data.get(n).setValue1(data.get(n).getValue1().cis());
                            data.get(n).clearFunction();
                        default:
                            break;
                    }
                }
            }
            for (int n = start; n <= stop; n++)
            {
                if (data.get(n).functionExistent())
                {
                    switch (data.get(n).getFunction())
                    {
                        case FUNCTION_FACTORIAL_CONNECTIVE:
                            data.get(n - 1).setValue1(data.get(n - 1).getValue1().fact());
                            data.get(n).clear();
                            break;
                        case FUNCTION_DOUBLE_FACTORIAL_CONNECTIVE:
                            data.get(n - 1).setValue1(data.get(n - 1).getValue1().dfact());
                            data.get(n).clear();
                            break;
                        case FUNCTION_POW:
                            if (data.get(n).getValue1().isReal() && data.get(n - 1).getValue1().getRe().abs().subtract(Component.CONSTANT_EULER_NUMBER.getValue()).signum() == 0)
                            {
                                data.get(n - 1).setValue1(data.get(n + 1).getValue1().exp());
                            } else
                            {
                                data.get(n - 1).setValue1(data.get(n - 1).getValue1().pow(data.get(n + 1).getValue1()));
                            }
                            data.remove(n + 1);
                            data.remove(n);
                            stop = stop - 2;
                            break;
                        case FUNCTION_POLAR:
                            data.get(n - 1).setValue1(data.get(n - 1).getValue1().polar(data.get(n + 1).getValue1()));
                            data.remove(n + 1);
                            data.remove(n);
                            stop = stop - 2;
                            break;
                        case FUNCTION_MODULO:
                            data.get(n - 1).setValue1(data.get(n - 1).getValue1().mod(data.get(n + 1).getValue1()));
                            data.remove(n + 1);
                            data.remove(n);
                            stop = stop - 2;
                            break;
                        case FUNCTION_PERCENTAGE_CHANGE:
                            data.get(n - 1).setValue1((data.get(n + 1).getValue1().subtract(data.get(n - 1).getValue1()).divide(data.get(n - 1).getValue1()).multiply(new BigUniversal(new BigDecimal("100")))));
                            data.remove(n + 1);
                            data.remove(n);
                            stop = stop - 2;
                            break;
                        default:
                            break;
                    }
                }
            }
            for (int n = stop; n >= start; n--)
            {
                if (data.get(n).operationDivide())
                {
                    if (data.get(n).equalsZero())
                    {
                        Main.globalError = Error.DIVIDE_BY_ZERO;
                        return new BigUniversal();
                    }
                    data.get(n).setValue1(data.get(n).getValue1().inv());
                    data.get(n).setOperation(Component.OPERATOR_MULTIPLY);
                }
            }
            for (int n = stop; n >= start; n--)
            {
                if (data.get(n).operationMultiply())
                {
                    data.get(n - 1).setValue1(data.get(n - 1).getValue1().multiply(data.get(n).getValue1()));
                    data.get(n).clearValues();
                    data.get(n).clearOperation();
                }
            }
            for (int n = start; n <= stop; n++)
            {
                if (data.get(n).operationAdd())
                {
                    data.get(start - 1).setValue1(data.get(start - 1).getValue1().add(data.get(n).getValue1()));
                    data.get(n).clearValues();
                    data.get(n).clearOperation();
                } else if (data.get(n).operationSubtract())
                {
                    data.get(start - 1).setValue1(data.get(start - 1).getValue1().subtract(data.get(n).getValue1()));
                    data.get(n).clearValues();
                    data.get(n).clearOperation();
                }
            }


            if (round != rounds)
            {
                for (int i = start; i <= stop + 1; i++)
                {
                    data.remove(start);
                }
            }
            data.get(start - 1).clearPriority();
        }
        return data.get(0).getValue1();
    }


    private int roundsNeeded(List<Data> data)
    {
        int n = 1;
        for (Data d : data)
        {
            if (d.priorityStart()) n++;
        }
        return n;
    }

    private int findStart(List<Data> data)
    {
        int start = 0;
        while (start < data.size() - 1 && !data.get(start).priorityStop())
        {
            start++;
        }
        while (start > 0 && !data.get(start).priorityStart())
        {
            start--;
        }
        return start + 1;
    }

    private int findStop(List<Data> data)
    {
        int stop = 0;
        while (stop < data.size() - 1 && !data.get(stop).priorityStop())
        {
            stop++;
        }
        if (data.get(stop).priorityStop()) return stop - 1;
        else return stop;
    }


}
