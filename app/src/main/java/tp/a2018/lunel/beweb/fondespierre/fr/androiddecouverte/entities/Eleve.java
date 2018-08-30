package tp.a2018.lunel.beweb.fondespierre.fr.androiddecouverte.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Eleve implements Serializable {
    private String nom;
    private String prenom;
    private List<String> skills;
    private String status;

    public Eleve(String nom, String prenom, String status) {
        this.nom = nom;
        this.prenom = prenom;
        this.status = status;
        this.skills = new ArrayList();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Eleve addSkill(String skill){
        this.skills.add(skill);
        return this;
    }


}
