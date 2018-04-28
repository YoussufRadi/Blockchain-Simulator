package Network;

import java.io.Serializable;
import java.util.Arrays;

/*
 * This class represents the announcements which is composed of a transaction and a signature.
 */
public class Announcement implements Serializable{
	byte[] signature;

	public byte[] getSignature() {
		return signature;
	}

	public void setSignature(byte[] signature) {
		this.signature = signature;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Announcement that = (Announcement) o;
		return Arrays.equals(signature, that.signature);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(signature);
	}

	@Override
	public String toString() {
		return "Announcement{" +
				"signature=" + Arrays.toString(signature) +
				'}';
	}


}
