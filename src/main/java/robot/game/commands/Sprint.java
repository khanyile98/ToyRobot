package robot.game.commands;

import robot.game.ToyRobot;

public class Sprint {
    ToyRobot robot;
    int steps;
    String robotName;

    public Sprint(String robotName, int steps, ToyRobot robot){
        this.robotName = robotName;
        this.steps = steps;
        this.robot = robot;
    }

    public String doSprint(){
        StringBuilder builder = new StringBuilder();

            for (int i = steps; i >= 1; i--){
                if (!robot.setPosition(i)){
                    builder.append(String.join("\n",
                    String.format("%s: Sorry, I cannot go outside my safe zone.", robotName),
                    String.format(" > %s now at position (%d,%d).", robotName, robot.position.getX(),
                    robot.position.getY())));

                    return String.valueOf(builder);
                }
                if (i == steps){
                    if (!robot.isReplay){robot.history.add("back " + steps);}
                }

                builder.append(String.format(" > %s moved forward by %d steps.\n", robotName, i));
            }
            builder.append(String.format(" > %s now at position (%d,%d).", robotName, robot.position.getX(),
            robot.position.getY()));
            return String.valueOf(builder);
    }
}
