package venturesf.alx.multibrandsales.aws;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;

import venturesf.alx.multibrandsales.ClientsList;
import venturesf.alx.multibrandsales.R;

import venturesf.alx.multibrandsales.util.LoginUtils;

/**
 * Created by B942272 on 27/02/2018.
 */

public class MBSAuthenticationHandler implements AuthenticationHandler {

    private String password;
    private Activity activity;

    public MBSAuthenticationHandler(String password, Activity activity){
        this.password = password;
        this.activity = activity;
    }

    @Override
    public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
        Context context = activity.getApplicationContext();
        LoginUtils. showProgress(this.activity,false);
       ((TextView)this.activity.findViewById(R.id.email )).setError(null);

            Toast toast = Toast.makeText(context, "Log In Success", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(this.activity, ClientsList.class);
            //intent.
        activity.startActivity(intent);
    }

    @Override
    public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String userId) {
        // The API needs user sign-in credentials to continue
        AuthenticationDetails authenticationDetails = new AuthenticationDetails(userId, password, null);


        // Pass the user sign-in credentials to the continuation
        authenticationContinuation.setAuthenticationDetails(authenticationDetails);

        // Allow the sign-in to continue
        authenticationContinuation.continueTask();
    }

    @Override
    public void getMFACode(MultiFactorAuthenticationContinuation continuation) {

    }

    @Override
    public void authenticationChallenge(ChallengeContinuation continuation) {

    }

    @Override
    public void onFailure(Exception exception) {
        AmazonServiceException ex = (AmazonServiceException) exception;
        Context context = activity.getApplicationContext();
        LoginUtils. showProgress(this.activity,false);
        if( ex.getErrorCode().equals( "UserNotFoundException" )){
            ((TextView)this.activity.findViewById(R.id.email )).setError(ex.getErrorMessage());

            CharSequence text = "User not found, please tray again or register";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }else if(ex.getErrorCode().equals( "UserNotConfirmedException")){
           LoginUtils.confirmFlow(this.activity);
        }

        Log.i( "Login fail",ex.getMessage() );

    }
}
