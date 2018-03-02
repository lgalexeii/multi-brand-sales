package venturesf.alx.multibrandsales;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.lambdainvoker.LambdaInvokerFactory;
import com.amazonaws.regions.Regions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import venturesf.alx.getclientsaws.vo.ClientsRequest;
import venturesf.alx.multibrandsales.aws.MBSAsyncClientLambda;
import venturesf.alx.multibrandsales.aws.MBSClientsLambda;

/**
 * A placeholder fragment containing a simple view.
 */
public class ClientsListFragment extends Fragment {

    public ClientsListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_clients_list, container, false);

        // Create an instance of CognitoCachingCredentialsProvider
        CognitoCachingCredentialsProvider cognitoProvider = new CognitoCachingCredentialsProvider(
                getActivity().getApplicationContext(), "us-east-1:f14dec35-2fe4-49b7-aae7-a01f99e14097", Regions.US_EAST_1);

        // Create LambdaInvokerFactory, to be used to instantiate the Lambda proxy.
        LambdaInvokerFactory factory = new LambdaInvokerFactory(getActivity().getApplicationContext(),
                Regions.US_EAST_1, cognitoProvider);

        // Create the Lambda proxy object with a default Json data binder.
        // You can provide your own data binder by implementing
        // LambdaDataBinder.
        final MBSClientsLambda iClientsLambda = factory.build(MBSClientsLambda.class);

        ClientsRequest request = new ClientsRequest();
        request.setClientId("1001");

        ArrayAdapter clientsAdapter =
                new ArrayAdapter(getActivity(), R.layout.list_item_client, R.id.list_item_client_textview, new ArrayList<String>());

        ListView clientsListView =rootView.findViewById(R.id.list_view_client);

        clientsListView.setAdapter(clientsAdapter);

        clientsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        (new MBSAsyncClientLambda(iClientsLambda,getActivity(), clientsAdapter) ).execute(request);

        return rootView;
    }
}
