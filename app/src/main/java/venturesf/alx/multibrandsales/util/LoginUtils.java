package venturesf.alx.multibrandsales.util;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import venturesf.alx.multibrandsales.R;

/**
 * Created by B942272 on 27/02/2018.
 */

public class LoginUtils {
    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static void showProgress(Activity activity, final boolean show) {
        final View mLoginFormView = activity.findViewById(R.id.login_form);
        final View mProgressView = activity.findViewById(R.id.login_progress);
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = activity.getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public static void confirmUser(Activity activity){
        Log.i("Confirm","Confirming user...");
        TextView confirmCode = activity.findViewById(R.id.confirmation_code);
        String confirmCodeTxt = confirmCode.getText().toString() ;

        Log.i("Confirm Code",confirmCodeTxt);

    }

    public static void confirmFlow(final Activity activity){

        Toast toast = Toast.makeText(activity.getApplicationContext(), "Please Confirm your user", Toast.LENGTH_LONG);
        toast.show();
        activity.findViewById(R.id.conf_code ).setVisibility(View.VISIBLE);
        activity.findViewById(R.id.password ).setEnabled(false);
        Button mButton = (Button)activity.findViewById(R.id.email_sign_in_button);
        mButton.setText(R.string.action_confirm );
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtils.confirmUser(activity);
            }
        });

    }
}
