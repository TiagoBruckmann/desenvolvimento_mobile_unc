package br.unc.projetodesenvolvimentomobile_unc.data.repository;

import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.data.datasource.LoginDataSource;
import br.unc.projetodesenvolvimentomobile_unc.data.model.UserModel;

public class LoginRepository {

    private static volatile LoginRepository instance;

    private final LoginDataSource dataSource;

    private UserModel user = null;

    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    private void setUserModel(UserModel user) {
        this.user = user;
    }

    public Result<UserModel> login(Map<String, Object> json) {
        Result<UserModel> result = dataSource.login(json);
        if (result instanceof Result.Success) {
            setUserModel(((Result.Success<UserModel>) result).getData());
        }
        return result;
    }
}