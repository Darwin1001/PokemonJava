package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import fr.efrei.pokemon_tcg.services.implementations.PokemonServiceImpl;
import org.apache.coyote.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {
	private final IPokemonService pokemonService;

	public PokemonController(PokemonServiceImpl pokemonService) {
		this.pokemonService = pokemonService;
	}

	@GetMapping
	public ResponseEntity<List<Pokemon>> getAll() {
		// SELECT * from pokemon;
		return new ResponseEntity<>(pokemonService.findAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> createPokemon(@RequestBody Pokemon pokemon) {
		// INSERT INTO pokemon(nom, niveau, type) VALUES (pokemon.nom, pokemon.niveau, pokemon.type);
		pokemonService.create(pokemon);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	// DEVELOPPER les méthodes pour supprimer et modifier un pokemon
}
