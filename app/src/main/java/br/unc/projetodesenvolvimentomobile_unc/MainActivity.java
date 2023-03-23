package br.unc.projetodesenvolvimentomobile_unc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import br.unc.projetodesenvolvimentomobile_unc.app.pages.ClientsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openClient();
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
}