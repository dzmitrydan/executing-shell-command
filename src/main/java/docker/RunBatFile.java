package docker;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunBatFile {

    private static String fileName = "docker-data";
    //private static String fileName = "container-data";

    public static void main(String[] args) {
        try {
            // File location for the bat script
            File dir = new File("bat_scripts");

            // Command to run the bat file in the same console
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", fileName + ".bat");
            pb.directory(dir);
            Process process = pb.start();

            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("**************************** The Output is ******************************");
                System.out.println(output);
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}