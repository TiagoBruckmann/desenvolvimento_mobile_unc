package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.app.core.EmployeesAdapter;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.EmployeesDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.EmployeesRepository;
import br.unc.projetodesenvolvimentomobile_unc.domain.source.AppEvents;

public class EmployeesActivity extends AppCompatActivity {

    private ArrayList<EmployeesModel> listEmployees;
    AppEvents appEvents = new AppEvents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employees);

        appEvents.sendScreen(this, "company");

        ActionBar appBar = Objects.requireNonNull(getSupportActionBar());
        appBar.setTitle(R.string.appbar_company);
        appBar.setDisplayHomeAsUpEnabled(true);

        getEmployees();

    }

    private void setList( ArrayList<EmployeesModel> list ) {
        this.listEmployees = list;
    }

    private void getEmployees() {

        listEmployees = new ArrayList<>();

        EmployeesRepository repository = EmployeesRepository.getInstance(new EmployeesDataSource());
        Result<ArrayList<EmployeesModel>> resultOrFail = repository.getEmployees();

        if (resultOrFail instanceof Result.Success) {
            setList(((Result.Success<ArrayList<EmployeesModel>>) resultOrFail).getData());
        }

        // inserir na lista
        ListView listView = findViewById(R.id.lv_employees);
        ArrayAdapter<EmployeesModel> adapter = new EmployeesAdapter(this, listEmployees);
        listView.setAdapter(adapter);
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