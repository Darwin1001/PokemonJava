package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;

import java.time.LocalDate;
import java.util.List;

public interface IDresseurService {

	List<Dresseur> findAll();
	Dresseur findById(String uuid);
	void create(DresseurDTO dresseurDTO);

	boolean update(String uuid, DresseurDTO dresseurDTO);
	boolean delete(String uuid);

	void capturerPokemon(String uuid, CapturePokemon capturePokemon);

    boolean echangerCartesEntrePaquets(String dresseurId, String carteId, boolean versPrincipal);

    boolean defierDresseur(String dresseur1Id, String dresseur2Id);

    List<Echange> getHistoriqueEchanges(LocalDate startDate, LocalDate endDate);

    List<Echange> getHistoriqueEchangesDresseur(String dresseurId, LocalDate startDate, LocalDate endDate);
}
