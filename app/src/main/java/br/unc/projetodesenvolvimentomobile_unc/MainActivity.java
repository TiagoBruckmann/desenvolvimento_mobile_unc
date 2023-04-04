package br.unc.projetodesenvolvimentomobile_unc;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.app.pages.ClientsActivity;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.ContactActivity;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.ServicesActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.hide();

        openClient();
        openContact();
        openServices();
    }

    void openClient() {
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