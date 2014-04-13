package org.bananaLaba.bootstrap.xml.tagModel.tree;

import org.bananaLaba.bootstrap.common.InstancePrototype;
import org.bananaLaba.bootstrap.common.Prototype;


// FIXME: add feature of radio group of tags.
public class TagPrototype {

    private Integer minCount;
    private Integer maxCount;
    private boolean blocked;
    private Prototype<? extends ExtendedTagHandler> handlerPrototype;

    public TagPrototype() {
        this.handlerPrototype = InstancePrototype.prepare(ExtendedTagHandler.NOOP_HANDLER);
    }

    public TagPrototype(final Prototype<? extends ExtendedTagHandler> handlerPrototype) {
        this.handlerPrototype = handlerPrototype;
    }

    public TagPrototype(final TagPrototype description) {
        this.maxCount = description.maxCount;
        this.minCount = description.minCount;
        this.blocked = description.blocked;
        this.handlerPrototype = description.handlerPrototype;
    }

    public Integer getMinCount() {
        return this.minCount;
    }

    public void setMinCount(final Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return this.maxCount;
    }

    public void setMaxCount(final Integer maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isBlocked() {
        return this.blocked;
    }

    public void setBlocked(final boolean blocked) {
        this.blocked = blocked;
    }

    public Prototype<? extends ExtendedTagHandler> getHandlerPrototype() {
        return this.handlerPrototype;
    }

    public void setHandlerPrototype(final Prototype<? extends ExtendedTagHandler> handlerPrototype) {
        this.handlerPrototype = handlerPrototype;
    }

}
