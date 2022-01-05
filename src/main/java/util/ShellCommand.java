package util;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class ShellCommand {

    public static void executeShFile(String fileName) {

        try {
            File directory = new File("sh_scripts");
            ProcessBuilder processBuilder = new ProcessBuilder("/bin/bash", fileName + ".sh");
            processBuilder.directory(directory);
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                ReportingUtils.info(String.valueOf(output));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeBatFile(String fileName) {

        try {
            // File location for the bat script
            File dir = new File("scripts");

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
                ReportingUtils.info(String.valueOf(output));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
