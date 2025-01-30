package fr.efrei.pokemon_tcg.services.implementations;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.models.Pokemon;
import fr.efrei.pokemon_tcg.repositories.DresseurRepository;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import fr.efrei.pokemon_tcg.services.IPokemonService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DresseurServiceImpl implements IDresseurService {

	private final DresseurRepository dresseurRepository;
	private final IPokemonService pokemonService;
	private final EchangeRepository echangeRepository;

	public DresseurServiceImpl(DresseurRepository dresseurRepository, PokemonServiceImpl pokemonService, EchangeRepository echangeRepository) {
		this.dresseurRepository = dresseurRepository;
		this.pokemonService = pokemonService;
		this.echangeRepository = echangeRepository;
	}

	@Override
	public List<Dresseur> findAll() {
		return dresseurRepository.findAllByDeletedAtNull();
	}

	@Override
	public Dresseur findById(String uuid) {
		return dresseurRepository.findById(uuid).orElse(null);
	}

	public void capturerPokemon(String uuid, CapturePokemon capturePokemon) {
		Dresseur dresseur = findById(uuid);
		Pokemon pokemon = pokemonService.findById(capturePokemon.getUuid());
		dresseur.getPokemonList().add(pokemon);
		dresseurRepository.save(dresseur);
	}

	@Override
	public void create(DresseurDTO dresseurDTO) {
		Dresseur dresseur = new Dresseur();
		dresseur.setNom(dresseurDTO.getNom());
		dresseur.setPrenom(dresseurDTO.getPrenom());
		dresseur.setDeletedAt(null);
		dresseurRepository.save(dresseur);
	}

	@Override
	public boolean update(String uuid, DresseurDTO dresseurDTO) {
		return false;
	}

	@Override
	public boolean delete(String uuid) {
		Dresseur dresseur = findById(uuid);
		dresseur.setDeletedAt(LocalDateTime.now());
		dresseurRepository.save(dresseur);
		return true;
	}

	@Override
	public boolean echangerCartesEntrePaquets(String dresseurId, String carteId, boolean versPrincipal) {
		Optional<Dresseur> dresseurOpt = dresseurRepository.findById(dresseurId);
		if (dresseurOpt.isPresent()) {
			Dresseur dresseur = dresseurOpt.get();
			if (versPrincipal) {
				Optional<Carte> carteOpt = dresseur.getPaquetSecondaire().stream().filter(c -> c.getUuid().equals(carteId)).findFirst();
				if (carteOpt.isPresent()) {
					Carte carte = carteOpt.get();
					dresseur.getPaquetSecondaire().remove(carte);
					dresseur.getPaquetPrincipal().add(carte);
					dresseurRepository.save(dresseur);
					return true;
				}
			} else {
				Optional<Carte> carteOpt = dresseur.getPaquetPrincipal().stream().filter(c -> c.getUuid().equals(carteId)).findFirst();
				if (carteOpt.isPresent()) {
					Carte carte = carteOpt.get();
					dresseur.getPaquetPrincipal().remove(carte);
					dresseur.getPaquetSecondaire().add(carte);
					dresseurRepository.save(dresseur);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean defierDresseur(String dresseur1Id, String dresseur2Id) {
		Optional<Dresseur> dresseur1Opt = dresseurRepository.findById(dresseur1Id);
		Optional<Dresseur> dresseur2Opt = dresseurRepository.findById(dresseur2Id);

		if (dresseur1Opt.isPresent() && dresseur2Opt.isPresent()) {
			Dresseur dresseur1 = dresseur1Opt.get();
			Dresseur dresseur2 = dresseur2Opt.get();

			// Simuler le combat (ici, on choisit aléatoirement le gagnant)
			boolean dresseur1Gagne = Math.random() > 0.5;

			if (dresseur1Gagne) {
				return transfererMeilleureCarte(dresseur2, dresseur1);
			} else {
				return transfererMeilleureCarte(dresseur1, dresseur2);
			}
		}
		return false;
	}

	private boolean transfererMeilleureCarte(Dresseur perdant, Dresseur gagnant) {
		Optional<Carte> meilleureCarteOpt = perdant.getPaquetPrincipal().stream()
				.max(Comparator.comparingInt(Carte::getRarete));

		if (meilleureCarteOpt.isPresent()) {
			Carte meilleureCarte = meilleureCarteOpt.get();
			perdant.getPaquetPrincipal().remove(meilleureCarte);
			gagnant.getPaquetPrincipal().add(meilleureCarte);
			dresseurRepository.save(perdant);
			dresseurRepository.save(gagnant);
			return true;
		}
		return false;
	}

	@Override
	public List<Echange> getHistoriqueEchanges(LocalDate startDate, LocalDate endDate) {
		// Créer des échanges fictifs pour tester
		Echange echange1 = new Echange();
		echange1.setUuid("1");
		echange1.setDresseur1("dresseur1_id");
		echange1.setDresseur2("dresseur2_id");
		echange1.setDateEchange(LocalDate.of(2025, 1, 15));

		Echange echange2 = new Echange();
		echange2.setUuid("2");
		echange2.setDresseur1("dresseur1_id");
		echange2.setDresseur2("dresseur3_id");
		echange2.setDateEchange(LocalDate.of(2025, 1, 20));

		List<Echange> echanges = Arrays.asList(echange1, echange2);
		System.out.println("Echanges fictifs créés: " + echanges.size());
		return echanges;
	}

	@Override
	public List<Echange> getHistoriqueEchangesDresseur(String dresseurId, LocalDate startDate, LocalDate endDate) {
		return echangeRepository.findByDresseur1OrDresseur2AndDateEchangeBetween(dresseurId, dresseurId, startDate, endDate);
	}
}
