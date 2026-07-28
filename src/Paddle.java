import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Paddle class for the game. paddle is very fun.
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private biuoop.KeyboardSensor keyboard;
    private final Block delegator;
    private final Game game;

    // constructors

    /**
     * Construct paddle with given block and game instance.
     *
     * @param game      game instance that paddle will be placed in.
     * @param delegator block object that will represent the paddle.
     */
    public Paddle(Block delegator, Game game) {
        this.delegator = delegator;
        this.game = game;
        keyboard = game.getGui().getKeyboardSensor();
    }


    /**
     * Move the paddle to the left. motion is circular.
     */
    public void moveLeft() {
        double curX = this.delegator.getUpperLeft().getX();
        double curY = this.delegator.getUpperLeft().getY();
        double paddleWidth = this.delegator.getWidth();
        int gameWidth = this.game.getGameWidth();
        int pxToMove = 2;

        if (curX + paddleWidth - pxToMove <= 0) {
            this.delegator.setUpperLeft(gameWidth - pxToMove, curY);
        } else {
            this.delegator.setUpperLeft(curX - pxToMove, curY);
        }
    }

    /**
     * Move the paddle to the right. motion is circular.
     */
    public void moveRight() {
        double curX = this.delegator.getUpperLeft().getX();
        double curY = this.delegator.getUpperLeft().getY();
        double paddleWidth = this.delegator.getWidth();
        int gameWidth = this.game.getGameWidth();
        int pxToMove = 2;

        if (curX + pxToMove >= gameWidth) {
            this.delegator.setUpperLeft(-paddleWidth + pxToMove, curY);
        } else {
            this.delegator.setUpperLeft(curX + pxToMove, curY);
        }
    }

    // Sprite

    /**
     * tell to object to think what he should do next frame, and do it.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw puddle on draw surface.
     *
     * @param d drawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        delegator.drawOn(d);
    }

    // Collidable

    /**
     * get the rectangle object that represents the paddle.
     *
     * @return rectangle object of paddle.
     */
    public Rectangle getCollisionRectangle() {
        return delegator.getCollisionRectangle();
    }

    /**
     * calculates the return velocity of given object after hitting the paddle.
     *
     * @param collisionPoint  point of collision.
     * @param currentVelocity velocity before hit.
     * @param hitter          the ball that hits the paddle
     * @return expected velocity after hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        Rectangle rect = this.delegator.getCollisionRectangle();
        double leftX = rect.getUpperLeft().getX();
        double rightX = leftX + rect.getWidth();
        double speed = currentVelocity.getSpeed();

        if (Math.abs(x - leftX) < GeometryTester.Comparison_threshold) {
            return Velocity.fromAngleAndSpeed(150, speed);
        }

        if (Math.abs(x - rightX) < GeometryTester.Comparison_threshold) {
            return Velocity.fromAngleAndSpeed(60, speed);
        }

        //calculate in what segment the ball hit
        double relativeX = collisionPoint.getX() - this.delegator.getUpperLeft().getX();
        int segment = (int) Math.floor((relativeX / this.delegator.getWidth()) * 5);
        double currentSpeed = currentVelocity.getSpeed();
        switch (segment) {
            case 0:
                return Velocity.fromAngleAndSpeed(150, currentSpeed);
            case 1:
                return Velocity.fromAngleAndSpeed(120, currentSpeed);
            case 2:
                return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            case 3:
                return Velocity.fromAngleAndSpeed(30, currentSpeed);
            case 4:
                return Velocity.fromAngleAndSpeed(60, currentSpeed);
            default:
                return currentVelocity;
        }

    }

    // Add this paddle to the game.

    /**
     * Adds the paddle to the game.
     *
     * @param g current game.
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    // בתוך מחלקת Paddle
    @Override
    public Rectangle getPredictedCollisionRectangle() {
        double curX = this.delegator.getUpperLeft().getX();
        double curY = this.delegator.getUpperLeft().getY();
        double paddleWidth = this.delegator.getWidth();
        double paddleHeight = this.delegator.getHeight();
        int pxToMove = 2;

        double predictedX = curX;

        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            predictedX = curX - pxToMove;
        } else if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            predictedX = curX + pxToMove;
        }

        int gameWidth = this.game.getGameWidth();
        predictedX = Math.min(predictedX, gameWidth - paddleWidth);
        predictedX = Math.max(predictedX, 0);

        return new Rectangle(new Point(predictedX, curY), paddleWidth, paddleHeight);
    }
}