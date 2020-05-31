package com.alg.utils

class HtmlUtilitiesTest extends GroovyTestCase {
    final String TEXT_RAW = "Lorem & ipsum dolor\nÑunc ornare &amp; laoreet aliquet.\n\tNunc ac libero\n\r\tNullam imperdiet.\n"
    final String TEXT_HTML = "<p>Pellentesqué<br/>Vestibülum</p><ul><li>Lorem & ipsum.</li></ul><h1>HTML Ipsum Presents</h1>"

    void testEscape() {
        final def EXPECTED = "Lorem & ipsum dolor<br/>Ñunc ornare &amp; laoreet aliquet.<br/>\tNunc ac libero<br/>\tNullam imperdiet.<br/>"
        def result = HtmlUtilities.escape(TEXT_RAW)
        assertEquals(EXPECTED, result)
    }

    void testEscapeBr() {
        final String TEST = "Hello\nWorld"
        final String EXPECTED = "Hello<br/>World"
        def result = HtmlUtilities.escapeBr(TEST)
        assertEquals(EXPECTED, result)
    }

    void testConvertHtmlToPlainText() {
        def EXPECTED = "Pellentesqué Vestibülum Lorem & ipsum. HTML Ipsum Presents"
        def result = HtmlUtilities.convertHtmlToPlainText(TEXT_HTML)
        assertEquals(EXPECTED, result)
    }

    void testTestConvertHtmlToPlainText() {
        final int SIZE = 20
        def expected = HtmlUtilities.convertHtmlToPlainText(TEXT_HTML).substring(0, SIZE)
        def result = HtmlUtilities.convertHtmlToPlainText(TEXT_HTML, SIZE)
        assertEquals("${expected}...", result)
    }
}
