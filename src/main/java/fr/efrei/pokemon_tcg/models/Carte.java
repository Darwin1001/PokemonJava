package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Carte {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;
    private int rarete;
    private String attaque1;
    private String attaque2;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getRarete() {
        return rarete;
    }

    public void setRarete(int rarete) {
        this.rarete = rarete;
    }

    public String getAttaque1() {
        return attaque1;
    }

    public void setAttaque1(String attaque1) {
        this.attaque1 = attaque1;
    }

    public String getAttaque2() {
        return attaque2;
    }

    public void setAttaque2(String attaque2) {
        this.attaque2 = attaque2;
    }
}