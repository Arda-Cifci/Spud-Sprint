package group19;
import group19.Entities.PlayerCharacter;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import static java.lang.System.exit;
import static org.junit.Assert.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;


public class PlayerCharacterTest {
    private PlayerCharacter playercharacter;

    @Before
    public void setupPlayerCharacter() {
        int[] Coordinates = {30, 60};
        try {
            playercharacter = new PlayerCharacter(Coordinates[0], Coordinates[1]);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testGetActiveImage() {
        int[] Coordinates = {30, 60};
        try {
            playercharacter = new PlayerCharacter(Coordinates[0], Coordinates[1]);
            BufferedImage expectedImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/FryCookLeft.png")));
            BufferedImage actualImage = playercharacter.getActiveImage();
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
        int[] moveXPositive = {50, 60};
        int[] moveYNegative = {-70, 60};
        int[] moveYPositive = {50, 60};
        int[] XYDoesNotMove = {0, 0};
        int[] Coordinates = {30, 60};
        try {
            playercharacter = new PlayerCharacter(Coordinates[0], Coordinates[1]);
            BufferedImage ImageLeft = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_left.png")));
            BufferedImage ImageRight = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_right.png")));
            BufferedImage ImageUp = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));
            BufferedImage ImageDown = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));
            BufferedImage ImageStill = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/potato_front.png")));


            int expectedNewXPos = playercharacter.getX() + moveXNegative[0];
            int expectedNewYPos = playercharacter.getY() + moveXNegative[1];
            playercharacter.move(moveXNegative[0], moveXNegative[1]);
            BufferedImage expectedImage = playercharacter.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageLeft.getWidth());
            assertEquals(expectedNewXPos, playercharacter.getX());
            assertEquals(expectedNewYPos, playercharacter.getY());

            expectedNewXPos = playercharacter.getX() + moveXPositive[0];
            expectedNewYPos = playercharacter.getY() + moveXPositive[1];
            playercharacter.move(moveXPositive[0], moveXPositive[1]);
            expectedImage = playercharacter.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageRight.getWidth());
            assertEquals(expectedNewXPos, playercharacter.getX());
            assertEquals(expectedNewYPos, playercharacter.getY());

            expectedNewXPos = playercharacter.getX() + moveYNegative[0];
            expectedNewYPos = playercharacter.getY() + moveYNegative[1];
            playercharacter.move(moveXNegative[0], moveYNegative[1]);
            expectedImage = playercharacter.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageUp.getWidth());
            assertEquals(expectedNewXPos, playercharacter.getX());
            assertEquals(expectedNewYPos, playercharacter.getY());

            expectedNewXPos = playercharacter.getX() + moveYPositive[0];
            expectedNewYPos = playercharacter.getY() + moveYPositive[1];
            playercharacter.move(moveYPositive[0], moveYPositive[1]);
            expectedImage = playercharacter.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageDown.getWidth());
            assertEquals(expectedNewXPos, playercharacter.getX());
            assertEquals(expectedNewYPos, playercharacter.getY());

            expectedNewXPos = playercharacter.getX() + XYDoesNotMove[0];
            expectedNewYPos = playercharacter.getY() + XYDoesNotMove[1];
            playercharacter.move(XYDoesNotMove[0], XYDoesNotMove[1]);
            expectedImage = playercharacter.getActiveImage();
            assertNotNull(expectedImage);
            assertEquals(expectedImage.getWidth(), ImageStill.getWidth());
            assertEquals(expectedNewXPos, playercharacter.getX());
            assertEquals(expectedNewYPos, playercharacter.getY());

        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }

    @Test
    public void testGetXY() {
        int[] Coordinates = {30, 60};
        try {
            playercharacter = new PlayerCharacter(Coordinates[0], Coordinates[1]);
            int expectedX = Coordinates[0];
            int expectedY = Coordinates[1];
            assertEquals(expectedX,playercharacter.getX());
            assertEquals(expectedY,playercharacter.getY());
            playercharacter.setY(5);
            expectedY = 5;
            assertEquals(expectedY,playercharacter.getY());
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
    }
}