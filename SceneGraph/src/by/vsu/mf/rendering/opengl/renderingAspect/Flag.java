package by.vsu.mf.rendering.opengl.renderingAspect;


public class Flag extends BaseRenderingAspect {

    private boolean flag;
    private FlagType type;

    public boolean getFlag() {
        return this.flag;
    }

    public void setFlag(final boolean flag) {
        this.flag = flag;
    }

    public void setType(final FlagType type) {
        this.type = type;
    }

    public FlagType getType() {
        return this.type;
    }

    public enum FlagType {
        DEPTH_TEST
    }

}
