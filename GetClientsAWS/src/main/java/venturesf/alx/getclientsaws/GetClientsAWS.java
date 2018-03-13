package venturesf.alx.getclientsaws;

import venturesf.alx.getclientsaws.model.MBSClient;
import venturesf.alx.getclientsaws.vo.ClientsRequest;
import venturesf.alx.getclientsaws.vo.ClientsResponse;
import com.amazonaws.services.lambda.runtime.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GetClientsAWS {
    public static ClientsResponse myHandler(ClientsRequest request, Context context){
        String requestMsg = String.format("Params in this request: %s, %s.", request.getClientId(), request.getStatus());
        context.getLogger().log(requestMsg);

        ClientsResponse response = new ClientsResponse();
        response.setClients(new ArrayList<MBSClient>());
        MBSClient client = new MBSClient();
        client.setClientId(1001);
        client.setBirthDate(GregorianCalendar.getInstance().getTime());
        client.setName("Gabriela");
        client.setLastName("Diaz");
        client.setSecondLastName("Sanchez");
        client.setPhoneNumber("2487778897");
        client.setStatus("Delivering pending");
        response.getClients().add(client);

        client = new MBSClient();
        client.setClientId(1002);
        client.setBirthDate(GregorianCalendar.getInstance().getTime());
        client.setName("Vick");
        client.setLastName("Lazaro");
        client.setSecondLastName("G");
        client.setPhoneNumber("5568180826");
        client.setStatus("Normal");
        response.getClients().add(client);

        client = new MBSClient();
        client.setClientId(1003);
        client.setBirthDate(GregorianCalendar.getInstance().getTime());
        client.setName("Vick");
        client.setLastName("Lazaro");
        client.setSecondLastName("Gonzalez");
        client.setPhoneNumber("5568180826");
        client.setStatus("Normal");
        response.getClients().add(client);

        client = new MBSClient();
        client.setClientId(1004);
        client.setBirthDate(GregorianCalendar.getInstance().getTime());
        client.setName("Vick");
        client.setLastName("Lazaro");
        client.setSecondLastName("G");
        client.setPhoneNumber("5568180826");
        client.setStatus("Le debo");
        response.getClients().add(client);

        client = new MBSClient();
        client.setClientId(1005);
        client.setBirthDate(GregorianCalendar.getInstance().getTime());
        client.setName("Vick");
        client.setLastName("Lazaro");
        client.setSecondLastName("G");
        client.setPhoneNumber("5568180826");
        client.setStatus("En la lista negra");
        response.getClients().add(client);


        response.setStatus("success");

        if(request.getClientId() != null)
        {
            final MBSClient client2search = new MBSClient();
            client2search.setClientId(Integer.parseInt(request.getClientId()));
            context.getLogger().log(client2search.toString());
            int index = response.getClients().indexOf(client2search) ;
            if(index < 0){
               response.setStatus("Client not found");
                response.getClients().clear();
               return response;
            }
            MBSClient clientF = response.getClients().get(index );

            clientF.setLocation("Any place...");
            response.getClients().clear();
            response.getClients().add(clientF);

        }

        return response;
    }
}
