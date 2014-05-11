package by.vsu.mf.simplegles20.core.util.packer;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Helper class that provides info on one of the free
 * rectangular areas of the atlas
 * @author Judzin
 */
public class TextureRectangle {
    //====================================
    //CONSTANTS
    //====================================
    public static final short FITS_IDEAL = 0;
    public static final short FITS_ONE_SIDE_MATCH = 1;
    public static final short FITS_INNER = 2;
    public static final short DOES_NOT_FIT = 3;

    //====================================
    //FIELDS
    //====================================
    private int xPos;//x-coordinate for left top corner of the rectangle
    private int yPos;//y-coordinate for left top corner of the rectangle

    private int width;//width of the rectangle
    private int height;//height of the rectangle

    private boolean isFragmented;//fragmentation flag

    //====================================
    //CONSTRUCTORS
    //====================================
    /**
     * Creates new TextureRectangle with specified left top corner coordinates
     * and size
     * @param xPos x-coordinate of the left top corner
     * @param yPos y-coordinate of the left top corner
     * @param width width value
     * @param height height value
     */
    public TextureRectangle (int xPos, int yPos, int width, int height) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.isFragmented = true;
    }

    //=====================================
    //METHODS
    //=====================================
    /**
     * Checks if texture with specified size fits in this rectangle
     * @param width texture width
     * @param height texture height
     * @return short constant
     */
    public short fits (int width, int height) {
        if(this.width == width && this.height == height) return FITS_IDEAL;

        if((this.width == width && this.height >= height) ||
                (this.height == height && this.width >= width)) {
            return FITS_ONE_SIDE_MATCH;
        }

        if(this.width > width && this.height > height) return FITS_INNER;
        else return DOES_NOT_FIT;
    }

    /**
     * Cuts out rectangular area with specified parameters from rectangle
     * @param xPos area x-position
     * @param yPos area y-position
     * @param width area width
     * @param height area height
     * @return list of free rectangles formed after cutting
     * NOTE: If there is no intersection with given area the whole rectangle is returned
     */
    public ArrayList <TextureRectangle> cutOut (int xPos, int yPos, int width,
            int height) {

        ArrayList <TextureRectangle> rectangles =
                new ArrayList <TextureRectangle> ();

        int maxLeft = Math.max(xPos, this.xPos);
        int minRight = Math.min(xPos + width, this.xPos + this.width);

        int maxTop = Math.max(yPos, this.yPos);
        int minBottom = Math.min(yPos + height, this.yPos + this.height);

        if(maxLeft > minRight || maxTop > minBottom) {
            rectangles.add(this);
        }
        else {

            if(xPos > this.xPos/* && yPos > this.yPos*/) {
                rectangles.add(
                        new TextureRectangle (this.xPos, this.yPos, xPos - this.xPos, this.height)
                );
            }

            if(xPos + width < this.xPos + this.width/* && yPos + height < this.yPos + this.height*/) {
                rectangles.add(
                        new TextureRectangle (xPos + width, this.yPos, this.xPos + this.width - xPos - width, this.height)
                );
            }

            if(yPos > this.yPos) {
                rectangles.add(
                    new TextureRectangle (maxLeft, this.yPos, this.width - Math.max(0, xPos - this.xPos) - Math.max(0, this.xPos + this.width - width - xPos), yPos - this.yPos)
                );
            }

            if(yPos + height < this.yPos + this.height) {
                rectangles.add(
                    new TextureRectangle (maxLeft, minBottom, this.width - Math.max(0, xPos - this.xPos) - Math.max(0, this.xPos + this.width - width - xPos), this.yPos + this.height - yPos - height)
                );
            }

        }

        return rectangles;
    }

    /**
     * Cuts out rectangular area with specified parameters from rectangle
     * @param r area to cut out
     * @return list of free rectangles formed after cutting
     * NOTE: If there is no intersection with given area the whole rectangle is returned
     */
    public ArrayList <TextureRectangle> cutOut (TextureRectangle r) {
        return this.cutOut(r.xPos, r.yPos, r.width, r.height);
    }

    //====================================
    //GETTERS AND SETTERS
    //====================================
    public int getXPos () {
        return this.xPos;
    }

    public int getYPos () {
        return this.yPos;
    }

    //====================================
    //STATIC METHODS
    //====================================
    /**
     * Try to reduce fragmentation of space formed by collection of rectangles
     * @param areas collection of rectangles to defrag
     */
    public static void organizeSpace (Collection <TextureRectangle> areas) {
        for(TextureRectangle r : areas) {
            if(r.isFragmented) {
                for(TextureRectangle r2 : areas) {
                    if(!r.equals(r2)) {
                        TextureRectangle.redistribute(r, r2, areas);
                    }
                }

                r.isFragmented = false;
            }
        }
    }

    /**
     * Redistributes space between two rectangles of given collection to form
     * one rectangle as big as possible and places new rectangles instead of old
     * two
     * @param r1 first rectangle
     * @param r2 second rectangle
     * @param areas whole collection of rectangles
     */
    private static void redistribute (TextureRectangle r1, TextureRectangle r2,
            Collection <TextureRectangle> areas) {
        int maxLeft = Math.max(r1.xPos, r2.xPos);
        int minRight = Math.min(r1.xPos + r1.width, r2.xPos + r2.width);

        int maxTop = Math.max(r1.yPos, r2.yPos);
        int minBottom = Math.min(r1.yPos + r1.height, r2.yPos + r2.height);

        if(minBottom >= maxTop) {
            if(r1.xPos >= r2.xPos && r1.xPos + r1.width < r2.xPos + r2.width) {
                if(r1.height*r1.width >= r2.height*r2.width) {
                    r1.yPos = Math.min(r1.yPos, r2.yPos);
                    r1.height = Math.max(r1.yPos + r1.height, r2.yPos + r2.height) - r1.yPos;

                    r1.isFragmented = false;

                    areas.remove(r2);
                    areas.addAll(r2.cutOut(r1));
                }
            }
            else if(r2.xPos >= r1.xPos && r2.xPos + r2.width < r1.xPos + r1.width) {
                if(r2.height*r2.width >= r1.height*r1.width) {
                    r2.yPos = Math.min(r2.yPos, r1.yPos);
                    r2.height = Math.max(r2.yPos + r2.height, r1.yPos + r1.height) - r2.yPos;

                    r2.isFragmented = false;

                    areas.remove(r1);
                    areas.addAll(r1.cutOut(r2));
                }
            }

        }
        else if(maxLeft <= minRight) {
            if(r1.yPos >= r2.yPos && r1.yPos + r1.height <= r2.yPos + r2.height) {
                if(r1.height*r1.width >= r2.height*r2.width) {
                    r1.xPos = Math.min(r1.xPos, r2.xPos);
                    r1.width = Math.max(r2.xPos + r2.width, r1.xPos + r1.width) - r1.xPos;

                    r1.isFragmented = false;

                    areas.remove(r2);
                    areas.addAll(r2.cutOut(r1));
                }
            }
            else if(r2.yPos >= r1.yPos && r2.yPos + r2.height <= r1.yPos + r1.height) {
                if(r2.height*r2.width >= r1.height*r1.width) {
                    r2.xPos = Math.min(r1.xPos, r2.xPos);
                    r2.width = Math.max(r2.xPos + r2.width, r1.xPos + r1.width) - r2.xPos;

                    r2.isFragmented = false;

                    areas.remove(r1);
                    areas.addAll(r1.cutOut(r2));
                }
            }
        }

        return;
    }
}
