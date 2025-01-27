package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

	final PokemonRepository repository;
	public PokemonController(PokemonRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public List<Pokemon> getAll() {
		return repository.findAll();
	}
}
