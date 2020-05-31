package com.alg.utils

import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j

@Slf4j
class JsonUtilities {
    static Map getJsonMap(String jsonRaw) {
        try {
            if (jsonRaw != null && jsonRaw instanceof String && !jsonRaw.isEmpty()) {
                def json = new JsonSlurper()
                def object = json.parseText(jsonRaw)
                if (object instanceof Map) {
                    object.put("isArray", false)
                    return object
                }
                if (object instanceof ArrayList) {
                    def data = [isArray: true, list: object]
                    return data
                }
            }
        } catch (Exception e) {
            log.error("Error al procesar los datos del json", e)
        }

        return [:]
    }
}
