import java.util.ArrayList;
import java.util.List;

/**
 * a class that holds all the collidable of the game, and can operate actions on them.
 */
public class GameEnvironment {
    //fields
    private final List<Collidable> collidables;

    //constructor

    /**
     * Create GameEnvironment object without any collidables.
     * this class holds all collidables in game.
     */
    public GameEnvironment() {
        collidables = new ArrayList<Collidable>();
    }

    /**
     * Create GameEnvironment object with List of collidables to initiate.
     * this class holds all collidables in game.
     *
     * @param collidables list of collidables.
     */
    public GameEnvironment(List<Collidable> collidables) {
        this.collidables = new ArrayList<Collidable>(collidables);
    }
    /**
     * Add the given collidable to the environment.
     *
     * @param c Collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }


    /**
     * Returns the closest collision that would occur along the given trajectory,
     * based on the current list of collidable objects.
     * If there are no collisions, returns null.
     *
     * @param trajectory the path the object is expected to move along
     * @return the CollisionInfo of the closest collision, or null if no collisions are detected
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Collidable> collidablesCopy = new ArrayList<>(this.collidables);
        List<CollisionInfo> occurringCollisions = new ArrayList<>();

        for (Collidable curCol : collidablesCopy) {
            Point interPoint = trajectory.closestIntersectionToStartOfLine(curCol.getCollisionRectangle());
            if (interPoint != null) {
                occurringCollisions.add(new CollisionInfo(interPoint, curCol));
            }
        }

        if (occurringCollisions.isEmpty()) {
            return null;
        }

        return trajectory.closestIntersectionToStartOfLine(occurringCollisions);
    }
    /**
     * Remove given collidable from collection.
     *
     * @param c collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}