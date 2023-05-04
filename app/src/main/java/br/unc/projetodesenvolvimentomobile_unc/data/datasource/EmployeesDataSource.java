package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class EmployeesDataSource {

    private ArrayList<EmployeesModel> listEmployees;
    private ResponseBody body;
    private Result result;

    public Result<ArrayList<EmployeesModel>> getEmployees() {
        try {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                .url("https://goldfish-app-eaau6.ondigitalocean.app/get-employees")
                .build();

            Call call = client.newCall(request);

            listEmployees = new ArrayList<>();

            call.enqueue(new Callback() {
                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if ( response.code() == 200 && response.isSuccessful() ) {

                        body = response.body();

                        try {
                            JSONArray json = new JSONArray(body.string());
                            for ( int i = 0; i < json.length(); i++ ) {
                                Object param = json.get(i);
                                JSONObject object = new JSONObject(param.toString());
                                listEmployees.add(new EmployeesModel(object));
                            }

                            result = new Result.Success<>(listEmployees);
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        result = new Result.Error(new IOException("Error response get Employees"));
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    result = new Result.Error(new IOException("Error response get Employees", e));
                }
            });

            while ( result == null ) {
                // Log.i("ta nulo => ", "Nullado");
            }

            return new Result.Success<>(listEmployees);

        } catch ( Exception e ) {
            return new Result.Error(new IOException("Error get Employees", e));
        }
    }

}
