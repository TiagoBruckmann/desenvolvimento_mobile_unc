package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServiceDataSource {

    private FirebaseFirestore db;
    private boolean successSave;
    private ArrayList<ServiceEntity> listService;

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

    public Result<ArrayList<ServiceEntity>> getServices(String userId ) {

        try {

            Log.i("GetServices => ", "CAiu !!");
            db = ConfigFirebase.getDb();
            db.collection("services")
                .whereEqualTo("user_id", userId)
                .get()
                .addOnCompleteListener(
                    response -> {
                        Log.i("Fia da puta => ", "CAiu !!");
                        QuerySnapshot result = response.getResult();
                        if ( !result.isEmpty() ) {
                            Log.i("Fia da puta => ", "CAiu 2 !!");
                            Log.i("doc => ", String.valueOf(result));
                            List<DocumentSnapshot> doc = result.getDocuments();

                            for ( int i = 0; i < doc.size(); i++ ) {
                                Log.i("i => ", String.valueOf(doc.get(i)));
                            }

                        }
                    }
                );

            listService = new ArrayList<>();
            // Vo2oY3h8FxO0nDzejMto3tlHI3E3
            listService.add(
                new ServiceEntity(
                    "Vo2oY3h8FxO0nDzejMto3tlHI3E3",
                    "Tiago B",
                    "tiago6@teste.com",
                    "Orçamento",
                    "Tue Apr 25 18:10:08 GMT-03:00 2023"
                )
            );

            Log.i("GetServices => ", "list:: " + listService);
            return new Result.Success<>(listService);
        } catch (Exception e) {
            Log.i("Deu ruim => ", "e :: " + e.getMessage());
            return new Result.Error(new IOException("Error get services", e));
        }

    }
}
