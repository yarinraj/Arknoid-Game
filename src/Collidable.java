/**
 * An interface representing an object that can participate in collision detection.
 *
 * <p>Any object that implements this interface defines a collision shape and
 * provides logic for determining how an incoming object (such as a ball)
 * should react when a collision occurs.
 */
public interface Collidable {

    /**
     * Returns the geometric shape that represents the collision boundaries
     * of this object.
     *
     * <p>The returned rectangle is used by the game engine to detect and
     * calculate collision points with moving objects.
     *
     * @return the {@link Rectangle} that defines this object's collision area
     */
    Rectangle getCollisionRectangle();

    /**
     * Notifies the object that a collision occurred at the given point,
     * with the specified incoming velocity.
     *
     * <p>The implementing object determines how the collision affects
     * the object's movement (for example, bouncing off edges).
     * The method returns the new expected velocity after applying the
     * collision response.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the velocity of the moving object before impact
     * @param hitter          the ball that hits the collidable object
     * @return a new {@link Velocity} representing the updated velocity
     * after the collision
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Returns the collision boundaries of the object as they will be
     * at the end of the current frame (after timePassed()).
     *
     * @return The predicted collision Rectangle.
     */
    Rectangle getPredictedCollisionRectangle();
}


