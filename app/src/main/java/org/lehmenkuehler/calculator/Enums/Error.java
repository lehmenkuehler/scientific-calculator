package org.lehmenkuehler.calculator.Enums;

import org.lehmenkuehler.calculator.R;
import org.lehmenkuehler.calculator.Utility;

public enum Error
{
    VOID,
    SYNTAX(Utility.getContext().getResources().getString(R.string.ERROR_SYNTAX)),
    DIVIDE_BY_ZERO(Utility.getContext().getResources().getString(R.string.ERROR_DIVIDE_BY_ZERO)),
    ARGUMENT_OUT_OF_RANGE(Utility.getContext().getResources().getString(R.string.ERROR_ARGUMENT_OUT_OF_RANGE)),
    ARGUMENT_MUST_BE_POSITIVE_INTEGER(Utility.getContext().getResources().getString(R.string.ERROR_ARGUMENT_MUST_BE_POSITIVE_INTEGER)),
    MISSING_ARGUMENT(Utility.getContext().getResources().getString(R.string.ERROR_MISSING_ARGUMENT)),
    FACTORIAL_DOMAIN(Utility.getContext().getResources().getString(R.string.ERROR_FACTORIAL_DOMAIN));

    String message = "Error";

    Error(String message)
    {
        this.message = message;
    }

    Error()
    {

    }

    public String getMessage()
    {
        return message;
    }
}
