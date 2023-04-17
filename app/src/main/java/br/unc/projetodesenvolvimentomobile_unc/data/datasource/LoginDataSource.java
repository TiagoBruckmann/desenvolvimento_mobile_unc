package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import static android.content.ContentValues.TAG;

import static com.google.android.material.internal.ContextUtils.getActivity;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login.LoginActivity;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.model.LoggedInUser;
import br.unc.projetodesenvolvimentomobile_unc.data.model.UserModel;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private FirebaseFirestore db;
    private FirebaseAuth auth;

    public Result<UserModel> login(Map<String, Object> json) {

        try {

            Log.e("json datasource => ", json.toString());
            String email = (String) json.get("email");
            String password = (String) json.get("password");

            assert email != null && password != null;

            final FirebaseUser[] user = new FirebaseUser[1];

            auth = ConfigFirebase.getAuth();
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.i("login", "signInWithEmailAndPassword:success");
                    user[0] = auth.getCurrentUser();
                    if ( user[0] != null ) {
                        Log.i("user.uid => ", user[0].getUid());
                    }
                })
                .addOnFailureListener(e -> Log.e("falha no login => ", e.getMessage()));

            UserModel userModel =
                new UserModel(
                    "user[0].getUid()",
                    "user[0].getDisplayName()",
                    email
                );

            return new Result.Success<>(userModel);
        } catch (Exception e) {
            Log.e("e datasource => ", e.getMessage());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public Result<UserModel> createUser(Map<String, Object> json) {

        try {
            String email = (String) json.get("email");
            String password = (String) json.get("password");
            assert email != null && password != null;

            final FirebaseUser[] user = new FirebaseUser[1];

            auth = ConfigFirebase.getAuth();
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "createUserWithEmail:success");
                    user[0] = auth.getCurrentUser();
                    if ( user[0] != null ) {
                        Log.i("user => ", user[0].toString());
                        Log.i("user.uid => ", user[0].getUid());
                        Log.i("user.name => ", user[0].getDisplayName());
                        Log.i("user.email => ", user[0].getEmail());
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("falha no login => ", e.getMessage());
                });

            UserModel userModel =
                new UserModel(
                    "user[0].getUid()",
                    "user[0].getDisplayName()",
                    email
                );

            db = ConfigFirebase.getDb();
            db.collection("users").add(userModel.toJson());

            return new Result.Success<>(userModel);
        } catch (Exception e) {
            Log.e("e create => ", e.getMessage());
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        try {

            auth = ConfigFirebase.getAuth();
            auth.signOut();

        } catch ( Exception e ) {
            Log.e("error => ", e.getMessage());
        }
    }
}