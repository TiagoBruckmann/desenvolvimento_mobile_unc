package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.model.UserModel;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class LoginDataSource {

    private FirebaseAuth auth;

    public Result<UserModel> login(Map<String, Object> json) {

        try {

            String email = (String) json.get("email");
            String password = (String) json.get("password");

            assert email != null && password != null;

            auth = ConfigFirebase.getAuth();
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> Log.i("login", "signInWithEmailAndPassword:success"))
                .addOnFailureListener(e -> Log.e("falha no login => ", e.getMessage()));

            FirebaseUser user = auth.getCurrentUser();
            UserModel userModel =
                new UserModel(
                    Objects.requireNonNull(user).getUid(),
                    user.getDisplayName(),
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
            String name = (String) json.get("name");
            String email = (String) json.get("email");
            String password = (String) json.get("password");
            assert name != null && email != null && password != null;

            auth = ConfigFirebase.getAuth();
            auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Log.d(TAG, "createUserWithEmail:success");
                    Objects.requireNonNull(authResult.getUser()).updateProfile(
                        new UserProfileChangeRequest.Builder().setDisplayName(
                            name
                        ).build()
                    );
                })
                .addOnFailureListener(e -> {
                    Log.e("falha no login => ", e.getMessage());
                });

            FirebaseUser user = auth.getCurrentUser();
            UserModel userModel =
                new UserModel(
                    Objects.requireNonNull(user).getUid(),
                    user.getDisplayName(),
                    email
                );

            FirebaseFirestore db = ConfigFirebase.getDb();
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