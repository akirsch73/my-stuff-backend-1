package de.telekom.sea.mystuff.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import de.telekom.sea.mystuff.backend.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findById(Long id);
/**
 * Was macht ein Respository überhaupt?
 * Wieso habe ich im Repository ein Interface?
 * Wieso muß ich noch findById hinzufügen? Wundert mich, dass es das noch nicht gibt.
 */
}
