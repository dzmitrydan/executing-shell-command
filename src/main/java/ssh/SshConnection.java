package ssh;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import lombok.extern.slf4j.Slf4j;
import util.TextReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class SshConnection {

    private Session session;
    private ChannelExec channel;

    public void openSshConnection(String host, int port, String username, String password) throws Exception {

        session = new JSch().getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();

        channel = (ChannelExec) session.openChannel("exec");
        channel.setCommand(TextReader.getCommandsFromTXT("shh_commands", "shh-commands.txt"));
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        ByteArrayOutputStream errorResponseStream = new ByteArrayOutputStream();
        channel.setOutputStream(responseStream);
        channel.setErrStream(errorResponseStream);

        InputStream inputStream = channel.getInputStream();
        InputStream errorInputStream = channel.getExtInputStream();

        channel.connect();

        log.info("ssh connection: " + channel.getSession().getHost() + ":"
                + channel.getSession().getPort()
                + "; server version: " + channel.getSession().getServerVersion()
        );
        readCommandOutput(inputStream, errorInputStream, responseStream, errorResponseStream, channel);

        String errorResponse = errorResponseStream.toString();

        if (!errorResponse.isEmpty()) {
            throw new Exception(errorResponse);
        }
    }

    public void closeSshConnection() {
        if (session != null) {
            session.disconnect();
            log.info("ssh session disconnect");
        }
        if (channel != null) {
            channel.disconnect();
            log.info("channel disconnect");
        }
    }

    private void readCommandOutput(InputStream inputStream, InputStream errorInputStream, ByteArrayOutputStream responseStream, ByteArrayOutputStream errorResponseStream, ChannelExec channel) throws IOException {
        byte[] tmp = new byte[1024];
        while (true) {
            while (inputStream.available() > 0) {
                int i = inputStream.read(tmp, 0, 1024);
                if (i < 0) break;
                responseStream.write(tmp, 0, i);
            }
            while (errorInputStream.available() > 0) {
                int i = errorInputStream.read(tmp, 0, 1024);
                if (i < 0) break;
                errorResponseStream.write(tmp, 0, i);
            }
            if (channel.isClosed()) {
                if ((inputStream.available() > 0) || (errorInputStream.available() > 0)) continue;
                break;
            }
        }

        String commandOutput = responseStream.toString(StandardCharsets.UTF_8);
        log.info("CommandOutput:\n" + commandOutput);
        CommandOutputStorage.setCommandOutput(commandOutput);
    }
}
