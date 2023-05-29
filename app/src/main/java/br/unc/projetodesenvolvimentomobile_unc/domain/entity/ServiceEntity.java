package br.unc.projetodesenvolvimentomobile_unc.domain.entity;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ServiceEntity {

    String userId, name, email, service, observation;

    public ServiceEntity(String userId, String name, String email, String service, String observation) {
        setUserId(userId);
        setName(name);
        setEmail(email);
        setService(service);
        setObservation(observation);
    }

    public void setUserId(String userId) { this.userId = userId; }

    public String getUserId() { return userId; }

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
        Date time = Calendar.getInstance().getTime();
        Map<String, Object> map = new HashMap<>();
        map.put("uid", generateUid());
        map.put("user_id", getUserId());
        map.put("name", getName());
        map.put("email", getEmail());
        map.put("service", getService());
        map.put("observation", getObservation());
        map.put("create_at", time.toString());

        return map;
    }

    static String generateUid() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("_", "").replaceAll("-", "");
    }

}
