package robot.game;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestToyRobot {
    private ToyRobot robot;

    @Test
    public void testValidCommandsTrue(){
        robot = new ToyRobot();
        assertTrue("Should be true.", robot.validateCommand("forward"));
        assertTrue("Should be true.", robot.validateCommand("back"));
        assertTrue("Should be true.", robot.validateCommand("left"));
        assertTrue("Should be true.", robot.validateCommand("right"));
        
    }

    @Test
    public void testInvalidCommands(){
        robot = new ToyRobot();
        assertFalse(robot.validateCommand("run"));
    }

    @Test
    public void testSetPositionTrue(){
        robot = new ToyRobot();
        boolean boolTrue = robot.setPosition(30);

        assertTrue(null, boolTrue);
    }

    @Test
    public void testSetPositionFalse(){
        robot = new ToyRobot();
        boolean boolTrue = robot.setPosition(201);

        assertFalse(null, boolTrue);
    }
}
