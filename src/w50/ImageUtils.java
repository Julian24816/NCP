package w50;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;


public class ImageUtils {

    private static class RecursiveGeneration extends RecursiveTask<BufferedImage> {

        private int width, height;

        private RecursiveGeneration(int width, int height) {
            this.width = width;
            this.height = height;
        }

        @Override
        protected BufferedImage compute() {
            if (shouldSplitTheWork(width))
                return splitTheWork(width, height);
            return doTheWork(width, height);
        }

        private BufferedImage splitTheWork(int width, int height) {
            int splitWidth = Math.min(WIDTH_THRESHOLD, width / 2);
            ForkJoinTask<BufferedImage> task1 = new RecursiveGeneration(splitWidth, height).fork(),
                    task2 = new RecursiveGeneration(width - splitWidth, height).fork();
            task1.fork();
            return joinBufferedImage(task1.join(), task2.join(), 0);
        }

        private boolean shouldSplitTheWork(int width) {
            return width > WIDTH_THRESHOLD;
        }

    }

    private static final int WIDTH_THRESHOLD = 100;

    // test
    public static void main (String [] args) throws IOException {
        save(new RecursiveGeneration(1200, 300).fork().join(), "./" + "output" + ".bmp");
    }

    @SuppressWarnings("SameParameterValue")
    private static void save(BufferedImage image, String path) {
        try {
            File outputFile = new File(path);
            ImageIO.write(image, "bmp", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage doTheWork(int width, int height) {
        int[][] red = new int[height][width];
        int[][] green = new int[height][width];
        int[][] blue = new int[height][width];

        fillRGBArraysWithRandomValues(width, height, red, green, blue);
        return buildBufferedImageFromRGBArrays(width, height, red, green, blue);
    }

    private static BufferedImage buildBufferedImageFromRGBArrays(int width, int height, int[][] red, int[][] green, int[][] blue) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++)
                image.setRGB(x, y, computeRGBValue(red[y][x], green[y][x], blue[y][x]));
        return image;
    }

    private static int computeRGBValue(int red, int green, int blue) {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    private static void fillRGBArraysWithRandomValues(int width, int height, int[][] red, int[][] green, int[][] blue) {
        Random random = new Random();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                red[h][w] = random.nextInt(255);
                green[h][w] = random.nextInt(255);
                blue[h][w] = random.nextInt(255);
            }
        }
    }

    private static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2, int offset) {

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

