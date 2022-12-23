package robot.game.commands;

public class Help {
    
    public String doHelp(){
        return String.join("\n",
        "I can understand these commands:",
        " OFF  - Shut down robot",
        " HELP - provide information about commands");
    }
}
