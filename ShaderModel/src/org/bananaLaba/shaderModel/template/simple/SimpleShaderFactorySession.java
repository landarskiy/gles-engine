package org.bananaLaba.shaderModel.template.simple;

import java.util.HashMap;
import java.util.Map;

import org.bananaLaba.shaderModel.ParameterMap;
import org.bananaLaba.shaderModel.ShaderFactorySession;

public class SimpleShaderFactorySession implements ShaderFactorySession {

    private Map<String, String> fragments = new HashMap<>();
    private Map<String, TemplateUnit> templates = new HashMap<>();
    private ParameterMap parameters;

    public void setParameters(final ParameterMap parameters) {
        this.parameters = parameters;
    }

    public void addFragment(final String id, final String content) {
        this.fragments.put(id, content);
    }

    public void addFragments(final Map<String, String> fragments) {
        this.fragments.putAll(fragments);
    }

    public void clearFragments() {
        this.fragments.clear();
    }

    public void addTemplate(final String id, final TemplateUnit unit) {
        this.templates.put(id, unit);
    }

    public void addTemplates(final Map<String, TemplateUnit> templates) {
        this.templates.putAll(templates);
    }

    public void clearTemplates() {
        this.templates.clear();
    }

    @Override
    public String getShaderCode(final String id) {
        return this.templates.get(id).instantiate(new TemplateContext() {

            @Override
            public <T> T getParameterValue(final String id, final Class<T> type) {
                return SimpleShaderFactorySession.this.parameters.getParameterValue(id, type);
            }

            @Override
            public boolean isParameterPresent(final String id) {
                return SimpleShaderFactorySession.this.parameters.isParameterPresent(id);
            }

            @Override
            public String getFragment(final String id) {
                return SimpleShaderFactorySession.this.fragments.get(id);
            }

            @Override
            public <T> T getVariableValue(final String id, final Class<T> type) {
                throw new RuntimeException("No such variable with id \"" + id + "\" in the current scope!");
            }

        });
    }

}
