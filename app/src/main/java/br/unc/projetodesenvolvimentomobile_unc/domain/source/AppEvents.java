package br.unc.projetodesenvolvimentomobile_unc.domain.source;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class AppEvents extends AppCompatActivity {

    FirebaseAnalytics analytics = FirebaseAnalytics.getInstance(this);

    public void sendScreen( android.app.Activity page, String name ) {
        analytics.setCurrentScreen(page, name, name);
    }

    public void globalEvent(String name, Bundle params) {
        analytics.logEvent(name, params);
    }

}
