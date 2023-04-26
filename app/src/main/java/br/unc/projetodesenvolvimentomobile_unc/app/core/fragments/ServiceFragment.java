package br.unc.projetodesenvolvimentomobile_unc.app.core.fragments;

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
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.ServiceDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.ServiceRepository;
import br.unc.projetodesenvolvimentomobile_unc.data.sources.local.ConfigFirebase;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServiceFragment extends Fragment {

    private ArrayList<ServiceEntity> serviceList;
    private ListView listView;

    public ServiceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_service, container, false);

        listView = view.findViewById(R.id.list_services);

        getServices();

        return view;
    }

    void setListServices( ArrayList<ServiceEntity> list ) {
        Log.i("list => ", String.valueOf(list.get(0).getUserId()));
        Log.i("list => ", String.valueOf(list.get(0).getName()));
        this.serviceList = list;
    }

    void getServices() {

        FirebaseAuth auth = ConfigFirebase.getAuth();
        FirebaseUser user = auth.getCurrentUser();

        if ( user == null ) {
            Log.i("user null => ", "nullouu");
            return;
        }

        ServiceRepository serviceRepository = ServiceRepository.getInstance(new ServiceDataSource());
        // Result<List<ServiceEntity>> list = serviceRepository.getServices(user.getUid());
        Result<ArrayList<ServiceEntity>> list = serviceRepository.getServices("Vo2oY3h8FxO0nDzejMto3tlHI3E3");

        if ( list instanceof Result.Success ) {
            setListServices(((Result.Success<ArrayList<ServiceEntity>> ) list).getData());
        }

        Log.i("serviceList => ", serviceList.get(0).getService());

        ArrayAdapter adapter = new ArrayAdapter(
            getActivity(),
            android.R.layout.simple_list_item_2,
            serviceList
        );
        listView.setAdapter(adapter);

    }
}