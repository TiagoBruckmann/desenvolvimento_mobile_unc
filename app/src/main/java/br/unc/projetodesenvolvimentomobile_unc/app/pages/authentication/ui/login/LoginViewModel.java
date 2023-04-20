package br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.model.UserModel;
import br.unc.projetodesenvolvimentomobile_unc.data.repository.LoginRepository;
import br.unc.projetodesenvolvimentomobile_unc.data.Result;
import br.unc.projetodesenvolvimentomobile_unc.R;

public class LoginViewModel extends ViewModel {

    private final MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private final MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public void login(String email, String password) {
        Map<String, Object> json = new HashMap<>();
        json.put("email", email);
        json.put("password", password);

        Result<UserModel> result = loginRepository.login(json);

        if (result instanceof Result.Success) {
            UserModel user = ((Result.Success<UserModel>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(user)));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void register(String name, String email, String password) {
        Map<String, Object> json = new HashMap<>();
        json.put("name", name);
        json.put("email", email);
        json.put("password", password);

        Result<UserModel> result = loginRepository.register(json);

        if (result instanceof Result.Success) {
            UserModel user = ((Result.Success<UserModel>) result).getData();
            loginResult.setValue(new LoginResult(new LoggedInUserView(user)));
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
    }

    public void loginDataChanged(String email, String password, String name) {
        if (!isEmailValid(email)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_email, null, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password, null));
        } else if ( !isNameValid(name) ) {
            loginFormState.setValue(new LoginFormState(null, null, R.string.invalid_name));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    private boolean isNameValid(String name) {
        return name != null && name.trim().length() > 2;
    }

    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}