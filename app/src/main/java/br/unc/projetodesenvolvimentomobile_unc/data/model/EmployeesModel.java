package br.unc.projetodesenvolvimentomobile_unc.data.model;

import java.util.Map;

public class EmployeesModel {

    int id, age;
    String name, function;

    public EmployeesModel( Map<String, Object> json ) {
        setId((Integer) json.get("id"));
        setName((String) json.get("name"));
        setAge((Integer) json.get("age"));
        setFunction((String) json.get("function"));
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
