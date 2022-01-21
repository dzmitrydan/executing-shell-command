package ssh;

public class TestSshConnection {

    private static String file = "install.sh";

    public static void main(String[] args) throws Exception {

        String host = "127.0.0.1";
        int port = 10022;
        String username = "string";
        String password = "string";

        SshConnection sshConnection = new SshConnection();
        sshConnection.openSshConnection(host, port, username, password);
        sshConnection.closeSshConnection();

        System.out.println("File '" + file + "' EXISTS - " + CommandOutputStorage.hasCommandOutputString(file));
    }
}
