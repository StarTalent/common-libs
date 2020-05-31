package com.alg.utils

class HtmlUtilitiesTest extends GroovyTestCase {
    final String TEXT_RAW = 'classify\t\n"frame"ñ&l'
    final String TEXT_RESULT = 'classify "frame"ñ&l'


    void testEscapeTextArea() {
        def result = HtmlUtilities.escapeTextArea(TEXT_RAW)
        assertEquals(TEXT_RESULT, result)
    }

    void testEscape() {
        def result = HtmlUtilities.escape(TEXT_RAW)
        assertEquals(TEXT_RESULT, result)
    }

    void testEscapeTags() {
        def result = HtmlUtilities.escapeTags("<$TEXT_RAW>")
        assertEquals(TEXT_RESULT, result)
    }

    void testEscapeBr() {
        def result = HtmlUtilities.escapeBr("$TEXT_RAW<br/><br />")
        assertEquals("${TEXT_RESULT}\n\n".toString(), result)
    }

    void testEscapeSpecial() {
        def result = HtmlUtilities.escapeSpecial("<span>$TEXT_RAW</span>")
        assertEquals(TEXT_RESULT, result)
    }

    void testConvertHtmlToPlainText() {
        def result = HtmlUtilities.convertHtmlToPlainText("<span>$TEXT_RAW</span>")
        assertEquals(TEXT_RESULT, result)
    }

    void testTestConvertHtmlToPlainText() {
        int size = 30
        int splitSize = 20
        def data = ""
        [1..size].each {
            data += it
        }
        def result = HtmlUtilities.convertHtmlToPlainText("<span>${data}</span>", splitSize)
        assertEquals("${[1..splitSize].join("")}...", result)
    }
}
