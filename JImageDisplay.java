import java.awt.*;
import java.awt.image.BufferedImage;

public class JImageDisplay extends javax.swing.JComponent
{
    public java.awt.image.BufferedImage image;
    JImageDisplay(int width, int height)
    {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        setPreferredSize(new java.awt.Dimension(width, height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
    }

    public void clearImage()
    {
        for (int i = 0; i < image.getWidth(); i++)
            for (int k = 0; k < image.getHeight(); k++)
                image.setRGB(i, k, 0);
    }

    public void drawPixel(int x, int y, int rgbColor)
    {
        image.setRGB(x, y, rgbColor);
    }
}