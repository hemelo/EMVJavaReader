package utils;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;

public class HexUtils {
	
	public static String prettifyHex(String in, int indent, boolean wrapLines) {
		
		StringBuilder str = new StringBuilder();
		
		for (int i = 0; i < in.length(); i++) {
			
			int next = i + 1;
			
			str.append(in.charAt(i));
			
			if (wrapLines && next % 32 == 0 && next != in.length()) {
				str.append("\n").append(StringUtils.repeat(' ', indent));
			} else if (next % 2 == 0 && next != in.length()) {
				str.append(" ");
			}
		}
		
		return str.toString();
	}
	
	public static String prettifyHex(BigInteger bi) {
		
		byte[] data = bi.toByteArray();
		
		if (data[0] == (byte) 0x00) {
			data = ByteUtils.copy(data, 1, data.length - 1);
		}
		
		return prettifyHex(data);
	}
    
	/* CONVERSION METHODS */
	
	public static byte[] toByteArray(String hex) {
		
		hex = hex.replaceAll(" ", "");
		
		if (hex.length() == 0) {
			return new byte[0];
		}
		else if ((hex.length() % 2) != 0) {
			throw new IllegalArgumentException("Argument 'hex' must contain an even number of characters");
		}
		
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		
		for (int i = 0; i < hex.length(); i += 2) {
			result.write(toByte(hex.substring(i, i + 2)));
		}
		
		return result.toByteArray();
	}
	
	/**
	 * Converts each hexadecimal character of a string of length = 2 into its equivalent binary representation. 
	 * These binary representations are then combined into a single 8-bit binary number.
	 * <p>
	 * For example, consider a string "AB". The hexadecimal character 'A' can be represented as binary "1010", 
	 * and the hexadecimal character 'B' can be represented as binary "1011". By combining these two binary representations, 
	 * we obtain the 8-bit binary number
	 */
	public static byte toByte(String hex) {
		if (hex.length() == 0) {
			throw new IllegalArgumentException("Argument 'hex' must not be null or empty");
		}
		else if (hex.isEmpty()) {
			throw new IllegalArgumentException("Argument 'hex' must contain at least 1 characters");
		}
		
		if (hex.length() == 1) {
			hex = "0" + hex;
		}
		
		// The << operator is used to shift the first hexadecimal digit left by 4 bits to make room for the second digit.
		int d1 = Character.digit(hex.charAt(0), 16) << 4;
		int d2 = Character.digit(hex.charAt(1), 16);
		return (byte) (d1 + d2);
	}
	
	/* OVERLOADED METHODS */
	
	public static String prettifyHex(String in, int indent) {
		return prettifyHex(in, indent, true);
	}
	
	public static String prettifyHex(byte[] data, int indent) {
		return prettifyHex(ByteUtils.toHexString(data), indent, true);
	}
	
	public static String prettifyHex(byte[] data) {
        return prettifyHex(ByteUtils.toHexString(data), 0, true);
    }
    
    public static String prettifyHex(byte[] data, int startPos, int length) {
        return prettifyHex(ByteUtils.toHexString(data, startPos, length), 0, true);
    }
    
    public static String prettifyHex(String in) {
        return prettifyHex(in, 0, true);
    }

    public static String prettifyHexNoWrap(byte[] data) {
        return prettifyHex(ByteUtils.toHexString(data), 0, false);
    }
    
    public static String prettifyHexNoWrap(byte[] data, int startPos, int length) {
        return prettifyHex(ByteUtils.toHexString(data, startPos, length), 0, false);
    }
    
    public static String prettifyHexNoWrap(String in) {
        return prettifyHex(in, 0, false);
    } 
}
