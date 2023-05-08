package br.unc.projetodesenvolvimentomobile_unc.data.repository;

import java.util.ArrayList;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.EmployeesDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;

public class EmployeesRepository {

    private static volatile EmployeesRepository instance;

    private final EmployeesDataSource dataSource;

    private ArrayList<EmployeesModel> listEmployees;

    private EmployeesRepository(EmployeesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EmployeesRepository getInstance(EmployeesDataSource dataSource) {
        if (instance == null) {
            instance = new EmployeesRepository(dataSource);
        }
        return instance;
    }

    private void setList(ArrayList<EmployeesModel> list) {
        this.listEmployees = list;
    }

    public Result<ArrayList<EmployeesModel>> getEmployees() {
        Result<ArrayList<EmployeesModel>> result = dataSource.getEmployees();
        if (result instanceof Result.Success) {
            setList(((Result.Success<ArrayList<EmployeesModel>>) result).getData());
        }
        return result;
    }
}
