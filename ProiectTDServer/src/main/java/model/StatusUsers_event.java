package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the `status_users&events` database table.
 * 
 */
@Entity
@Table(name="`status_users&events`")
@NamedQuery(name="StatusUsers_event.findAll", query="SELECT s FROM StatusUsers_event s")
public class StatusUsers_event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="`idstatus_users&events`")
	private int idstatusUsers_events;

	@Column(name="id_event")
	private String idEvent;

	@Column(name="id_user")
	private String idUser;

	@Column(name="status_user_participation")
	private String statusUserParticipation;

	public StatusUsers_event() {
	}

	public int getIdstatusUsers_events() {
		return this.idstatusUsers_events;
	}

	public void setIdstatusUsers_events(int idstatusUsers_events) {
		this.idstatusUsers_events = idstatusUsers_events;
	}

	public String getIdEvent() {
		return this.idEvent;
	}

	public void setIdEvent(String idEvent) {
		this.idEvent = idEvent;
	}

	public String getIdUser() {
		return this.idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getStatusUserParticipation() {
		return this.statusUserParticipation;
	}

	public void setStatusUserParticipation(String statusUserParticipation) {
		this.statusUserParticipation = statusUserParticipation;
	}

}