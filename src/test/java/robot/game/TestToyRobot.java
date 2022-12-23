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

    @Test
    public void testForward(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                " > Ben moved forward by 30 steps.",
                " > Ben now at position (0,30).");

        assertEquals(expected, robot.handleCommands("Ben", "forward 30"));
    }

    @Test
    public void testForwardOutOfBounds(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                "Ben: Sorry, I cannot go outside my safe zone.",
                " > Ben now at position (0,0).");

        assertEquals(expected, robot.handleCommands("Ben", "forward 205"));
    }

    @Test
    public void testBack(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                " > Ben moved back by 30 steps.",
                " > Ben now at position (0,-30).");

        assertEquals(expected, robot.handleCommands("Ben", "back 30"));
    }

    @Test
    public void testBackOutOfBounds(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                "Ben: Sorry, I cannot go outside my safe zone.",
                " > Ben now at position (0,0).");

        assertEquals(expected, robot.handleCommands("Ben", "back -205"));
    }

    @Test
    public void testSprint(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                " > Ben moved forward by 5 steps.",
                " > Ben moved forward by 4 steps.",
                " > Ben moved forward by 3 steps.",
                " > Ben moved forward by 2 steps.",
                " > Ben moved forward by 1 steps.",
                " > Ben now at position (0,15).");

        assertEquals(expected, robot.handleCommands("Ben", "sprint 5"));
    }

    @Test
    public void testSprintOutOfBounds(){
        robot = new ToyRobot();
        String expected = String.join("\n",
                " > Ben moved forward by 50 steps.",
                " > Ben moved forward by 49 steps.",
                " > Ben moved forward by 48 steps.",
                " > Ben moved forward by 47 steps.",
                "Ben: Sorry, I cannot go outside my safe zone.",
                " > Ben now at position (0,194).");

        assertEquals(expected, robot.handleCommands("Ben", "sprint 50"));
    }
}
