package org.bananaLaba.shaderModel.template.condition;

import org.bananaLaba.shaderModel.ParameterMap;

public class NumericComparisonCondition extends SingleTemplateCondition {

    private ComparisonOperator operator;
    private double comparand;

    public ComparisonOperator getOperator() {
        return this.operator;
    }

    public void setOperator(final ComparisonOperator operator) {
        if (operator == null) {
            throw new IllegalArgumentException("Expected a not-null operator!");
        }

        this.operator = operator;
    }

    public double getComparand() {
        return this.comparand;
    }

    public void setComparand(final double comparand) {
        this.comparand = comparand;
    }

    @Override
    public boolean check(final ParameterMap parameters) {
        final double actualValue = parameters.getParameterValue(this.getParameterId(), Number.class).doubleValue();
        if (this.operator == ComparisonOperator.EQUALS) {
            return actualValue == this.comparand;
        } else if (this.operator == ComparisonOperator.NOT_EQUALS) {
            return actualValue != this.comparand;
        } else if (this.operator == ComparisonOperator.GREATER) {
            return actualValue > this.comparand;
        } else if (this.operator == ComparisonOperator.GREATER_OR_EQUALS) {
            return actualValue >= this.comparand;
        } else if (this.operator == ComparisonOperator.LESS) {
            return actualValue < this.comparand;
        } else {
            return actualValue <= this.comparand;
        }
    }

}
