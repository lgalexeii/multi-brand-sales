package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import venturesf.alx.vo.ClientsResponse;
import venturesf.alx.multibrandsales.ClientListActivity;

/**
 * Created by B942272 on 01/03/2018.
 */

public class MBSAsyncClientLambdaLs extends MBSAsyncClientLambda {
    private ClientListActivity.SimpleItemRecyclerViewAdapter adapter;

    public MBSAsyncClientLambdaLs(Activity activity, ClientListActivity.SimpleItemRecyclerViewAdapter adapter) {
        super(activity);
        this.adapter = adapter;
    }

    @Override
    protected void onPostExecute(ClientsResponse result) {
        if (result == null) {
            return;
        }
        this.adapter.setmValues( result.getClients() );
    }
}
