package org.bananaLaba.fdp.scenario;

import java.util.ArrayList;
import java.util.List;

public class CallActionSpecification extends ActionSpecification {

    private String methodName;
    private List<ArgumentSpecification> argumentSpecifications = new ArrayList<>();

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(final String methodName) {
        this.methodName = methodName;
    }

    public void addArgument(final ArgumentSpecification specification) {
        this.argumentSpecifications.add(specification);
    }

    public void addArguments(final List<ArgumentSpecification> specifications) {
        this.argumentSpecifications.addAll(specifications);
    }

    public void clearArguments() {
        this.argumentSpecifications.clear();
    }

    public List<ArgumentSpecification> getArguments() {
        return new ArrayList<>(this.argumentSpecifications);
    }

}
