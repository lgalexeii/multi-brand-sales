package venturesf.alx.getclientsaws.vo;

import java.util.List;

import venturesf.alx.getclientsaws.model.MBSClient;

/**
 * Created by B942272 on 01/03/2018.
 */

public class ClientsResponse {
    private String status;
    private List<MBSClient> clients;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MBSClient> getClients() {
        return clients;
    }

    public void setClients(List<MBSClient> clients) {
        this.clients = clients;
    }
}
