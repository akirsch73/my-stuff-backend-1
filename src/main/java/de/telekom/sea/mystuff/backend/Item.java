package de.telekom.sea.mystuff.backend;

import java.sql.Date;

/**
 * Beachte beim Datentyp Date den Import von java.SQL.Date - das Datum wird das String im Format "YYYY-MM-DD" notiert.
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * Beachte die verschiedenen Annotationen. Besonders wichtig sind @Id
 * und @GeneratedValue
 * 
 * @author sea29
 *
 */

@Getter
@Setter
@Entity
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private int amount;
	private String location;
	private String description;
	private Date date;

	public Item(Long id, String name, int amount, String location, String description, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.location = location;
		this.description = description;
		this.date = date;
	}

	public Item() {
	}

}
