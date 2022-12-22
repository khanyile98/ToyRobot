package robot.game;

public class Commands {
    String robotName;

    public Commands(String robotName){
        this.robotName = robotName;
    }


    public String offCommand(){
        return robotName + ": Shutting down..";
    }

    public String helpCommand(){
        return String.join("\n",
        "I can understand these commands:",
        " OFF  - Shut down robot",
        " HELP - provide information about commands");

    }

    public String forwardCommand(int steps){
        return String.format(" > %s moved forward by %d steps.", robotName, steps);
    }

    public String backCommand(int steps){

        return String.format(" > %s moved back by %d steps.", robotName, steps);
    }

}
