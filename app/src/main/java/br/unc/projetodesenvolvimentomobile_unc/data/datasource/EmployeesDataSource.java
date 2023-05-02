package br.unc.projetodesenvolvimentomobile_unc.data.datasource;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class EmployeesDataSource {

    private ArrayList<EmployeesModel> listEmployees;
    private OkHttpClient client;

    public Result<ArrayList<EmployeesModel>> getEmployees() {
        try {

            client = new OkHttpClient();
            Log.i("client =>", client.toString());
            Request request = new Request.Builder()
                .url("https://goldfish-app-eaau6.ondigitalocean.app/get-employees")
                .build();

            Log.i("request =>", request.toString());

            Call call = client.newCall(request);

            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if ( response.code() == 200 && response.isSuccessful() ) {
                        Log.i("sucesso! => ", "Yeaaa");
                        Gson gson = new Gson();
                        ResponseBody body = response.body();
                        Log.i("body => ", String.valueOf(body));
                        Log.i("body 2 => ", body.string());
                        Log.i("body 3 => ", Arrays.toString(body.string().split("}")));
                        String[] array = body.string().split("}");
                        Log.i("array => ", String.valueOf(array));
                        for ( int i = 0; i < array.length; i++ ) {
                            Log.i("array[i] => ", array[i]);
                        }
                        Log.i("body =>", body.string());
                        EmployeesModel employeesModel = gson.fromJson(gson.toJson(body), EmployeesModel.class);
                        Log.i("listEmployees =>", String.valueOf(listEmployees));
                    } else {
                        Log.i("Deu ruim no else =>", String.valueOf(response.code()));
                    }
                }

                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("Deu ruim no response =>", e.getMessage());
                    // return new Result.Error(new IOException("Error response get Employees", e));
                }
            });

            return new Result.Success<>(listEmployees);

        } catch ( Exception e ) {
            // Log.e("error => ", e.getMessage());
            return new Result.Error(new IOException("Error get Employees", e));
        }
    }

}
