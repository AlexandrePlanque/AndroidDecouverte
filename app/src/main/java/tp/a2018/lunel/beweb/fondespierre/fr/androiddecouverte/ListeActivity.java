package tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.adapters.ListeElevesAdapter;
import tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.entities.Eleve;

public class ListeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //bind de la vue avec le controler
        setContentView(R.layout.liste_apprenant);


        //ListView liste = (ListView)findViewById(R.id.liste_dynamic);


        //création de la liste des items à afficher dans la listeview
        List<String> items = new ArrayList<>(); //objet de type ArrayList
        //peuplage de la liste avec une boucle
        for(int i=0; i<100; i++){
            items.add("item"+i);

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.template_item_eleve,items);
        //liste.setAdapter(adapter);

        //création de la liste des eleves ( normalement issue d'une requête sur API web
        ArrayList<Eleve> ListeEleves = new ArrayList<>();

        Eleve eleve = (new Eleve("Hallot", "Thiery", "stage"));
        eleve.addSkill("html").addSkill("jQuery").addSkill("css").addSkill("php");

        Eleve eleveB = (new Eleve("Canehan", "Fabien", "formation"));
        eleveB.addSkill("html").addSkill("css").addSkill("javascript").addSkill("php");

        Eleve eleveC = (new Eleve("Lavery", "Cédric", "404"));
        eleveC.addSkill("python").addSkill("monty");

        //ajout de chaque eleve à la liste
        ListeEleves.add(eleve);
        ListeEleves.add(eleveB);
        ListeEleves.add(eleveC);


        //On instancie un customAdapter qui prend le template custom ainsi que la liste des eleves
       // ListeElevesAdapter elevesAdapter = new ListeElevesAdapter(getApplicationContext(), R.layout.template_item_eleve, ListeEleves);

        //on envoi le customAdapter dans la ListView pour fabriquer le rendu
     //   liste.setAdapter(elevesAdapter);






        // on recupere une RequestQueue venant de Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        // initialisation de l'url
        String url = "http://192.168.1.10/beWeb/api_beweb/index.php/api/eleves";

        // inititialisation d'un tableau au format JsonArray (donnees a envoyer sur le serveur)
        JSONArray jArray = new JSONArray();

        // instance d'un ResponseListennerJsonArray
        Response.Listener<JSONArray> response = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    loadDatas(response);

            }
        };
        // instance d'un ResponseErrorListenner
        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("erreur");
            }
        };
        // instance d'un JsonArrayRequest en passant en argument les objets créés précédemment
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,jArray,response,error);
        // a jouter  dans la RequestQueue l'objet JsonArrayRequest
        queue.add(jsonArrayRequest);
        // afficher en console le nom du premier objet



/**
 *  Utilisation de deux adapter dédiés à l'affichage correct des spinners afin de leur ajouter
 *  les champs souhaiter pour chacuns d'entre eux
 *
 *  */

        Spinner spinner = (Spinner) findViewById(R.id.spinner4);

        // On instancie un ArrayAdapter utilisant une string array ainsi que le layout des spinner par défaut
        ArrayAdapter<CharSequence> adapterSpin = ArrayAdapter.createFromResource(this,
                R.array.spinner_choice_1, android.R.layout.simple_spinner_item);
        // On précise ici quel layout utiliser lorsque qu'un utilisateur va cliquer sur le spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Finalement on applique l'adapter à notre spinner
        spinner.setAdapter(adapterSpin);


        Spinner spinner2 = (Spinner) findViewById(R.id.spinner5);

        // On instancie un ArrayAdapter utilisant une string array ainsi que le layout des spinner par défaut
        ArrayAdapter<CharSequence> adapterSpinTwo = ArrayAdapter.createFromResource(this,
                R.array.spinner_choice_2, android.R.layout.simple_spinner_item);
        // On précise ici quel layout utiliser lorsque qu'un utilisateur va cliquer sur le spinner.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Finalement on applique l'adapter à notre spinner
        spinner2.setAdapter(adapterSpinTwo);









        ListView listeb = (ListView)findViewById(R.id.liste_dynamic);

        //on aasigne un listener sur le click des items pour changer d'activité (DetailActivity)
        listeb.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on va envoyé l'eleve séléctionné vers DetailActivity
                Eleve e = (Eleve)parent.getItemAtPosition(position);

                Intent j = new Intent(getApplicationContext(), DetailActivity.class);
                j.putExtra("apprenant", e);

                startActivity(j);
            }
        });

    }

    private void loadDatas(JSONArray datas) {

        ListView liste = (ListView)findViewById(R.id.liste_dynamic);

        //on aasigne un listener sur le click des items pour changer d'activité (DetailActivity)
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //on va envoyé l'eleve séléctionné vers DetailActivity
                JSONObject e = (JSONObject) parent.getItemAtPosition(position);

                Intent j = new Intent(getApplicationContext(), DetailActivity.class);
                j.putExtra("id", e.optInt("id"));
                startActivity(j);
            }
        });


        //On instancie un customAdapter qui prend le template custom ainsi que la liste des eleves
        ListeElevesAdapter elevesAdapterbis = new ListeElevesAdapter(getApplicationContext(), R.layout.template_item_eleve, datas);

        //on envoi le customAdapter dans la ListView pour fabriquer le rendu
        liste.setAdapter(elevesAdapterbis);


    }

}
