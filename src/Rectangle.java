import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * The Rectangle class represents a rectangle defined by its top-left corner (upperLeft),
 * width, height, and color. It can be drawn on a DrawSurface.
 */
public class Rectangle {
    private double width;
    private double height;
    private Point upperLeft;
    private Color color;

    /**
     * Constructs a random colored rectangle with a given top-left corner point, width and height.
     *
     * @param upperLeft the top-left corner of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        this.color = color.BLUE;
    }

    /**
     * Returns a list of intersection points between this rectangle and a given line.
     *
     * @param line the line to check for intersections
     * @return a list of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> points = new ArrayList<>();
        Point ul = this.upperLeft;
        Point ur = new Point(ul.getX() + width, ul.getY());
        Point ll = new Point(ul.getX(), ul.getY() + height);
        Point lr = new Point(ul.getX() + width, ul.getY() + height);
        Line top = new Line(ul, ur);
        Line bottom = new Line(ll, lr);
        Line left = new Line(ul, ll);
        Line right = new Line(ur, lr);
        Point p;
        p = line.intersectionWith(top);
        if (p != null) {
            points.add(p);
        }

        p = line.intersectionWith(bottom);
        if (p != null) {
            points.add(p);
        }

        p = line.intersectionWith(left);
        if (p != null) {
            points.add(p);
        }

        p = line.intersectionWith(right);
        if (p != null) {
            points.add(p);
        }

        return points;
    }


    /**
     * Returns the top-left point (upperLeft) of the rectangle.
     *
     * @return the top-left point
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Sets the top-left point (upperLeft) of the rectangle.
     *
     * @param upperLeft the new top-left point
     */
    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft.copy();
    }

    /**
     * Sets the top-left point of the rectangle.
     *
     * @param x x coordinate.
     * @param y y coordinate.
     */
    public void setUpperLeft(double x, double y) {
        this.upperLeft = new Point(x, y);
    }


    /**
     * Returns the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the rectangle.
     *
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Checks if a given point is strictly inside the bounds of this rectangle.
     *
     * @param p The point to check.
     * @return true if the point is inside the rectangle, false otherwise.
     */
    public boolean containsPoint(Point p) {
        double x1 = this.getUpperLeft().getX();
        double y1 = this.getUpperLeft().getY();
        double x2 = x1 + this.getWidth();
        double y2 = y1 + this.getHeight();

        return p.getX() > x1 && p.getX() < x2
                && p.getY() > y1 && p.getY() < y2;
    }

    /**
     * Draws the rectangle on the given DrawSurface.
     *
     * @param drawSurface the surface on which to draw the rectangle
     */
    public void drawOn(DrawSurface drawSurface) {
        if (drawSurface == null) {
            return;
        }
        drawSurface.setColor(color);
        drawSurface.fillRectangle((int) upperLeft.getX(), (int) upperLeft.getY(), (int) width, (int) height);
    }

}