import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class FractalExplorer
{
    private int screenSize;
    private JImageDisplay jImageDisplay;
    private FractalGenerator generator;
    private Rectangle2D.Double rectangle;


    FractalExplorer(int size)
    {
        screenSize = size;
        rectangle = new Rectangle2D.Double();
        generator = new Mandelbrot();
        generator.getInitialRange(rectangle);
        jImageDisplay = new JImageDisplay(screenSize, screenSize);
    }

    public void createAndShowGUI()
    {
        JFrame f = new JFrame("Fractal Explorer");
        f.add(jImageDisplay, BorderLayout.CENTER);

        JButton reset = new JButton("Reset Display");
        ButtonHandler resetHandler = new ButtonHandler();
        reset.addActionListener(resetHandler);
        f.add(reset, BorderLayout.SOUTH);

        MouseHandler click = new MouseHandler();
        jImageDisplay.addMouseListener(click);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
        f.setResizable(false);
    }

    private void drawFractal()
    {
        for (int x = 0; x < screenSize; x++)
        {
            for (int y = 0; y < screenSize; y++)
            {
                double xCoord = generator.getCoord(rectangle.x, rectangle.x + rectangle.width, screenSize, x);
                double yCoord = generator.getCoord(rectangle.y, rectangle.y + rectangle.height, screenSize, y);
                int num = generator.numIterations(xCoord, yCoord);
                if (num == -1)
                    jImageDisplay.drawPixel(x, y, 0);
                else
                {
                    float hue = 0.7f + (float) num / 200f;
                    int rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    jImageDisplay.drawPixel(x, y, rgbColor);
                }
            }
        }
        jImageDisplay.repaint();
    }

    private class ButtonHandler implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            generator.getInitialRange(rectangle);
            drawFractal();
        }
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            double xCoord = generator.getCoord(rectangle.x, rectangle.x + rectangle.width, screenSize, x);
            int y = e.getY();
            double yCoord = generator.getCoord(rectangle.y, rectangle.y + rectangle.height, screenSize, y);
            generator.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }
}