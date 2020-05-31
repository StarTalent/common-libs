package com.alg.utils

import org.joda.time.DateTime

import java.text.SimpleDateFormat

class DateUtilitiesTest extends GroovyTestCase {

    final def TODAY = new DateTime()
    final def TODAY_DATE = TODAY.toDate()
    final def DATE_FORMAT_PATTERN = "dd-MM-yyyy"
    final def DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_PATTERN)

    void testParseFormat() {
        def result = DateUtilities.parseFormat(DATE_FORMAT.format(TODAY_DATE), DATE_FORMAT_PATTERN)
        assertEquals(DATE_FORMAT.format(TODAY.toDate()), DATE_FORMAT.format(result))
    }

    void testMultiParse() {
        def result = DateUtilities.multiParse(DATE_FORMAT.format(TODAY_DATE))
        assertEquals(DATE_FORMAT.format(TODAY_DATE), DATE_FORMAT.format(result))
    }

    void testGetFirstDayOfYear() {
        def result = DateUtilities.getFirstDayOfYear(TODAY.getYear())
        assertEquals(TODAY.dayOfYear().withMinimumValue().getDayOfYear(), result.getDayOfYear())
    }

    void testGetLastDayOfYear() {
        def result = DateUtilities.getLastDayOfYear(TODAY.getYear())
        assertEquals(TODAY.dayOfYear().maximumValue, result.getDayOfYear())
    }

    void testGetDateByYear() {
        def result = DateUtilities.getDateByYear(TODAY.getYear())
        assertEquals(DATE_FORMAT.format(TODAY_DATE), DATE_FORMAT.format(result.toDate()))
    }

}
