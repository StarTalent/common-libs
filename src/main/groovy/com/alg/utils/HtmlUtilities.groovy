package com.alg.utils

import groovy.util.logging.Slf4j
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup

@Slf4j
class HtmlUtilities {

    static escapeTextArea(original) {
        return escapeSpecial(escapeTags(original))
    }

    static escape(original) {
        return escapeSpecial(escapeBr(escapeTags(original)))
    }

    static escapeTags(original) {
        StringBuffer out = new StringBuffer("")
        if (original instanceof String) {
            if (original == null) return ""

            char[] chars = original.toCharArray()
            for (int i = 0; i < chars.length; i++) {
                boolean found = true
                switch (chars[i]) {
                    case 60: out.append("&lt;"); break
                    case 62: out.append("&gt;"); break
                    case 34: out.append("&quot;"); break
                    default: found = false; break
                }
                if (!found) out.append(chars[i])

            }
        }

        return out.toString()
    }

    static escapeBr(original) {
        StringBuffer out = new StringBuffer("")
        if (original instanceof String) {
            if (original == null) return ""
            char[] chars = original.toCharArray()
            for (int i = 0; i < chars.length; i++) {
                boolean found = true
                switch (chars[i]) {
                    case '\n': out.append("<br/>"); break
                    case '\r': break
                    default: found = false; break
                }
                if (!found) out.append(chars[i])

            }
        }
        return out.toString()
    }

    static escapeSpecial(original) {
        if (original == null) return ""
        StringBuffer out = new StringBuffer("")
        out.append("${original}")
        return StringEscapeUtils.unescapeHtml4(HtmlCleanUtilities.escapeSpecial(out.toString()))
    }

    static convertHtmlToPlainText(html, size = 0) {
        def text = Jsoup.parse("${html}").text()
        return StringUtilities.truncate(text, size)
    }
}
