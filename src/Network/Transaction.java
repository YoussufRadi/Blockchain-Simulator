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

}
