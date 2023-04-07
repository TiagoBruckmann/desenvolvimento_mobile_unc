package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class ServicesActivity extends AppCompatActivity {

    AppEvents appEvents = new AppEvents();
    EditText name, email, obs;
    Spinner spinner;

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

        ServiceEntity serviceEntity = new ServiceEntity(
            fieldName, fieldEmail, fieldSpinner, fieldObs
        );

        serviceEntity.sendToFirebase(serviceEntity.toJson());
    }

}