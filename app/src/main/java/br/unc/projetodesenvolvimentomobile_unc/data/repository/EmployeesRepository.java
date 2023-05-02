package br.unc.projetodesenvolvimentomobile_unc.data.repository;

import java.util.ArrayList;
import java.util.List;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.EmployeesDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.EmployeesModel;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class EmployeesRepository {

    private static volatile EmployeesRepository instance;

    private final EmployeesDataSource dataSource;

    private List<EmployeesModel> listEmployees;

    private EmployeesRepository(EmployeesDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static EmployeesRepository getInstance(EmployeesDataSource dataSource) {
        if (instance == null) {
            instance = new EmployeesRepository(dataSource);
        }
        return instance;
    }

    private void setListServices(List<EmployeesModel> list ) {
        this.listEmployees = list;
    }

    public Result<ArrayList<EmployeesModel>> getEmployees() {
        Result<ArrayList<EmployeesModel>> list = dataSource.getEmployees();
        if ( list instanceof Result.Success ) {
            setListServices(((Result.Success<List<EmployeesModel>> ) list).getData());
        }
        return list;
    }
}
