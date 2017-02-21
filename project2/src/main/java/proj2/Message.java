package proj2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;

	@Column(nullable = false)
	String text;

	public Message() { } // must be presented based on Hibernate doc
						 // http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorialbasic-entity

	public Message(String text) {
		super();
		this.text = text;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
		
	@Override
	public String toString() {
		return "Message{id=" + getId() + ", text='" + getText() + '\'' + '}'; 
	}

}
