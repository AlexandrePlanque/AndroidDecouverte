package tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.entities.Eleve;


public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_apprenant);


        int id = getIntent().getExtras().getInt("id");




        // on recupere une RequestQueue venant de Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        // initialisation de l'url
        String url = "http://192.168.1.10/beWeb/api_beweb/index.php/api/eleves/" + id;

        // inititialisation d'un tableau au format JsonArray
        JSONArray jArray = new JSONArray();

        // instance d'un ResponseListennerJsonArray
        Response.Listener<JSONObject> response = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                afficheInfos(response);
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
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET,url,null,response,error);
        // a jouter  dans la RequestQueue l'objet JsonArrayRequest
        queue.add(jsonArrayRequest);
        // afficher en console le nom du premier objet


    }

    private void afficheInfos(JSONObject eleve){
        TextView entete = (TextView) findViewById(R.id.entete);
        TextView nom = (TextView) findViewById(R.id.nom_detail);
        TextView prenom = (TextView) findViewById(R.id.prenom_detail);
        //TextView age = (TextView) findViewById(R.id.age);
        TextView stat = (TextView) findViewById(R.id.status_detail);

        entete.setText("Fiche de " + eleve.optString("nom") + " " + eleve.optString("prenom"));
        nom.setText(eleve.optString("nom"));
        prenom.setText(eleve.optString("prenom"));
        stat.setText(eleve.optString("ville"));
    }
}
