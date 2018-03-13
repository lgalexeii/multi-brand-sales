package venturesf.alx.getclientsaws.model;

import java.util.Date;
//import android.location.Location;

/**
 * Created by B942272 on 01/03/2018.
 */

public class MBSClient{
    private int clientId;
    private String name;
    private String lastName;
    private String secondLastName;
    private Date birthDate;
    private String status;
    private String phoneNumber;
    private String email;
    private String location;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MBSClient mbsClient = (MBSClient) o;

        return clientId == mbsClient.clientId;
    }

    @Override
    public int hashCode() {
        return clientId;
    }

    @Override
    public String toString() {
        return "MBSClient{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
