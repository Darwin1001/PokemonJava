package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.PokemonRepository;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonServiceImpl implements IPokemonService {

	private final PokemonRepository repository;

	public PokemonServiceImpl(PokemonRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Pokemon> findAll() {
		return repository.findAll();
	}

	@Override
	public void create(Pokemon pokemon) {
		repository.save(pokemon);
	}
}
