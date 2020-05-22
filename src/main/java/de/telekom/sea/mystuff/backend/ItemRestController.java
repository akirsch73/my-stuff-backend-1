package de.telekom.sea.mystuff.backend;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemRestController {

	// verdrahte Controller mit Repository
	private final ItemRepository itemRepository;

	/**
	 * Warum muss das final sein?
	 */

	@Autowired // Konstruktor befüllt mit Repository
	public ItemRestController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
		/**
		 * Was bedeutet das, warum muß ich hier einen Konstruktor mit Instanzen von
		 * ItemRepository befüllen? Das ItemRepository ist doch ein Interface.
		 */
	}

	@GetMapping("/api/v1/items")
	public List<Item> findAll() {
		/**
		 * Hier kommt kein Model als Input rein - wäre Thymeleaf. Was sind Spring/ REST
		 * Annotations, was sind Thymeleaf Annotations - z.B.: "addAttribute"?
		 */
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}

	@GetMapping("/api/v1/items/{id}")
	public Optional<Item> getItem(@PathVariable Long id) {
		/**
		 * was ist der Unterschied zwischen @PathVariable und Requestparam?
		 */
		Optional<Item> item;
		/**
		 * warum funktioniert das nur mit Optional<Item>??? Nicht mit Item item = new
		 * Item();
		 */
		item = itemRepository.findById(id);// getOne(id);
		return item;

	}

	@PostMapping("/api/v1/items")
	public Item add(@RequestBody Item item) {
		/**
		 * Während ein GetMapping keine Request Body besitzt, hat ein PostMapping einen
		 * solchen. Dieser wird von Json zusammengefasst. Über den Browser kann ich nur
		 * einen GetRequest absetzen. Andere Requests müssen über CURL oder POSTMAN oder
		 * SWAGGER abgesetzt werden.
		 */
		item = itemRepository.save(item);

		return item;
	}

	@PutMapping("/api/v1/items/{id}")
	public Item editItem(@RequestParam Long id, @RequestBody Item itemNew) {

		Item item = itemRepository.findById(id).get();
		item.setName(itemNew.getName());
		item.setAmount(itemNew.getAmount());
		item.setLocation(itemNew.getLocation());
		item.setDescription(itemNew.getDescription());
		item.setDate(itemNew.getDate());
		itemRepository.save(item);

		/**
		 * Die unten stehenden Zeilen referenzieren auf ein Lambda-Konstrukt.
		 */

//		itemNew -> {
//			itemNew.setName(item.getName());
//			itemNew.setAmount(item.getAmount());
//			itemNew.setLocation(item.getLocation());
//			itemNew.setDescription(item.getDescription());
//			itemNew.setDate(item.getDate());
//			return itemRepository.save(itemNew);
//		}
//		new Supplier<Item>() {
//
//			@Override
//			public Item get() {
//				itemNew.setName(item.getName());
//				itemNew.setAmount(item.getAmount());
//				itemNew.setLocation(item.getLocation());
//				itemNew.setDescription(item.getDescription());
//				itemNew.setDate(item.getDate());
//				return itemRepository.save(itemNew);
//			}
//		};

		return item;

	}

	@DeleteMapping("/api/v1/items/{id}")
	public List<Item> delete(Item item) {

		itemRepository.deleteById(item.getId());
		List<Item> itemList = itemRepository.findAll();
		return itemList;
	}
}
