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
import okio.Timeout;

public class EmployeesDataSource {

    private ArrayList<EmployeesModel> listEmployees;
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
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    result = new Result.Error(new IOException("error inside http get Employees", e));
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if ( response.code() == 200 && response.isSuccessful() ) {

                        ResponseBody body = response.body();

                        try {
                            JSONArray array = new JSONArray(body.string());
                            Log.i("array => ", array.toString());
                            for ( int i = 0; i < array.length(); i++ ) {
                                Object params = array.get(i);
                                JSONObject object = new JSONObject(params.toString());
                                Log.i("object => ", object.toString());
                                listEmployees.add(new EmployeesModel(object));
                            }

                            result = new Result.Success<>(listEmployees);
                        } catch (JSONException e) {
                            Log.i("exception =>", body.string());
                            result = new Result.Error(new IOException("error on convert Employees", e));
                            throw new RuntimeException(e);
                        }

                    }
                }
            });

            while (result == null ) {

            }

            return result;
        } catch ( Exception e) {
            return new Result.Error(new IOException("error on get Employees", e));
        }
    }

}
