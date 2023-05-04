package br.unc.projetodesenvolvimentomobile_unc.app.core.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import br.unc.projetodesenvolvimentomobile_unc.R;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServicesAdapter extends ArrayAdapter<ServiceEntity> {

    private final ArrayList<ServiceEntity> listServices;
    private final Context context;

    public ServicesAdapter(@NonNull Context context, @NonNull ArrayList<ServiceEntity> list) {
        super(context, 0, list);
        this.listServices = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        //verifica se a lista esta vazia
        if ( listServices != null ){

            // iniciar objeto para montegem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // montar a view
            view = inflater.inflate( R.layout.list_employees, parent, false );

            // recuperar elemento para exibição
            TextView nameUser = view.findViewById(R.id.user_name);
            TextView nameService = view.findViewById(R.id.service_name);
            TextView serviceObs = view.findViewById(R.id.service_obs);

            ServiceEntity serviceEntity = listServices.get( position );
            nameUser.setText( serviceEntity.getName() );
            nameService.setText( serviceEntity.getService() );
            serviceObs.setText( serviceEntity.getObservation() );

        }

        return view;

    }

}
