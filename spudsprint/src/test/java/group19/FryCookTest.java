package group19;
import group19.Entities.FryCook;
import group19.GameLoop.Direction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static java.lang.System.exit;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class FryCookTest {
    private FryCook frycook;

    @Before
    public void setupFryCook() {
        int[] Coordinates = {30, 60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testGetActiveImage() {
        int[] Coordinates = {30, 60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
            BufferedImage expectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookLeft.png")));
            BufferedImage actualImage = frycook.getActiveImage();
            assertNotNull(expectedImage);
            assertNotNull(actualImage);
            assertEquals(expectedImage.getWidth(), actualImage.getWidth());
            assertEquals(expectedImage.getHeight(), actualImage.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testMove() {
        int[] moveXNegative = {-70, 60};
        int[] moveXPositive = {50,60};
        int[] XDoesNotMove = {0,60};
        int[] Coordinates = {30,60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
            BufferedImage ImageLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookLeft.png")));
            BufferedImage ImageRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookRight.png")));

            int expectedNewXPos = frycook.getX() + moveXNegative[0];
            int expectedNewYPos = frycook.getY() + moveXNegative[1];
            frycook.move(moveXNegative[0],moveXNegative[1]);
            BufferedImage expectedImage = frycook.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageLeft.getWidth());
            assertEquals(expectedNewXPos,frycook.getX());
            assertEquals(expectedNewYPos,frycook.getY());

            expectedNewXPos = frycook.getX() + moveXPositive[0];
            expectedNewYPos = frycook.getY() + moveXPositive[1];
            frycook.move(moveXPositive[0],moveXPositive[1]);
            expectedImage = frycook.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageRight.getWidth());
            assertEquals(expectedNewXPos,frycook.getX());
            assertEquals(expectedNewYPos,frycook.getY());

            expectedNewXPos = frycook.getX() + XDoesNotMove[0];
            expectedNewYPos = frycook.getY() + XDoesNotMove[1];
            frycook.move(XDoesNotMove[0],XDoesNotMove[1]);
            expectedImage = frycook.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageLeft.getWidth());
            assertEquals(expectedNewXPos,frycook.getX());
            assertEquals(expectedNewYPos,frycook.getY());

        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testGetSetForce() {
        int[] Coordinates = {30, 60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
            Direction expectedDirection = Direction.RIGHT;
            frycook.setForce(Direction.RIGHT);
            Direction actualDirection = frycook.getForce();
            assertEquals(expectedDirection,actualDirection);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }
    @Test
    public void testGetSetCaused() {
        int[] Coordinates = {30, 60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
            Direction expectedDirection = Direction.RIGHT;
            frycook.setCaused(Direction.RIGHT);
            Direction actualDirection = frycook.getCaused();
            assertEquals(expectedDirection,actualDirection);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testGetXY() {
        int[] Coordinates = {30, 60};
        try {
            frycook = new FryCook(Coordinates[0], Coordinates[1]);
            int expectedX = Coordinates[0];
            int expectedY = Coordinates[1];
            assertEquals(expectedX,frycook.getX());
            assertEquals(expectedY,frycook.getY());
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }
}
