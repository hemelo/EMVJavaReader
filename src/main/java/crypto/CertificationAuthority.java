package crypto;

import utils.data.ByteUtils;
import utils.data.IntegerUtils;
import utils.crypto.HashUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CertificationAuthority {

    private byte[] data;

    private String name;
    private String description;
    private Map<Integer, PublicKey> publicKeys = new LinkedHashMap<>();

    public Collection<PublicKey> getCAPublicKeys() {
        return Collections.unmodifiableCollection(publicKeys.values());
    }

    private PublicKey getPublicKey(int index) {
        return publicKeys.get(index);
    }

    private void setPublicKey(int index, PublicKey key) {

        if (publicKeys.containsKey(index)) {
            throw new IllegalArgumentException("Invalid 'index'. A public key is already set on this position. ");
        }

        publicKeys.put(index, key);
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static byte[] calculateCAPublicKeyCheckSum(int index, byte[] data, byte[] mod, byte[] exp) {
        byte[] indexAsByteArray = IntegerUtils.toByteArray(index);
        return HashUtils.SHA1(ByteUtils.mergeArrays(data, indexAsByteArray, mod, exp));
    }
}
