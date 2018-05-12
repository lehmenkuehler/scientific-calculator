package org.lehmenkuehler.calculator.Mechanics;

import java.math.BigDecimal;

public class Result {
    private BigUniversal value = new BigUniversal();

    Result() {
    }

    public Result(BigDecimal convertedValue) {
        value = new BigUniversal(convertedValue);
    }

    public BigDecimal getRe() {
        return value.getRe().stripTrailingZeros();
    }

    public BigDecimal getIm() {
        return value.getIm().stripTrailingZeros();
    }

    public BigDecimal getRadius() {
        return value.getRadius().stripTrailingZeros();
    }

    public BigDecimal getAngle() {
        return value.getAngle().stripTrailingZeros();
    }

    void setValue(BigUniversal value) {
        this.value = value;
    }

}
