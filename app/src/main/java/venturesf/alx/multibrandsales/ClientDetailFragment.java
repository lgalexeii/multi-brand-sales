package venturesf.alx.multibrandsales;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import venturesf.alx.multibrandsales.aws.MBSAsyncClientImageLambda;
import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.multibrandsales.aws.MBSAsyncClientLambdaSr;

/**
 * A fragment representing a single Client detail screen.
 * This fragment is either contained in a {@link ClientListActivity}
 * in two-pane mode (on tablets) or a {@link ClientDetailActivity}
 * on handsets.
 */
public class ClientDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "client";
    public static final String ARG_TWO_PANE = "mTwoPane";


    /**
     * The dummy content this fragment is presenting.
     */
    private String mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ClientDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = getArguments().getString(ARG_ITEM_ID);

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem );
            }
        }

        loadImageParallax(R.drawable.usersr );// Cargar Imagen

        //Load client information
        ClientsRequest request = new ClientsRequest();
        request.setClientId(mItem);
        (new MBSAsyncClientLambdaSr(this.getActivity() )).execute(request);

        (new MBSAsyncClientImageLambda(this.getActivity() )).execute(request);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.client_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.client_detail_a)).setText(mItem);
        }

        return rootView;
    }



    private void showSnackBar(String msg) {
        Snackbar
                .make( getView(), msg, Snackbar.LENGTH_LONG)
                .show();
    }

    /**
     * Se carga una imagen aleatoria para el detalle
     */
    private void loadImageParallax(int id) {
        ImageView image = (ImageView) getActivity().findViewById(R.id.client_image);
        // Usando Glide para la carga asíncrona
        if(image != null){
            Glide.with(this)
                    .load(id)
                    .centerCrop()
                    .into(image);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);
        inflater.inflate(R.menu.detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                showSnackBar("Se abren los ajustes");
                return true;
            case R.id.action_add:
                showSnackBar("Añadir a contactos");
                return true;
            case R.id.action_favorite:
                showSnackBar("Añadir a favoritos");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
