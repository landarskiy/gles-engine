package org.bananaLaba.shaderModel.template.simple.meta;

import java.util.ArrayList;
import java.util.List;

import org.bananaLaba.shaderModel.template.BaseTemplateBuilder;
import org.bananaLaba.shaderModel.template.ForkTemplateBuilder;
import org.bananaLaba.shaderModel.template.TemplateBuilder;
import org.bananaLaba.shaderModel.template.TemplateCondition;
import org.bananaLaba.shaderModel.template.simple.ForkTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;

public abstract class SimpleConditionalTemplateBuilder implements ForkTemplateBuilder {

    private ForkTemplateUnit unit = new ForkTemplateUnit();
    private List<BaseTemplateBuilder> nestedBuilder = new ArrayList<>();

    @Override
    public TemplateBuilder getDefaultTemplateBuilder() {
        final SimpleTemplateBuilder builder = new SimpleTemplateBuilder() {

            @Override
            protected void commitInternal(final TemplateUnit unit) {
                SimpleConditionalTemplateBuilder.this.unit.setDefaultUnit(unit);
            }

            @Override
            protected void ensureFragmentDependency(final String id) {
                SimpleConditionalTemplateBuilder.this.ensureFragmentDependency(id);
            }

        };

        this.nestedBuilder.add(builder);

        return builder;
    }

    @Override
    public TemplateBuilder addBranch(final TemplateCondition condition) {
        final SimpleTemplateBuilder builder = new SimpleTemplateBuilder() {

            private boolean committed;

            @Override
            protected void commitInternal(final TemplateUnit unit) {
                if (!this.committed) {
                    SimpleConditionalTemplateBuilder.this.unit.addBranch(unit, condition);
                    this.committed = true;
                }
            }

            @Override
            protected void ensureFragmentDependency(final String id) {
                SimpleConditionalTemplateBuilder.this.ensureFragmentDependency(id);
            }

        };

        this.nestedBuilder.add(builder);

        return builder;
    }

    @Override
    public void commit() {
        for (final BaseTemplateBuilder builder : this.nestedBuilder) {
            builder.commit();
        }

        this.commitInternal(this.unit);
    }

    protected abstract void ensureFragmentDependency(final String id);

    protected abstract void commitInternal(final TemplateUnit unit);

}
