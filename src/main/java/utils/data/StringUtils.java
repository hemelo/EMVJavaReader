package utils.data;

public class StringUtils {
	
	public static String repeat(char c, int length) {
		return String.valueOf(c).repeat(length); // JDK11
		//return new String(new char[length]).replaceAll("\0", String.valueOf(c)); 
	}
	

}
