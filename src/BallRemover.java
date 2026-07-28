/**
 * a BallRemover is in charge of removing balls from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;
    /**
     * a BallRemover is in charge of removing balls from the game, as well as keeping count
     * of the number of balls that remain.
     *
     * @param game          game that is being played
     * @param remainingBalls remaining balls counter
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }
    /**
     * Removing ball from game.
     *
     * @param beingHit the block that is being hit
     * @param hitter   the ball that is hitting the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
