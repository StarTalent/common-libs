package com.alg.utils;

import java.util.HashMap;
import java.util.Map;

public class HtmlCleanUtilities {
    private static final char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private HtmlCleanUtilities() {
        throw new IllegalStateException("Utility class");
    }

    public static String escapeTextArea(String original) {
        return escapeSpecial(escapeTags(original));
    }

    public static String escape(String original) {
        return escapeSpecial(escapeBr(escapeTags(original)));
    }

    private static String escapeTags(String original) {
        if (original == null) return "";
        StringBuilder out = new StringBuilder();
        char[] chars = original.toCharArray();
        for (char aChar : chars) {
            boolean found = true;
            switch (aChar) {
                case 60:
                    out.append("&lt;");
                    break; //<
                case 62:
                    out.append("&gt;");
                    break; //>
                case 34:
                    out.append("&quot;");
                    break; //"
                default:
                    found = false;
                    break;
            }
            if (!found) out.append(aChar);

        }
        return out.toString();

    }

    private static String escapeBr(String original) {
        if (original == null) return "";
        StringBuilder out = new StringBuilder();
        char[] chars = original.toCharArray();
        for (char aChar : chars) {
            boolean found = true;
            switch (aChar) {
                case '\n':
                    out.append("<br/>");
                    break;
                case '\r':
                    break;
                default:
                    found = false;
                    break;
            }
            if (!found) out.append(aChar);

        }
        return out.toString();
    }

    public static String escapeSpecial(String original) {
        if (original == null) return "";
        StringBuilder out = new StringBuilder();
        char[] chars = original.toCharArray();
        Map<Integer, String> characters = characterMap();

        for (char aChar : chars) {
            boolean found = false;
            if (characters.containsKey((int) aChar)) {
                out.append(characters.get((int) aChar));
                found = true;
            }
            if (!found) {
                if (aChar > 127) {
                    char c = aChar;
                    int a4 = c % 16;
                    c = (char) (c / 16);
                    int a3 = c % 16;
                    c = (char) (c / 16);
                    int a2 = c % 16;
                    c = (char) (c / 16);
                    int a1 = c % 16;
                    out.append("&#x").append(hex[a1]).append(hex[a2]).append(hex[a3]).append(hex[a4]).append(";");
                } else {
                    out.append(aChar);
                }
            }
        }
        return out.toString();
    }

    private static Map<Integer, String> characterMap() {
        Map<Integer,String> characters = new HashMap<>();
        characters.put(38, "&amp;");
        characters.put(162, "&cent;");
        characters.put(192, "&Agrave;");
        characters.put(193, "&Aacute;");
        characters.put(194, "&Acirc;");
        characters.put(195, "&Atilde;");
        characters.put(196, "&Auml;");
        characters.put(197, "&Aring;");
        characters.put(198, "&AElig;");
        characters.put(199, "&Ccedil;");
        characters.put(200, "&Egrave;");
        characters.put(201, "&Eacute;");
        characters.put(202, "&Ecirc;");
        characters.put(203, "&Euml;");
        characters.put(204, "&Igrave;");
        characters.put(205, "&Iacute;");
        characters.put(206, "&Icirc;");
        characters.put(207, "&Iuml;");
        characters.put(208, "&ETH;");
        characters.put(209, "&Ntilde;");
        characters.put(210, "&Ograve;");
        characters.put(211, "&Oacute;");
        characters.put(212, "&Ocirc;");
        characters.put(213, "&Otilde;");
        characters.put(214, "&Ouml;");
        characters.put(216, "&Oslash;");
        characters.put(217, "&Ugrave;");
        characters.put(218, "&Uacute;");
        characters.put(219, "&Ucirc;");
        characters.put(220, "&Uuml;");
        characters.put(221, "&Yacute;");
        characters.put(222, "&THORN;");
        characters.put(223, "&szlig;");
        characters.put(224, "&agrave;");
        characters.put(225, "&aacute;");
        characters.put(226, "&acirc;");
        characters.put(227, "&atilde;");
        characters.put(228, "&auml;");
        characters.put(229, "&aring;");
        characters.put(230, "&aelig;");
        characters.put(231, "&ccedil;");
        characters.put(232, "&egrave;");
        characters.put(233, "&eacute;");
        characters.put(234, "&ecirc;");
        characters.put(235, "&euml;");
        characters.put(236, "&igrave;");
        characters.put(237, "&iacute;");
        characters.put(238, "&icirc;");
        characters.put(239, "&iuml;");
        characters.put(240, "&eth;");
        characters.put(241, "&ntilde;");
        characters.put(242, "&ograve;");
        characters.put(243, "&oacute;");
        characters.put(244, "&ocirc;");
        characters.put(245, "&otilde;");
        characters.put(246, "&ouml;");
        characters.put(248, "&oslash;");
        characters.put(249, "&ugrave;");
        characters.put(250, "&uacute;");
        characters.put(251, "&ucirc;");
        characters.put(252, "&uuml;");
        characters.put(253, "&yacute;");
        characters.put(254, "&thorn;");
        characters.put(255, "&yuml;");
        return characters;
    }
}
