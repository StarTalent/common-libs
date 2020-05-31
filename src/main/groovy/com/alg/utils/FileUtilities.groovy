package com.alg.utils

import org.apache.commons.io.FileUtils

import java.nio.charset.StandardCharsets

class FileUtilities {
    static List<String> readLines(String path, encode = StandardCharsets.UTF_8.name()) {
        File file = new File(path)
        return FileUtils.readLines(file, "${encode}")
    }
}
