import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
    ///////////////////// constructors //////////////////////////////////

    /**
     * Constructor that takes no arguments 
     */
    public Picture ()
    {
        /* not needed but use it to show students the implicit call to super()
         * child constructors always call a parent constructor 
         */
        super();  
    }

    /**
     * Constructor that takes a file name and creates the picture 
     * @param fileName the name of the file to create the picture from
     */
    public Picture(String fileName)
    {
        // let the parent class handle this fileName
        super(fileName);
    }

    /**
     * Constructor that takes the width and height
     * @param height the height of the desired picture
     * @param width the width of the desired picture
     */
    public Picture(int height, int width)
    {
        // let the parent class handle this width and height
        super(width,height);
    }

    /**
     * Constructor that takes a picture and creates a 
     * copy of that picture
     * @param copyPicture the picture to copy
     */
    public Picture(Picture copyPicture)
    {
        // let the parent class do the copy
        super(copyPicture);
    }

    /**
     * Constructor that takes a buffered image
     * @param image the buffered image to use
     */
    public Picture(BufferedImage image)
    {
        super(image);
    }

    ////////////////////// methods ///////////////////////////////////////

    /**
     * Method to return a string with information about this picture.
     * @return a string with information about the picture such as fileName,
     * height and width.
     */
    public String toString()
    {
        String output = "Picture, filename " + getFileName() + 
            " height " + getHeight() 
            + " width " + getWidth();
        return output;

    }

    /** Method to set the blue to 0 */
    public void zeroBlue()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(0);
            }
        }
    }
    
    public void zeroRed()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setRed(0);
            }
        }
    }
    
    public void zeroGreen()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setGreen(0);
            }
        }
    }
    
    public void lowBlueHighRed()
    {
        Pixel[][] pixels = this.getPixels2D();
        for (Pixel[] rowArray : pixels)
        {
            for (Pixel pixelObj : rowArray)
            {
                pixelObj.setBlue(50);
                pixelObj.setRed(200);
            }
        }
    }

    /** Method that mirrors the picture around a 
     * vertical mirror in the center of the picture
     * from left to right */
    public void mirrorVertical()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                rightPixel.setColor(leftPixel.getColor());
            }
        } 
    }

    public void mirrorVerticalRightToLeft()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int width = pixels[0].length;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < width / 2; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][width - 1 - col];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontal()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;

        int width = pixels[0].length;
        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < pixels.length/2; row++)
            {
                bottomPixel = pixels[row][col];
                topPixel = pixels[pixels.length - 1 - row][col];
                topPixel.setColor(bottomPixel.getColor());
            }
        } 
    }

    public void mirrorHorizontalBotToTop()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel topPixel = null;
        Pixel bottomPixel = null;

        int width = pixels[0].length;
        for (int col = 0; col < width; col++)
        {
            for (int row = 0; row < pixels.length/2; row++)
            {
                bottomPixel = pixels[row][col];
                topPixel = pixels[pixels.length - 1 - row][col];
                bottomPixel.setColor(topPixel.getColor());
            }
        } 
    }

    public void mirrorDiaganol()
    {
        Pixel[][] pixels = this.getPixels2D();
        Pixel rightPixel = null;
        Pixel leftPixel = null;

        int diagonalLength;

        int width = pixels[0].length;
        if(pixels.length > width)
        {
            diagonalLength = width;
        }
        else
        {
            diagonalLength = pixels.length;
        }

        for (int row = 0; row < diagonalLength; row++)
        {
            for (int col = 0; col < diagonalLength; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[diagonalLength - col - 1][diagonalLength- row - 1];
                leftPixel.setColor(rightPixel.getColor());
            }
        } 
    }

    /** Mirror just part of a picture of a temple */
    public void mirrorTemple()
    {
        int mirrorPoint = 276;
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        Pixel[][] pixels = this.getPixels2D();

        // loop through the rows
        for (int row = 27; row < 97; row++)
        {
            // loop from 13 to just before the mirror point
            for (int col = 13; col < mirrorPoint; col++)
            {

                leftPixel = pixels[row][col];      
                rightPixel = pixels[row]                       
                [mirrorPoint - col + mirrorPoint];
                rightPixel.setColor(leftPixel.getColor());
            }
        }
    }

    public void cropAndCopy( Picture sourcePicture, int startSourceRow, int endSourceRow, int startSourceCol, int endSourceCol,
    int startDestRow, int startDestCol )
    {
        Pixel[][] pixels=this.getPixels2D();
        Pixel[][] pixels1=sourcePicture.getPixels2D();
        for (int row1=0;row1<endSourceRow-startSourceRow;row1++)
        {
            for (int col1=0;col1<endSourceCol-startSourceCol;col1++)
            {
                pixels[startDestRow+row1][startDestCol+col1].setColor(pixels1[startSourceRow+row1][startSourceCol+col1].getColor());
            }
        }
    }

    /** copy from the passed fromPic to the
     * specified startRow and startCol in the
     * current picture
     * @param fromPic the picture to copy from
     * @param startRow the start row to copy to
     * @param startCol the start col to copy to
     */
    public void copy(Picture fromPic, 
    int startRow, int startCol)
    {
        Pixel fromPixel = null;
        Pixel toPixel = null;
        Pixel[][] toPixels = this.getPixels2D();
        Pixel[][] fromPixels = fromPic.getPixels2D();
        for (int fromRow = 0, toRow = startRow; 
        fromRow < fromPixels.length &&
        toRow < toPixels.length; 
        fromRow++, toRow++)
        {
            for (int fromCol = 0, toCol = startCol; 
            fromCol < fromPixels[0].length &&
            toCol < toPixels[0].length;  
            fromCol++, toCol++)
            {
                fromPixel = fromPixels[fromRow][fromCol];
                toPixel = toPixels[toRow][toCol];
                toPixel.setColor(fromPixel.getColor());
            }
        }   
    }

    /** Method to create a collage of several pictures */
    public void createCollage()
    {
        Picture car1 = new Picture("mustang3.jpg");
        Picture car2 = new Picture("mustang3.jpg");
        Picture car3 = new Picture("mustang3.jpg");
        Picture car4 = new Picture("mustang3.jpg");
        Picture car5 = new Picture("mustang3.jpg");
        
        car2.mirrorVerticalRightToLeft();
        car2.zeroRed();
        
        car3.mirrorDiaganol();
        car3.zeroBlue();
        
        car4.mirrorHorizontal();
        car4.zeroGreen();
        
        car5.mirrorVerticalRightToLeft();
        car5.lowBlueHighRed();
        
        this.copy(car1, 184, 312);
        this.cropAndCopy(car2, 105, 305, 5,395, 10, 0);
        this.cropAndCopy(car3, 105, 305, 5,395, 540, 0);
        this.cropAndCopy(car4, 105, 305, 5,395, 10, 600);
        this.cropAndCopy(car5, 105, 305, 5,395, 540, 600);
        Picture carNoBlue = new Picture(car1);
        this.write("collage.jpg");
    }

    /** Method to show large changes in color 
     * @param edgeDist the distance for finding edges
     */
    public void edgeDetection(int edgeDist)
    {
        Pixel leftPixel = null;
        Pixel rightPixel = null;
        Pixel[][] pixels = this.getPixels2D();
        Color rightColor = null;
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; 
            col < pixels[0].length-1; col++)
            {
                leftPixel = pixels[row][col];
                rightPixel = pixels[row][col+1];
                rightColor = rightPixel.getColor();
                if (leftPixel.colorDistance(rightColor) > 
                edgeDist)
                    leftPixel.setColor(Color.BLACK);
                else
                    leftPixel.setColor(Color.WHITE);
            }
        }
    }

    /* Main method for testing - each class in Java can have a main 
     * method 
     */
    public static void main(String[] args) 
    {
        Picture beach = new Picture("schmit.jpg");
        beach.explore();
        beach.zeroBlue();
        beach.explore();
    }

} // this } is the end of class Picture, put all new methods before this
