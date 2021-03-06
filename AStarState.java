/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AStarState
{
    HashMap<Location, Waypoint> opened = new HashMap<Location, Waypoint>();
    HashMap<Location, Waypoint> closed = new HashMap<Location, Waypoint>();
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        float min = 1000000;
        Waypoint minWay = null;
        Iterator<HashMap.Entry<Location, Waypoint>> it = opened.entrySet().iterator();
        while (it.hasNext()) {
            HashMap.Entry<Location, Waypoint> pair = it.next();
            if (pair.getValue() != null)
                if (pair.getValue().getTotalCost() < min) {
                    minWay = pair.getValue();
                    min = minWay.getTotalCost();
                }
        }
        return minWay;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        boolean contains = false;
        HashMap.Entry<Location, Waypoint> thatPair = null;
        Iterator<HashMap.Entry<Location, Waypoint>> it = opened.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap.Entry<Location, Waypoint> pair = it.next();
            if (newWP.getLocation().hashCode() == pair.getKey().hashCode())
            {
                contains = true;
                thatPair = pair;
            }
        }
        if (!contains)
        {
            opened.put(newWP.getLocation(), newWP);
            return true;
        }
        else if (newWP.getTotalCost() < thatPair.getValue().getTotalCost())
        {
            opened.put(newWP.getLocation(), newWP);
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        return opened.size();
    }

    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        closed.put(loc, opened.get(loc));
        opened.remove(loc);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        boolean contains = false;
        Iterator<HashMap.Entry<Location, Waypoint>> it = closed.entrySet().iterator();
        while (it.hasNext())
        {
            HashMap.Entry<Location, Waypoint> pair = it.next();
            if (loc.equals(pair.getKey()) && pair.getValue() != null)
                contains = true;
        }
        return contains;
    }
}