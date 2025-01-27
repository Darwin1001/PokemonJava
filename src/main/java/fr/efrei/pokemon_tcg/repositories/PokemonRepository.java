package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, String> {
}
