import java.awt.geom.Rectangle2D;

public class Tricorn extends FractalGenerator
{
    public static final int MAX_ITERATIONS = 2000;
    int sch = 0;
    double xz = 0, yz = 0, xv;

    public void getInitialRange(Rectangle2D.Double range)
    {
        range.x = -2;
        range.y = -2;
        range.height = 4;
        range.width = 4;
    }

    public int numIterations(double x, double y)
    {
        xz = 0;
        yz = 0;
        sch = 0;
        while (xz*xz + yz*yz < 4 && sch < MAX_ITERATIONS)
        {
            sch += 1;
            xv = xz;
            xz = xz * xz - yz * yz + x;
            yz = -2 * xv * yz + y;
        }

        if (sch >= MAX_ITERATIONS)
            return -1;
        else
            return sch;
    }

    @Override
    public String toString() {
        return "Tricorn";
    }
}