package tlv;

/**
 * The distinction between primitive and constructed tags is important because it affects how the information 
 * represented by the tag is processed. For example, a primitive tag may represent a simple value such as an 
 * account number, while a constructed tag may represent a more complex structure such as a multi-language string or a set of encryption keys
 */
public enum TagType {
	PRIMITIVE, 
	
	/**
	 * The first byte of the tag value indicates that it is a constructed tag, and the subsequent bytes 
	 * define the individual data elements that make up the structure. These individual elements can be 
	 * either primitive or constructed tags themselves, allowing for the creation of complex data structures.
	 */
	CONSTRUCTED
}
