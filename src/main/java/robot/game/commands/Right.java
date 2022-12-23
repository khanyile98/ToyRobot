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
        if (!robot.isReplay){robot.history.add("right");}

        return String.join("\n",
        String.format(" > %s turned right.", robotName),
        String.format(" > %s now at position (%d,%d).", robotName, robot.position.getX(), robot.position.getY()));
    }
}
