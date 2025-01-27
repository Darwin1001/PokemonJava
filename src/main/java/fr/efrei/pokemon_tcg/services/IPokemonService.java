package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Pokemon;

import java.util.List;

public interface IPokemonService {

	List<Pokemon> findAll();

	void create(Pokemon pokemon);
}
