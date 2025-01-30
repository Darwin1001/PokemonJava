
package fr.efrei.pokemon_tcg.repositories;

import fr.efrei.pokemon_tcg.models.Echange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EchangeRepository extends JpaRepository<Echange, String> {
    Optional<Echange> findByDresseur1AndDresseur2AndDateEchange(String dresseur1, String dresseur2, LocalDate dateEchange);
    List<Echange> findByDateEchangeBetween(LocalDate startDate, LocalDate endDate);
    List<Echange> findByDresseur1OrDresseur2AndDateEchangeBetween(String dresseur1, String dresseur2, LocalDate startDate, LocalDate endDate);
}