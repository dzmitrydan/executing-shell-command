package ssh;

import java.util.Arrays;
import java.util.List;


public class CommandOutputStorage {

    private static List<String> listCommandOutput;

    public static List<String> getCommandOutputList() {
        return listCommandOutput;
    }

    public static void setCommandOutput(String commandOutput) {
        listCommandOutput = Arrays.asList(commandOutput.split("\n"));
    }

    public static boolean hasCommandOutputString(String expectedString) {
        boolean result = false;
        for (String actualString : listCommandOutput) {
            if (actualString.equals(expectedString)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
