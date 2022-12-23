package robot.game.commands;

import robot.game.ToyRobot;

public class Back {
    private final ToyRobot robot;
    private final String robotName;
    private final int steps;

    public Back(String robotName, int steps, ToyRobot robot){
        this.robot = robot;
        this.robotName = robotName;
        this.steps = steps;
    }

    public String doBack(){
        StringBuilder builder = new StringBuilder();

        if (!robot.setPosition(-steps)){
            builder.append(String.format("%s: Sorry, I cannot go outside my safe zone.\n", robotName));
        }else{
            if (!robot.isReplay){robot.history.add("back " + steps);}
            builder.append(String.format(" > %s moved back by %d steps.\n", robotName, steps));
        }

        builder.append(String.format(" > %s now at position (%d,%d).", robotName,
        robot.position.getX(), robot.position.getY()));

        return String.valueOf(builder);
    }


}
