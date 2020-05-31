package com.alg.securities

import java.text.Normalizer
import java.util.regex.Pattern

class SlugGenerator {
    private static final Pattern DIACRITICAL_PATTERN = Pattern.compile("\\p{InCombiningDiacriticalMarks}+", Pattern.UNICODE_CASE)
    private static final Pattern PUNCTUATION_PATTERN = Pattern.compile("\\p{Punct}+", Pattern.UNICODE_CASE)
    private static final Pattern PUNCTUATION_SPECIAL_PATTERN = Pattern.compile("[¡¿·¹²³⁴⁵⁶⁷⁸⁹⁰]+", Pattern.UNICODE_CASE)
    private static final Pattern WHITE_SPACES_PATTERN = Pattern.compile("\\s+", Pattern.UNICODE_CASE)
    private static final Pattern ASCII_PATTERN = Pattern.compile("[^\\p{ASCII}a-z0-9]", Pattern.UNICODE_CASE)
    private static final CHAR_MAP = ["æ": "ae", "ß": "ss", "ø": "o", "ĸ": "k"]

    static encode( String str, Boolean asciiOnly = false) {
        if (str.isEmpty()) {
            return ""
        }
        str = translate(str.toLowerCase())
        if (asciiOnly) {
            str = castToAscii(str)
        }
        return removeWhitespaces(normalize(str))
    }

    private static String castToAscii(String str) {
        return str.replaceAll(ASCII_PATTERN, "")
    }

    private static String removeWhitespaces(String str) {
        return str.replaceAll(WHITE_SPACES_PATTERN, "-")
    }

    private static String normalize(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD)
                .replaceAll(DIACRITICAL_PATTERN, "")
                .replaceAll(PUNCTUATION_PATTERN, " ")
                .replaceAll(PUNCTUATION_SPECIAL_PATTERN, " ").trim()
    }

    private static String translate(String str) {
        CHAR_MAP.each { k, v ->
            str = str.replaceAll(k, v)
        }
        return str
    }

}
