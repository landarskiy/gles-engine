package org.bananaLaba.shaderModel.template.simple.meta;

import org.bananaLaba.shaderModel.template.IdentifiedTemplateBuilder;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;

public abstract class SimpleIdentifiedTemplateBuilder extends SimpleTemplateBuilder implements IdentifiedTemplateBuilder {

    private String id;

    @Override
    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    @Override
    protected void commitInternal(final TemplateUnit unit) {
        if (this.id == null) {
            throw new IllegalStateException("Expected a not-null template id!");
        }

        this.commitInternal(this.id, unit);
    }

    protected abstract void commitInternal(final String id, final TemplateUnit unit);

}
