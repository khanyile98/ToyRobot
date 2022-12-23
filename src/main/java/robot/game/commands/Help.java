package robot.game.commands;

public class Help {
    
    public String doHelp(){
        return String.join("\n",
        "I can understand these commands:",
        " OFF  - Shut down robot",
        " HELP - provide information about commands",
        " FORWARD - moves the robot forward",
        " BACK - moves the robot backwards",
        " RIGHT - turns the robot 90 degrees to the right",
        " LEFT  - turns the robot 90 degrees to the left",
        " SPRINT - moves the robot forward n! steps");
    }
}
