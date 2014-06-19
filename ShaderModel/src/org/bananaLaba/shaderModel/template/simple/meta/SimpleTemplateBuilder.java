package org.bananaLaba.shaderModel.template.simple.meta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bananaLaba.shaderModel.template.BaseTemplateBuilder;
import org.bananaLaba.shaderModel.template.ForkTemplateBuilder;
import org.bananaLaba.shaderModel.template.LoopTemplateBuilder;
import org.bananaLaba.shaderModel.template.TemplateBuilder;
import org.bananaLaba.shaderModel.template.simple.CompositeTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.ReferenceUnit;
import org.bananaLaba.shaderModel.template.simple.StaticUnit;
import org.bananaLaba.shaderModel.template.simple.TemplateReferenceType;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;

// TODO: add merging for a sequence of static units.
// TODO: add string formatting (i.e. remove redundant whitespaces as possible).
public abstract class SimpleTemplateBuilder implements TemplateBuilder {

    private List<TemplateUnit> units = new ArrayList<>();

    private Set<String> dependenyIds = new HashSet<>();

    private List<BaseTemplateBuilder> nestedBuilders = new ArrayList<>();

    @Override
    public void addStaticUnit(final String content) {
        this.units.add(new StaticUnit(content));
    }

    @Override
    public void addInclusionUnit(final String id) {
        if (!this.dependenyIds.contains(id)) {
            this.dependenyIds.add(id);
        }

        this.units.add(new ReferenceUnit(id, TemplateReferenceType.FRAGMENT));
    }

    @Override
    public void addParameterUnit(final String id) {
        this.units.add(new ReferenceUnit(id, TemplateReferenceType.PARAMETER));
    }

    @Override
    public void addVariableUnit(final String id) {
        this.units.add(new ReferenceUnit(id, TemplateReferenceType.VARIABLE));
    }

    @Override
    public ForkTemplateBuilder addForkUnit() {
        final SimpleConditionalTemplateBuilder nestedBuilder = new SimpleConditionalTemplateBuilder() {

            // TODO: all this stuff with anonymous inner classes are potential mess with state.
            // The best solution is to use intermediate specification beans to store template units
            // in prototype form which should be instantiated when a shader factory instance is requested.
            private int index = SimpleTemplateBuilder.this.units.size();
            private boolean committed;

            @Override
            protected void commitInternal(final TemplateUnit unit) {
                if (!this.committed) {
                    if (this.index == SimpleTemplateBuilder.this.units.size()) {
                        SimpleTemplateBuilder.this.units.add(unit);
                    } else {
                        SimpleTemplateBuilder.this.units.add(this.index, unit);
                    }

                    this.committed = true;
                }
            }

            @Override
            protected void ensureFragmentDependency(final String id) {
                SimpleTemplateBuilder.this.ensureFragmentDependency(id);
            }

        };

        this.nestedBuilders.add(nestedBuilder);

        return nestedBuilder;
    }

    @Override
    public LoopTemplateBuilder addLoopUnit() {
        final SimpleLoopTemplateBuilder nestedBuilder = new SimpleLoopTemplateBuilder() {

            @Override
            protected void commitInternal(final TemplateUnit unit) {
            }

            @Override
            protected void ensureFragmentDependency(final String id) {
                SimpleTemplateBuilder.this.ensureFragmentDependency(id);
            }

        };

        this.units.add(nestedBuilder.getLoopUnit());

        this.nestedBuilders.add(nestedBuilder);

        return nestedBuilder;
    }

    @Override
    public void commit() {
        for (final BaseTemplateBuilder nestedBuilder : this.nestedBuilders) {
            nestedBuilder.commit();
        }

        final CompositeTemplateUnit unit = new CompositeTemplateUnit();
        unit.addSubUnits(this.units);

        for (final String dependencyId : this.dependenyIds) {
            this.ensureFragmentDependency(dependencyId);
        }

        this.commitInternal(this.modify(unit));
    }

    protected TemplateUnit modify(final TemplateUnit unit) {
        return unit;
    }

    protected abstract void commitInternal(final TemplateUnit unit);

    protected abstract void ensureFragmentDependency(final String id);

    @Override
    public void clearUnits() {
        this.units.clear();
        this.dependenyIds.clear();
        this.nestedBuilders.clear();
    }

}
