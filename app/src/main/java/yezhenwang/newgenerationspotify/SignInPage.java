package yezhenwang.newgenerationspotify;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class SignInPage extends AppCompatActivity {

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    EditText userName, password;
    boolean loginUN, loginPas;

    private boolean isMainPageActivityStarted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userName = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        setContentView(R.layout.activity_sign_in_page);
        loginButton = (LoginButton) findViewById(R.id.login_button);

        final Intent MainPageActivity = new Intent(this, yezhenwang.newgenerationspotify.FragmentMainPage.class);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("Log in Success!");

                if(!isMainPageActivityStarted) {
                    isMainPageActivityStarted = true;
                    startActivity(MainPageActivity);
                }
            }

            @Override
            public void onCancel() {
                System.out.println("Log in Canceled!");
            }

            @Override
            public void onError(FacebookException error) {
                System.out.println("Log in Error!");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void databaseSignUp(View view) {
    }

    public void databaseLogIn(View view) {
            Intent intent = new Intent(this, FragmentMainPage.class);
            startActivity(intent);
    }
}