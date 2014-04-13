package org.bananaLaba.ioc.bootstrap;

import org.bananaLaba.ioc.injection.Argument;
import org.bananaLaba.ioc.injection.ArgumentGroup;

// FIXME: need to use Argument concept from FDP instead of the IoC's one. The only question in this case is how and
// when to notify dependency tracker about bean references.
public class SimpleArgumentStore implements ArgumentStore {

    private ArgumentGroup group = new ArgumentGroup();
    private int currentargumentIndex;

    @Override
    public void addPrimitive(final Argument<?> argument) {
        argument.setIndex(this.currentargumentIndex++);
        this.group.addPrimitive(argument);
    }

    @Override
    public void addReference(final Argument<String> argument) {
        argument.setIndex(this.currentargumentIndex++);
        this.group.addReference(argument);
    }

    public ArgumentGroup getGroup() {
        return this.group;
    }

}
