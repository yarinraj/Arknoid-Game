import java.awt.Color;
/**
 * A factory class for creating different types of blocks with predefined styles.
 */
public class BlockFactory {
    /**
     * Creates a game block with decorations, outline, and shades.
     *
     * @param x      the x-coordinate of the block's top-left corner
     * @param y      the y-coordinate of the block's top-left corner
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     * @return a new Block instance styled as a game block
     */
    public static Block gameBlock(double x, double y, double width, double height, Color color) {
        Block newBlock = new Block(x, y, width, height, color);
        newBlock.setDrawDecorations(true);
        newBlock.setDrawOutline(true);
        newBlock.setDrawShades(true);
        return newBlock;
    }
/**
     * Creates a border block with outline but no decorations or shades.
     *
     * @param x      the x-coordinate of the block's top-left corner
     * @param y      the y-coordinate of the block's top-left corner
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     * @return a new Block instance styled as a border block
     */
    public static Block borderBlock(double x, double y, double width, double height, Color color) {
        Block newBlock = new Block(x, y, width, height, color);
        newBlock.setDrawDecorations(false);
        newBlock.setDrawOutline(true);
        newBlock.setDrawShades(false);
        return newBlock;
    }
/**
     * Creates a background block with no decorations, outline, or shades.
     *
     * @param x      the x-coordinate of the block's top-left corner
     * @param y      the y-coordinate of the block's top-left corner
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     * @return a new Block instance styled as a background block
     */
    public static Block backgroundBlock(double x, double y, double width, double height, Color color) {
        Block newBlock = new Block(x, y, width, height, color);
        newBlock.setDrawDecorations(false);
        newBlock.setDrawOutline(false);
        newBlock.setDrawShades(false);
        return newBlock;
    }
/**
     * Creates a paddle block with outline but no decorations or shades.
     *
     * @param x      the x-coordinate of the block's top-left corner
     * @param y      the y-coordinate of the block's top-left corner
     * @param width  the width of the block
     * @param height the height of the block
     * @param color  the color of the block
     * @return a new Block instance styled as a paddle block
     */
    public static Block paddleBlock(double x, double y, double width, double height, Color color) {
        Block newBlock = new Block(x, y, width, height, color);
        newBlock.setDrawDecorations(false);
        newBlock.setDrawOutline(true);
        newBlock.setDrawShades(false);
        return newBlock;

    }
}