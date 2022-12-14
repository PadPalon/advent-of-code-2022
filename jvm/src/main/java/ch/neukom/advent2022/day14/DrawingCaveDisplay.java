package ch.neukom.advent2022.day14;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class DrawingCaveDisplay implements CaveDisplay {
    private static final int IMAGE_SCALE = 4;

    private final JFrame frame;
    private final ImageIcon imageIcon;
    private final BufferedImage image;

    private final int minX;
    private final int minY;
    private final int width;
    private final int height;

    private final int drawSpeed;

    private int movementCount = 0;

    public DrawingCaveDisplay(int width,
                              int height,
                              int minX,
                              int maxX,
                              int minY,
                              int maxY,
                              Map<GridCoordinate, TileType> cave,
                              int drawSpeed) {
        this.image = new BufferedImage(width + 1, height + 1, Image.SCALE_DEFAULT);
        this.imageIcon = new ImageIcon(image.getScaledInstance(width * IMAGE_SCALE, height * IMAGE_SCALE, Image.SCALE_DEFAULT));
        this.frame = initializeFrame();
        this.width = width;
        this.height = height;
        this.minX = minX;
        this.minY = minY;
        this.drawSpeed = drawSpeed;

        initializeImage(maxY, maxX, cave);
    }

    public void update() {
        imageIcon.setImage(image.getScaledInstance(width * IMAGE_SCALE, height * IMAGE_SCALE, Image.SCALE_DEFAULT));
        frame.repaint();
        Thread.sleep(10);
    }

    private void initializeImage(int maxY, int maxX, Map<GridCoordinate, TileType> cave) {
        for (int y = this.minY; y <= maxY; y++) {
            for (int x = this.minX; x <= maxX; x++) {
                TileType tileType = cave.getOrDefault(new GridCoordinate(x, y), TileType.VOID);
                setPixel(new GridCoordinate(x, y), tileType);
            }
        }
    }

    private JFrame initializeFrame() {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(this.imageIcon));
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        return frame;
    }

    public void handleChange(GridCoordinate coordinate, TileType type) {
        setPixel(coordinate, type);
        movementCount = (movementCount + 1) % drawSpeed;
        if (movementCount == 0) {
            update();
        }
    }

    private void setPixel(GridCoordinate coordinate, TileType tileType) {
        int x = coordinate.x() - this.minX;
        int y = coordinate.y() - this.minY;
        if (x >= 0 && x < this.image.getWidth() && y >= 0 && y < this.image.getHeight()) {
            this.image.setRGB(x, y, tileType.getColor().getRGB());
        }
    }
}
