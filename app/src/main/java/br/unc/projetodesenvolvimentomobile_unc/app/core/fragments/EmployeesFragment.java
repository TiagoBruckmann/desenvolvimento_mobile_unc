package br.unc.projetodesenvolvimentomobile_unc.app.core.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.app.core.adapter.EmployeesAdapter;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.EmployeesDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.EmployeesRepository;

public class EmployeesFragment extends Fragment {

    private ArrayList<EmployeesModel> listEmployees;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    private void setList(ArrayList<EmployeesModel> value ) {
        listEmployees = value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employees, container, false);

        EmployeesRepository repository = EmployeesRepository.getInstance(new EmployeesDataSource());
        Result<ArrayList<EmployeesModel>> successOrFailure = repository.getEmployees();

        if ( successOrFailure instanceof Result.Success ) {
            setList(((Result.Success<ArrayList<EmployeesModel>> ) successOrFailure).getData());
        }

        ListView listViewEmployees = view.findViewById(R.id.lv_employees);
        ArrayAdapter<EmployeesModel> adapter = new EmployeesAdapter(requireActivity(), listEmployees);
        listViewEmployees.setAdapter(adapter);

        return view;
    }
}