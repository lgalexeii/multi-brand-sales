package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.widget.TextView;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunctionException;

import venturesf.alx.model.MBSClient;
import venturesf.alx.multibrandsales.R;
import venturesf.alx.vo.ClientsRequest;
import venturesf.alx.vo.ClientsResponse;

/**
 * Created by B942272 on 01/03/2018.
 */

public class MBSAsyncClientLambdaSr extends MBSAsyncClientLambda {
    private Activity activity;

    public  MBSAsyncClientLambdaSr(Activity activity ){
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onPostExecute(ClientsResponse result) {
        if (result == null) {
            return;
        }
        Log.i("Single Cliente result->",result.getStatus());
        TextView cDetail = activity.findViewById(R.id.client_detail_a) ;

        MBSClient client = result.getClients().get(0);
        cDetail.setText( client.toString());

        TextView cDetailX = activity.findViewById(R.id.client_detail_x) ;

        cDetailX.setText( client.getPhoneNumber() + ":" + client.getEmail() );

        cDetailX.animate();

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(client.getClientId() + " " + client.getName() + " " + client.getLastName() );
        }
    }
}
