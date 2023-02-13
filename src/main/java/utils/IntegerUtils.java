package utils;

public class IntegerUtils {
	
	public static String toHex(short s) {
		byte b1 = (byte) (s >>> 8);
        byte b2 = (byte) (s & 0xFF);
        return ByteUtils.toHex(b1) + ByteUtils.toHex(b2);
	}

	/**
	 * Checks overflow
	 * 
	 * @param origin 
	 * @return
	 */
	public static boolean checkOverflow(String origin) {
		
		try {
			int value = Integer.parseInt(origin);
			long value2 = Long.parseLong(origin);
			
			if (value2 != value) {
				return true;
			}
			
			return false;
			
		} catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Argument 'origin' must be digits only, and integer", ex);
        }
	}
}
