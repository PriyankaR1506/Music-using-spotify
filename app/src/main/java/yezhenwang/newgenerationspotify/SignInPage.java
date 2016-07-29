package yezhenwang.newgenerationspotify;

import android.content.Context;
import android.hardware.camera2.params.Face;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;

public class SignInPage extends AppCompatActivity {

    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_sign_in_page);
        loginButton = (LoginButton) findViewById(R.id.login_button);
    }

    public void logIn(View view) {
    }

    public void signUp(View view) {
    }

    public void fbLogin(View view) {

    }
}

