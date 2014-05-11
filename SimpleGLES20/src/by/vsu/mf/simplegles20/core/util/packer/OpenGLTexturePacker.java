package by.vsu.mf.simplegles20.core.util.packer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Simple realization of texture packer for OpenGL
 * @author Judzin
 */
public class OpenGLTexturePacker implements TexturePacker{

	//========================
    //CONSTANTS
    //========================
    public static final int DEFAULT_BORDER_IN_PIX = 1;
    public static final int DEFAULT_ATLAS_SIZE_LIMIT = 1024;

    //========================
    //FIELDS
    //========================
    private ArrayList <AtlasInfo> atlases;//list of generated atlases

    private int border;//inner borders value
    private int maxAtlasDimension;//size limit for generated atlases

    //========================
    //CONSTRUCTORS
    //========================
    public OpenGLTexturePacker () {
        this.atlases = null;
        this.border = DEFAULT_BORDER_IN_PIX;
        this.maxAtlasDimension = DEFAULT_ATLAS_SIZE_LIMIT;
    }

    //========================
    //GETTERS AND SETTERS
    //========================
    public Collection <AtlasInfo> getAtlases () {
        return this.atlases;
    }

    public void setBorder (int border) {
        this.border = border;
    }

    public void setMaxAtlasDimension (int limit) {
        this.maxAtlasDimension = limit;
    }

    //=========================
    //METHODS
    //=========================
    public void reset () {
        this.atlases = null;
    }

    public void packTextures (Collection <AtlasTextureInfo> textures) {
        this.atlases = new ArrayList <AtlasInfo> ();

        ArrayList <AtlasTextureInfo> texturesList = new ArrayList <AtlasTextureInfo> (textures);
        Collections.sort(texturesList, new Comparator <AtlasTextureInfo> () {

            public int compare(AtlasTextureInfo o1, AtlasTextureInfo o2) {
                int area1 = o1.getHeight()*o1.getWidth();
                int area2 = o2.getHeight()*o2.getWidth();

                if(area1 == area2) return 0;
                else if(area1 > area2) return -1;
                else return 1;
            }

        });

        /*for(TextureInfo texture : texturesList) {
            texture.setSize(texture.getWidth() + this.border, texture.getHeight() + this.border);
        }*/

        boolean notFinished = true;
        while(notFinished) {
            ArrayList <TextureRectangle> freeRectangles = new ArrayList <TextureRectangle> ();

            freeRectangles.add(new TextureRectangle (0, 0, this.maxAtlasDimension, this.maxAtlasDimension));

            int maxRightEdge = 0;
            int maxBottomEdge = 0;
            notFinished = false;

            ArrayList <AtlasTextureInfo> placedTextures = new ArrayList <AtlasTextureInfo> ();

            for(int i=0; i<texturesList.size(); i++) {
                AtlasTextureInfo texture = texturesList.get(i);
                if(!texture.isPlaced()) {
                    for(TextureRectangle area : freeRectangles) {
                        if(area.fits(texture.getWidth() + this.border, texture.getHeight() + this.border) == TextureRectangle.FITS_IDEAL) {
                            freeRectangles.remove(area);
                            freeRectangles.addAll(area.cutOut(area.getXPos(), area.getYPos(), texture.getWidth() + this.border, texture.getHeight() + this.border));
                            texture.setPosition(area.getXPos(), area.getYPos());
                            texture.setPlaced(true);

                            int rightEdge = area.getXPos() + texture.getWidth() + this.border;
                            int bottomEdge = area.getYPos() + texture.getHeight() + this.border;

                            if(rightEdge > maxRightEdge) maxRightEdge = rightEdge;
                            if(bottomEdge > maxBottomEdge) maxBottomEdge = bottomEdge;

                            break;
                        }
                    }

                    if(!texture.isPlaced()) {
                        for(TextureRectangle area : freeRectangles) {
                            if(area.fits(texture.getWidth() + this.border, texture.getHeight() + this.border) == TextureRectangle.FITS_ONE_SIDE_MATCH) {
                                freeRectangles.remove(area);
                                freeRectangles.addAll(area.cutOut(area.getXPos(), area.getYPos(), texture.getWidth() + this.border, texture.getHeight() + this.border));
                                texture.setPosition(area.getXPos(), area.getYPos());
                                texture.setPlaced(true);

                                int rightEdge = area.getXPos() + texture.getWidth() + this.border;
                                int bottomEdge = area.getYPos() + texture.getHeight() + this.border;

                                if(rightEdge > maxRightEdge) maxRightEdge = rightEdge;
                                if(bottomEdge > maxBottomEdge) maxBottomEdge = bottomEdge;

                                break;
                            }
                        }

                        if(!texture.isPlaced()) {
                            for(TextureRectangle area : freeRectangles) {
                                if(area.fits(texture.getWidth() + this.border, texture.getHeight() + this.border) == TextureRectangle.FITS_INNER) {
                                    freeRectangles.remove(area);
                                    freeRectangles.addAll(area.cutOut(area.getXPos(), area.getYPos(), texture.getWidth() + this.border, texture.getHeight() + this.border));
                                    texture.setPosition(area.getXPos(), area.getYPos());
                                    texture.setPlaced(true);

                                    int rightEdge = area.getXPos() + texture.getWidth() + this.border;
                                    int bottomEdge = area.getYPos() + texture.getHeight() + this.border;

                                    if(rightEdge > maxRightEdge) maxRightEdge = rightEdge;
                                    if(bottomEdge > maxBottomEdge) maxBottomEdge = bottomEdge;

                                    break;
                                }
                            }
                        }
                    }
                }

                TextureRectangle.organizeSpace(freeRectangles);
                if(!texture.isPlaced()) notFinished = true;
                else {
                    //texturesList.remove(i);
                    //placedTextures.add(texture);
                }
            }

            for(AtlasTextureInfo texture : texturesList) {
                if(texture.isPlaced()) {
                    placedTextures.add(texture);
                }
            }

            for(AtlasTextureInfo texture : textures) {
                if(texture.isPlaced()) {
                    texturesList.remove(texture);
                }
            }

            AtlasInfo currentAtlas = new AtlasInfo ();
            currentAtlas.setHeight(nextPowOfTwo(maxBottomEdge));
            currentAtlas.setWidth(nextPowOfTwo(maxRightEdge));
            currentAtlas.addTextures(placedTextures);
            this.atlases.add(currentAtlas);
        }
    }

    //==========================
    //INEER METHODS
    //==========================
    private int nextPowOfTwo (int number) {
        int result = 2;
        while(result < number) {
            result *= 2;
        }

        return result;
    }
}
