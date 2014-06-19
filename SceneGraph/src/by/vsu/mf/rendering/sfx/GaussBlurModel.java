package by.vsu.mf.rendering.sfx;

import by.vsu.mf.rendering.opengl.shader.ShaderInput;
import by.vsu.mf.rendering.opengl.shader.ShaderInput.CompoundUniformType;
import by.vsu.mf.rendering.pass.RenderSubPassModel;
import by.vsu.mf.rendering.pass.RendererContext;

public class GaussBlurModel implements RenderSubPassModel {

    private static final int ELEMENT_COUNT = 44;
    private static final FilterKernelElement[] GAUSSIAN_1D = new FilterKernelElement[GaussBlurModel.ELEMENT_COUNT];
    static {
        GaussBlurModel.initializeGaussian();
    }

    private float[] offsets = new float[4];
    private float[] pixelMultipliers = new float[4];

    private float perTexelWidth;
    private float perTexelHeight;

    private boolean inverted;

    public GaussBlurModel(final int width, final int height, final float ratio) {
        float rr = width / (float) height;
        float rs = rr / ratio;

        this.perTexelWidth = rs / width;
        this.perTexelHeight = 1.0f / height;
    }

    private void perform(final RendererContext context, final boolean inverted) {
        final ShaderInput input = context.getShaderInput();
        for (int i = 0; i < GaussBlurModel.ELEMENT_COUNT; i += 4) {
            this.prepareSubPass(input, i, inverted);
            context.draw();
        }
    }

    private void prepareSubPass(final ShaderInput input, final int i, final boolean inverted) {
        for (int n = 0; n < 4; n++) {
            final FilterKernelElement element = GaussBlurModel.GAUSSIAN_1D[i + n];

            for (int k = 0; k < 4; k++) {
                this.pixelMultipliers[k] = element.getCoefficient() * 0.10f;
            }

            final float du = element.getDu();
            final float dv = element.getDv();
            this.offsets[0] = this.perTexelWidth * (inverted ? dv : du);
            this.offsets[1] = this.perTexelHeight * (inverted ? du : dv);

            input.setUniform(String.format("uTexCoef%d", n), CompoundUniformType.VECTOR4, this.pixelMultipliers);
            input.setUniform(String.format("uTexOffset%d", n), CompoundUniformType.VECTOR4, this.offsets);
        }
    }

    public void setInverted(final boolean inverted) {
        this.inverted = inverted;
    }

    @Override
    public void perform(final RendererContext context) {
        this.perform(context, this.inverted);
    }

    private static void initializeGaussian() {
        float c = (GaussBlurModel.ELEMENT_COUNT - 1.0f) / 2.0f;
        for (int u = 0; u < GaussBlurModel.ELEMENT_COUNT; u++) {
            final FilterKernelElement element = new FilterKernelElement();

            final float du = u - c - 0.1f;
            element.setDu(du);

            final float dv = 0.0f;
            element.setDv(dv);

            final float r = (du * dv) / (c * c);
            final float coefficient = (float) ((0.24 / Math.exp(r * 0.18)) + (0.41 / Math.exp(r * 4.5)));
            element.setCoefficient(coefficient);

            GaussBlurModel.GAUSSIAN_1D[u] = element;
        }
    }

    private static class FilterKernelElement {

        private float du;
        private float dv;
        private float coefficient;

        public float getDu() {
            return this.du;
        }

        public void setDu(final float du) {
            this.du = du;
        }

        public float getDv() {
            return this.dv;
        }

        public void setDv(final float dv) {
            this.dv = dv;
        }

        public float getCoefficient() {
            return this.coefficient;
        }

        public void setCoefficient(final float coefficient) {
            this.coefficient = coefficient;
        }

    }

}
