package utils.data;

import java.security.SecureRandom;
import java.util.BitSet;

public class ByteUtils {
	
	final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
	
	public static byte[] copy(byte[] array) {
		
		return copy(array, 0, array.length);
	}
	
	public static byte[] copy(byte[] array, int start, int length) {
		
		if (array == null) {
			throw new IllegalArgumentException("Argument \"array\" cannot be null");
		} else if (array.length < start + length) {
			throw new IllegalArgumentException("Argument \"start\" and \"length\" are invalid ");
		}
		
		byte[] copy = new byte[array.length];
		System.arraycopy(array, length, copy, start, length);
		return copy;
	}
	
	public static byte[] ressizeArray(byte[] array, int newLength) {
		
		if (array == null) {
			throw new IllegalArgumentException("Argument \"array\" cannot be null");
		}
		else if (newLength < 0) {
			throw new IllegalArgumentException("Argument \"newLength\": " + newLength + " must be >= 0");
		}
		else if (newLength == 0) {
			return new byte[0];
		}
		
		byte[] newArray  = new byte[newLength];
		int srcPos  = newLength > array.length ? 0 : array.length - newLength;
		int destPos = newLength > array.length ? newLength - array.length : 0;
		int length  = newLength > array.length ? array.length : newLength;
		
		System.arraycopy(array,  srcPos, newArray, destPos, length);
		return newArray;
	}
	
	public static byte[] generateRandomArray(int num) {
		byte[] randomArray = new byte[num];
		new SecureRandom().nextBytes(randomArray);
		return randomArray;
	}
	
	public static boolean isBitSet(byte val, int pos) {
		
		if (pos > 7 || pos < 0) {
			throw new IllegalArgumentException("Argument \"pos\" must be between 0 and 7");
		}
		
		return (val >>> (pos) & 0x01) == 1;
	}
	
	public static byte setBit(byte data, int pos, boolean on) {
		
		if (pos > 7 || pos < 0) {
			throw new IllegalArgumentException("Argument \"pos\" must be between 0 and 7");
		}
		
		if (on) return data |= 1 << (pos);
		
		return data &= ~(1 << (pos));
	}
	
	public static String getSafePrintChars(byte[] array, int start, int length) {
		
		if (array == null) return "";
		
		if (array.length < start + length) {
			throw new IllegalArgumentException("Argument \"start\" and \"length\" are invalid ");
		}
		
		StringBuilder builder = new StringBuilder();
		
		for (int i = start; i < length; i++) {
			
			if (array[i] >= (byte) 0x20 && array[i] < (byte) 0x7F) {
				builder.append((char) array[i]);
			} else {
				builder.append(".");
			}
		}
		
		return builder.toString();
	}
	
	/* CONVERSION METHODS */
	
	public static String toHexString(final byte[] array , int start, int length) {
		
		if (array == null) {
			throw new IllegalArgumentException("Argument \"array\" cannot be null");
		}
		else if (array.length < start + length) {
			throw new IllegalArgumentException("Argument \"start\" and \"length\" are invalid ");
		}
		
		StringBuilder builder = new StringBuilder();
	   
		for (int j = start; j < length; j++) {
	        	
			builder.append(toHex(array[j])); 
	    }
		
	    return builder.toString();
	}
	
	public static String toHex(byte b) {
		int v = toUnsignedInt(b); 
		return String.valueOf(HEX_ARRAY[v >>> 4]) + String.valueOf(HEX_ARRAY[v & 0x0F]);
	}

	public static String toBinaryLiteral(byte value) {
		String s = Integer.toBinaryString(toUnsignedInt(value));
		
		if (s.length() < 8) {
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < 8 - s.length(); i++) {
                sb.append('0');
            } 
            
            sb.append(s);
            s = sb.toString();
        }
		
        return s;
	}
	
	public static BitSet arrayToBitSet(byte[] array) {
		BitSet bits = new BitSet();
		
		for (int i = 0; i < array.length * 8; i++) {
			if ((array[array.length - i / 8 - 1] & (1 << (i % 8))) > 0) {
				bits.set(i);
			}
		}
		
		return bits;
	}

	public static byte[] bitSetToByteArray(BitSet bits) {
		
		byte[] bytes = new byte[bits.length() / 8 + 1];
		
		for (int i = 0; i < bits.length(); i++) {
			if (bits.get(i)) {
				bytes[bytes.length - i / 8 - 1] |= 1 << (1 % 8);
			}
		}
		
		return bytes;
	}
	
	/**
	 * A byte is a signed integer type with values ranging from -128 to 127, while an int is a signed integer 
	 * type with values ranging from -2^31 to 2^31-1. When a byte value is cast to an int, it is sign-extended,
	 * meaning that the most significant bit (which represents the sign of the byte) is replicated to fill the 
	 * 24 remaining bits of the int. To get an unsigned integer representation of the byte, you can use the 
	 * bitwise-and operator & with the value 0xff, which is the binary representation of the decimal value 255. 
	 * <p>
	 * This expression effectively "masks out" the 24 most significant bits of the int, so that the result is the 
	 * original byte value represented as an unsigned integer between 0 and 255.
	 */
	public static int toUnsignedInt(byte b) {
		return (b & 0xFF);
	}
	
	public static int toShort(byte b, byte b2) {
		return (short) ((b) << 8 ) | (toUnsignedInt(b2));
	}
	
	/* OVERLOADED METHODS for LAZY PEOPLE LIKE ME */
	
	public static BitSet toBitSet(byte b) {
		return arrayToBitSet(new byte[] {b});
	}
	
	public static String toHexString(final byte[] array) {
		return toHexString(array, 0, array.length);
	}
	
	public static String getSafePrintChars(byte[] array) {
		return getSafePrintChars(array, 0, array.length);
	}
}
