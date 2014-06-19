package org.bananaLaba.shaderModel.template.simple;

import java.util.ArrayList;
import java.util.List;

public class CompositeTemplateUnit implements TemplateUnit {

    private List<TemplateUnit> subUnits = new ArrayList<>();

    public void addSubUnit(final TemplateUnit unit) {
        this.subUnits.add(unit);
    }

    public void addSubUnits(final List<TemplateUnit> units) {
        this.subUnits.addAll(units);
    }

    public void clearSubUnits() {
        this.subUnits.clear();
    }

    @Override
    public String instantiate(final TemplateContext context) {
        final StringBuilder contentBuilder = new StringBuilder();
        for (final TemplateUnit unit : this.subUnits) {
            contentBuilder.append(unit.instantiate(context));
        }

        return contentBuilder.toString();
    }

}
