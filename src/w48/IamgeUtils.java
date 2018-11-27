package w48;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


class ImageUtils {

    // test
    public static void main (String [] args){
        Stopwatch stopwatch = new Stopwatch();
        for (int size : new int[]{30, 100, 500, 4000}) {
            FutureTask<Boolean> task = getTask(size);
            stopwatch.reset();
            new Thread(task).start();
            try {task.get(); } catch (InterruptedException | ExecutionException e) {e.printStackTrace();}
            System.out.println("Image with resolution " + size + "x" + size + " took " + stopwatch + "s to generate.");
        }

    }

    private static FutureTask<Boolean> getTask(int size) {
        return new FutureTask<>(() -> ImageUtils.generateImage(size,size, "./output-" + size + ".bmp"));
    }

    private static boolean generateImage(int width, int height, String fileName) {
        try {
            BufferedImage image = makeRandomImage(width, height);
            File outputFile = new File(fileName);
            ImageIO.write(image, "bmp", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    private static BufferedImage makeRandomImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int[][] red = new int[height][width];
        int[][] green = new int[height][width];
        int[][] blue = new int[height][width];

        Random random = new Random();
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                red[h][w] = random.nextInt(255);
                green[h][w] = random.nextInt(255);
                blue[h][w] = random.nextInt(255);
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
}
