package utils.crypto;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

public class RSAKeyGen {
	
	
	public static RSAKeyPair generateKeys(int nBits, BigInteger exponent) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		AlgorithmParameterSpec specs = new RSAKeyGenParameterSpec(nBits, exponent);
		generator.initialize(specs, new SecureRandom());
		
		return RSAKeyPair.from(generator.generateKeyPair());
	}
	
	//---------------------------------------------------------------------------------------------------------------------
	
	public static class RSAKeyPair {
		private RSAPublicKey pk;
		private RSAPrivateKey sk;
		
		RSAKeyPair(RSAPublicKey pk, RSAPrivateKey sk) {
			this.pk = pk;
			this.sk = sk;
		}

		public RSAPublicKey getPublicKey() {
			return pk;
		}

		public RSAPrivateKey getSecretKey() {
			return sk;
		}
		
		private static RSAKeyPair from(KeyPair pair) {
			return new RSAKeyPair((RSAPublicKey) pair.getPublic(), (RSAPrivateKey) pair.getPrivate());
		}
	}
}
	