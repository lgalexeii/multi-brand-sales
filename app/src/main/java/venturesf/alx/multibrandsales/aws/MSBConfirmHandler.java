package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;

import venturesf.alx.multibrandsales.ClientListActivity;
import venturesf.alx.multibrandsales.R;
import venturesf.alx.multibrandsales.util.LoginUtils;

/**
 * Created by B942272 on 01/03/2018.
 */

public class MSBConfirmHandler implements GenericHandler {
    private Activity activity;

    public MSBConfirmHandler(Activity activity){
        this.activity = activity;
    }

    @Override
    public void onSuccess() {

        Context context = activity.getApplicationContext();
        LoginUtils. showProgress(this.activity,false);
        ((TextView)this.activity.findViewById(R.id.email )).setError(null);

        Toast toast = Toast.makeText(context, "User has been confirmed", Toast.LENGTH_LONG);
        toast.show();

        Intent intent = new Intent(this.activity, ClientListActivity.class);
        //intent.
        activity.startActivity(intent);

    }

    @Override
    public void onFailure(Exception exception) {
        AmazonServiceException ex = (AmazonServiceException) exception;
        LoginUtils. showProgress(this.activity,false);

        Context context = activity.getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, exception.getMessage(), duration);
        toast.show();

    }
}
