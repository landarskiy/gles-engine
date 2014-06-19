package org.bananaLaba.shaderModel.test;

import org.bananaLaba.shaderModel.ShaderFactory;
import org.bananaLaba.shaderModel.SimpleParameterMap;
import org.bananaLaba.shaderModel.template.ForkTemplateBuilder;
import org.bananaLaba.shaderModel.template.IdentifiedTemplateBuilder;
import org.bananaLaba.shaderModel.template.LoopTemplateBuilder;
import org.bananaLaba.shaderModel.template.TemplateBuilder;
import org.bananaLaba.shaderModel.template.condition.BooleanFlagCondition;
import org.bananaLaba.shaderModel.template.simple.meta.SimpleShaderFactoryPrototype;

public class SimpleShaderFactoryPrototypeTest {

    public static void main(final String[] arguments) {
        final SimpleShaderFactoryPrototype prototype = new SimpleShaderFactoryPrototype();

        final IdentifiedTemplateBuilder fragmentBuilder = prototype.getFragmentTemplateBuilder();
        fragmentBuilder.setId("header");
        String content = "#define MAX_LIGHTS 8\r\n"
                + "uniform int numLights\r\n";
        final ForkTemplateBuilder conditionalBuilder =
                fragmentBuilder.addForkUnit();
        TemplateBuilder builder = conditionalBuilder.addBranch(new BooleanFlagCondition("lighting"));
        builder.addStaticUnit(content);
        builder.commit();

        builder = conditionalBuilder.getDefaultTemplateBuilder();
        builder.addStaticUnit("Hello!\r\n");
        builder.commit();

        content = "varying vec3 normal\r\n"
                + "varying vec3 eyeVec\r\n"
                + "varying vec3 lightDir[MAX_LIGHTS]\r\n";
        fragmentBuilder.addStaticUnit(content);
        fragmentBuilder.commit();

        final IdentifiedTemplateBuilder templateBuilder = prototype.getTemplateBuilder();
        templateBuilder.setId("vertexShader");
        templateBuilder.addInclusionUnit("header");
        content = "void main() {\r\n"
                + "\tgl_Position = ftransform();\r\n"
                + "\tnormal = gl_NormalMatrix * gl_Normal;\r\n"
                + "\tvec4 vVertex = gl_ModelViewMatrix * gl_Vertex;\r\n"
                + "\teyeVec = -vVertex.xyz;\r\n"
                + "\tint i;";
        templateBuilder.addStaticUnit(content);

        final LoopTemplateBuilder loopBuilder = templateBuilder.addLoopUnit();
        loopBuilder.setIntegerCounter(true);
        loopBuilder.setCounterId("i");
        loopBuilder.setStartValue(0);
        loopBuilder.setStepValue(1);
        loopBuilder.setCountValue("numLights");
        loopBuilder.addStaticUnit("\r\n\tlightDir[");
        loopBuilder.addVariableUnit("i");
        loopBuilder.addStaticUnit("] = vec3(gl_LightSource[");
        loopBuilder.addVariableUnit("i");
        loopBuilder.addStaticUnit("].position.xyz - vVertex.xyz);");
        loopBuilder.commit();

        templateBuilder.commit();

        final SimpleParameterMap parameters = new SimpleParameterMap();
        parameters.setParameter("lighting", false);
        parameters.setParameter("numLights", 2);

        final ShaderFactory factory = prototype.create();
        System.out.println(factory.getSession(parameters).getShaderCode("vertexShader"));
    }

}
