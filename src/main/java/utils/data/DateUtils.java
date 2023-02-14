package utils.data;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class DateUtils {

	static SimpleDateFormat FORMAT_YYMMDD  = new SimpleDateFormat("yyMMdd");
	
	public static byte[] getCurrentDateAsNumericEncodedByteArray() {
	
		return HexUtils.toByteArray(FORMAT_YYMMDD.format(LocalDate.now()));
	}
	
	public static String getFormattedNanoTime(long nano) {
		StringBuilder builder = new StringBuilder();
		builder.append((int) (nano / 1000000));
		builder.append("ms ");
		builder.append(nano % 1000000);
		builder.append("ns ");
		return builder.toString();
	}
}
