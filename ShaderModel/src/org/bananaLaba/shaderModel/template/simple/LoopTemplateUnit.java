package org.bananaLaba.shaderModel.template.simple;

import java.util.HashMap;
import java.util.Map;

public class LoopTemplateUnit implements TemplateUnit {

    private ParameterValue<Integer> countParameter;
    private ParameterValue<Double> stepParameter;
    private ParameterValue<Double> startValueParameter;

    private String counterVariableId;

    private boolean integerCounter;

    private TemplateUnit unit;

    public String getCounterVariableId() {
        return this.counterVariableId;
    }

    public void setCounterVariableId(final String counterVariableId) {
        this.counterVariableId = counterVariableId;
    }

    public TemplateUnit getUnit() {
        return this.unit;
    }

    public void setUnit(final TemplateUnit unit) {
        this.unit = unit;
    }

    public boolean isIntegerCounter() {
        return this.integerCounter;
    }

    public void setIntegerCounter(final boolean integerCounter) {
        this.integerCounter = integerCounter;
    }

    @Override
    public String instantiate(final TemplateContext context) {
        final int loopCount = this.countParameter.get(context);
        final StringBuilder builder = new StringBuilder();
        double counterValue = this.startValueParameter.get(context);
        double step = this.stepParameter.get(context);
        final LoopTemplateContext innerContext = new LoopTemplateContext(context);
        for (int i = 0; i < loopCount; i++) {
            if (this.integerCounter) {
                innerContext.setVariable(this.counterVariableId, (long) counterValue);
            } else {
                innerContext.setVariable(this.counterVariableId, counterValue);
            }
            builder.append(this.unit.instantiate(innerContext));

            counterValue += step;
        }

        return builder.toString();
    }

    private static class LoopTemplateContext implements TemplateContext {

        private TemplateContext parentContext;
        private Map<String, Object> variableMap = new HashMap<>();

        public LoopTemplateContext(final TemplateContext parentContext) {
            this.parentContext = parentContext;
        }

        public void setVariable(final String id, final Object value) {
            this.variableMap.put(id, value);
        }

        @Override
        public <T> T getParameterValue(final String id, final Class<T> type) {
            return this.parentContext.getParameterValue(id, type);
        }

        @Override
        public boolean isParameterPresent(final String id) {
            return this.parentContext.isParameterPresent(id);
        }

        @Override
        public String getFragment(final String id) {
            return this.parentContext.getFragment(id);
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> T getVariableValue(final String id, final Class<T> type) {
            if (this.variableMap.containsKey(id)) {
                return (T) this.variableMap.get(id);
            } else {
                return this.parentContext.getVariableValue(id, type);
            }
        }

    }

    public ParameterValue<Integer> getCountParameter() {
        return this.countParameter;
    }

    public void setCountParameter(final ParameterValue<Integer> countParameter) {
        this.countParameter = countParameter;
    }

    public ParameterValue<Double> getStepParameter() {
        return this.stepParameter;
    }

    public void setStepParameter(final ParameterValue<Double> stepParameter) {
        this.stepParameter = stepParameter;
    }

    public ParameterValue<Double> getStartValueParameter() {
        return this.startValueParameter;
    }

    public void setStartValueParameter(final ParameterValue<Double> startValueParameter) {
        this.startValueParameter = startValueParameter;
    }

}
