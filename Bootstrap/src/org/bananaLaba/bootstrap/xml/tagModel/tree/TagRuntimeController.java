package org.bananaLaba.bootstrap.xml.tagModel.tree;


public class TagRuntimeController implements TagController {

    private int currentCount;
    private TagPrototype originalPrototype;
    private TagPrototype runtimePrototype;

    public TagRuntimeController(final TagPrototype prototype) {
        this.originalPrototype = new TagPrototype(prototype);
        this.reset();
    }

    private void reset() {
        this.currentCount = 0;
        this.runtimePrototype = new TagPrototype(this.originalPrototype);
    }

    public ExtendedTagHandler onTagOpen() throws TagStructureViolationException {
        if (this.runtimePrototype.isBlocked()) {
            throw new IllegalStateException("The tag is not allowed for this parent configuration!");
        }

        this.currentCount++;

        final Integer maxCount = this.runtimePrototype.getMaxCount();
        if ((maxCount != null) && (this.currentCount > maxCount)) {
            throw new TagStructureViolationException("Expected at most " + maxCount + " occurences!");
        }

        return this.runtimePrototype.getHandlerPrototype().create();
    }

    public void onParentTagClose() throws TagStructureViolationException {
        final Integer minCount = this.runtimePrototype.getMinCount();
        if ((minCount != null) && (this.currentCount < minCount)) {
            throw new TagStructureViolationException("Expected at least " + minCount + " occurences!");
        }

        this.reset();
    }

    @Override
    public int getCurrentCount() {
        return this.currentCount;
    }

    @Override
    public void setBlocked(final boolean blocked) {
        this.runtimePrototype.setBlocked(blocked);
    }

    @Override
    public void setMinCount(final Integer count) {
        this.runtimePrototype.setMinCount(count);
    }

    @Override
    public void setMaxCount(final Integer count) {
        this.runtimePrototype.setMaxCount(count);
    }

}
