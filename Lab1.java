public class Lab1 {
    public static void main (String[] args) {
        Lab1 lab = new Lab1();
        double x, y, z, ans;
        x = 0;
        y = 0;
        z = 0;
        Point3d first, second, third;
        first = null;
        second = null;
        third = null;
        for (int i = 0; i < 9; i++) {
            if (i == 0)
                x = Double.parseDouble(args[i]);
            else if (i % 3 == 1)
                y = Double.parseDouble(args[i]);
            else if (i % 3 == 2)
                z = Double.parseDouble(args[i]);
            else if (i == 3)
                first = new Point3d(x, y, z);
            else
                second = new Point3d(x, y, z);
        }
        third = new Point3d(x, y, z);
        if (first.isEqual(second) || second.isEqual(third) || first.isEqual(third))
            System.out.println("Неверные входные данные");
        else {
            ans = lab.computeArea(first, second, third);
            System.out.println(ans);
        }
    }

    public double computeArea(Point3d first, Point3d second, Point3d third) {
        double x, y, z, p;
        x = first.distanceTo(second);
        y = first.distanceTo(third);
        z = second.distanceTo(third);
        p = (x + y + z) / 2;
        return Math.sqrt(p * (p - x) * (p - y) * (p - z));
    }
}