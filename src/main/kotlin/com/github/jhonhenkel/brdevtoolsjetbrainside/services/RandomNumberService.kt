package com.github.jhonhenkel.brdevtoolsjetbrainside.services

import kotlin.math.floor

class RandomNumberService {
    companion object {
        private fun isEqualsOrMoreGreatThanTen(number:Int): Boolean {
            return number >= 10
        }

        fun getVerificationDigit(dv:Int): Int {
            val digit = 11 - (dv % 11)
            if (isEqualsOrMoreGreatThanTen(digit)) {
                return 0
            }
            return digit
        }

        fun getRandomInt(min:Int, max:Int): Int {
            return floor(Math.random() * (max - min + 1)).toInt() + min
        }
    }
}