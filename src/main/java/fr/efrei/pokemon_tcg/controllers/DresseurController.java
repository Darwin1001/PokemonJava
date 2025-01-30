package fr.efrei.pokemon_tcg.controllers;

import fr.efrei.pokemon_tcg.dto.CapturePokemon;
import fr.efrei.pokemon_tcg.dto.DresseurDTO;
import fr.efrei.pokemon_tcg.models.Dresseur;
import fr.efrei.pokemon_tcg.models.Echange;
import fr.efrei.pokemon_tcg.services.IDresseurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/dresseurs")
public class DresseurController {

    private final IDresseurService dresseurService;

    public DresseurController(IDresseurService dresseurService) {
        this.dresseurService = dresseurService;
    }

    @GetMapping
    public ResponseEntity<List<Dresseur>> findAll() {
        return new ResponseEntity<>(dresseurService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DresseurDTO dresseurDTO) {
        dresseurService.create(dresseurDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/echanger-paquets")
    public ResponseEntity<String> echangerCartesEntrePaquets(@RequestParam String dresseurId, @RequestParam String carteId, @RequestParam boolean versPrincipal) {
        boolean success = dresseurService.echangerCartesEntrePaquets(dresseurId, carteId, versPrincipal);
        if (success) {
            return new ResponseEntity<>("Échange entre paquets réussi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Échange entre paquets échoué", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/defier")
    public ResponseEntity<String> defierDresseur(@RequestParam String dresseur1Id, @RequestParam String dresseur2Id) {
        boolean success = dresseurService.defierDresseur(dresseur1Id, dresseur2Id);
        if (success) {
            return new ResponseEntity<>("Défi réussi", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Défi échoué", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/echanges")
    public ResponseEntity<List<Echange>> getHistoriqueEchanges(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Echange> echanges = dresseurService.getHistoriqueEchanges(startDate, endDate);
        return new ResponseEntity<>(echanges, HttpStatus.OK);
    }

    @GetMapping("/echanges/{dresseurId}")
    public ResponseEntity<List<Echange>> getHistoriqueEchangesDresseur(@PathVariable String dresseurId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        List<Echange> echanges = dresseurService.getHistoriqueEchangesDresseur(dresseurId, startDate, endDate);
        return new ResponseEntity<>(echanges, HttpStatus.OK);
    }
}
