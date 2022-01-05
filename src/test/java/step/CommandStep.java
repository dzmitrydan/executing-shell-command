package step;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import util.ShellCommand;

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
        ShellCommand.executeBatFile("scripts");
    }
}
