package by.vsu.mf.simplegles20.core.util.packer;

/**
 * Class that holds info on texture
 * @author Judzin
 */
public class AtlasTextureInfo {
    //============================
    //FIELDS
    //============================
    private String name;//path to texture file

    private int width;//texture width
    private int height;//texture height
    private int xPos;//texture x-position within some atlas
    private int yPos;//texture y-position within some atlas

    private boolean placed;//placed flag

    //============================
    //CONSTRUCTORS
    //============================
    /**
     * Creates new TextureInfo instance with specified basic parameters
     * @param path path to texture file
     * @param width texture width
     * @param height texture height
     */
    public AtlasTextureInfo (String path, int width, int height) {
        this.name = path;
        this.width = width;
        this.height = height;
        this.placed = false;
    }

    //============================
    //GETTERS AND SETTERS
    //============================
    public void setPosition (int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getXPos () {
        return this.xPos;
    }

    public int getYPos () {
        return this.yPos;
    }

    public int getWidth () {
        return this.width;
    }

    public int getHeight () {
        return this.height;
    }

    public String getName () {
        return this.name;
    }

    public boolean isPlaced () {
        return this.placed;
    }

    public void setPlaced (boolean placed) {
        this.placed = placed;
    }

    public void setSize (int width, int height) {
        this.width = width;
        this.height = height;
    }
}
