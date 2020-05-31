package com.alg.utils

import org.junit.Test

class JsonUtilitiesTest extends GroovyTestCase {

    final def JSON = '{"name":"Arturo López","age":37,"pets":["dog","cat"]}'
    Map<String, String> MAP = new HashMap<String, String>();

    @Test
    void testGetJsonMap() {
        def map = JsonUtilities.getJsonMap(JSON)
        assertEquals("Arturo López", map.name)
        assertEquals(37, map.age)
        assertEquals(["dog","cat"], map.pets)
    }
}
