package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class ClientsActivity extends AppCompatActivity {

    AppEvents appEvents = new AppEvents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clients);

        appEvents.sendScreen(this, "clients");

        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.setDisplayHomeAsUpEnabled(true);
        appBar.setTitle(R.string.appbar_client);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            onBackPressed();
            return (true);
        }
        return(super.onOptionsItemSelected(item));
    }
}