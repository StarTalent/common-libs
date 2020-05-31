package com.alg.utils

import groovy.util.logging.Slf4j
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

import java.text.ParseException
import java.text.SimpleDateFormat

@Slf4j
class DateUtilities {

    private final static DATE_FORMAT_PATTERNS = [
            'dd-MM-yy', 'dd-MM-yyyy', 'yy-MM-dd', 'yyyy-MM-dd', 'MM-dd-yy', 'MM-dd-yyyy']
    private static List<DateTimeFormatter> DATE_FORMAT = new ArrayList<DateTimeFormatter>()

    private static Date tryToParse(String input, DateTimeFormatter format) {
        DateTime date = null
        try {
            date = format.parseDateTime(input)
        } catch (ParseException e) {
            log.error("No se pudo procesar la fecha: [input: ${input}, format: ${format}]", e)
        }
        return (date != null) ? date.toDate() : null
    }

    static Date parseFormat(String input, String format) {
        try {
            return new SimpleDateFormat(format).parse(input)
        } catch (ParseException e) {
            log.error("No se pudo procesar la fecha: [input: ${input}, format: ${format}]", e)
        }
        return null
    }

    static Date multiParse(String input) {
        Date date = null

        DATE_FORMAT_PATTERNS.each {
            DATE_FORMAT.add(DateTimeFormat.forPattern(it))
        }

        for (DateTimeFormatter format : DATE_FORMAT) {
            date = tryToParse(input, format)
            if (date != null) break
        }

        return date
    }

    static DateTime getFirstDayOfYear(int year = 0) {
        DateTime date = getDateByYear(year)
        return date.dayOfYear().withMinimumValue().withTimeAtStartOfDay()
    }

    static DateTime getLastDayOfYear(int year = 0) {
        DateTime date = getDateByYear(year)
        return date.dayOfYear().withMaximumValue().withTime(23, 59, 59, 0)
    }

    static DateTime getDateByYear(int year = 0) {
        DateTime date
        if (year == 0) {
            date = new DateTime()
        } else {
            date = new DateTime().withYear(year)
        }
        return date
    }
}
