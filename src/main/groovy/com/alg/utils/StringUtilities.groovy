package com.alg.utils

import org.joda.time.DateTime
import org.joda.time.Years
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import java.nio.charset.StandardCharsets
import java.text.Normalizer

class StringUtilities {

    static String getURIEncodedUTF8(String content) {
        String cleanText = Normalizer.normalize(content, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}\\p{IsLm}\\p{IsSk}]+", "")
                .replaceAll(/[\p{Punct}&&[^-._]]/, "")
                .replaceAll(/[\p{Space}\+]+/, "-").trim()
        URLEncoder.encode(cleanText, StandardCharsets.UTF_8.toString())
    }

    static String getWhatsAppUrl(String phone, String country = "52") {
        def show = false
        def url = ""
        if (phone && phone.length() == 10) {
            phone = "${country}${phone}"
            show = true
        }

        if (show) {
            url = "&nbsp;<a href=\"https://api.whatsapp.com/send?phone=${phone}\" target=\"_blank\">" +
                    "<i class=\"fa fa-whatsapp\" aria-hidden=\"true\"></i></a>"
        }
        return url
    }

    static String randomString(boolean alpha, int size) {
        Date date = new Date()
        long time = date.getTime()
        Random rd = new Random(time)
        def randomString = ""
        int i = 0
        while (i < size) {
            char c = (char) rd.nextInt(255)
            if (((c >= ('0' as Character) && c <= ('9' as Character)) || (c >= ('A' as Character) && c <= ('Z' as Character)) || (c >= ('a' as Character) && c <= ('z' as Character))) && alpha) {
                randomString += c
                i++
            } else if (c >= ('0' as Character) && c <= ('9' as Character)) {
                randomString += c
                i++
            }
        }
        return randomString
    }

    static Integer randomInteger(int size) {
        size = (size > 9 || size < 2) ? 9 : size
        Date date = new Date()
        long time = date.getTime()
        Random rd = new Random(time)

        def rdI = "9"
        def rdE = "1"

        (size - 1).times {
            rdI += "9"
            rdE += "0"
        }

        return rd.nextInt(rdI.toInteger()) + rdE.toInteger()
    }

    static String randomPassword(String value, boolean alpha, int size) {
        def pswd = ""
        value = (value == null) ? "" : value
        size = (size > 0) ? size : 4

        if (value.isEmpty() || value.length() < 4) {
            def numeric = ""
            def letters = ""

            (0..9).each { numeric += it }
            ('a'..'z').each { letters += it }
            ('A'..'Z').each { letters += it }
            value = (alpha) ? "$numeric$letters" : "$numeric"
        } else {
            size = value.length()
            value = removeAccents(value, false)
        }

        for (int i = 0; i < size; i++) {
            pswd += (value.charAt((int) (Math.random() * value.length())))
        }

        return pswd
    }

    static String removeAccents(String title = "", boolean slug = false) {
        if (title) {
            def normalString = Normalizer.normalize(title, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                    .replaceAll(/\p{Punct}+/, '').trim()

            if (slug) {
                normalString = normalString.replaceAll(/\W+/, '-').toLowerCase()
            } else {
                normalString = normalString.replaceAll(/\W+/, ' ')
            }

            return normalString
        }

        return ""
    }

    static normalSlug(String title) {
        return removeAccents(title, true)
    }

    static randomSlug(String title, int size) {
        size = (size == 0) ? 32 : size
        def normalString = removeAccents(title, true)
        def valorRd = randomInteger(4)
        def randomString = StringUtilities.randomString(true, 4)
        def password = randomPassword(randomString, false, 0)
        def result = "$normalString$randomString$valorRd$password"

        return (result.length() > size) ?
                "${normalString.substring(0, (size - 13))}-$randomString$valorRd$password" :
                result
    }

    static cleanContentHTML(String html) {
        if (html == null) return ""

        return html.replaceAll("<br>", "<br />")
                .replaceAll("<hr>", "<hr />")
                .replaceAll("&amp;amp;", "&amp;")
                .replaceAll(/&(?!#?[a-z0-9A-Z]+;)/, "&amp;")
                .replaceAll("&amp;Ntilde;", "&Ntilde;")
                .replaceAll("&amp;Aacute;", "&Aacute;")
                .replaceAll("&amp;Eacute;", "&Eacute;")
                .replaceAll("&amp;Iacute;", "&Iacute;")
                .replaceAll("&amp;Oacute;", "&Oacute;")
                .replaceAll("&amp;Uacute;", "&Uacute;")

    }

    static cleanTextToHTML(String text) {
        if (text == null) return ""

        return cleanContentHTML(text.replaceAll('\n', '<br />'))
    }

    static rawText(String text) {
        if (text == null) return ""

        return cleanContentHTML(text
                .replaceAll('\n', "")
                .replaceAll('\r', "")
                .replaceAll('\t', ""))
    }

    static convertHTMLtoText(String html) {
        if (html == null) return ""

        html = htmlClean(html)

        String text = html.replaceAll("</li>", "\n\r</li>")
                .replaceAll("<br>", "\n\r")
                .replaceAll("<br />", "\n\r")
                .replaceAll("<br/>", "\n\r")
                .replaceAll("&nbsp;", " ")
                .replaceAll("&bull;", "-")
                .replaceAll("<[^>]*>", "")

        return text
    }

    static String rawLastActivityProfile(String html) {
        String value = rawClean(html)
        if (value.equalsIgnoreCase("null")) {
            return "--"
        }
        return value
    }

    static String rawClean(String html) {
        if (html) {
            def emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$/
            def anchorRegex = "(?i)<a([^>]+)>(.+?)</a>"
            def phone1Regex = /(\d{3})-(\d{3})-(\d{4})/
            def phone2Regex = /(\d{3})-(\d{7})/
            def phone3Regex = /(\d{10})/
            def urlRegex = /\b(https?|ftp|file):\/\/[-A-Za-z0-9+&@#\/%?=~_|!:,.;]*[-A-Za-z0-9+&@#\/%=~_|]/

            html = Jsoup.parse(html).text()

            return html
                    .replaceAll(emailRegex, "")
                    .replaceAll(anchorRegex, "")
                    .replaceAll(urlRegex, "")
                    .replaceAll(phone1Regex, "")
                    .replaceAll(phone2Regex, "")
                    .replaceAll(phone3Regex, "")
        }
        return ""
    }

    static String htmlClean(String html, Boolean whitelist = true, Boolean cleanRegex = true) {
        if (html) {
            def emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$/
            def anchorRegex = /<\s*a[^>]*>(.*?)<\s*\/\s*a>/
            def spacesRegex = /<\s*p[^>]*>&nbsp;<\s*\/\s*p>/
            def phone1Regex = /(\d{3})-(\d{3})-(\d{4})/
            def phone2Regex = /(\d{3})-(\d{7})/
            def phone3Regex = /(\d{10})/
            def urlRegex = /\b(https?|ftp|file):\/\/[-A-Za-z0-9+&@#\/%?=~_|!:,.;]*[-A-Za-z0-9+&@#\/%=~_|]/

            if (whitelist) {
                Whitelist tagsWhitelist = new Whitelist()
                tagsWhitelist.addTags("div", "p", "b", "i", "u", "small", "br", "ul", "li", "ol", "blockquote")
                html = Jsoup.clean(html, tagsWhitelist)
            } else {
                html = Jsoup.parse(html).text()
            }

            if (cleanRegex) {
                return html
                        .replaceAll(emailRegex, "")
                        .replaceAll(anchorRegex, "")
                        .replaceAll(urlRegex, "")
                        .replaceAll(phone1Regex, "")
                        .replaceAll(phone2Regex, "")
                        .replaceAll(phone3Regex, "")
                        .replaceAll(spacesRegex, "")
                        .replaceAll("\"", "'").trim()
            } else {
                return html.replaceAll("\"", "'").trim()
            }
        }
        return ""
    }

    static String getExcerpt(String content, int size) {
        def text = htmlClean(content)
        if (text) {
            text = truncate(text, size)
        }
        return text
    }

    static String getPlainText(String content, int size = 0) {
        if (size == 0) {
            return htmlClean(content, false, false)
        } else {
            def text = htmlClean(content, false, false)
            return truncate(text, size)
        }
    }

    static liTagReporte(String url) {
        def text = validateUrlToContainsString(url)
        if (url.contains(".docx")) {
            text += " Word (.docx)"
        } else {
            text += " PDF (.pdf)"
        }

        return "<li><a href='${url}?ver=${new Date().getTime()}' target='_blank'>${text}</a></li>"
    }

    static validateUrlToContainsString(String url) {
        String text = "Documento"
        if (url.contains("Entrevista")) {
            text = "Entrevista"
        } else if (url.contains("ReporteEjecutivo")) {
            text = "ReporteEjecutivo"
        } else if (url.contains("Candidato")) {
            text = "Candidato"
        } else if (url.contains("Referencias")) {
            text = "Referencias"
        } else if (url.contains("CheckList")) {
            text = "CheckList"
        }
        return text
    }

    static getAgeByDate(Date date, int age) {
        int years = Years.yearsBetween(new DateTime(date).withTimeAtStartOfDay(), new DateTime().withTimeAtStartOfDay()).getYears()
        return (years != age) ? years : age
    }

    static getEmailEncrypted(String email) {
        byte[] apiKey = "03af6e94f6250f40e77a50f3faf4564c".getBytes(StandardCharsets.UTF_8)
        byte[] dataKey = new byte[16]
        System.arraycopy(apiKey, 0, dataKey, 0, dataKey.length)
        byte[] email_bytes = email.getBytes(StandardCharsets.UTF_8)
        SecretKeySpec key = new SecretKeySpec(dataKey, "AES")
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        IvParameterSpec ivspec = new IvParameterSpec([0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0] as byte[])
        cipher.init(Cipher.ENCRYPT_MODE, key, ivspec)
        byte[] email_encrypted = cipher.doFinal(email_bytes)
        StringBuilder buf = new StringBuilder(email_encrypted.length * 2)

        for (byte b : email_encrypted) {
            String hexDigits = Integer.toHexString((int) b & 0x00ff)
            if (hexDigits.length() == 1)
                buf.append('0')
            buf.append(hexDigits)
        }
        String encrypted_email = buf.toString()
        return encrypted_email
    }

    static truncate(String text, Integer size = 0) {
        def ellipsis = ""
        if (size > text.length()) {
            size = text.length()
        } else {
            ellipsis = "..."
        }

        if (size > 0) {
            text = "${text.substring(0, size)}${ellipsis}"
        }
        return text
    }
}
