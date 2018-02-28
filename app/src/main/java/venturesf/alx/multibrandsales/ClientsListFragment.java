package venturesf.alx.multibrandsales;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        String[] clientArray ={
                "1001  - Gaby D - Por entregar",
                "1009  - Naty - Me debe $53.85",
                "1099  - Gaby D - Normal",
                "1087  - Gaby D - Por pedir",
                "1002  - Gaby D - Normal"};

        List<String> clientList = new ArrayList<>(Arrays.asList(clientArray));

        ArrayAdapter clientsAdapter =
                new ArrayAdapter(getActivity(), R.layout.list_item_client, R.id.list_item_client_textview, clientList);

        ListView clientsListView =rootView.findViewById(R.id.list_view_client); //this.getActivity().findViewById(R.id.list_view_client);



        clientsListView.setAdapter(clientsAdapter);

        return rootView;
    }
}
