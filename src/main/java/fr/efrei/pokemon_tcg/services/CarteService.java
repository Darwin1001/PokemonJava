package fr.efrei.pokemon_tcg.services;

import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.repositories.CarteRepository;
import fr.efrei.pokemon_tcg.repositories.EchangeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CarteService {

    private final CarteRepository carteRepository;
    private final EchangeRepository echangeRepository;

    public CarteService(CarteRepository carteRepository, EchangeRepository echangeRepository) {
        this.carteRepository = carteRepository;
        this.echangeRepository = echangeRepository;
    }

    public List<Carte> tirerCartes() {
        List<Carte> cartes = new ArrayList<>();
        Random random = new Random();
        String[] noms = {"Pikachu", "Bulbasaur", "Charmander", "Squirtle", "Eevee"};
        String[] attaques = {"Thunder Shock", "Vine Whip", "Flamethrower", "Water Gun", "Quick Attack"};

        for (int i = 0; i < 5; i++) {
            Carte carte = new Carte();
            carte.setNom(noms[random.nextInt(noms.length)]);
            carte.setAttaque1(attaques[random.nextInt(attaques.length)]);
            carte.setAttaque2(attaques[random.nextInt(attaques.length)]);
            carte.setRarete(genererRarete(random));
            cartes.add(carte);
            carteRepository.save(carte);
        }

        return cartes;
    }

    private int genererRarete(Random random) {
        int rarete = random.nextInt(100) + 1;
        if (rarete <= 50) {
            return 1;
        } else if (rarete <= 75) {
            return 2;
        } else if (rarete <= 90) {
            return 3;
        } else if (rarete <= 98) {
            return 4;
        } else {
            return 5;
        }
    }

    public boolean echangerCartes(String dresseur1, String dresseur2) {
        LocalDate today = LocalDate.now();
        Optional<Echange> echange = echangeRepository.findByDresseur1AndDresseur2AndDateEchange(dresseur1, dresseur2, today);

        if (echange.isPresent()) {
            return false; // Échange déjà effectué aujourd'hui
        }

        Echange newEchange = new Echange();
        newEchange.setDresseur1(dresseur1);
        newEchange.setDresseur2(dresseur2);
        newEchange.setDateEchange(today);
        echangeRepository.save(newEchange);

        return true;
    }
}