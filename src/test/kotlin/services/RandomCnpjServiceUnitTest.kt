package services

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomCnpjService
import com.intellij.openapi.components.service
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class RandomCnpjServiceUnitTest: BasePlatformTestCase() {

        fun testGenerateRandomCnpj() {
            val service = project.service<RandomCnpjService>()

            val cnpj = service.generateRandomCnpj()
            assertTrue(cnpj.length == 18)
            assertTrue(cnpj.matches(Regex("\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}")))

            val cnpjDigits = cnpj.replace(".", "").replace("-", "").replace("/", "")
            assertTrue(cnpjDigits.length == 14)
            assertTrue(cnpjDigits.matches(Regex("\\d+")))

            val dv1 = cnpjDigits[12].toString().toInt()
            val dv2 = cnpjDigits[13].toString().toInt()
            assertTrue(dv1 in 0..9)
            assertTrue(dv2 in 0..9)

            val cnpjDigitsWithoutDv = cnpjDigits.substring(0, 12)
            assertTrue(cnpjDigitsWithoutDv.matches(Regex("\\d+")))

            var dv1Calc = 0
            var dv2Calc = 0
            for (i in 0..11) {
                val digit = cnpjDigitsWithoutDv[i].toString().toInt()
                dv1Calc += digit * (5 - (i % 4))
                dv2Calc += digit * (6 - (i % 5))
            }
        }

        override fun getTestDataPath() = "src/test/testData"
}