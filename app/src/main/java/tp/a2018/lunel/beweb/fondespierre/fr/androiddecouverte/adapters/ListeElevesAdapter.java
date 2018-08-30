package tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.R;
import tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.entities.Eleve;

public class ListeElevesAdapter extends ArrayAdapter {
    private Context activity;
    private int template;
    private JSONArray eleves;

    public ListeElevesAdapter(@NonNull Context context, int resource, @NonNull JSONArray objects){
        super(context, resource);
        this.activity = context;
        this.template = template;
        this.eleves = objects;


    }

    @Override
    public int getCount() {
        return eleves.length();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        Object o = null;
        try{
            o = eleves.get(position);
        } catch( JSONException e){

        }





        return o;
    }

    //@Override
    //public int getPosition(@Nullable Object item) {


        //return eleves.get(item);
        //return eleves.;
    //}

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //faire un clonage du template à appliquer à chaque item
        LayoutInflater inflater = LayoutInflater.from(this.activity);
        convertView = inflater.inflate(R.layout.template_item_eleve, null);

        //on recupere chaque champ de notre template
        TextView champNom = convertView.findViewById(R.id.nom);
        TextView champStatut = convertView.findViewById(R.id.status);
        TextView champPrenom = convertView.findViewById(R.id.prenom);

        TextView champX = convertView.findViewById(R.id.first_skill);
        TextView champSkills = convertView.findViewById(R.id.liste_skill);

        //on recupere l'eleve courant a afficher dans la liste grace a position avant d'assigner les valeurs dans les champ adéquats

        //Eleve eleve = this.eleves.get(position);
        JSONObject eleve = null;
        try {
            eleve = this.eleves.getJSONObject(position);
        //String nom  = eleve.getString("nom");
        champStatut.setText(eleve.getString("nom"));
        champPrenom.setText(eleve.getString("prenom"));
        champNom.setText(eleve.getString("nom"));
        champX.setText(eleve.getString("ville"));
        champSkills.setText(eleve.getString("genre"));
        //champX.setText(eleve.getSkills().get(0));
        } catch (JSONException e) {
            e.printStackTrace();
        }


       // List<String> skills =  eleve.getSkills();
       // String listSkill = "";

       // for(int i = 0; i < skills.size(); i++ ){
       //     listSkill += skills.get(i) + " ";
       // }

        //champSkills.setText(listSkill);

        //On retourne la vue traitée
        return convertView;
    }
}
