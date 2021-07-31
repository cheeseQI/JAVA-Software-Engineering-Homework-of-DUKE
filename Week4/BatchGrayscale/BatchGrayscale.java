import edu.duke.*;
import java.io.File;

/**
 * 在这里给出对类 BatchGrayscale 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class BatchGrayscale {
    
    public ImageResource makeGray(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int ave = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            
            pixel.setRed(ave);
            pixel.setGreen(ave);
            pixel.setBlue(ave);
        }
        return outImage;
    }
    
    public ImageResource makeInvert(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel : outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int red = 255 - inPixel.getRed();
            int green = 255 - inPixel.getGreen();
            int blue = 255 - inPixel.getBlue();
            pixel.setRed(red);
            pixel.setGreen(green);
            pixel.setBlue(blue);
        }
        return outImage;
    }
    
    public ImageResource convertToGray(ImageResource inImage){
        DirectoryResource dr = new DirectoryResource();
        ImageResource gray = makeGray(inImage);
        gray.draw();
        return gray;
    }
    
    public ImageResource convertToInvert(ImageResource inImage){
        DirectoryResource dr = new DirectoryResource();
        ImageResource invert = makeInvert(inImage);
        invert.draw();
        return invert;
    }
    
    public void selectGrayAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = convertToGray(inImage);

            String fname = inImage.getFileName();
            System.out.println(fname);
            String newName = "gray-" + fname; 
            outImage.setFileName(newName);
            outImage.save();
        }

    }
    
    public void selectInvertAndSave(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = convertToInvert(inImage);

            String fname = inImage.getFileName();
            System.out.println(fname);
            String newName = "Inverted-" + fname; 
            outImage.setFileName(newName);
            outImage.save();
        }

    }
    
}

