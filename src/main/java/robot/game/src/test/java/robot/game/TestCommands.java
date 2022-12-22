package robot.game;

import static org.junit.Assert.*;
import org.junit.Test;

public class TestCommands {
    private Commands commands = new Commands("Ben");

    @Test
    public void testHelpCommand(){
        String expected = String.join("\n",
        "I can understand these commands:",
        " OFF  - Shut down robot",
        " HELP - provide information about commands");

        assertTrue(expected.equals(commands.helpCommand()));
    }

    @Test
    public void testForwardCommand(){
        String expected = " > Ben moved forward by 30 steps.";
        assertTrue(expected.equals(commands.forwardCommand(30)));
    }

    @Test
    public void testBackCommand(){
        String expected = " > Ben moved back by 30 steps.";
        assertTrue(expected.equals(commands.backCommand(30)));
    }

    @Test
    public void testRight(){
        String expected = " > Ben turned right.";
        assertTrue(expected.equals(commands.rightCommand()));
    }

    @Test
    public void testLeft(){
        String expected = " > Ben turned left.";
        assertTrue(expected.equals(commands.leftCommand()));
    }
    
}
