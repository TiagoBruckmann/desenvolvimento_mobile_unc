package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;

public class ServicesActivity extends AppCompatActivity {

    EditText name;
    EditText email;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.setDisplayHomeAsUpEnabled(true);
        appBar.setTitle(R.string.appbar_services);

        name = findViewById(R.id.name_field);
        email = findViewById(R.id.email_field);
        configureSpinner();
    }

    void configureSpinner() {
        spinner = findViewById(R.id.spinner_services);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter
            .createFromResource(
                this,
                R.array.spinner_options,
                R.layout.support_simple_spinner_dropdown_item
            );
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }
}