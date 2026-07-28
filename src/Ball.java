import java.awt.Color;

import biuoop.DrawSurface;

/**
 * The {@code Ball} class represents a ball with a center point, radius, color,
 * velocity, and movement boundaries. The ball can draw itself on a
 * {@link DrawSurface} and move within defined borders while bouncing off them.
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private int minX, minY, maxX, maxY;
    private GameEnvironment gameEnvironment;


    /**
     * Constructs a new ball.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Constructs a new Ball with a specified position, size, color, and velocity.
     *
     * @param x        the x-coordinate of the ball's center
     * @param y        the y-coordinate of the ball's center
     * @param r        the radius (size) of the ball
     * @param color    the color of the ball
     * @param velocity the velocity to assign to the ball (will be copied)
     */
    public Ball(double x, double y, int r, Color color, Velocity velocity) {
        this(new Point(x, y), r, color);
        this.velocity = velocity.copy();
    }

    /**
     * Returns the x-coordinate of this ball's center.
     *
     * @return the x value of the center point
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Returns the y-coordinate of this ball's center.
     *
     * @return the y value of the center point
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Returns the radius of the ball.
     *
     * @return the radius
     */
    public int getSize() {
        return r;
    }

    /**
     * Returns the ball's color.
     *
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Sets the ball's color.
     *
     * @param color the new color of the ball
     */
    public void setColor(java.awt.Color color) {
        this.color = color;
    }

    /**
     * Draws the ball on the given surface.
     *
     * @param surface the surface on which the ball will be drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * Sets the velocity of the ball.
     *
     * @param v the velocity to apply to this ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets the velocity of the ball using dx and dy components.
     *
     * @param dx change in x direction
     * @param dy change in y direction
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Returns the current velocity of the ball.
     *
     * @return the velocity object
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Sets the center point of the ball.
     *
     * @param center the new center point
     */
    public void setCenter(Point center) {
        this.center = center;
    }

    /**
     * Returns a copy of the center point of the ball.
     *
     * @return a new Point representing the center
     */
    public Point getCenter() {
        return center.copy();
    }

    /**
     * Sets the gameEnvironment of the ball (not a copy).
     *
     * @param g GameEnvironment to set.
     */
    public void setGameEnvironment(GameEnvironment g) {
        this.gameEnvironment = g;
    }

    /**
     * Moves the ball one step according to its velocity.
     * If the ball reaches one of the boundaries,
     * it bounces off by reversing its velocity direction.
     */
    public void moveOneStep() {
        Point curPosition = this.getCenter();
        Line trajectory = new Line(curPosition, this.velocity.applyToPoint(curPosition));
        CollisionInfo closestCollision = gameEnvironment.getClosestCollision(trajectory);
        if (closestCollision == null) {
            this.setCenter(this.velocity.applyToPoint(curPosition));
            return;
        }
        // move Geometry.Ball slightly before hitting the wall and update velocity.


        this.setCenter(trajectory.pointAtDistance(closestCollision.collisionPoint(), 0.1));
        this.setVelocity(closestCollision.collisionObject().hit(this, closestCollision.collisionPoint(),
                this.velocity));
    }


    /**
     * Sets the valid movement boundaries for the ball.
     *
     * @param minX minimum x boundary (left)
     * @param minY minimum y boundary (top)
     * @param maxX maximum x boundary (right)
     * @param maxY maximum y boundary (bottom)
     */
    public void setBoundary(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * Notify the ball that time has passed, move it a step.
     */
    public void timePassed() {
        this.moveOneStep();
    }
    /**
     * removes this ball to the given game.
     *
     * @param game the game to add the ball to
     */
    public void removeFromGame(Game game) {
        game.removeSprite(this);
    }
}