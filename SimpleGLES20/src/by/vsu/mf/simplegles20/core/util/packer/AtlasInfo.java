package by.vsu.mf.simplegles20.core.util.packer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that holds info on texture atlas
 * @author Judzin
 */
public class AtlasInfo {
    //==========================
    //FIELDS
    //==========================
    private Map <String, AtlasTextureInfo> textures;//list of textures placed on atlas
    
    
    private int width;//atlas width
    private int height;//atlas height

    //==========================
    //CONSTRUCTORS
    //==========================
    /*
     * Default constructor
     */
    public AtlasInfo () {
    }

    //==========================
    //GETTERS AND SETTERS
    //==========================
    public void setWidth (int width) {
        this.width = width;
    }

    public void setHeight (int height) {
        this.height = height;
    }

    public int getWidth () {
        return this.width;
    }

    public int getHeight () {
        return this.height;
    }

    public Collection <AtlasTextureInfo> getTextures () {
        return this.textures.values();
    }

    public AtlasTextureInfo getTexture(String name) {
    	return this.textures.get(name);
    }
    //===========================
    //METHODS
    //===========================
    /**
     * Adds new texture on the atlas
     * @param texture new texture to add
     */
    public void addTexture (AtlasTextureInfo texture) {
        if(this.textures == null) {
            this.textures = new HashMap<String, AtlasTextureInfo>();
        }

        this.textures.put(texture.getName(), texture);
    }

    /**
     * Adds all textures from given collection
     * @param textures collection of textures to add
     */
    public void addTextures (Collection <AtlasTextureInfo> textures) {
        if(this.textures == null) {
            this.textures = new HashMap<String, AtlasTextureInfo>();
        }
        for(AtlasTextureInfo info : textures) {
        	this.textures.put(info.getName(), info);
        }
    }

}
