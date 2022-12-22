package robot.game;


import java.util.*;

public class ToyRobot {
    public Scanner scan = new Scanner(System.in);
    public String[] validCommands = {"off", "help", "forward", "back"};
    public Position position = new Position(0, 0);
    public int positionX, positionY;

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
            position = new Position(positionX, positionY);

            return commands.forwardCommand(value);
        }
        else if (command.equals("back")){
            int value = Integer.valueOf(steps);
            position = new Position(positionX, positionY);

            return commands.backCommand(value);
        }
        else{
            return "";
        }
    }

    public void setPosition(){
        this.positionX = position.getX();
        this.positionY = position.getY();
    }


    public void robotStart(){
        String robotName = getRobotName();
        setPosition();

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
