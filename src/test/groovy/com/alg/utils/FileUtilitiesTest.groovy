package com.alg.utils

import org.junit.Test

import java.nio.file.Paths

class FileUtilitiesTest extends GroovyTestCase {

    @Test
    void testReadLines() {
        def path = Paths.get("").toAbsolutePath().toString();
        def lines = FileUtilities.readLines("$path${File.separator}settings.gradle")
        assertTrue(lines.size() > 0)
        assertTrue(lines.first().contains("rootProject.name = 'common-libs'"))
    }
}
