/** HitListener interface defines a listener for hit events in a game.
 * It contains a method that is called whenever a block is hit by a ball.
 */
public interface HitListener {
    /** This method is called whenever a block is hit.
     *
     * @param beingHit the Block that was hit
     * @param hitter   the Ball that hit the block
     */
    void hitEvent(Block beingHit, Ball hitter);
}