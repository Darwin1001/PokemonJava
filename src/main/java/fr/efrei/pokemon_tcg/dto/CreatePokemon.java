package fr.efrei.pokemon_tcg.dto;

import fr.efrei.pokemon_tcg.constants.TypePokemon;

public class CreatePokemon {

	private String nom;

	private Integer niveau;

	private TypePokemon type;

	public String getNom() {
		return nom;
	}

	public Integer getNiveau() {
		return niveau;
	}

	public TypePokemon getType() {
		return type;
	}
}
