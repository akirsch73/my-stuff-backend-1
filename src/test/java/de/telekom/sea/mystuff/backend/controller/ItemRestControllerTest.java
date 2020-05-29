package de.telekom.sea.mystuff.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;



import de.telekom.sea.mystuff.backend.Item;
import de.telekom.sea.mystuff.backend.ItemRepository;;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemRestControllerTest {

	private static final String BASE_PATH = "/api/v1/items";

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private ItemRepository repository;

	@BeforeEach
	void cleardatabase() {
		repository.deleteAll();
	}

//// UPLOAD	
//
//	@Test
//	void shouldBeAbleToUploadAnItem() {
//		// Given | Arrange
//		Item lawnMower = buildLawnMower();
//		// When | Act
//		ResponseEntity<Item> response = restTemplate.postForEntity(BASE_PATH, lawnMower, Item.class);
//		// Then | Assert
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//		assertThat(response.getBody().getId()).isNotNull();
//	}
//
//	@Test
//	void shouldReadAllItems() {
//		// Given | Arrange
//		List<Item> itemList = new ArrayList<Item>();
//		Item lawnMower = buildLawnMower();
//		Item lawnTrimmer = buildLawnTrimmer();
//		itemList.add(lawnMower);
//		itemList.add(lawnTrimmer);
//		// When | Act
//		ResponseEntity<Item[]> response = restTemplate.getForEntity(BASE_PATH, Item[].class);
//
//		// List<Item> passt nicht. Alternative Item[]
//
//		// Then | Assert
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
//
//	@Test
//	void shouldFindOneItem() {
//		// Given | Arrange
//		Item lawnMower = givenAnInsertedItem().getBody();
//		// When | Act
//		ResponseEntity<Item> response = restTemplate.getForEntity(BASE_PATH + "/" + lawnMower.getId(), Item.class);
//		// Then | Assert
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//		assertThat(response.getBody()).isEqualToComparingFieldByField(lawnMower);
//
//	}
//
//	@Test
//	void shouldFindNoItemForUnknownId() throws URISyntaxException {
//		// Given | Arrange
//		// When | Act
//		ResponseEntity<Item> response = restTemplate.getForEntity(BASE_PATH + "/1", Item.class);
//		// Then | Assert
//		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//	}
//
//	@Test
//	void shouldBeAbleToDeleteAnItem() throws URISyntaxException {
//	
//		// Given | Arrange
//		Item item = givenAnInsertedItem().getBody();
//		// When | Act
//		URI uRI = new URI (restTemplate.getRootUri() + BASE_PATH + "/"+ item.getId());
//		RequestEntity<Item> requestEntity = new RequestEntity<>(HttpMethod.DELETE, uRI);	
//		ResponseEntity<Item> deleteResponse = restTemplate.exchange(requestEntity,  Item.class);
//		ResponseEntity<Item> getResponse = restTemplate.getForEntity(BASE_PATH +"/"+item.getId(), Item.class);	
//		// Then | Assert
//		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
//		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
//		
//		
//	}
//
//	@Test
//	void shouldNotBeAbleToDeleteAnItemWithUnknownId() throws URISyntaxException {
//		// Given | Arrange
//		// When  | Act
//		URI uRI = new URI (restTemplate.getRootUri() + BASE_PATH + "/1");
//		RequestEntity<Item> requestEntity = new RequestEntity<>(HttpMethod.DELETE, uRI);
//		ResponseEntity<Item> deleteResponse = restTemplate.exchange(requestEntity,  Item.class);
//		// Then  | Assert
//		assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
//	
//	}
//
	@Test
	void shouldBeAbleToReplaceAnItem() throws URISyntaxException {

		// Given | Arrange
		Item item = givenAnInsertedItem().getBody();
		Item itemPut = buildLawnTrimmer();
		System.out.println(item.getName() + " 1");
		System.out.println(itemPut.getName());
		// When  | Act
		URI uRI = new URI (restTemplate.getRootUri() + BASE_PATH + "/"+ item.getId());
		System.out.println(uRI);
		RequestEntity<Item> requestEntity = new RequestEntity<>(itemPut, HttpMethod.PUT, uRI);
		ResponseEntity<Item> putResponse = restTemplate.exchange(requestEntity, Item.class);
		System.out.println(item.getName() + "Nach ACT");
		System.out.println(itemPut.getName());
		
		// Then  | Assert
		assertThat(putResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		System.out.println("Nach assertthat");
		System.out.println(itemPut.getDescription());
		
		itemPut.setId(item.getId());
		System.out.println(item.getDescription());
		System.out.println(itemPut.getDescription());
		assertThat(putResponse.getBody()).isEqualToComparingFieldByField(itemPut);
	}

//	@Test
//	void shouldNotBeAbleToReplaceAnItemWithUnknownId() throws URISyntaxException {
//		fail();
//	}

	private ResponseEntity<Item> givenAnInsertedItem() {
		Item item = buildLawnMower();
		return restTemplate.postForEntity(BASE_PATH, item, Item.class);
	}

	private Item buildLawnMower() {
		Item item = Item.builder().name("Lawn mower").amount(1).date(Date.valueOf("2019-05-01")).location("Basement")
				.build();
		return item;
	}

	private Item buildLawnTrimmer() {
		Item item = Item.builder().name("Lawn trimmer").amount(1).date(Date.valueOf("2018-05-01")).location("Basement")
				.build();
		return item;
	}

}
