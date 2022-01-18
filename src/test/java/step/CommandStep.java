package step;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import util.ShellCommand;
import util.TextReader;

public class CommandStep {

    @Given("run docker and container")
    public void runDockerAndContainer() {
        ShellCommand.executeShFile("run-docker");
    }

    @When("date change +5 days")
    public void dateChange() {
        ShellCommand.executeShFile("change-date");
    }

    @And("set up the CURRENT date")
    public void setUpTheCurrentDate() {
        ShellCommand.executeShFile("set-current-date");
    }

    @And("get CURRENT date")
    public void getCurrentDate() {
        ShellCommand.executeBatFile("commands");
    }

    @And("executed {word}.sh file")
    public void executeShFile(String file) {
        ShellCommand.executeShFile(file);
    }

    @And("log {string} wos found")
    public void findLog(String log) {
        String foundLog = TextReader.searchStringInTheTXT("sh_scripts", "search-linux-logs.txt", "Key type", "blacklist registered");
        Assertions.assertEquals(log, foundLog);
    }
}
