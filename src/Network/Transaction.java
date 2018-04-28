package Network;

import java.io.Serializable;

/*
 * This class stores the represents the transaction which is composed of the id, amount, payer and receiver
 */
public class Transaction extends Announcement implements Serializable{
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + id;
		result = prime * result + ((payer == null) ? 0 : payer.hashCode());
		result = prime * result
				+ ((receiver == null) ? 0 : receiver.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transaction other = (Transaction) obj;
		if (amount != other.amount)
			return false;
		if (id != other.id)
			return false;
		if (payer == null) {
			if (other.payer != null)
				return false;
		} else if (!payer.equals(other.payer))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", from payer=" + payer.getName()
				+ ", to receiver=" + receiver.getName() + "]";
	}	
	
	
	
	


}
