package hs.kr.equus.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.status.domain.status.service.CreateStatusService
import hs.kr.equus.status.domain.status.service.UpdateStatusService
import hs.kr.equus.status.infrastructure.kafka.config.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class StatusConsumer(
    private val mapper: ObjectMapper,
    private val createStatusService: CreateStatusService,
    private val updateStatusService: UpdateStatusService
) {

    @KafkaListener(
        topics = [KafkaTopics.SUBMIT_APPLICATION_FINAL],
        groupId = "update-status",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun updateStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        updateStatusService.execute(receiptCode)
    }
}
