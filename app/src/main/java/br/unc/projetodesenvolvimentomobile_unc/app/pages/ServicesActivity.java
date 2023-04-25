package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login.LoginActivity;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.ServiceDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.ServiceRepository;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class ServicesActivity extends AppCompatActivity {

    private AppEvents appEvents = new AppEvents();
    private ServiceEntity serviceEntity;
    private EditText name, email, obs;
    private Boolean successSave;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.setDisplayHomeAsUpEnabled(true);
        appBar.setTitle(R.string.appbar_services);

        appEvents.sendScreen(this, "services");

        Button btnService = findViewById(R.id.btn_send_service);

        configureSpinner();

        btnService.setOnClickListener(
            v -> validateFields()
        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            onBackPressed();
            return (true);
        }
        return(super.onOptionsItemSelected(item));
    }

    void configureSpinner() {
        spinner = findViewById(R.id.spinner_services);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
            this,
            R.array.spinner_options,
            R.layout.support_simple_spinner_dropdown_item
        );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    void validateFields() {

        Bundle bundle = new Bundle();

        name = findViewById(R.id.name_field);
        email = findViewById(R.id.email_field);
        obs = findViewById(R.id.obs_field);

        String fieldName = name.getText().toString();
        String fieldEmail = email.getText().toString();
        String fieldObs = obs.getText().toString();
        String fieldSpinner = spinner.getSelectedItem().toString();

        if ( fieldName.trim().isEmpty() || fieldName.trim().length() < 3 ) {
            appEvents.globalEvent("service_error_field_name", bundle);
            Toast.makeText(this, "Informe um nome válido", Toast.LENGTH_LONG).show();
            return;
        }

        if ( fieldEmail.trim().isEmpty() || !fieldEmail.contains("@") ) {
            Toast.makeText(this, "Informe um e-mail válido", Toast.LENGTH_LONG).show();
            return;
        }

        if ( fieldObs.trim().isEmpty() || fieldObs.trim().length() < 15 ) {
            Toast.makeText(this, "Informe o motivo da observação.", Toast.LENGTH_LONG).show();
            return;
        }

        if ( fieldSpinner.contains("Selecione um serviço") ) {
            Toast.makeText(this, "Selecione o motivo do serviço.", Toast.LENGTH_LONG).show();
            return;
        }

        FirebaseAuth auth = ConfigFirebase.getAuth();
        FirebaseUser user = auth.getCurrentUser();

        if ( user == null ) {
            startActivityForResult(
                new Intent(
                    this,
                    LoginActivity.class
                ),
                200
            );
            return;
        }

        serviceEntity = new ServiceEntity(
            user.getUid(),
            fieldName,
            fieldEmail,
            fieldSpinner,
            fieldObs
        );

        createService();
    }

    private void createService() {

        ServiceRepository repository = ServiceRepository.getInstance(new ServiceDataSource());
        Log.i("service => ", serviceEntity.toJson().toString());
        Result<Boolean> success = repository.setService(serviceEntity.toJson());

        Log.i("success => ", success.toString());
        if ( success instanceof Result.Success ) {
            setSuccess(((Result.Success<Boolean> ) success).getData());
        }

        if ( successSave ) {
            Toast.makeText(this, "Sucesso ao cadastrar serviço!", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(this, "Falha ao cadastrar serviço!", Toast.LENGTH_LONG).show();
        }
    }

    private void setSuccess(Boolean value ) {
        successSave = value;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i("status_code => ", String.valueOf(requestCode));
        Log.i("resultCode => ", String.valueOf(resultCode));
        if ( resultCode == Activity.RESULT_OK ) {
            String fieldName = name.getText().toString();
            String fieldEmail = email.getText().toString();
            String fieldObs = obs.getText().toString();
            String fieldSpinner = spinner.getSelectedItem().toString();

            Object response = data.getExtras().get("response");
            Log.i("response => ", response.toString());
            String[] values = response.toString().replace("{", "").replace("}", "").split(",");
            Log.i("getExtras() => ", String.valueOf(data.getExtras()));
            String userId = values[2].split("=")[1];
            Log.i("userId => ", userId);

            serviceEntity = new ServiceEntity(
                userId,
                fieldName,
                fieldEmail,
                fieldSpinner,
                fieldObs
            );

            createService();

        }
    }
}