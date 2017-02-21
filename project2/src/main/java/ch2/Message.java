package ch2;

public class Message {

	String text;

	public Message() {} //must be presented based on hibernate doc http://docs.jboss.org/hibernate/orm/5.2/quickstart/html_single/#hibernate-gsg-tutorialbasic-entity
	
	public Message(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
