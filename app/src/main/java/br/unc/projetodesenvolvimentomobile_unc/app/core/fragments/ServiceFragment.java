package br.unc.projetodesenvolvimentomobile_unc.app.core.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.app.core.adapter.EmployeesAdapter;
import br.unc.projetodesenvolvimentomobile_unc.app.core.adapter.ServicesAdapter;
import br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login.LoginActivity;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.ServiceDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.ServiceRepository;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServiceFragment extends Fragment {

    private ArrayList<ServiceEntity> serviceList;

    public ServiceFragment() {
        // Required empty public constructor
    }

    private void setList(ArrayList<ServiceEntity> value ) {
        serviceList = value;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        FirebaseAuth auth = ConfigFirebase.getAuth();
        FirebaseUser user = auth.getCurrentUser();

        if ( user != null ) {
            ServiceRepository serviceRepository = ServiceRepository.getInstance(new ServiceDataSource());
            Result<ArrayList<ServiceEntity>> successOrFailure = serviceRepository.getServices(user.getUid());

            if ( successOrFailure instanceof Result.Success ) {
                setList(((Result.Success<ArrayList<ServiceEntity>> ) successOrFailure).getData());
            }

            ListView listViewServices = view.findViewById(R.id.lv_services);
            ArrayAdapter<ServiceEntity> adapter = new ServicesAdapter(requireActivity(), serviceList);
            listViewServices.setAdapter(adapter);
        } else {
            startActivityForResult(
                new Intent(
                    view.getContext(),
                    LoginActivity.class
                ),
                200
            );
        }

        return view;
    }

}