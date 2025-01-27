package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.constants.TypePokemon;
import fr.efrei.pokemon_tcg.models.Pokemon;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	public Pokemon greetings() {
		Pokemon salameche = new Pokemon();
		salameche.setNiveau(10);
		salameche.setNom("Robert");
		salameche.setType(TypePokemon.FEU);
		return salameche;
	}

	@GetMapping("/{prenom}")
	public String greetingsName(@PathVariable String prenom) {
		return "Hello " + prenom;
	}
}
