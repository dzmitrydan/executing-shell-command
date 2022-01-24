package ssh;

import lombok.Data;

@Data
public class Tunnel {

    private String tunnelHost;
    private int tunnelPort;
    private int localPort;

    public Tunnel(String tunnelHost, int tunnelPort, int localPort) {
        this.tunnelHost = tunnelHost;
        this.tunnelPort = tunnelPort;
        this.localPort = localPort;
    }
}
