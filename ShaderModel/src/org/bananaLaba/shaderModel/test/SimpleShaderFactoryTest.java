package org.bananaLaba.shaderModel.test;

import org.bananaLaba.shaderModel.SimpleParameterMap;
import org.bananaLaba.shaderModel.template.condition.BooleanFlagCondition;
import org.bananaLaba.shaderModel.template.simple.CompositeTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.ConstantValue;
import org.bananaLaba.shaderModel.template.simple.ForkTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.LoopTemplateUnit;
import org.bananaLaba.shaderModel.template.simple.ParameterExtractor;
import org.bananaLaba.shaderModel.template.simple.ReferenceUnit;
import org.bananaLaba.shaderModel.template.simple.SimpleShaderFactory;
import org.bananaLaba.shaderModel.template.simple.StaticUnit;
import org.bananaLaba.shaderModel.template.simple.TemplateReferenceType;
import org.bananaLaba.shaderModel.template.simple.TemplateUnit;
import org.bananaLaba.shaderModel.util.DecoratedAdjacencyListGraph;
import org.bananaLaba.shaderModel.util.DecoratedGraph;

public class SimpleShaderFactoryTest {

    public static void main(final String[] arguments) {
        final SimpleShaderFactory factory = new SimpleShaderFactory();

        CompositeTemplateUnit unit = new CompositeTemplateUnit();
        String content = "#define MAX_LIGHTS 8\r\n"
                + "uniform int numLights\r\n";
        final ForkTemplateUnit conditionalUnit = new ForkTemplateUnit();
        conditionalUnit.addBranch(new StaticUnit(content), new BooleanFlagCondition("lighting"));
        unit.addSubUnit(conditionalUnit);

        content = "varying vec3 normal\r\n"
                + "varying vec3 eyeVec\r\n"
                + "varying vec3 lightDir[MAX_LIGHTS]\r\n";
        unit.addSubUnit(new StaticUnit(content));

        final DecoratedGraph<String, TemplateUnit> templateGraph = new DecoratedAdjacencyListGraph<>();
        templateGraph.insertNode("header").setContent(unit);
        factory.setFragmentTemplateGraph(templateGraph);

        unit = new CompositeTemplateUnit();
        unit.addSubUnit(new ReferenceUnit("header", TemplateReferenceType.FRAGMENT));
        content = "void main() {\r\n"
                + "\tgl_Position = ftransform();\r\n"
                + "\tnormal = gl_NormalMatrix * gl_Normal;\r\n"
                + "\tvec4 vVertex = gl_ModelViewMatrix * gl_Vertex;\r\n"
                + "\teyeVec = -vVertex.xyz;\r\n"
                + "\tint i;";
        unit.addSubUnit(new StaticUnit(content));

        final LoopTemplateUnit loopUnit = new LoopTemplateUnit();
        loopUnit.setCounterVariableId("i");
        loopUnit.setStartValueParameter(ConstantValue.create(0.0));
        loopUnit.setIntegerCounter(true);
        loopUnit.setStepParameter(ConstantValue.create(1.0));
        loopUnit.setCountParameter(ParameterExtractor.create("numLights", Integer.class));
        final CompositeTemplateUnit loopBodyUnit = new CompositeTemplateUnit();
        loopBodyUnit.addSubUnit(new StaticUnit("lightDir["));
        loopBodyUnit.addSubUnit(new ReferenceUnit("i", TemplateReferenceType.VARIABLE));
        loopBodyUnit.addSubUnit(new StaticUnit("] = vec3(gl_LightSource["));
        loopBodyUnit.addSubUnit(new ReferenceUnit("i", TemplateReferenceType.VARIABLE));
        loopBodyUnit.addSubUnit(new StaticUnit("].position.xyz - vVertex.xyz);\r\n"));
        loopUnit.setUnit(loopBodyUnit);

        unit.addSubUnit(loopUnit);

        factory.addTemplate("vertexShader", unit);

        final SimpleParameterMap parameters = new SimpleParameterMap();
        parameters.setParameter("lighting", true);
        parameters.setParameter("numLights", 3);

        System.out.println(factory.getSession(parameters).getShaderCode("vertexShader"));
    }

}
