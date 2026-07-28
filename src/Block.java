import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * class for game blocks, represented by rectangle.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private List<HitListener> hitListeners = new ArrayList<HitListener>();
    private Boolean drawOutline = true;
    private Boolean drawShades = true;
    private Boolean drawDecorations = true;

    /**
     * Creates new block with given a rectangle.
     *
     * @param rect the rectangle
     */
    public Block(Rectangle rect) {
        this.rectangle = rect;
    }

    /**
     * Creates a new block with given x,y and color.
     *
     * @param x      top-left point X coordinate.
     * @param y      top-left point Y coordinate.
     * @param width  block width.
     * @param height block height.
     * @param color  block color.
     */
    public Block(double x, double y, double width, double height, Color color) {
        Rectangle rect = new Rectangle(new Point(x, y), width, height);
        rect.setColor(color);
        this.rectangle = rect;
    }

    /**
     * Gets the state of DrawOutLine.
     *
     * @return true if draws outline, false otherwise.
     */
    public Boolean getDrawOutline() {
        return drawOutline;
    }

    /**
     * Sets the draw outline status of a block.
     *
     * @param drawOutline true or false, according to wanted state.
     */
    public void setDrawOutline(Boolean drawOutline) {
        this.drawOutline = drawOutline;
    }

    /**
     * Gets the state of DrawShades.
     *
     * @return true if draws shades, false otherwise.
     */
    public Boolean getDrawShades() {
        return drawShades;
    }

    /**
     * Sets the draw shades status of a block.
     *
     * @param drawShades true or false, according to wanted state.
     */
    public void setDrawShades(Boolean drawShades) {
        this.drawShades = drawShades;
    }

    /**
     * Gets the state of DrawDecorations.
     *
     * @return true if draws decorations, false otherwise.
     */
    public Boolean getDrawDecorations() {
        return drawDecorations;
    }

    /**
     * Sets the draw decorations status of a block.
     *
     * @param drawDecorations true or false, according to wanted state.
     */
    public void setDrawDecorations(Boolean drawDecorations) {
        this.drawDecorations = drawDecorations;
    }

    /**
     * Sets the color of this object by delegating to the internal object.
     *
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.rectangle.setColor(color);
    }

    /**
     * Sets the upper-left point of this object by delegating to the internal object.
     *
     * @param upperLeft the new upper-left point
     */
    public void setUpperLeft(Point upperLeft) {
        this.rectangle.setUpperLeft(upperLeft.copy());
    }

    /**
     * Sets the upper-left coordinates of this object by delegating to the internal object.
     *
     * @param x the x-coordinate of the upper-left point
     * @param y the y-coordinate of the upper-left point
     */
    public void setUpperLeft(double x, double y) {
        this.rectangle.setUpperLeft(x, y);
    }

    /**
     * Get the width of the block.
     *
     * @return width of the block.
     */
    public double getWidth() {
        return this.rectangle.getWidth();
    }

    /**
     * Get the height of the block.
     *
     * @return height of the block.
     */
    public double getHeight() {
        return this.rectangle.getHeight();
    }

    /**
     * get the upper left point of the block.
     *
     * @return the upper-left point.
     */
    public Point getUpperLeft() {
        return this.rectangle.getUpperLeft();
    }

    /**
     * Get the color of the block.
     *
     * @return color of the block.
     */
    public Color getColor() {
        return this.rectangle.getColor();
    }

    /**
     * Returns the rectangle that defines the collision boundaries of this block.
     *
     * <p>This rectangle is used by the game engine to detect collisions
     * between moving objects and the block.
     *
     * @return the {@link Rectangle} representing this block's collision shape
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hitter the hit listener to add
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl to the list of listeners to hit event.
     *
     * @param hl hit listener to add
     */
    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl hit listener to remove
     */
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }

    /**
     * Calculates the new velocity of an object after colliding with this block.
     *
     * <p>The method checks whether the collision point lies on one of the
     * block's edges, using a geometric comparison threshold to account for
     * floating-point inaccuracies. If the collision occurs on a vertical edge,
     * the horizontal component of the velocity (dx) is inverted. If the
     * collision occurs on a horizontal edge, the vertical component (dy) is
     * inverted. A collision on a corner results in flipping both components.
     *
     * @param collisionPoint  the point at which the object hit the block
     * @param currentVelocity the object's velocity just before the collision
     * @return a new {@link Velocity} object representing the updated velocity
     * after applying the collision response
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double leftX = rectangle.getUpperLeft().getX();
        double rightX = leftX + rectangle.getWidth();
        double topY = rectangle.getUpperLeft().getY();
        double bottomY = topY + rectangle.getHeight();

        double x = collisionPoint.getX();
        double y = collisionPoint.getY();

        boolean hitVertical = false;
        boolean hitHorizontal = false;

        if (Math.abs(x - leftX) < GeometryTester.Comparison_threshold
                || Math.abs(x - rightX) < GeometryTester.Comparison_threshold) {
            hitVertical = true;
        }

        if (Math.abs(y - topY) < GeometryTester.Comparison_threshold
                || Math.abs(y - bottomY) < GeometryTester.Comparison_threshold) {
            hitHorizontal = true;
        }
        if (hitVertical) {
            dx = -dx;
        }
        if (hitHorizontal) {
            dy = -dy;
        }
        if (!this.ballColorMatch(hitter) && !this.hitListeners.isEmpty()) {
            hitter.setColor(this.rectangle.getColor());
            this.notifyHit(hitter);
        } else if (this.rectangle.getColor().equals(Color.WHITE)) {
            this.notifyHit(hitter);
        }
        return new Velocity(dx, dy);
    }

    /**
     * Check if the ball's color does not match the block's color.
     *
     * @param ball the ball to check
     * @return true if the ball's color is different from the block's color, false otherwise
     */
    public boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.rectangle.getColor());
    }

    /**
     * remove this block to the game as a collidable and sprite.
     *
     * @param game the game to add the block to
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * Draw block on given drawSurface.
     *
     * @param drawSurface drawSurface to draw on.
     */
    public void drawOn(DrawSurface drawSurface) {
        rectangle.drawOn(drawSurface);
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * Notify block that time has passed, currently doing nothing.
     */
    public void timePassed() {
        return;
    }
    /**
     * Get the predicted collision rectangle of this block.
     *
     * @return the collision rectangle of this block
     */
    @Override
    public Rectangle getPredictedCollisionRectangle() {
        return this.getCollisionRectangle();
    }
}
