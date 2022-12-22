package robot.game;

import java.util.*;

public class ToyRobot {
    public final Scanner scan;
    public String[] validCommands = {"off", "help", "forward", "back", "right", "left", "sprint"};
    Position position;
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
        String commandinput = scan.nextLine();

        while (!validateCommand(commandinput.toLowerCase())){
            System.out.println(robotName + ": Sorry, I did not understand " + "'" + commandinput + "'");
            System.out.print(robotName + ": What must I do next? ");

            commandinput = scan.nextLine();
        }
        return commandinput.toLowerCase();
    }

    public boolean validateCommand(String command){
        LinkedList<String> validCommands = new LinkedList<String>(Arrays.asList(this.validCommands));
        String[] arr = splitCommand(command);

        if (validCommands.contains(arr[0])){
            return true;
        }
        return false;
    }

    public String[] splitCommand(String command){
        String[] arr = command.split(" ");

        if (arr.length == 2){
            return arr;
        }else{
            String arr2[] = {arr[0], ""};
            return arr2;
        }
    }

    
    public String handleCommands(String robotname, String command){
        Commands commands = new Commands(robotname);
        String[] commandArr = splitCommand(command);

        command = commandArr[0];
        String steps = commandArr[1];

        if (command.equals("off")){
            return commands.offCommand();
        }
        else if (command.equals("help")){
            return commands.helpCommand();
        }
        else if (command.equals("forward")){
            int value = Integer.valueOf(steps);
            if (!setPosition(value)){
                return String.join("\n",
                String.format("%s: Sorry, I cannot go outside my safe zone.", robotname),
                String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
            }
            
            return String.join("\n", commands.forwardCommand(value),
            String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
        }
        else if (command.equals("back")){
            int value = Integer.valueOf(steps);

            if (!setPosition(-value)){
                return String.join("\n",
                String.format("%s: Sorry, I cannot go outside my safe zone.", robotname),
                String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
            }

            return String.join("\n", commands.backCommand(value),
            String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
        }
        else if (command.equals("right")){
            directionIndex += 1;
            updateDirection();

            return String.join("\n", commands.rightCommand(),
            String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
        }

        else if (command.equals("left")){
            directionIndex -= 1;
            updateDirection();

            return String.join("\n", commands.leftCommand(),
            String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
        }
        else if (command.equals("sprint")){
            StringBuilder builder = new StringBuilder();

            for (int i = 1; i <= Integer.valueOf(steps); i++){
                if (!setPosition(i)){
                    builder.append(String.join("\n",
                    String.format("%s: Sorry, I cannot go outside my safe zone.", robotname),
                    String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY())));

                    return String.valueOf(builder);
                }

                builder.append(commands.forwardCommand(i) + "\n");
            }
            builder.append(String.format(" > %s now at position (%d,%d).", robotname, position.getX(), position.getY()));
            return String.valueOf(builder);
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
