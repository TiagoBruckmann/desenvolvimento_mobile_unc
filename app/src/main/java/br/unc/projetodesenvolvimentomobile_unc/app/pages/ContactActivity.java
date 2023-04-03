package br.unc.projetodesenvolvimentomobile_unc.app.pages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import br.unc.projetodesenvolvimentomobile_unc.R;
import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        setContentView(aboutPage());
    }

    View aboutPage() {
        return new AboutPage(this)
            .isRTL(false)
            .setDescription("Veja todas as nossas informações de contato!")
            .addGroup("Informações de contato")
            .addEmail("tiagobruckmann@gmail.com", "Envie-nos um e-mail")
            .addWebsite("https://tiagobruckmann.dev/", "Acesse nosso site")
            .addGroup("Redes sociais")
            .addFacebook("tiagoandre.bruckmann", "Siga-nos no Facebook")
            .addYoutube("UC49Ynz5L2J3b9Xy389VfZnQ", "Canal do youtube")
            .addPlayStore("https://play.google.com/store/apps/developer?id=Tiago+Br%C3%BCckmann", "Veja nossos apps")
            .addGitHub("TiagoBruckmann", "Siga-nos no GitHub")
            .addInstagram("tiago_bruckk", "Siga-nos no instagram")
            .create();
    }
}