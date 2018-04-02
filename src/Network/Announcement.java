package Network;

import java.io.Serializable;
import java.util.Arrays;

/*
 * This class represents the announcements which is composed of a transaction and a signature.
 */
public class Announcement implements Serializable{
	Transaction transaction;
	byte[] signature;
	
	public Announcement(Transaction t, byte[] signature){
		this.transaction = t;
		this.signature = signature;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(signature);
		result = prime * result
				+ ((transaction == null) ? 0 : transaction.hashCode());
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
		Announcement other = (Announcement) obj;
		if (!Arrays.equals(signature, other.signature))
			return false;
		if (transaction == null) {
			if (other.transaction != null)
				return false;
		} else if (!transaction.equals(other.transaction))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Announcement [transaction=" + transaction + ", signature="
				+ Arrays.toString(signature) + "]";
	}
	
	
	
	

}
