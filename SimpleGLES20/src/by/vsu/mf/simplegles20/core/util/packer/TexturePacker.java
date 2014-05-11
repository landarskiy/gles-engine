package by.vsu.mf.simplegles20.core.util.packer;

import java.util.Collection;

/**
 * Interface that must be provided by any texture packing realization
 * @author Judzin
 */
public interface TexturePacker {

    /**
     * Gets all generated during packing atlases
     * @return list of atlases
     */
    public Collection <AtlasInfo> getAtlases ();

    /**
     * Clears all lists and prepares packer to new portion of textures
     */
    public void reset ();

    /**
     * Packs all textures contained in given list
     * @param textures list of textures to pack
     */
    public void packTextures (Collection <AtlasTextureInfo> textures);

    /**
     * Sets size limit for generated atlases
     * @param limit size limit value
     */
    public void setMaxAtlasDimension (int limit);

    /**
     * Sets border value for elements placed inside generated atlases
     * @param border inner borders value
     */
    public void setBorder (int border);
    
}
