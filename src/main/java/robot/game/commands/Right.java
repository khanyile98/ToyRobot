package robot.game.commands;

import robot.game.ToyRobot;

public class Right {
    String robotName;
    ToyRobot robot;

    public Right(String robotName, ToyRobot robot){
        this.robotName = robotName;
        this.robot = robot;
    }

    public String turnRight(){
        robot.updateDirection();

        return String.join("\n",
        String.format(" > %s turned .", robotName),
        String.format(" > %s now at position (%d,%d).", robotName, robot.position.getX(), robot.position.getY()));
    }
}
