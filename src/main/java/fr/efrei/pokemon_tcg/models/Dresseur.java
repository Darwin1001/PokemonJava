package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Dresseur {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String nom;
    private String prenom;
    private LocalDateTime deletedAt;

    @OneToMany
    private List<Pokemon> pokemonList;

    @OneToMany
    private List<Carte> paquetPrincipal;

    @OneToMany
    private List<Carte> paquetSecondaire;

    // Getters and Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public List<Pokemon> getPokemonList() {
        return pokemonList;
    }

    public void setPokemonList(List<Pokemon> pokemonList) {
        this.pokemonList = pokemonList;
    }

    public List<Carte> getPaquetPrincipal() {
        return paquetPrincipal;
    }

    public void setPaquetPrincipal(List<Carte> paquetPrincipal) {
        this.paquetPrincipal = paquetPrincipal;
    }

    public List<Carte> getPaquetSecondaire() {
        return paquetSecondaire;
    }

    public void setPaquetSecondaire(List<Carte> paquetSecondaire) {
        this.paquetSecondaire = paquetSecondaire;
    }
}
