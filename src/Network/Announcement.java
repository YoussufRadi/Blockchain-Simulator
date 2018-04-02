package Network;

/*
 * This class represents the announcements which is composed of a transaction and a signature.
 */
public class Announcement {
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

}
