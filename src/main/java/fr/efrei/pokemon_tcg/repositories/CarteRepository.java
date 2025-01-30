package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Carte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarteRepository extends JpaRepository<Carte, String> {
}