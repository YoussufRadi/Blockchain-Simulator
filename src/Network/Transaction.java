package Network;


public class Transaction {
	int id;
	String amount;
	User sender;
	User reciever;
	public Transaction(String amount, User sender, User reciever) {
		super();
		this.amount = amount;
		this.sender = sender;
		this.reciever = reciever;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public User getSender() {
		return sender;
	}
	public void setSender(User sender) {
		this.sender = sender;
	}
	public User getReciever() {
		return reciever;
	}
	public void setReciever(User reciever) {
		this.reciever = reciever;
	}
	public int getId() {
		return id;
	}

}
