package robot.game.commands;

import robot.game.ToyRobot;

public class Forward {
    private final ToyRobot robot;
    private final String robotName;
    private final int steps;

    public Forward(String robotName, int steps, ToyRobot robot){
        this.robot = robot;
        this.robotName = robotName;
        this.steps = steps;
    }

    public String doForward(){
        StringBuilder builder = new StringBuilder();

        if (!robot.setPosition(steps)){
            builder.append(String.format("%s: Sorry, I cannot go outside my safe zone.\n", robotName));
        }else{
            builder.append(String.format(" > %s moved forward by %d steps.\n", robotName, steps));
        }

        builder.append(String.format(" > %s now at position (%d,%d).", robotName,
        robot.position.getX(), robot.position.getY()));

        return String.valueOf(builder);
    }
}
