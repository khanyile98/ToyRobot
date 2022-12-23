package robot.game.commands;
import robot.game.ToyRobot;

public class Left {
    String robotName;
    ToyRobot robot;

    public Left(String robotName, ToyRobot robot){
        this.robotName = robotName;
        this.robot = robot;
    }

    public String turnLeft(){
        robot.updateDirection();
        if (!robot.isReplay){robot.history.add("left");}

        return String.join("\n",
        String.format(" > %s turned left.", robotName),
        String.format(" > %s now at position (%d,%d).", robotName, robot.position.getX(), robot.position.getY()));
    }
}
