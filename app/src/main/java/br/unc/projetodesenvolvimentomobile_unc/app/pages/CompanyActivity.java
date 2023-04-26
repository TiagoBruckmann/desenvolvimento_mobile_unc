package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.app.core.fragments.EmployeesFragment;
import br.unc.projetodesenvolvimentomobile_unc.app.core.fragments.ServiceFragment;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class CompanyActivity extends AppCompatActivity {

    private final FragmentManager fragmentManager = getSupportFragmentManager();
    private Button btnEmployees, btnServices;
    AppEvents appEvents = new AppEvents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.setDisplayHomeAsUpEnabled(true);
        appBar.setTitle(R.string.appbar_company);

        btnEmployees = findViewById(R.id.btn_fragment_employees);
        btnServices = findViewById(R.id.btn_fragment_services);

        configureEmployeesFragment();
        configureServicesFragment();

    }

    void configureEmployeesFragment() {
        btnEmployees.setOnClickListener(v -> {
            appEvents.globalEvent("company_employees_fragment", new Bundle());
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, EmployeesFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("Funcionarios")
                .commit();
        });
    }

    void configureServicesFragment() {
        btnServices.setOnClickListener(v -> {
            appEvents.globalEvent("company_services_fragment", new Bundle());
            fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, ServiceFragment.class, null)
                .setReorderingAllowed(true)
                .addToBackStack("Servicos")
                .commit();
        });
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