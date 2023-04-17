package br.unc.projetodesenvolvimentomobile_unc.data.model;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UserModel {

    String id, name, email;

    public UserModel( String id, String name, String email ) {
        setId(id);
        setName(name);
        setEmail(email);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Map<String, Object> toJson() {
        Date time = Calendar.getInstance().getTime();
        Map<String, Object> json = new HashMap<>();
        json.put("id", getId());
        json.put("name", getName());
        json.put("email", getEmail());
        json.put("created_at", time);
        return json;
    }
}
