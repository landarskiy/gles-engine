package org.bananaLaba.ioc.injection;

import java.util.ArrayList;
import java.util.List;

public class ListArgument<T> extends BaseArgument<List<T>> {

    private List<Argument<T>> sourceList = new ArrayList<>();

    public void addSourceItem(final Argument<T> source) {
        this.sourceList.add(source);
    }

    public void addSourceItems(final List<Argument<T>> sources) {
        for (final Argument<T> source : sources) {
            this.sourceList.add(source);
        }
    }

    public void clearSources() {
        this.sourceList.clear();
    }

    @Override
    public List<T> getValue() {
        final ArrayList<T> valueList = new ArrayList<>();
        for (final Argument<T> source : this.sourceList) {
            valueList.add(source.getValue());
        }

        return valueList;
    }

}
