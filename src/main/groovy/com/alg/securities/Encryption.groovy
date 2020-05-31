package com.alg.securities

import org.apache.commons.codec.binary.Hex

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.StandardCharsets
import java.security.Key


class Encryption {
    private static final String ALGORITHM = "AES"
    private static final SECRET_KEY = "mop.bale.beta.du".getBytes()

    static String encrypt(String rawData) throws Exception {
        Key key = generateKey()
        Cipher cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.ENCRYPT_MODE, key)
        byte[] encryptedBytes = cipher.doFinal(rawData.getBytes(StandardCharsets.UTF_8.name()))
        return new String(Hex.encodeHex(encryptedBytes))
    }


    static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey()
        Cipher cipher = Cipher.getInstance(ALGORITHM)
        cipher.init(Cipher.DECRYPT_MODE, key)
        byte[] decryptedBytes = cipher.doFinal(Hex.decodeHex(encryptedData.toCharArray()))
        return new String(decryptedBytes)
    }

    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(SECRET_KEY, ALGORITHM)
        return key
    }
}
