package w50;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class ImageUtils {

    // test
    public static void main (String [] args){
        ImageUtils.example(600,300);
    }

    private static void example(int width, int height) {
        try {
            BufferedImage image1 = makeRandomImage(width, height);
            BufferedImage image2 = makeRandomImage(width, height);
            BufferedImage image3 = joinBufferedImage (image1, image2,0);
            save(image3,"./output2.bmp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void save(BufferedImage image, String path) {
        try {
            File outputFile = new File(path);
            ImageIO.write(image, "bmp", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage makeRandomImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[][] red = new int[height][width];
        int[][] green = new int[height][width];
        int[][] blue = new int[height][width];

        Random random = new Random();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                red[h][w] = random.nextInt(255); // here just playing with Red channel
                green[h][w] = random.nextInt(255); // here just playing with Red channel
                blue[h][w] = random.nextInt(255); // here just playing with Red channel
            }
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = red[y][x];
                rgb = (rgb << 8) + green[y][x];
                rgb = (rgb << 8) + blue[y][x];
                image.setRGB(x, y, rgb);
            }
        }
        return image;
    }

    private static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2, int offset) throws IOException {

        int width = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight()); //+offset;

        BufferedImage newImage = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = newImage.createGraphics();
        Color oldColor = g2.getColor();
//        fill background
        g2.setPaint(Color.WHITE);
        g2.fillRect(0, 0, width, height);
//        draw image
        g2.setColor(oldColor);
        g2.drawImage(img1, 0, 0,null);
        g2.drawImage(img2, img1.getWidth()+offset,0, null);
        g2.dispose();
        return newImage;
    }
}

