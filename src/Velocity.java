/**
 * The Velocity class represents the change in position on the x and y axes.
 * It is used to control the speed and direction of a moving object such as a Ball.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a new Velocity with the given horizontal and vertical components.
     *
     * @param dx the change in x direction
     * @param dy the change in y direction
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a new velocity object with same attributes.
     *
     * @return copy of this velocity.
     */
    public Velocity copy() {
        return new Velocity(this.dx, this.dy);
    }

    /**
     * Returns the horizontal velocity component (dx).
     *
     * @return dx
     */
    public double getDx() {
        return dx;
    }

    /**
     * Returns the vertical velocity component (dy).
     *
     * @return dy
     */
    public double getDy() {
        return dy;
    }

    /**
     * calculates speed value of given velocity.
     *
     * @return Speed value.
     */
    public double getSpeed() {
        return Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * Sets the horizontal velocity component.
     *
     * @param dx the new horizontal velocity
     */

    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets the vertical velocity component.
     *
     * @param dy the new vertical velocity
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Creates a Velocity instance from an angle (in degrees) and speed.
     * Angle 0 points to the right, 90 points upward.
     *
     * @param angleDegrees the angle of movement in degrees
     * @param speed        the speed (magnitude of velocity)
     * @return a new Velocity with calculated dx and dy
     */
    public static Velocity fromAngleAndSpeed(double angleDegrees, double speed) {
        double angleRadians = Math.toRadians(angleDegrees);
        double dx = speed * Math.cos(angleRadians);
        double dy = -speed * Math.sin(angleRadians);
        return new Velocity(dx, dy);
    }

    /**
     * Applies this velocity to a given point and returns the resulting point.
     * The new point will have coordinates (x + dx, y + dy).
     *
     * @param p the point to apply the velocity to
     * @return a new Point after moving according to this velocity
     */
    public Point applyToPoint(Point p) {
        Point p1 = new Point(p.getX() + dx, p.getY() + dy);
        return p1;
    }

    /**
     * Reverses the horizontal direction of the velocity (dx = -dx).
     */
    public void reverseX() {
        dx = -dx;
    }

    /**
     * Reverses the vertical direction of the velocity (dy = -dy).
     */
    public void reverseY() {
        dy = -dy;
    }
}