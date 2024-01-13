package services

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCpfService
import com.intellij.openapi.components.service
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class RandomCpfServiceUnitTest : BasePlatformTestCase() {

    fun testRandomCpf() {
        val service = project.service<RandomCpfService>()

        val cpf = service.generateRandomCpf()

        assertEquals(14, cpf.length)
        assertEquals(3, cpf.split(".").size)
        assertEquals(2, cpf.split("-").size)

        val cpfDigits = cpf.replace(".", "").replace("-", "")
        assertEquals(11, cpfDigits.length)
        assertTrue(cpfDigits.matches(Regex("\\d+")))

        val dv1 = cpfDigits[9].toString().toInt()
        val dv2 = cpfDigits[10].toString().toInt()
        assertTrue(dv1 in 0..9)
        assertTrue(dv2 in 0..9)

        val cpfDigitsWithoutDv = cpfDigits.substring(0, 9)
        assertTrue(cpfDigitsWithoutDv.matches(Regex("\\d+")))

        var dv1Calc = 0
        var dv2Calc = 0
        for (i in 0..8) {
            val digit = cpfDigitsWithoutDv[i].toString().toInt()
            dv1Calc += digit * (10 - i)
            dv2Calc += digit * (11 - i)
        }
    }

    override fun getTestDataPath() = "src/test/testData"
}