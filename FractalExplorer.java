import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.FileFilter;
import java.io.IOException;

public class FractalExplorer
{
    private int screenSize;
    private JImageDisplay jImageDisplay;
    private FractalGenerator generator;
    private Rectangle2D.Double rectangle;
    private JFrame f;
    private JButton reset, save;

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
        f = new JFrame("Fractal Explorer");
        f.add(jImageDisplay, BorderLayout.CENTER);

        reset = new JButton("Reset Display");
        save = new JButton("Save Image");
        ButtonHandler buttonHandler = new ButtonHandler();
        save.addActionListener(buttonHandler);
        reset.addActionListener(buttonHandler);
        JPanel buttons = new JPanel();
        buttons.add(save);
        buttons.add(reset);
        f.add(buttons, BorderLayout.SOUTH);

        MouseHandler click = new MouseHandler();
        jImageDisplay.addMouseListener(click);

        JPanel fractalBox = new JPanel();
        JLabel fractalLabel = new JLabel("Fractal: ");
        JComboBox fractals = new JComboBox();

        fractals.addItem(new Mandelbrot());
        fractals.addItem(new Tricorn());
        fractals.addItem(new BurningShip());
        ComboBoxHandler fractalsHandler = new ComboBoxHandler();
        fractals.addActionListener(fractalsHandler);

        fractalBox.add(fractalLabel);
        fractalBox.add(fractals);

        f.add(fractalBox, BorderLayout.NORTH);

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

    public class ComboBoxHandler implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            JComboBox c = (JComboBox) e.getSource();
            String s = c.getSelectedItem().toString();
            if (s == "Mandelbrot")
            {
                generator = new Mandelbrot();
                generator.getInitialRange(rectangle);
                drawFractal();
            }
            else if(s == "Tricorn")
            {
                generator = new Tricorn();
                generator.getInitialRange(rectangle);
                drawFractal();
            }
            else
            {
                generator = new BurningShip();
                generator.getInitialRange(rectangle);
                drawFractal();
            }
        }
    }

    private class ButtonHandler implements java.awt.event.ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == reset)
            {
                generator.getInitialRange(rectangle);
                drawFractal();
            }
            else
            {
                JFileChooser chooser = new JFileChooser();
                javax.swing.filechooser.FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(jImageDisplay) == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        ImageIO.write(jImageDisplay.image, "png", chooser.getSelectedFile());
                    }catch (IOException exception)
                    {
                        JOptionPane.showMessageDialog(f, exception.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
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