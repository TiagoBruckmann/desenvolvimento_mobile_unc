package br.unc.projetodesenvolvimentomobile_unc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.app.pages.ClientsActivity;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.ContactActivity;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.EmployeesActivity;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.ServicesActivity;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class MainActivity extends AppCompatActivity {

    AppEvents appEvents = new AppEvents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appEvents.sendScreen(this, "main");
        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.hide();

        openCompany();
        openClient();
        openContact();
        openServices();
    }

    void openCompany() {
        appEvents.globalEvent("main_open_company", new Bundle());

        ImageView company = findViewById(R.id.btnCompany);
        company.setOnClickListener(
            v-> startActivity(
                new Intent(
                    this,
                    EmployeesActivity.class
                )
            )
        );
    }

    void openClient() {
        Bundle bundle = new Bundle();
        appEvents.globalEvent("main_open_client", bundle);

        ImageView client = findViewById(R.id.btnClients);
        client.setOnClickListener(
            v-> startActivity(
                new Intent(
                    this,
                    ClientsActivity.class
                )
            )
        );
    }

    void openServices() {
        Bundle bundle = new Bundle();
        appEvents.globalEvent("main_open_service", bundle);
        ImageView services = findViewById(R.id.btnServices);
        services.setOnClickListener(
            v-> startActivity(
                new Intent(
                    this,
                    ServicesActivity.class
                )
            )
        );
    }

    void openContact() {
        Bundle bundle = new Bundle();
        appEvents.globalEvent("main_open_contact", bundle);
        ImageView contact = findViewById(R.id.btnContact);
        contact.setOnClickListener(
            v-> startActivity(
                new Intent(
                    this,
                    ContactActivity.class
                )
            )
        );
    }
}