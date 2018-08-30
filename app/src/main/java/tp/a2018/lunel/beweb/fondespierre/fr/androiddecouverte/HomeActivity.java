package tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class HomeActivity extends AppCompatActivity {
    //final Intent intent = getParentActivityIntent();
    public HomeActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        //On récupere la reference du composant button avec l'id go
        Button b = (Button) findViewById(R.id.go);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // a defaut d'utiliser this pour referencer l'objet courant  qu'on considere etre le bouton
                // l'objet Listener passer en argument implemente la méthode onClick qui prend en argument
                // un objet de type de la super classe (View) le bouton est une spécialisation de la classe View
                Button b = (Button) v; // on caste l'objet v en Button

                EditText c = (EditText) findViewById(R.id.editText);
                String retour = c.getText().toString();

                TextView d = (TextView) findViewById(R.id.textView);
                d.setText(retour);

                String texte = b.getText().toString();
                System.err.println("le bouton à été clické " + texte + "\n" + retour);
                Intent j = new Intent(getApplicationContext(), ListeActivity.class);

                //Intent i = new Intent(activity, ListeActivity.class);
                startActivity(j);
            }
        });

        EditText edit = (EditText)findViewById(R.id.editText);


        edit.setOnKeyListener(new EditText.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                TextView e = (TextView) findViewById(R.id.textView);
                e.setText(((EditText)v).getText().toString());
                System.err.println( ( (EditText)v).getText().toString() );
                return false;
            }
        });
    }

    public void testRequest() {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.10/beWeb/api_beweb/index.php/api/test/message";
        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
            }
        };

        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("erreur");
            }
        };
        StringRequest request = new StringRequest(Request.Method.GET,url,response,error);
        queue.add(request);
    }



    public void testJsonRequest(){

        // on recupere une RequestQueue venant de Volley
        RequestQueue queue = Volley.newRequestQueue(this);

        // initialisation de l'url
        String url = "http://192.168.1.10/beWeb/api_beweb/index.php/api/test/datas";

        // inititialisation d'un tableau au format JsonArray
        JSONArray jArray = new JSONArray();

        // instance d'un ResponseListennerJsonArray
        Response.Listener<JSONArray> response = new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONObject o = response.getJSONObject(0);
                    String username = o.getString("nom");
                    System.err.println(username);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    }
};

