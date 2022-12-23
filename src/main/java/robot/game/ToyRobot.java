package robot.game;

import java.util.*;
import robot.game.commands.*;


public class ToyRobot {
    public final Scanner scan;
    public String[] validCommands = {"off", "help", "forward", "back", "right", "left", "sprint"};
    public Position position;
    public int positionX, positionY;
    Directions currentDirection = Directions.NORTH;
    int directionIndex;

    public ToyRobot(){
        position = new Position(0, 0);
        scan = new Scanner(System.in);
        directionIndex = 0;
    }

    public String getRobotName(){
        System.out.print("What do you want to name your robot? ");
        String robotName = scan.nextLine();

        System.out.println(robotName + ": Hello kiddo!");
        return robotName;
    }

    public String getCommandInput(String robotName){
        System.out.print(robotName + ": What must I do next? ");
        String commandInput = scan.nextLine();

        while (!validateCommand(commandInput.toLowerCase())){
            System.out.println(robotName + ": Sorry, I did not understand " + "'" + commandInput + "'");
            System.out.print(robotName + ": What must I do next? ");

            commandInput = scan.nextLine();
        }
        return commandInput.toLowerCase();
    }

    public boolean validateCommand(String command){
        LinkedList<String> validCommands = new LinkedList<String>(Arrays.asList(this.validCommands));
        String[] arr = splitCommand(command);

        return validCommands.contains(arr[0]);
    }

    public String[] splitCommand(String command){
        String[] arr = command.split(" ");

        if (arr.length == 2){
            return arr;
        }else{
            return new String[]{arr[0], ""};
        }
    }

    
    public String handleCommands(String robotname, String command){
        String[] commandArr = splitCommand(command);

        command = commandArr[0];
        String steps = commandArr[1];

        switch (command) {
            case "off":
                return robotname + ": Shutting down..";
            case "help":
                Help help = new Help();

                return help.doHelp();
            case "forward": {
                int value = Integer.parseInt(steps);
                Forward forward = new Forward(robotname, value, this);

                return forward.doForward();
            }
            case "back": {
                int value = Integer.parseInt(steps);
                Back back = new Back(robotname, value, this);

                return back.doBack();
            }
            case "right":
                directionIndex += 1;
                Right right = new Right(robotname, this);

                return right.turnRight();
            case "left":
                directionIndex -= 1;
                Left left = new Left(robotname, this);

                return left.turnLeft();
            case "sprint": {
                int value = Integer.parseInt(steps);
                Sprint sprint = new Sprint(robotname, value, this);

                return sprint.doSprint();
            }
        }
        return "";
    }


    public void updateDirection(){
        if (directionIndex == 4){directionIndex = 0;}
        if (directionIndex == -1){directionIndex = 3;}


        if (directionIndex == 0){
            currentDirection = Directions.NORTH;
        }
        else if (directionIndex == 1){
            currentDirection = Directions.EAST;
        }
        else if (directionIndex == 2){
            currentDirection = Directions.SOUTH;
        }
        else{
            currentDirection = Directions.WEST;
        }
    }

    public boolean setPosition(int steps){
        this.positionX = position.getX();
        this.positionY = position.getY();
        int newX = positionX;
        int newY = positionY;

        if (currentDirection.equals(Directions.NORTH)){
            newY += steps;
        }else if (currentDirection.equals(Directions.EAST)){
            newX += steps;
        }else if (currentDirection.equals(Directions.SOUTH)){
            newY -= steps;
        }else if (currentDirection.equals(Directions.WEST)){
            newX -= steps;
        }
        position = new Position(newX, newY);

        if (-100 <= position.getX() && position.getX() <= 100 && position.getY() >= -200 && position.getY() <= 200){
            return true;
        }
        position = new Position(positionX, positionY);
        return false;
    }


    public void robotStart(){
        String robotName = getRobotName();

        while (true){
            String commandInput = getCommandInput(robotName);
            String response = handleCommands(robotName, commandInput);
            System.out.println(response);

            if (commandInput.equals("off")){
                return;
            }
        }
    }


    public static void main(String[] args) {
        ToyRobot toyRobot = new ToyRobot();
        toyRobot.robotStart();
    }

}
