package org.bananaLaba.ioc.test;

import java.util.List;

import org.bananaLaba.ioc.simple.DependencyGraphTracker;
import org.bananaLaba.ioc.simple.DependencyTracker;

public class DependencyTrackerTest {

    public static void main(final String[] arguments) {
        final DependencyTracker tracker = new DependencyGraphTracker();
        tracker.ensureConstructionDependency("b1", "b2");
        tracker.ensureConstructionDependency("b2", "b3");
        tracker.ensureConstructionDependency("b3", "b4");
        tracker.ensureConstructionDependency("b3", "b5");
        //tracker.ensureConstructionDependency("b3", "b1");

        final List<String> chain = tracker.getInstantiationChain("b3");
        for (final String beanName : chain) {
            System.out.println(beanName);
        }
    }

}
