package org.bananaLaba.shaderModel.template.simple;


public class StaticUnit implements TemplateUnit {

    private String content;

    public StaticUnit() {
    }

    public StaticUnit(final String content) {
        this.content = content;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    @Override
    public String instantiate(final TemplateContext context) {
        return this.content;
    }

}
