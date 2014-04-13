package org.bananaLaba.ioc.bootstrap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;

public class BeansTagHandler implements ExtendedTagHandler {

    public static final String SHARED_TEMPLATE_MAP = "templateMap";
    public static final String USED_CLASS_BEAN_TYPES = "classBeanSet";
    public static final String USED_BEAN_NAMES = "beanNames";

    private Map<String, ObjectBeanBuilder> tempaltes = new HashMap<>();
    private Set<Class<?>> usedClassBeanTypes = new HashSet<>();
    private Set<String> usedBeanNames = new HashSet<>();

    @Override
    public void close() {
    }

    @Override
    public void handle(final AttributeMap attributes) {
    }

    @Override
    public void setContext(final TagContext context) {
        context.setGlobal(BeansTagHandler.SHARED_TEMPLATE_MAP, this.tempaltes);
        context.setGlobal(BeansTagHandler.USED_BEAN_NAMES, this.usedBeanNames);
        context.setGlobal(BeansTagHandler.USED_CLASS_BEAN_TYPES, this.usedClassBeanTypes);
    }

}
