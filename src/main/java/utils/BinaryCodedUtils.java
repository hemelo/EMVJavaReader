package utils;

public class BinaryCodedUtils {
	
	
	public static byte[] intToBinaryCodedDecimalByteArray(int val) {
		
		String str = String.valueOf(val);
		
		if (str.length() % 2 != 0) {
			str = "0" + str;
		}
		
		return HexUtils.toByteArray(str);
	}
	
	/**
     * This method converts the literal hex representation of a byte to an integer.
     * e.g 0x21 = 21 
     */
	public static int binaryCodedDecimalToInt(byte b) {
		
		try {
			return Integer.parseInt(ByteUtils.toHex(b));
		} catch (NumberFormatException ex) {
            throw new IllegalArgumentException("The hex representation of argument 'b' must be digits only, and integer", ex);
        }
	}
	
	/**
     * This method converts the literal hex representation of a decimal encoded in 1-5 bytes to an integer. 
     *  
     * e.g 0x70 = 70
     * e.g 0x21 47 48 36 47 = 2147483647
     * @param hex
     */
	public static int binaryHexCodedDecimalToInt(String hex) {
		
		final int limit = 10;
		
		if (hex == null) {
            throw new IllegalArgumentException("Argument 'hex' cannot be null");
        } else if (hex.length() > limit) {
        	throw new IllegalArgumentException("Argument 'hex' cannot be longer than " + limit + " characters. There must be a maximum of " + limit/2 + " hex octets");
        } else if (IntegerUtils.checkOverflow(hex)) {
        	throw new IllegalArgumentException("Argument 'hex' should not be higher than " + Integer.MAX_VALUE);
        }
		
		try {
			return Integer.parseInt(hex);
		} catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Argument 'hex' must be digits only, and integer", ex);
        }
  	}
	
	/* OVERLOADED METHODS */
	
	public static int binaryHexCodedDecimalToInt(byte[] array) {
		return binaryHexCodedDecimalToInt(ByteUtils.toHexString(array));
	}
}
