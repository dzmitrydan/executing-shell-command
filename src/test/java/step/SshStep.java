package step;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import ssh.CommandOutputStorage;
import ssh.SshConnection;

public class SshStep {

    private SshConnection sshConnection;

    @When("open ssh connection")
    public void openSshConnection() {

        try {
            sshConnection = new SshConnection();
            sshConnection.openSshConnection("127.0.0.1", 10022, "roche", "roche");
            sshConnection.closeSshConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("file {word} is exists in the host system")
    public void fileIsExistsInTheHostSystem(String file) {
        Assert.assertTrue(CommandOutputStorage.hasCommandOutputString(file));
    }
}
