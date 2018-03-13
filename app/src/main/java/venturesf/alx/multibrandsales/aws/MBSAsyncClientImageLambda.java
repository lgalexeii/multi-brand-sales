package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import venturesf.alx.multibrandsales.R;
import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.vo.ClientsResponse;

/**
 * Created by B942272 on 13/03/2018.
 */

public class MBSAsyncClientImageLambda extends MBSAsyncClientLambda {
    private Activity activity;

    public  MBSAsyncClientImageLambda(Activity activity ){
        super(activity);
        this.activity = activity;
    }

    @Override
    protected ClientsResponse doInBackground(ClientsRequest... objects) {
        return this.mbsClientsLambda.mbsClientImage(objects[0]);
    }

    @Override
    protected void onPostExecute(ClientsResponse clientsResponse) {
        if (clientsResponse == null) {
            return;
        }

        Log.i("Image Base 64: ",clientsResponse.getClients().get(0).getPhotoB64());

        ImageView image = (ImageView) activity.findViewById(R.id.client_image);
        // Usando Glide para la carga asíncrona

        Glide.with(activity)
                .load( Base64.decode(clientsResponse.getClients().get(0).getPhotoB64(),Base64.DEFAULT )   )
                .centerCrop()
                .into(image);

    }
}
