package org.bananaLaba.shaderModel.template.simple;

public abstract class ConditionalTemplateUnit implements TemplateUnit {

    private TemplateUnit defaultUnit;

    @Override
    public String instantiate(final TemplateContext context) {
        final String mainContent = this.instantiateInternal(context);
        if (mainContent != null) {
            return mainContent;
        }

        if (this.defaultUnit == null) {
            return "";
        } else {
            return this.defaultUnit.instantiate(context);
        }
    }

    /**
     * Tries to instantiate content guarded by some conditions.
     * @param context template context
     * @return string content or null if conditions have failed
     */
    protected abstract String instantiateInternal(final TemplateContext context);

    public TemplateUnit getDefaultUnit() {
        return this.defaultUnit;
    }

    public void setDefaultUnit(final TemplateUnit unit) {
        this.defaultUnit = unit;
    }

}
