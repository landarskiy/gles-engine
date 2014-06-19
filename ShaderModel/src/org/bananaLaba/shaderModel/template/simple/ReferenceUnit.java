package org.bananaLaba.shaderModel.template.simple;

public class ReferenceUnit implements TemplateUnit {

    private TemplateReferenceType type;
    private String id;

    public ReferenceUnit() {
    }

    public ReferenceUnit(final String id, final TemplateReferenceType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public String instantiate(final TemplateContext context) {
        if (this.type == TemplateReferenceType.FRAGMENT) {
            return context.getFragment(this.id);
        } else if (this.type == TemplateReferenceType.VARIABLE) {
            return String.valueOf(context.getVariableValue(this.id, Object.class));
        } else {
            return String.valueOf(context.getParameterValue(this.id, Object.class));
        }
    }

    public TemplateReferenceType getType() {
        return this.type;
    }

    public void setType(final TemplateReferenceType type) {
        if (type == null) {
            throw new IllegalArgumentException("Expected a not-null reference type!");
        }

        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
