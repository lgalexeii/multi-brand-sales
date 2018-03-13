package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.vo.ClientsResponse;

/**
 * Created by B942272 on 01/03/2018.
 */

public abstract class MBSAsyncClientLambda extends AsyncTask<ClientsRequest, Void, ClientsResponse> {
    protected MBSClientsLambda mbsClientsLambda;

    public MBSAsyncClientLambda(Activity activity) {

        // Create an instance of CognitoCachingCredentialsProvider
        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
               activity. getApplicationContext(), "us-east-1:f14dec35-2fe4-49b7-aae7-a01f99e14097", Regions.US_EAST_1);

        // Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
        LambdaInvokerFactory factory = new LambdaInvokerFactory(activity.getApplicationContext(),
                Regions.US_EAST_1, cognitoProvider);

        // Create the Lambda proxy object with a default Json data binder.
        // You can provide your own data binder by implementing
        // LambdaDataBinder.
        this.mbsClientsLambda = factory.build(MBSClientsLambda.class);
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
}
