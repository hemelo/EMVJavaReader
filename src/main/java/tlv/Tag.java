package tlv;

/**
 * Refers to a data element that is used to identify a specific type of information within a data structure. 
 * A tag is usually represented as a 2-byte or 4-byte binary value, and its interpretation is defined in the 
 * EMV specifications.
 */
public interface Tag {
	
	default boolean isConstructed() {
		return getType().equals(TagType.CONSTRUCTED);
	};
	
	byte[] getBytes();
	String getName();
	String getDescription();
	int getNumBytes();
	TagType getType();
}
