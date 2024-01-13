package services

import com.github.jhonhenkel.brdevtoolsjetbrainside.services.RandomNumberService
import com.intellij.testFramework.fixtures.BasePlatformTestCase

class RandomNumberServiceUnitTest : BasePlatformTestCase() {

    fun testGetRandomInt() {
        assertTrue(RandomNumberService.getRandomInt(0, 10) in 0..10)
        assertTrue(RandomNumberService.getRandomInt(10, 100) in 10..100)
        assertTrue(RandomNumberService.getRandomInt(50, 58) in 50..58)
    }

    fun testGetVerificationDigit() {
        assertTrue(RandomNumberService.getVerificationDigit(50) == (11 - (50 % 11)))
        assertTrue(RandomNumberService.getVerificationDigit(99) == 0)
    }

    override fun getTestDataPath() = "src/test/testData"
}