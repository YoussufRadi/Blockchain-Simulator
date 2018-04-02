package Network;

/*
 * This class stores the represents the transaction which is composed of the id, amount, payer and receiver
 */
public class Transaction {
	int id; 
	int amount;
	User payer;
	User receiver;
	static int counter = 0; 
	
	public Transaction(int a, User p, User r) {
		this.id = counter;
		this.amount = a;
		this.payer = p; 
		this.receiver = r;
		counter ++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public User getPayer() {
		return payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}	


}
