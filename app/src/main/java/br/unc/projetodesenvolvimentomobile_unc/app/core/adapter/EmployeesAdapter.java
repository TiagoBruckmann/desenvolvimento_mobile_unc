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
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;

public class EmployeesAdapter extends ArrayAdapter<EmployeesModel> {

    private final ArrayList<EmployeesModel> listEmployees;
    private final Context context;

    public EmployeesAdapter(@NonNull Context context, @NonNull ArrayList<EmployeesModel> list) {
        super(context, 0, list);
        this.listEmployees = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = null;

        //verifica se a lista esta vazia
        if ( listEmployees != null ){

            // iniciar objeto para montegem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            // montar a view
            view = inflater.inflate( R.layout.list_employees, parent, false );

            // recuperar elemento para exibição
            TextView nameEmployee = view.findViewById(R.id.employee_name);
            TextView ageEmployee = view.findViewById(R.id.employee_age);
            TextView functionEmployee = view.findViewById(R.id.employee_function);

            EmployeesModel employeesModel = listEmployees.get( position );
            nameEmployee.setText( employeesModel.getName() );
            ageEmployee.setText( employeesModel.getAge() );
            functionEmployee.setText( employeesModel.getFunction() );

        }

        return view;

    }

}
