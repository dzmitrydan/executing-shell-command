package shell_command;

import java.io.IOException;
import java.io.OutputStream;

public class RunWinCommand {
    public static void main(String[] args) {
        try {
            // Execute command
            String command = "cmd /c start cmd.exe";
            Process child = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
        }
    }
}
