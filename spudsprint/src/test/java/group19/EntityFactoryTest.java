package group19;

import group19.Entities.*;
import org.junit.Before;
import org.junit.Test;
import group19.Entities.PlayerCharacter;
import java.io.IOException;
import java.util.ArrayList;
import static java.lang.System.exit;
import static org.junit.Assert.assertEquals;

public class EntityFactoryTest {
    private DeepFryer expected_deepfryer;
    private PotatoFriend expected_potatofriend;
    private PlayerCharacter expected_playercharacter;
    private FryCook expected_frycook;
    private DoorKey expected_doorkey;

    @Before
    public void setupEntityFactory() {
        int[] Coordinates = {30, 60};
        expected_deepfryer = new DeepFryer(Coordinates[0], Coordinates[1]);
        expected_potatofriend = new PotatoFriend(Coordinates);
        expected_doorkey = new DoorKey(Coordinates[0] * 32, Coordinates[1] * 32);
    }

    @Test
    public void testMakeDeepFryers() {
        ArrayList<int[]> coordinates = new ArrayList<>();
        coordinates.add(new int[]{30, 60});
        DeepFryer make_deepfryer = EntityFactory.makeDeepFryers(coordinates, 1).get(0);
        assertEquals(expected_deepfryer.getX(), make_deepfryer.getX());
        assertEquals(expected_deepfryer.getY(), make_deepfryer.getY());
    }


    @Test
    public void testMakePotatoFriend() {
        int[] Coordinates = {30, 60};
        assertEquals(expected_potatofriend.getX(), EntityFactory.makePotatoFriend(Coordinates).getX());
        assertEquals(expected_deepfryer.getY(), EntityFactory.makePotatoFriend(Coordinates).getY());
    }

    @Test
    public void testMakePlayerCharacter() {
        int[] Coordinates = {30, 60};
        PlayerCharacter make_playerCharacter = null;
        try {
            expected_playercharacter = new PlayerCharacter(Coordinates[0], Coordinates[1]);
            make_playerCharacter = EntityFactory.makePlayerCharacter(Coordinates[0], Coordinates[1]);
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }
        assertEquals(expected_playercharacter.getX(), make_playerCharacter.getX());
        assertEquals(expected_playercharacter.getY(), make_playerCharacter.getY());
    }

    @Test
    public void testMakeFryCook() {
        int tileSize = 32;
        ArrayList<FryCook> make_frycook;

        for (int difficulty = 1; difficulty <= 9; difficulty++) {
            try {
                make_frycook = EntityFactory.makeFryCooks(difficulty, tileSize);

                switch (difficulty) {
                    case 1, 2, 3:
                        expected_frycook = new FryCook(21 * tileSize, 11 * tileSize);
                        make_frycook.add(new FryCook(21 * tileSize, 11 * tileSize));
                        assertEquals(expected_frycook.getX(), make_frycook.get(0).getX());
                        assertEquals(expected_frycook.getY(), make_frycook.get(0).getY());
                        break;

                    case 4, 5, 6:
                        expected_frycook = new FryCook(21 * tileSize, 12 * tileSize);
                        make_frycook.add(new FryCook(21 * tileSize, 12 * tileSize));
                        assertEquals(expected_frycook.getX(), make_frycook.get(0).getX());
                        expected_frycook = new FryCook(21 * tileSize, 17 * tileSize);
                        make_frycook.add(new FryCook(21 * tileSize, 17 * tileSize));
                        assertEquals(expected_frycook.getY(), make_frycook.get(1).getY());

                        break;

                    case 7:
                        expected_frycook = new FryCook(10 * tileSize, 9 * tileSize);
                        make_frycook.add(new FryCook(10 * tileSize, 9 * tileSize));
                        assertEquals(expected_frycook.getX(), make_frycook.get(0).getX());
                        expected_frycook = new FryCook(24 * tileSize, 9 * tileSize);
                        make_frycook.add(new FryCook(24 * tileSize, 9 * tileSize));
                        assertEquals(expected_frycook.getY(), make_frycook.get(1).getY());
                        break;

                    case 8:
                        expected_frycook = new FryCook(9 * tileSize, 9 * tileSize);
                        make_frycook.add(new FryCook(9 * tileSize, 9 * tileSize));
                        assertEquals(expected_frycook.getX(), make_frycook.get(0).getX());
                        expected_frycook = new FryCook(23 * tileSize, 9 * tileSize);
                        make_frycook.add(new FryCook(23 * tileSize, 9 * tileSize));
                        assertEquals(expected_frycook.getY(), make_frycook.get(1).getY());
                        break;

                    case 9:
                        expected_frycook = new FryCook(9 * tileSize, 10 * tileSize);
                        make_frycook.add(new FryCook(9 * tileSize, 10 * tileSize));
                        assertEquals(expected_frycook.getX(), make_frycook.get(0).getX());
                        expected_frycook = new FryCook(23 * tileSize, 9 * tileSize);
                        make_frycook.add(new FryCook(23 * tileSize, 9 * tileSize));
                        assertEquals(expected_frycook.getY(), make_frycook.get(1).getY());
                        break;

                    default:
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                exit(0);
            }
        }
    }

    @Test
    public void testMakeDoorKeys() {
        ArrayList<int[]> coordinates = new ArrayList<int[]>();
        int[] coords = {30,60};
        coordinates.add(coords);
        ArrayList<DoorKey> make_doorkey = EntityFactory.makeDoorKeys(coordinates, 32);
        assertEquals(expected_doorkey.getXCoord(), make_doorkey.get(0).getXCoord());
        assertEquals(expected_doorkey.getYCoord(), make_doorkey.get(0).getYCoord());
    }
}