/**
 * Tracker for the game score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter score;

    /**
     * Initializing Score Tracker.
     *
     * @param score Score Counter of the game.
     */
    public ScoreTrackingListener(Counter score) {
        this.score = score;
    }

    /**
     * Increasing the score accordingly.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.ballColorMatch(hitter)) {
            score.increase(5);
        }
    }
}