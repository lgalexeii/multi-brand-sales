package venturesf.alx.getclientsaws.vo;

/**
 * Created by B942272 on 01/03/2018.
 */

public class ClientsRequest {
    private String clientId;
    private String location;
    private String status;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
