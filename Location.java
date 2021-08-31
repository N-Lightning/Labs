/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Location location)
    {
        if (this.xCoord == location.xCoord && this.yCoord == location.yCoord)
            return true;
        else
            return false;
    }

    public int hashCode()
    {
        return this.xCoord*1000+this.yCoord;
    }
}