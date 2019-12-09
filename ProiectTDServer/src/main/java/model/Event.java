package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the events database table.
 * 
 */
@Entity
@Table(name="events")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idevent;

	private String codLocatie;

	private String file;

	private String name;

	public Event() {
	}

	public int getIdevent() {
		return this.idevent;
	}

	public void setIdevent(int idevent) {
		this.idevent = idevent;
	}

	public String getCodLocatie() {
		return this.codLocatie;
	}

	public void setCodLocatie(String codLocatie) {
		this.codLocatie = codLocatie;
	}

	public String getFile() {
		return this.file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}