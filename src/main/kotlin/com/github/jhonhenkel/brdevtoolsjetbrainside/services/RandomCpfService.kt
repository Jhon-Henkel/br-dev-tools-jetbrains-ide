package com.github.jhonhenkel.brdevtoolsjetbrainside.services

import com.intellij.openapi.components.Service

@Service(Service.Level.PROJECT)
class RandomCpfService {

    fun generateRandomCpf(): String {
        var dv2 = 1
        var cpf = ""
        while ((dv2 % 2) != 0) {
            val n1 = RandomNumberService.getRandomInt(0, 9)
            val n2 = RandomNumberService.getRandomInt(0, 9)
            val n3 = RandomNumberService.getRandomInt(0, 9)
            val n4 = RandomNumberService.getRandomInt(0, 9)
            val n5 = RandomNumberService.getRandomInt(0, 9)
            val n6 = RandomNumberService.getRandomInt(0, 9)
            val n7 = RandomNumberService.getRandomInt(0, 9)
            val n8 = RandomNumberService.getRandomInt(0, 9)
            val n9 = RandomNumberService.getRandomInt(0, 9)
            var dv1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10
            dv1 = RandomNumberService.getVerificationDigit(dv1)
            dv2 = dv1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11
            dv2 = RandomNumberService.getVerificationDigit(dv2)
            cpf = "$n1$n2$n3.$n4$n5$n6.$n7$n8$n9-$dv1$dv2"
        }
        return cpf
    }
}
