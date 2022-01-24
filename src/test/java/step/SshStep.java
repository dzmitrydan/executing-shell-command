package step;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import ssh.CommandOutputStorage;
import ssh.SshConnection;
import ssh.Tunnel;
import java.util.ArrayList;
import java.util.List;

public class SshStep {

    String localHost = "127.0.0.1";

    String bastionHost = "10.145.129.52";
    int bastionPort = 22;
    String bastionUsername = "string1";
    String bastionPassword = "string2";

    String tunnelHostInstr = "162.132.242.130";
    int tunnelPortInstr = 22;
    int localPortInstr = 10022;
    String tunnelUsernameInstr = "string3";
    String tunnelPasswordInstr = "string3";

    String tunnelHostRabbitMQUI = "162.132.242.130";
    int tunnelPortRabbitMQUI = 15672;
    int localPortRabbitMQUI = 10172;


    @When("open ssh connection")
    public void openSshConnection() throws Exception {

        Session sessionBastion = new JSch().getSession(bastionUsername, bastionHost, bastionPort);

        List<Tunnel> tunnels = new ArrayList<>();
        Tunnel rabbitMQUITunnel = new Tunnel(tunnelHostRabbitMQUI, tunnelPortRabbitMQUI, localPortRabbitMQUI);
        tunnels.add(rabbitMQUITunnel);
        Tunnel instrumentTunnel = new Tunnel(tunnelHostInstr, tunnelPortInstr, localPortInstr);
        tunnels.add(instrumentTunnel);

        SshConnection sshBastion = new SshConnection()
                .setShhPassword(sessionBastion, bastionPassword)
                .addShhTunnels(tunnels)
                .setShhConfig()
                .openSshConnection()
                .executeCommandsFromFile("shh-commands.txt");

        Session sessionInstrument = new JSch().getSession(tunnelUsernameInstr, localHost, localPortInstr);
        new SshConnection()
                .setShhPassword(sessionInstrument, tunnelPasswordInstr)
                .setShhConfig()
                .openSshConnection()
                .executeCommandsFromFile("shh-commands.txt")
                .closeSshConnection();

        sshBastion.closeSshConnection();
    }

    @Then("file {word} is exists in the host system")
    public void fileIsExistsInTheHostSystem(String file) {
        Assert.assertTrue(CommandOutputStorage.hasCommandOutputString(file));
    }
}
