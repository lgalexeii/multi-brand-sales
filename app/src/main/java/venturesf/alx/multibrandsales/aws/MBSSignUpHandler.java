package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;

import venturesf.alx.multibrandsales.R;
import venturesf.alx.multibrandsales.util.LoginUtils;

/**
 * Created by B942272 on 27/02/2018.
 */

public class MBSSignUpHandler implements SignUpHandler {

    private final Activity activity;

    public MBSSignUpHandler(Activity activity){
        this.activity = activity;
    }
    @Override
    public void onSuccess(CognitoUser user, boolean userConfirmed, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
        // Sign-up was successful
        // Check if this user (cognitoUser) needs to be confirmed
        LoginUtils. showProgress(this.activity,false);
        Context context = activity.getApplicationContext();
        if(!userConfirmed) {
            Toast.makeText(context, "Please confirm the user", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "User successfully registered", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Exception exception) {
        AmazonServiceException ex = (AmazonServiceException) exception;
        LoginUtils. showProgress(this.activity,false);

        Context context = activity.getApplicationContext();
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, exception.getMessage(), duration);
        toast.show();

        Log.i( "Login fail",ex.getMessage() );
    }
}
