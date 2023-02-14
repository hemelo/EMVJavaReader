package iso7816;

public class ShortFileIdentifier {
	
	private int SFI;
	
	public ShortFileIdentifier(int sfi) {
		
		checkSFIRange(sfi);
		
		this.SFI = sfi;
	}
	
	public int getValue() {
		return SFI;
	}
	
	public String getDescription() {
		return getDescription(SFI);
	}
	
	public static String getDescription(int sfi) {
		
		checkSFIRange(sfi);
			
		if (sfi <= 10) { 
            return "EMV specification";
        } else if (sfi <= 20) { 
            return "Payment system-specific";
        } else { 
            return "Issuer-specific";
        }
	}
	
	private static void checkSFIRange(int sfi) {
		if (sfi > 30 || sfi < 1) {
			throw new IllegalArgumentException("Illegal SFI value. SFI must be in the range [1-30]");
		}
	}
}
