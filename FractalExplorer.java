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
    private int screenSize, rowsRemaining;
    private JImageDisplay jImageDisplay;
    private FractalGenerator generator;
    private Rectangle2D.Double rectangle;
    private JFrame f;
    private JButton reset, save;
    private JComboBox fractals;

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
        fractals = new JComboBox();

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
        enableUI(false);
        rowsRemaining = screenSize;
        FractalWorker fractal;
        for (int y = 0; y < screenSize; y++)
        {
            fractal = new FractalWorker(y);
            fractal.execute();
        }
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
        public void mouseClicked(MouseEvent e)
        {
            if (rowsRemaining == 0)
            {
                int x = e.getX();
                double xCoord = generator.getCoord(rectangle.x, rectangle.x + rectangle.width, screenSize, x);
                int y = e.getY();
                double yCoord = generator.getCoord(rectangle.y, rectangle.y + rectangle.height, screenSize, y);
                generator.recenterAndZoomRange(rectangle, xCoord, yCoord, 0.5);
                drawFractal();
            }
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractal = new FractalExplorer(800);
        fractal.createAndShowGUI();
        fractal.drawFractal();
    }

    private class FractalWorker extends SwingWorker<Object, Object>
    {
        int y;
        int[] rgb;
        int rgbColor;
        float hue;
        FractalWorker(int y)
        {
            this.y = y;
        }

        @Override
        protected Object doInBackground() throws Exception {
            rgb = new int[screenSize];
            for (int x = 0; x < screenSize; x++)
            {
                double xCoord = generator.getCoord(rectangle.x, rectangle.x + rectangle.width, screenSize, x);
                double yCoord = generator.getCoord(rectangle.y, rectangle.y + rectangle.height, screenSize, y);
                int num = generator.numIterations(xCoord, yCoord);
                if (num == -1)
                    rgb[x] = 0;
                else
                {
                    hue = 0.7f + (float) num / 200f;
                    rgbColor = Color.HSBtoRGB(hue, 1f, 1f);
                    rgb[x] = rgbColor;
                }
            }
            return null;
        }

        @Override
        protected void done()
        {
            for (int x = 0; x < screenSize; x++)
                jImageDisplay.drawPixel(x, y, rgb[x]);
            jImageDisplay.repaint(0, 0, y, screenSize, 1);
            rowsRemaining -= 1;
            if (rowsRemaining < 1)
                enableUI(true);
        }
    }

    void enableUI(boolean val)
    {
        reset.setEnabled(val);
        save.setEnabled(val);
        fractals.setEnabled(val);
    }
}