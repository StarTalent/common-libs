package com.alg.validations

import groovy.util.logging.Slf4j

@Slf4j
class PasswordChecker {
    private final static int PASSWORD_MIN_SIZE = 5
    private final static int PASSWORD_MAX_SIZE = 24

    static passwordValidCheck(password = "", confirmPassword = "") {
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            log.debug("El password o la confirmación estan en blanco [$password:$confirmPassword]")
            return false
        }
        if (password.size() <= PASSWORD_MIN_SIZE || password.size() >= PASSWORD_MAX_SIZE) {
            log.debug("El tamano del password debe ser mayor a $PASSWORD_MIN_SIZE y menor que $PASSWORD_MAX_SIZE caracteres [${password.size()}]")
            return false
        }
        if (password != confirmPassword) {
            log.debug("El password y la confirmación son diferentes [$password:$confirmPassword]")
            return false
        }
        return true
    }
}
