package br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login;

import javax.annotation.Nullable;

class LoginFormState {
    @Nullable
    private final Integer emailError;
    @Nullable
    private final Integer passwordError;
    @Nullable
    private final Integer nameError;
    private boolean isValidLogin;
    private final boolean isValidRegister;

    LoginFormState(@Nullable Integer emailError, @Nullable Integer passwordError, @Nullable Integer nameError) {
        this.emailError = emailError;
        this.passwordError = passwordError;
        this.nameError = nameError;
        this.isValidRegister = false;
        this.isValidLogin = false;
        if ( emailError == null && passwordError == null ) {
            this.isValidLogin = true;
        }
    }

    LoginFormState(boolean isDataValid) {
        this.emailError = null;
        this.passwordError = null;
        this.nameError = null;
        this.isValidLogin = isDataValid;
        this.isValidRegister = isDataValid;
    }

    @Nullable
    Integer getEmailError() {
        return emailError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getNameError() {return  nameError;}

    boolean isLoginValid() {
        return isValidLogin;
    }

    boolean isRegisterValid() {
        return isValidRegister;
    }
}