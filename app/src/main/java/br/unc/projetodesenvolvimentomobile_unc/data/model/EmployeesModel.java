package br.unc.projetodesenvolvimentomobile_unc.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class EmployeesModel {

    int id;
    String name, age, function;

    public EmployeesModel( JSONObject json ) throws JSONException {
        setId(json.getInt("id"));
        setName(json.getString("name"));
        setAge(json.getInt("age") + " anos");
        setFunction( json.getString("function"));
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAge() {
        return age;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunction() {
        return function;
    }
}
