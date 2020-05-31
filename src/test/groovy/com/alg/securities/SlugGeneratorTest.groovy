package com.alg.securities

import org.junit.Test

class SlugGeneratorTest extends GroovyTestCase {

    private final String TEXT = "9ea1421e 812b 4108 a139 066db62d7472"
    private final String TEXT_RESULT = "9ea1421e-812b-4108-a139-066db62d7472"

    @Test
    void testEncode() {
        def result = SlugGenerator.encode(TEXT)
        assertEquals(TEXT_RESULT, result)
    }

    void testEncodeAsciiOnly() {
        def result = SlugGenerator.encode("${TEXT}:&", true)
        assertEquals(TEXT_RESULT, result)
    }
}
