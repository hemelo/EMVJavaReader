package concepts;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BitOperationsTest {
    private static final int TEST_INT_1 = 0b10101010;
    private static final int TEST_INT_2 = 0b01010101;

    @Test
    public void testBitwiseAnd() {
        int result = TEST_INT_1 & TEST_INT_2;
        assertEquals(0b00000000, result);
    }

    @Test
    public void testBitwiseOr() {
        int result = TEST_INT_1 | TEST_INT_2;
        assertEquals(0b11111111, result);
    }

    @Test
    public void testBitwiseXor() {
        int result = TEST_INT_1 ^ TEST_INT_2;
        assertEquals(0b11111111, result);
    }

    @Test
    public void testBitwiseComplement() {
        int result = ~TEST_INT_1 & 0xff;
        assertEquals(0b01010101, result);
    }

    @Test
    public void testLeftShift() {
        int result = TEST_INT_1 << 4; // Multiplies by 2^4
        assertEquals(0b101010100000, result);
    }

    @Test
    public void testLeftShiftSingleByte() {
    	int result = (TEST_INT_1 << 4) & 0xFF;
        assertEquals(0b10100000, result);
    }
    
    @Test
    public void testRightShift() {
        int result = TEST_INT_1 >> 4; // Divides by 2^4
        assertEquals(0b00001010, result);
    }

    @Test
    public void testUnsignedRightShift() {
        int result = TEST_INT_1 >>> 2;
        assertEquals(0b00101010, result);
    }
}