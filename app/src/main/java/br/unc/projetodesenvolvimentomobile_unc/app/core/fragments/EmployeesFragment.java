package br.unc.projetodesenvolvimentomobile_unc.app.core.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.EmployeesDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.EmployeesRepository;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeesFragment extends Fragment {

    private ArrayList<EmployeesModel> listEmployees;

    public EmployeesFragment() {
        // Required empty public constructor
    }

    public static EmployeesFragment newInstance(String param1, String param2) {
        EmployeesFragment fragment = new EmployeesFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        Log.i("successOrFailure => ", String.valueOf(successOrFailure));

        if ( successOrFailure instanceof Result.Success ) {
            setList(((Result.Success<ArrayList<EmployeesModel>> ) successOrFailure).getData());
        }
        // Log.i("listEmployees => ", listEmployees.toString());

        return view;
    }
}