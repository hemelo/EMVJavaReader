package unit;

import utils.ByteUtils;
import utils.HexUtils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.BitSet;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import config.LogTestExecutionTimeExtension;
import config.UtilsTestConfig;

//@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(LogTestExecutionTimeExtension.class)
public class UtilsTest extends UtilsTestConfig {

	@ParameterizedTest
    @MethodSource("byteArrayToStringHexProvider")
    public void testArrayToHexString(byte[] input, String expectedOutput) {
        assertEquals(expectedOutput,  ByteUtils.toHexString(input));
    }

	@ParameterizedTest
    @MethodSource("hexStringToByteArrayProvider")
    public void testHexStringToArray(String input, byte[] expectedOutput) {
    	assertArrayEquals(expectedOutput, HexUtils.toByteArray(input));
    }
	
	@ParameterizedTest
	@MethodSource("hexToByteProvider")
    void testHexToByte(String hex, byte expected) {
        assertEquals(expected, HexUtils.toByte(hex));
    }

    @ParameterizedTest
    @MethodSource("byteToHexProvider")
    void testByteToHex(byte b, String expected) {
        String actual = ByteUtils.toHex(b);
        assertEquals(expected, actual);
    }
	
    @ParameterizedTest
    @MethodSource("byteToBinaryLiteralProvider")
    void testByteToBinaryLiteral(byte b, String expected) {
    	assertEquals(expected, ByteUtils.toBinaryLiteral(b));
    }
    
    @ParameterizedTest
    @MethodSource("byteToBitSetProvider")
    void testByteToBitSet(byte b, BitSet expected) {
    	assertArrayEquals(expected.toByteArray(), ByteUtils.toBitSet(b).toByteArray());
    }
    
    @ParameterizedTest
    @MethodSource("byteArrayToBitSetProvider")
    void testByteToBitSet(byte[] array, BitSet expected) {
    	assertArrayEquals(expected.toByteArray(), ByteUtils.arrayToBitSet(array).toByteArray());
    }
}
