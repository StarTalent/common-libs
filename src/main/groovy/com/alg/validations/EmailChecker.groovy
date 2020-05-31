package com.alg.validations

import org.apache.commons.validator.routines.EmailValidator

class EmailChecker {
    static emailValidCheck(emailAddress = "") {
        EmailValidator emailValidator = EmailValidator.getInstance()
        if (emailAddress && !"${emailAddress}".allWhitespace) {
            if (emailValidator.isValid("${emailAddress}".toString())) {
                return true
            }
        }
        return false
    }
}
