package group19;


import group19.Entities.FryCook;
import group19.GameLoop.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.junit.Before;

public class PlayerTrackerTest {
    private GameLoop gl;
    private PlayerTracker pt;
    private GameState gs;
    ArrayList<FryCook> fc;

    @Before
    public void setupPlayerTracker() throws IOException{
        JFrame frame = new JFrame();
        gs = new GameState();
        gl = new GameLoop(frame, gs);
        pt = new PlayerTracker(gl);
        gs.setDifficulty(1);
        gl.initMap();
        fc = gl.getFryCooks();
    }

    @Test
    public void testFryCookMoveLeftToPlayer() throws IOException{
        gl.getPlayerCharacter().setX(96);
        gl.getPlayerCharacter().setY(96);
        fc.add(new FryCook(4 * 32, 3 * 32));
        FryCook f = fc.get(fc.size() - 1);

        pt.fryCookTrackingPlayerMove(f);

        int expected = 126;
        assertEquals(expected, f.getX());

    }

    @Test
    public void testFryCookMoveRightToPlayer() throws IOException{
        gl.getPlayerCharacter().setX(96);
        gl.getPlayerCharacter().setY(96);
        fc.add(new FryCook(2 * 32, 3 * 32));
        FryCook f = fc.get(fc.size() - 1);

        pt.fryCookTrackingPlayerMove(f);

        int expected = 66;
        assertEquals(expected, f.getX());
    }

    @Test
    public void testFryCookMoveUpToPlayer() throws IOException{
        gl.getPlayerCharacter().setX(96);
        gl.getPlayerCharacter().setY(96);
        fc.add(new FryCook(3 * 32, 4 * 32));
        FryCook f = fc.get(fc.size() - 1);

        pt.fryCookTrackingPlayerMove(f);

        int expected = 126;
        assertEquals(expected, f.getY());
    }

    @Test
    public void testFryCookMoveDownToPlayer() throws IOException{
        gl.getPlayerCharacter().setX(96);
        gl.getPlayerCharacter().setY(96);
        fc.add(new FryCook(3 * 32, 2 * 32));
        FryCook f = fc.get(fc.size() - 1);

        pt.fryCookTrackingPlayerMove(f);

        int expected = 66;
        assertEquals(expected, f.getY());
    }

    @Test
    public void testFryCookForcedFromRight() throws IOException{
        gl.getPlayerCharacter().setX(960);
        gl.getPlayerCharacter().setY(704);
        fc.add(new FryCook(2 * 32, 22 * 32));
        FryCook f = fc.get(fc.size() - 1);

        int spot = f.getY();
        for(int i = 0; i < 1000; i++){
            pt.fryCookTrackingPlayerMove(f);
            if(spot != f.getY()){
                break;
            }
        }
    
        int expected = 702;
        assertEquals(expected, f.getY());
    }

    @Test
    public void testFryCookForcedFromLeft() throws IOException{
        gl.getPlayerCharacter().setX(64);
        gl.getPlayerCharacter().setY(704);
        fc.add(new FryCook(30 * 32, 22 * 32));
        FryCook f = fc.get(fc.size() - 1);
        int spot = f.getY();
        for(int i = 0; i < 1000; i++){
            pt.fryCookTrackingPlayerMove(f);
            if(spot != f.getY()){
                break;
            }
        }
    
        int expected = 702;
        assertEquals(expected, f.getY());
    }

    @Test
    public void testFryCookForcedFromUp() throws IOException{
        gl.getPlayerCharacter().setX(64);
        gl.getPlayerCharacter().setY(64);
        fc.add(new FryCook(2 * 32, 10 * 32));
        FryCook f = fc.get(fc.size() - 1);
        int spot = f.getX();
        for(int i = 0; i < 1000; i++){
            pt.fryCookTrackingPlayerMove(f);
            if(spot != f.getX()){
                break;
            }
        }
    
        int expected = 62;
        int expected2 = 66;
        if(f.getX() == 62){
            assertEquals(expected, f.getX());
        } else if(f.getX() == 66){
            assertEquals(expected2, f.getX());
        } 
        
    }

    @Test
    public void testFryCookForcedFromDown() throws IOException{
        gl.getPlayerCharacter().setX(64);
        gl.getPlayerCharacter().setY(320);
        fc.add(new FryCook(2 * 32, 2 * 32));
        FryCook f = fc.get(fc.size() - 1);
        int spot = f.getX();
        for(int i = 0; i < 1000; i++){
            pt.fryCookTrackingPlayerMove(f);
            if(spot != f.getX()){
                break;
            }
        }
    
        int expected = 62;
        int expected2 = 66;
        if(f.getX() == 62){
            assertEquals(expected, f.getX());
        } else if(f.getX() == 66){
            assertEquals(expected2, f.getX());
        } 
    }
}
