// filepath: /c:/Users/david/Desktop/Pokemon/pokemon-tcg/src/main/java/fr/efrei/pokemon_tcg/models/Echange.java
package fr.efrei.pokemon_tcg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Echange {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String dresseur1;
    private String dresseur2;
    private LocalDate dateEchange;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDresseur1() {
        return dresseur1;
    }

    public void setDresseur1(String dresseur1) {
        this.dresseur1 = dresseur1;
    }

    public String getDresseur2() {
        return dresseur2;
    }

    public void setDresseur2(String dresseur2) {
        this.dresseur2 = dresseur2;
    }

    public LocalDate getDateEchange() {
        return dateEchange;
    }

    public void setDateEchange(LocalDate dateEchange) {
        this.dateEchange = dateEchange;
    }
}