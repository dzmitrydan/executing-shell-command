package docker;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunLinuxCommand {

    public static void main(String args[]) {

        executeLinuxCommand("timedatectl");
        executeLinuxCommand("ls -aF");
    }

    private static void executeLinuxCommand(String command) {
        try {
            String string;

            Process process = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while ((string = br.readLine()) != null) {
                System.out.println("line: " + string);
            }
            process.waitFor();
            System.out.println ("exit: " + process.exitValue());
            process.destroy();
        } catch (Exception e) {

        }
    }

}
