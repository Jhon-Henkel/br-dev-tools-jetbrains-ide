package com.github.jhonhenkel.brdevtoolsjetbrainside.services

import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project
import kotlin.math.floor

@Service(Service.Level.PROJECT)
class RandomCpfService(project: Project) {

    fun generateRandomCpf(): String {
        var dv2 = 1
        var cpf = ""
        while ((dv2 % 2) != 0) {
            val n1 = getRandomInt(0, 9);
            val n2 = getRandomInt(0, 9);
            val n3 = getRandomInt(0, 9);
            val n4 = getRandomInt(0, 9);
            val n5 = getRandomInt(0, 9);
            val n6 = getRandomInt(0, 9);
            val n7 = getRandomInt(0, 9);
            val n8 = getRandomInt(0, 9);
            val n9 = getRandomInt(0, 9);
            var dv1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;
            dv1 = cpfVerificationDigit(dv1);
            dv2 = dv1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;
            dv2 = cpfVerificationDigit(dv2);
            cpf = "$n1$n2$n3.$n4$n5$n6.$n7$n8$n9-$dv1$dv2"
        }
        return cpf
    }

    private fun cpfVerificationDigit(dv:Int): Int {
        val digit = 11 - (dv % 11)
        if (isEqualsOrMoreGreatThanTen(digit)) {
            return 0
        }
        return digit
    }

    private fun isEqualsOrMoreGreatThanTen(number:Int): Boolean {
        return number >= 10
    }

    fun getRandomInt(min:Int, max:Int): Int {
        return floor(Math.random() * (max - min + 1)).toInt() + min
    }
}
