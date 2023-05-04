package br.unc.projetodesenvolvimentomobile_unc.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.ServiceDataSource;
import br.unc.projetodesenvolvimentomobile_unc.domain.entity.ServiceEntity;

public class ServiceRepository {

    private static volatile ServiceRepository instance;

    private final ServiceDataSource dataSource;

    private List<ServiceEntity> listServices;

    private ServiceRepository(ServiceDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static ServiceRepository getInstance(ServiceDataSource dataSource) {
        if (instance == null) {
            instance = new ServiceRepository(dataSource);
        }
        return instance;
    }

    public Result<Boolean> setService( Map<String, Object> json ) {
        return dataSource.setService(json);
    }

    private void setListServices(List<ServiceEntity> list ) {
        this.listServices = list;
    }

    public Result<ArrayList<ServiceEntity>> getServices( String userId ) {
        Result<ArrayList<ServiceEntity>> list = dataSource.getServices(userId);
        if ( list instanceof Result.Success ) {
            setListServices(((Result.Success<List<ServiceEntity>> ) list).getData());
        }
        return list;
    }
}
