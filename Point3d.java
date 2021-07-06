public class Point3d {
    private double xCoord;
    private double yCoord;
    private double zCoord;

    public Point3d (double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }
    public Point3d () {
        this(0.0, 0.0, 0.0);
    }

    public double getX () {
        return xCoord;
    }
    public double getY () {
        return yCoord;
    }
    public double getZ () {
        return zCoord;
    }

    public void setX (double val) {
        xCoord = val;
    }
    public void setY (double val) {
        yCoord = val;
    }
    public void setZ (double val) {
        zCoord = val;
    }

    public boolean isEqual (Point3d that) {
        if (this.getX() == that.getX() && this.getY() == that.getY() && this.getZ() == that.getZ()) {
            return true;
        }
        else {
            return false;
        }
    }





    public double distanceTo (Point3d that) {
        double dist = Math.sqrt((this.getX() - that.getX())*(this.getX() - that.getX()) + (this.getY() - that.getY())*(this.getY() - that.getY()) + (this.getZ() - that.getZ())*(this.getZ() - that.getZ()));
        dist = Math.round(dist*100)/100.0;
        return dist;
    }
}