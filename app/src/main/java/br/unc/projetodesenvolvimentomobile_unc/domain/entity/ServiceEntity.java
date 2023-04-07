package br.unc.projetodesenvolvimentomobile_unc.domain.entity;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import br.unc.projetodesenvolvimentomobile_unc.domain.source.Crash;

public class ServiceEntity {

    String name, email, service, observation;

    public ServiceEntity(String name, String email, String service, String observation) {
        setName(name);
        setEmail(email);
        setService(service);
        setObservation(observation);
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

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getObservation() {
        return observation;
    }

    public Map<String, Object> toJson() {
        Date time = new Date();
        Map<String, Object> map = new HashMap<>();
        map.put("name", getName());
        map.put("email", getEmail());
        map.put("service", getService());
        map.put("observation", getObservation());
        map.put("create_at", time.getTime());

        return map;
    }

    public void sendToFirebase( Map<String, Object> params ) {
        Crash crash = new Crash();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("services")
            .add(params)
            .addOnSuccessListener(
                success -> Log.i("sucesso => ", "ihuull")
            )
            .addOnFailureListener(
                failure -> {
                    crash.log(failure.getMessage());
                }
            );
    }
}
