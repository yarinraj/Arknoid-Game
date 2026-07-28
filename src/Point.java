/**
 * The Point class represents a point in a 2D plane,
 * defined by its x and y coordinates.
 */
public class Point {
    private double x;
    private double y;
    // constructor

    /**
     * Constructs a new point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point.
     * @param y the y-coordinate of the point.
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Sets the x-coordinate of this point.
     *
     * @param x the new x-coordinate
     */
    public void setX(double x) {
        this.x = x;
    }
    /**
     * Sets the y-coordinate of this point.
     *
     * @param y the new y-coordinate
     */
    public void setY(double y) {
        this.y = y;
    }


    /**
     * Calculates the distance between this point and another point.
     *
     * @param other the point to calculate the distance to.
     * @return the distance between the two points.
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    // equals -- return true is the points are equal, false otherwise

    /**
     * Checks whether this point is equal to another point.
     * Two points are considered equal if their x and y coordinates
     * are equal (compared using double tolerance).
     *
     * @param other the point to compare with.
     * @return true if the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        return GeometryTester.doubleEquals(x, other.getX()) && GeometryTester.doubleEquals(y, other.getY());
    }

    // Return the x and y values of this point

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x value.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y value.
     */
    public double getY() {
        return y;
    }
    /**
     * Returns a copy of this point.
     * @return a new Point with same coordinates
     */
    public Point copy() {
        return new Point(this.x, this.y);
    }
}
