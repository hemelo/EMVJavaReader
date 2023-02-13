package config;

import java.util.BitSet;
import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public class UtilsTestConfig {
	
	public static Stream<Arguments> byteArrayToStringHexProvider() {
		return Stream.of(
				Arguments.of(new byte[] { 0x01, 0x23, (byte) 0xAB, (byte) 0xFF }, "0123ABFF"),
				Arguments.of(new byte[] { 0x02, 0x24, (byte) 0xAC, (byte) 0x09 }, "0224AC09"),
				Arguments.of(new byte[] { 0x03, 0x25, (byte) 0xAD, (byte) 0x0A }, "0325AD0A"),
				Arguments.of(new byte[] { 0x04, 0x26, (byte) 0xAE, (byte) 0x0B }, "0426AE0B"),
				Arguments.of(new byte[] { 0x05, 0x27, (byte) 0xAF, (byte) 0x0C }, "0527AF0C"),
				Arguments.of(new byte[] { 0x06, 0x28, (byte) 0xB1, (byte) 0x0D }, "0628B10D")
		);
	}
	
	public static Stream<Arguments> hexStringToByteArrayProvider() {
		return byteArrayToStringHexProvider().map(args -> Arguments.of(args.get()[1], args.get()[0]));
	}
	
	public static Stream<Arguments> hexToByteProvider() {
		return Stream.of(
		        Arguments.of("00", (byte) 0),
		        Arguments.of("01", (byte) 1),
		        Arguments.of("02", (byte) 2),
		        Arguments.of("03", (byte) 3),
		        Arguments.of("04", (byte) 4),
		        Arguments.of("05", (byte) 5),
		        Arguments.of("06", (byte) 6),
		        Arguments.of("07", (byte) 7),
		        Arguments.of("08", (byte) 8),
		        Arguments.of("09", (byte) 9),
		        Arguments.of("0A", (byte) 10),
		        Arguments.of("0B", (byte) 11),
		        Arguments.of("0C", (byte) 12),
		        Arguments.of("0D", (byte) 13),
		        Arguments.of("0E", (byte) 14),
		        Arguments.of("0F", (byte) 15),
		        Arguments.of("00", (byte) 0),
		        Arguments.of("01", (byte) 1),
		        Arguments.of("0A", (byte) 10),
		        Arguments.of("FF", (byte) 255),
		        Arguments.of("7F", (byte) 127)
		);
	}
	
	public static Stream<Arguments> byteToHexProvider() {
		return hexToByteProvider().map(args -> Arguments.of(args.get()[1], args.get()[0]));
	}
	
	public static Stream<Arguments> byteToBinaryLiteralProvider() {
		return Stream.of(
				Arguments.of((byte) 0b00100011, "00100011"),
				Arguments.of((byte) 0x23, "00100011"),
				Arguments.of((byte) 35, "00100011"),
				Arguments.of((byte) 0b11, "00000011")
		);
	}
	
	public static Stream<Arguments> byteToBitSetProvider() {
		
		return Stream.of(
				Arguments.of((byte) 0x03, BitSet.valueOf(new byte[] { (byte) 0x03 })),
				Arguments.of((byte) 0x23, BitSet.valueOf(new byte[] { (byte) 0x23 })),
				Arguments.of((byte) 0x00, BitSet.valueOf(new byte[] { (byte) 0x00 })),
				Arguments.of((byte) 0xFF, BitSet.valueOf(new byte[] { (byte) 0xFF }))
		);
	}
	
	public static Stream<Arguments> byteArrayToBitSetProvider() {
		
		return Stream.of(
				Arguments.of(new byte[] { 0x03 }, BitSet.valueOf(new byte[] { 0x03 })),
				Arguments.of(new byte[] { 0x03, 0x04 }, BitSet.valueOf(new byte[] { 0x04, 0x03 })),
				Arguments.of(new byte[] { 0x03, 0x04, 0x05 }, BitSet.valueOf(new byte[] { 0x05, 0x04, 0x03 })),
				Arguments.of(new byte[] { 0x03, 0x04, 0x05, 0x06 }, BitSet.valueOf(new byte[] { 0x06, 0x05, 0x04, 0x03 })),
				Arguments.of(new byte[] { 0x03, 0x04, 0x05, 0x06, 0x07 }, BitSet.valueOf(new byte[] { 0x07, 0x06, 0x05, 0x04, 0x03 })),
				Arguments.of(new byte[] { 0x03, 0x04, 0x05, 0x06, 0x07, 0x08 }, BitSet.valueOf(new byte[] { 0x08, 0x07, 0x06, 0x05, 0x04, 0x03 }))
		);
	}
}
