package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.models.Carte;
import fr.efrei.pokemon_tcg.services.CarteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cartes")
public class CarteController {

    private final CarteService carteService;

    public CarteController(CarteService carteService) {
        this.carteService = carteService;
    }

    @GetMapping("/tirer")
    public ResponseEntity<List<Carte>> tirerCartes() {
        List<Carte> cartes = carteService.tirerCartes();
        return new ResponseEntity<>(cartes, HttpStatus.OK);
    }

    @PostMapping("/echanger")
    public ResponseEntity<String> echangerCartes(@RequestParam String dresseur1, @RequestParam String dresseur2) {
        boolean success = carteService.echangerCartes(dresseur1, dresseur2);
        if (success) {
            return new ResponseEntity<>("Échange réussi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Échange déjà effectué aujourd'hui", HttpStatus.BAD_REQUEST);
        }
    }
}