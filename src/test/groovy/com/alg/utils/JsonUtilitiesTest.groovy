package com.alg.utils

import org.junit.Test

class JsonUtilitiesTest extends GroovyTestCase {

    final def JSON = '{"person":{"name":"Arturo López","age":37,"pets":["dog","cat"]}}'
    Map<String, String> MAP = new HashMap<String, String>();

    @Test
    void testGetJsonMap() {
        MAP.put("username", "lgzarturo")
        MAP.put("email", "lgzarturo@gmail.com")
        def map = JsonUtilities.getJsonMap(JSON)
        assertEquals(MAP, map)
    }
}
