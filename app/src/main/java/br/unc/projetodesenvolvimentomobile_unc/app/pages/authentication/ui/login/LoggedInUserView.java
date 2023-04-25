package br.unc.projetodesenvolvimentomobile_unc.app.pages.authentication.ui.login;

import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.data.model.UserModel;

class LoggedInUserView {
    private final UserModel userModel;

    LoggedInUserView(UserModel user) {
        this.userModel = user;
    }

    String getDisplayName() {
        return userModel.getName();
    }

    String getUid() {
        return userModel.getId();
    }

    Map<String, Object> userToJson() {
        return userModel.toJson();
    }
}