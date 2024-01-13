package com.github.jhonhenkel.brdevtoolsjetbrainside.services

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class RandomCnpjService {

    fun generateRandomCnpj(): String {
        var dv2 = 1
        var cnpj = ""
        while ((dv2 % 2) != 0) {
            val n1 = RandomNumberService.getRandomInt(0, 9)
            val n2 = RandomNumberService.getRandomInt(0, 9)
            val n3 = RandomNumberService.getRandomInt(0, 9)
            val n4 = RandomNumberService.getRandomInt(0, 9)
            val n5 = RandomNumberService.getRandomInt(0, 9)
            val n6 = RandomNumberService.getRandomInt(0, 9)
            val n7 = RandomNumberService.getRandomInt(0, 9)
            val n8 = RandomNumberService.getRandomInt(0, 9)
            val n9 = 0
            val n10 = 0
            val n11 = 0
            val n12 = 1

            var dv1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5
            dv1 = RandomNumberService.getVerificationDigit(dv1)

            dv2 = dv1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6
            dv2 = RandomNumberService.getVerificationDigit(dv2)
            cnpj = "$n1$n2.$n3$n4$n5.$n6$n7$n8/$n9$n10$n11$n12-$dv1$dv2"
        }
        return cnpj
    }
}