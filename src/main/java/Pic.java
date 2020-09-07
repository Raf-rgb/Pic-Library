import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Pic extends Mat {
    private BufferedImage img;
    private JFrame window;
    
    public int width;
    public int height;

    //Constructors
    Pic(String dir_) {
        try {
            img = ImageIO.read(new File(dir_));
        } catch (IOException e) {
            e.printStackTrace();
        }

        width = img.getWidth();
        height = img.getHeight();
    }

    Pic(int w, int h) {
        width = w;
        height = h;

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    Pic(BufferedImage img_) {
        img = img_;
        width = img.getWidth();
        height = img.getHeight();
    }

    //Function to visualize the image
    public void show() {
        window = new JFrame(img.getClass().getName());
        window.setSize(width, height);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.getContentPane().add(new JLabel(new ImageIcon(img)));
        window.pack();
        window.setVisible(true);
    }

    //Function to save image .png
    public void save(String dir) {
        File outputImage = new File(dir);
        try {
            ImageIO.write(img, "png", outputImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Function to get pixels from the image as an array
    public int[] pixels() {
        int[] pixels = new int[width*height];
        img.getRGB(0, 0, width, height, pixels, 0, width);

        return pixels;
    } 

    //Function to get a copy of the Pic object
    public Pic copy() {
        BufferedImage copy = new BufferedImage(img.getWidth(), img.getWidth(), img.getType());
        copy.setRGB(0, 0, width, height, pixels(), 0, width);
        Pic newPic = new Pic(copy);

        return newPic;
    }

    //Setters
    public void set(int x, int y, int r, int g, int b) { img.setRGB(x, y, new Color(r, g, b).getRGB()); }

    public void set(int x, int y, Color c) { img.setRGB(x, y, c.getRGB()); }

    public void set(int x, int y, int c) { img.setRGB(x, y, new Color(c, c, c).getRGB()); }

    //Getters
    public int get(int x, int y) { return new Color(img.getRGB(x, y)).getRGB(); }

    public int getRed(int x, int y) { return new Color(img.getRGB(x, y)).getRed(); }

    public int getGreen(int x, int y) { return new Color(img.getRGB(x, y)).getGreen(); }

    public int getBlue(int x, int y) { return new Color(img.getRGB(x, y)).getBlue(); }

    public int getAlpha(int x, int y) { return new Color(img.getRGB(x, y)).getAlpha(); }

    //Function to get brightness from the pixel in (x, y) location
    public float getBrightness(int x, int y) {
        Color c = new Color(img.getRGB(x, y));
        float[] HSB = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), HSB);

        return map(HSB[HSB.length-1], 0, 1, 0, 255);
    }
}