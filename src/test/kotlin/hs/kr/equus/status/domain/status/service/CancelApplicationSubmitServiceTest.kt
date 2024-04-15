package hs.kr.equus.status.domain.status.service

import hs.kr.equus.status.domain.status.domain.Status
import hs.kr.equus.status.domain.status.domain.repository.StatusRepository
import hs.kr.equus.status.domain.status.exception.StatusNotFoundException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class CancelApplicationSubmitServiceTest {
    @MockBean
    private lateinit var statusRepository: StatusRepository

    private lateinit var cancelApplicationSubmitService: CancelApplicationSubmitService

    private val receiptCode = 1L

    @BeforeEach
    fun setUp() {
        cancelApplicationSubmitService = CancelApplicationSubmitService(statusRepository)
    }

    @Test
    fun 원서접수_취소_성공() {
        //given
        val status = Status(
            isSubmitted = true,
            receiptCode = 1L
        )
        given(statusRepository.findByReceiptCode(receiptCode))
            .willReturn(status)
        //when
        cancelApplicationSubmitService.execute(receiptCode)

        //then
        assertEquals(status.isSubmitted, false)
    }

    @Test
    fun 상태_엔티티_없을시_실패() {
        given(statusRepository.findByReceiptCode(receiptCode))
            .willReturn(null)
        //when
        assertThrows<StatusNotFoundException> { cancelApplicationSubmitService.execute(receiptCode) }
    }
}