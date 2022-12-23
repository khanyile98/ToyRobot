package robot.game.commands;

import robot.game.ToyRobot;
import java.util.Collections;

public class Replay {
    ToyRobot robot;
    String robotName;
    String value;

    public Replay(ToyRobot robot, String robotName, String value){
        this.robot = robot;
        this.robotName = robotName;
        this.value = value;
    }
    public String handleReplay(){
        StringBuilder builder = new StringBuilder();
        robot.isReplay = true;

        switch (value){
            case "":
                for (String command: robot.history){
                    builder.append(robot.handleCommands(robotName, command)).append("\n");
                }
                robot.isReplay = false;

                builder.append(String.join("\n",
                        " > " + robotName + " replayed " + robot.history.size() + " commands.",
                        String.format(" > %s now at position (%d,%d).", robotName,
                                robot.position.getX(), robot.position.getY())));

                return String.valueOf(builder);

            case "silent":
                for (String command: robot.history){
                    robot.handleCommands(robotName, command);
                }
                robot.isReplay = false;

                builder.append(String.join("\n",
                        robotName + ": replayed " + robot.history.size() + " commands silently.",
                        String.format(" > %s now at position (%d,%d).", robotName,
                                robot.position.getX(), robot.position.getY())));

                return String.valueOf(builder);

            case "reversed":
                Collections.reverse(robot.history);
                for (String command: robot.history){
                    builder.append(robot.handleCommands(robotName, command)).append("\n");
                }
                robot.isReplay = false;

                builder.append(String.join("\n",
                        robotName + ": replayed " + robot.history.size() + " commands in reverse.",
                        String.format(" > %s now at position (%d,%d).", robotName,
                                robot.position.getX(), robot.position.getY())));

                return String.valueOf(builder);

            case "reversed silent":
                Collections.reverse(robot.history);
                for (String command: robot.history){
                    robot.handleCommands(robotName, command);
                }
                robot.isReplay = false;

                builder.append(String.join("\n",
                        robotName + " replayed " + robot.history.size() + " commands in reverse silently.",
                        String.format(" > %s now at position (%d,%d).", robotName,
                                robot.position.getX(), robot.position.getY())));

                return String.valueOf(builder);
        }
        return "";
    }
}
