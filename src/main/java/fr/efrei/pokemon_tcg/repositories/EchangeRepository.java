// filepath: /c:/Users/david/Desktop/Pokemon/pokemon-tcg/src/main/java/fr/efrei/pokemon_tcg/repositories/EchangeRepository.java
package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EchangeRepository extends JpaRepository<Echange, String> {
    Optional<Echange> findByDresseur1AndDresseur2AndDateEchange(String dresseur1, String dresseur2, LocalDate dateEchange);
}