package Skypro.work;

public class IncorrectArgumentException extends Exception {
    private String argument;

    public IncorrectArgumentException(String argument) {
        super(argument);
        this.argument = argument;
    }

    @Override

    public String toString() {
        return "error " + this.argument;
    }

    public String getArgument() {
        return argument;
    }
}
