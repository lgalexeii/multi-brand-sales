package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;

import venturesf.alx.getclientsaws.model.MBSClient;
import venturesf.alx.getclientsaws.vo.ClientsRequest;
import venturesf.alx.getclientsaws.vo.ClientsResponse;

/**
 * Created by B942272 on 01/03/2018.
 */

public class MBSAsyncClientLambda extends AsyncTask<ClientsRequest, Void, ClientsResponse> {
    private MBSClientsLambda mbsClientsLambda;
    private Activity activity;
    private ArrayAdapter adapter;

    public MBSAsyncClientLambda(MBSClientsLambda mbsClientsLambda, Activity activity, ArrayAdapter adapter) {
        this.mbsClientsLambda = mbsClientsLambda;
        this.activity = activity;
        this.adapter = adapter;
    }

    @Override
    protected ClientsResponse doInBackground(ClientsRequest ... objects) {
        // invoke "echo" method. In case it fails, it will throw a
        // LambdaFunctionException.
        try {
            return mbsClientsLambda.mbsClients(objects[0]);
        } catch (LambdaFunctionException lfe) {
            Log.e("Tag", "Failed to invoke echo", lfe);
            return null;
        }
    }

    @Override
    protected void onPostExecute(ClientsResponse result) {
        if (result == null) {
            return;
        }
        for(MBSClient client : result.getClients() )
            this.adapter.add(client.getClientId() + " - " + client.getName() + " " +client.getLastName() +
            " " + client.getSecondLastName() + " - " + client.getStatus());
    }
}
