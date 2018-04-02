package Network;



public class Announcement {
	Transaction t;
	byte[] signature;
	public Announcement(Transaction t, byte[] signature) {
		super();
		this.t = t;
		this.signature = signature;
	}
}
