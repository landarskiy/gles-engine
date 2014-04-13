package org.bananaLaba.ioc.bootstrap;

import java.util.Map;
import java.util.Set;

import org.bananaLaba.bootstrap.common.AttributeMap;
import org.bananaLaba.bootstrap.common.ConversionUtils;
import org.bananaLaba.bootstrap.xml.tagModel.tree.ExtendedTagHandler;
import org.bananaLaba.bootstrap.xml.tagModel.tree.TagContext;
import org.bananaLaba.ioc.BeanContainerFactory;
import org.bananaLaba.ioc.bean.BeanBuilder;
import org.bananaLaba.ioc.bean.BeanScope;
import org.bananaLaba.ioc.bean.ObjectBeanBuilder;

public abstract class BaseBeanTagHandler  implements ExtendedTagHandler {

    private static final String ATTRIBUTE_TYPE = "type";
    private static final String ATTRIBUTE_CATEGORY = "category";
    private static final String ATTRIBUTE_PARENT = "parent";
    private static final String ATTRIBUTE_SCOPE = "scope";

    public static final String ATTRIBUTE_NAME = "name";
    public static final String SHARED_BUILDER = "builder";

    private BeanContainerFactory containerFactory;

    private BeanBuilder beanBuilder;

    private BeanCategory currentCategory;

    private TagContext context;

    private Map<String, ObjectBeanBuilder> builderTemplates;
    private Set<String> usedNames;
    private Set<Class<?>> usedClassBeanTypes;

    public BaseBeanTagHandler(final BeanContainerFactory containerFactory) {
        this.containerFactory = containerFactory;
    }

    @Override
    public void handle(final AttributeMap attributes) {
        if (attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_CATEGORY)) {
            try {
                this.currentCategory =
                        BeanCategory.valueOf(attributes.getAttribute(BaseBeanTagHandler.ATTRIBUTE_CATEGORY).toUpperCase());
            } catch (IllegalArgumentException e) {
                // TODO: throw a custom exception here.
                throw new RuntimeException(
                        "A bean category must have one the following values: class, template, object!");
            }
        } else {
            this.currentCategory = BeanCategory.OBJECT;
        }

        this.onCheckCategory(this.currentCategory);

        BeanScope scope = null;
        if (attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_SCOPE)) {
            scope = ConversionUtils.getStringConverter(BeanScope.class)
                    .convert(attributes.getAttribute(BaseBeanTagHandler.ATTRIBUTE_SCOPE).toUpperCase());
        }

        if (this.currentCategory == BeanCategory.CLASS) {
            if ((scope != BeanScope.SINGLETON) && (scope != null)) {
                // TODO: throw a custom exception here.
                throw new IllegalArgumentException("Class beans cannot have a scope other than singleton!");
            }

            this.context.getChildController(null, IoCTagConstants.TAG_CONSTRUCTION).setBlocked(true);
        }

        final String beanName = this.getBeanName(attributes);
        if (this.usedNames.contains(beanName)) {
            // TODO: throw a custom exception here.
            throw new IllegalStateException("The bean name \"" + beanName + "\" is already used!");
        } else {
            this.usedNames.add(beanName);
        }

        if (attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_PARENT)) {
            if (this.currentCategory == BeanCategory.CLASS) {
                // TODO: throw a custom exception here.
                throw new RuntimeException("The class beans cannot be inherited or inherit!");
            }

            final String parentName = attributes.getAttribute(BaseBeanTagHandler.ATTRIBUTE_PARENT);

            final ObjectBeanBuilder builder = this.builderTemplates.get(parentName);
            if (builder == null) {
                // TODO: throw a custom exception here.
                throw new RuntimeException("A parent bean with name \"" + parentName + "\" not found!");
            } else {
                final ObjectBeanBuilder objectBeanBuilder = builder.getClone();
                this.beanBuilder = objectBeanBuilder;
                if (this.canBeInherited()) {
                    this.builderTemplates.put(beanName, objectBeanBuilder);
                }

                if (scope != null) {
                    objectBeanBuilder.setScope(scope);
                }
            }
        } else {
            if (this.currentCategory == BeanCategory.CLASS) {
                this.beanBuilder = this.containerFactory.getClassBeanBuilder();
            } else {
                final ObjectBeanBuilder objectBeanBuilder = this.containerFactory.getObjectBeanBuilder();
                if (this.canBeInherited()) {
                    this.builderTemplates.put(beanName, objectBeanBuilder);
                }

                if (scope != null) {
                    objectBeanBuilder.setScope(scope);
                }

                this.beanBuilder = objectBeanBuilder;
            }
        }

        this.context.propagateAttributeDown(BaseBeanTagHandler.SHARED_BUILDER, this.beanBuilder);

        this.beanBuilder.setName(beanName);

        if (attributes.isPresent(BaseBeanTagHandler.ATTRIBUTE_TYPE)) {
            final String beanTypeName = attributes.getAttribute(BaseBeanTagHandler.ATTRIBUTE_TYPE);
            try {
                final Class<?> beanType = Class.forName(beanTypeName);
                if ((beanType == Class.class) && (this.currentCategory != BeanCategory.CLASS)) {
                    // TODO: throw a custom exception here.
                    throw new RuntimeException("An object or a template bean cannot have java.lang.Class type!");
                } else if (this.usedClassBeanTypes.contains(beanType)
                        && (this.currentCategory == BeanCategory.CLASS)) {
                    // TODO: throw a custom exception here.
                    throw new RuntimeException("A class bean of type \"" + beanTypeName + "\" is already decalred!");
                } else {
                    this.beanBuilder.setType(beanType);
                    this.usedClassBeanTypes.add(beanType);
                }
            } catch (ClassNotFoundException e) {
                // TODO: throw a custom exception here.
                throw new RuntimeException("Invalid bean type!", e);
            }
        }

    }

    protected abstract String getBeanName(final AttributeMap attributes);

    protected abstract void onCheckCategory(final BeanCategory category);

    protected abstract boolean canBeInherited();

    @Override
    public void close() {
        if (this.currentCategory != BeanCategory.TEMPLATE) {
            this.beanBuilder.commit();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setContext(final TagContext context) {
        this.context = context;
        this.builderTemplates = context.getGlobal(BeansTagHandler.SHARED_TEMPLATE_MAP, Map.class);
        this.usedClassBeanTypes = context.getGlobal(BeansTagHandler.USED_CLASS_BEAN_TYPES, Set.class);
        this.usedNames = context.getGlobal(BeansTagHandler.USED_BEAN_NAMES, Set.class);
    }

    public enum BeanCategory {
        TEMPLATE, CLASS, OBJECT
    }

}
