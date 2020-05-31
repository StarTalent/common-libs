package com.alg.utils

import groovy.util.logging.Slf4j

@Slf4j
class MapUtilities {
    static List getMapFromListString(List<String> list) {
        def result = []
        list.each {
            def map = [:]
            def data = Arrays.asList(it.split('\\|'))
            try {
                map.put('id', Integer.parseInt(data.get(0)))
                map.put('rfc', data.get(1))
                map.put('result', data.get(2))
                result << map
            } catch (Exception e) {
                log.error("No se pudo generar el map ${list}", e)
            }
        }
        return result
    }
}
