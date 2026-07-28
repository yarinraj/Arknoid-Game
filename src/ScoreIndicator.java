import biuoop.DrawSurface;

import java.awt.Color;

/**
 * In Charge of displaying the score.
 */
public class ScoreIndicator implements Sprite {
    private final Game game;
    private final Counter score;

    /**
     * Creates ScoreIndicator.
     *
     * @param score counter of score to display.
     * @param game the game that is being played
     */
    public ScoreIndicator(Counter score, Game game) {
        this.score = score;
        this.game = game;
    }


    /**
     * Draws sprite on given drawSurface.
     *
     * @param d drawSurface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        int x = (game.getGameWidth() / 2) - 50;
        int y = 17;
        d.setColor(Color.BLACK);
        d.drawText(x, y, "Score: " + score.getValue(), 20);
    }

    /**
     * Notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}