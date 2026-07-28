import java.util.List;
/**
 * The Line class represents a line segment defined by two points:
 * a start point and an end point. It provides methods to calculate
 * length, midpoint, check intersection with other lines,
 * and compute intersection points.
 */
public class Line {
    private Point  start;
    private Point end;
    // constructors
    /**
     * Constructs a line segment from two given points.
     *
     * @param start the starting point of the line.
     * @param end the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a line segment from the coordinates of two points.
     *
     * @param x1 the x-coordinate of the start point.
     * @param y1 the y-coordinate of the start point.
     * @param x2 the x-coordinate of the end point.
     * @param y2 the y-coordinate of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    /**
     * Returns the length of the line segment.
     *
     * @return the distance between the start and end points.
     */
    public double length() {
        return end.distance(start);
    }

    // Returns the middle point of the line
    /**
     * Returns the midpoint of the line segment.
     *
     * @return a new Point representing the middle of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    // Returns the start point of the line
    /**
     * Returns the starting point of the line.
     *
     * @return the start point.
     */
    public Point start() {
        return this.start;
    }

    // Returns the end point of the line
    /**
     * Returns the ending point of the line.
     *
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }

    // Returns true if the lines intersect, false otherwise
    /**
     * Determines whether this line intersects with another line.
     *
     * @param other the other line to test against.
     * @return true if the two line segments intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        Point a = this.start();
        Point b = this.end();
        Point c = other.start();
        Point d = other.end();
        double alpha = (d.getX() - c.getX()) * (c.getY() - a.getY()) - (d.getY() - c.getY()) * (c.getX() - a.getX());
        double beta = (d.getX() - c.getX()) * (b.getY() - a.getY()) - (d.getY() - c.getY()) * (b.getX() - a.getX());
        double gama = (b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX());
        if (beta == 0 && alpha != 0) {
            return false;
        }
        if (alpha == 0 && beta == 0) {
            return true;
        }
        return (alpha / beta >= 0 && alpha / beta <= 1) && (gama / beta >= 0 && gama / beta  <= 1);
    }

    // Returns true if this 2 lines intersect with this line, false otherwise
    /**
     * Checks if this line intersects with two other lines.
     *
     * @param other1 the first line.
     * @param other2 the second line.
     * @return true if both lines intersect with this one, false otherwise.
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return isIntersecting(other1) && isIntersecting(other2);
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    /**
     * Returns the intersection point between this line and another line.
     * If the lines do not intersect, null is returned.
     *
     * @param other the other line to check for intersection.
     * @return the intersection point if the lines intersect, otherwise null.
     */
    public Point intersectionWith(Line other) {
        Point a = this.start();
        Point b = this.end();
        Point c = other.start();
        Point d = other.end();
        double alpha = (d.getX() - c.getX()) * (c.getY() - a.getY()) - (d.getY() - c.getY()) * (c.getX() - a.getX());
        double beta = (d.getX() - c.getX()) * (b.getY() - a.getY()) - (d.getY() - c.getY()) * (b.getX() - a.getX());
        double tOnThisLine = alpha / beta;
        if (alpha == 0 && beta == 0) {
            return null;
        }
        if (isIntersecting(other)) {
            double intersectX = a.getX() + tOnThisLine * (b.getX() - a.getX());
            double intersectY = a.getY() + tOnThisLine * (b.getY() - a.getY());
            return new Point(intersectX, intersectY);
        }
        return null;
    }
    /**
     * Returns the closest intersection point between this line and a rectangle,
     * relative to the start point of this line.
     * If there are no intersection points, returns null.
     *
     * @param rect the rectangle to check intersections with
     * @return the closest intersection point to the start of the line, or null if there are none
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersections = rect.intersectionPoints(this);
        if (intersections.isEmpty()) {
            return null;
        }
        Point closest = intersections.get(0);
        double minDistance = closest.distance(this.start);
        for (Point point : intersections) {
            if (point.distance(this.start) < minDistance) {
                closest = point;
                minDistance = closest.distance(this.start);
            }
        }
        return closest;
    }
    /**
     * Returns the closest intersection point between this line and a given list of potential intersections,
     * relative to the start point of this line.
     * If there are no intersection points, returns null.
     *
     * @param intersections list of potential intersections.
     * @return the closest intersection collision info to the start of the line, or null if there are none
     */
    public CollisionInfo closestIntersectionToStartOfLine(List<CollisionInfo> intersections) {
        if (intersections.isEmpty()) {
            return null;
        }
        CollisionInfo closest = intersections.get(0);
        double minDistance = closest.collisionPoint().distance(this.start);

        for (CollisionInfo curCol : intersections) {
            if (curCol.collisionPoint().distance(this.start) < minDistance) {
                closest = curCol;
                minDistance = closest.collisionPoint().distance(this.start);
            }
        }
        return closest;
    }
    /**
     * Returns a point located at a certain distance from a given point along the direction from start to end.
     * towards start.
     * The given point must lie on the current line segment.
     *
     * @param from     the point to start from
     * @param distance the distance to move along the line
     * @return a new point on the line at the specified distance from 'from', in the direction of starting point.
     */

    public Point pointAtDistance(Point from, double distance) {
        double dx = start.getX() - from.getX();
        double dy = start.getY() - from.getY();
        double len = Math.sqrt(dx * dx + dy * dy);

        double ux = dx / len;
        double uy = dy / len;

        return new Point(from.getX() + ux * distance, from.getY() + uy * distance);
    }

    // equals -- return true if the lines are equal, false otherwise

    /**
     * Checks whether two line segments are equal.
     * Two lines are equal if they have the same two endpoints (in any order).
     *
     * @param other the line to compare to.
     * @return true if the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        return (this.start().equals(other.start()) && this.end().equals(other.end()))
                || (this.end().equals(other.start()) && this.start().equals(other.end()));
    }
}


