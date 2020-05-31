package com.alg.securities

import org.junit.Test


class EncryptionTest extends GroovyTestCase {

    final String RAW = "e9ec3f70-1ee4-4757-92a0-9fab595f0872"
    String encrypted = ""
    String decrypted = ""

    @Override
    protected void setUp() throws Exception {
        encrypted = Encryption.encrypt(RAW)
        decrypted = Encryption.decrypt(encrypted)
    }

    @Test
    void testEncrypt() {
        assertEquals(true, encrypted.size() > 0)
    }

    @Test
    void testDecrypt() {
        assertEquals(RAW, decrypted)
    }
}
