package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServiceDataSource {

    private FirebaseFirestore db;
    private boolean successSave;

    public Result<Boolean> setService(Map<String, Object> json) {
        try {

            db = ConfigFirebase.getDb();
            db.collection("services").add(json)
                .addOnSuccessListener(success -> setSuccessSave(true))
                .addOnFailureListener(failure -> setSuccessSave(false));

            Log.i("successSave => ", String.valueOf(successSave));
            return new Result.Success<>(successSave);

        } catch ( Exception e ) {
            Log.e("error => ", e.getMessage());
            return new Result.Error(new IOException("Error register service", e));
        }
    }

    private void setSuccessSave(Boolean value) {
        Log.i("successSave a => ", String.valueOf(successSave));
        successSave = value;
        Log.i("successSave d => ", String.valueOf(successSave));
    }

    public Result<List<ServiceEntity>> getServices(String userId ) {

        try {

            db = ConfigFirebase.getDb();
            db.collection("services")
                .whereEqualTo("user_id", userId)
                .get()
                .addOnSuccessListener(
                    response -> {
                        List<DocumentSnapshot> doc = response.getDocuments();
                        for ( int i = 0; i < doc.size(); i++ ) {
                            Log.i("i => ", String.valueOf(doc.get(i)));
                        }
                    }
                );

            List<ServiceEntity> list = null;

            return new Result.Success<>(list);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error get services", e));
        }

    }
}
