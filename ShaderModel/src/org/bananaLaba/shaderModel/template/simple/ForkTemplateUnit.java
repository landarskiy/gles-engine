package org.bananaLaba.shaderModel.template.simple;

import java.util.ArrayList;
import java.util.List;

import org.bananaLaba.shaderModel.template.TemplateCondition;

public class ForkTemplateUnit extends ConditionalTemplateUnit {

    private List<ConditionalBranch> branches = new ArrayList<>();

    @Override
    protected String instantiateInternal(final TemplateContext context) {
        for (final ConditionalBranch branch : this.branches) {
            if (branch.condition.check(context)) {
                return branch.unit.instantiate(context);
            }
        }

        return null;
    }

    public void addBranch(final TemplateUnit unit, final TemplateCondition condition) {
        if (unit == null) {
            throw new IllegalArgumentException("Expected a not-null branch template unit!");
        }

        if (condition == null) {
            throw new IllegalArgumentException("Expected a not-null branch condition!");
        }

        this.branches.add(new ConditionalBranch(unit, condition));
    }

    public void clearBranches() {
        this.branches.clear();
    }

    private static class ConditionalBranch {

        private TemplateCondition condition;
        private TemplateUnit unit;

        public ConditionalBranch(final TemplateUnit unit, final TemplateCondition condition) {
            this.unit = unit;
            this.condition = condition;
        }

    }

}
