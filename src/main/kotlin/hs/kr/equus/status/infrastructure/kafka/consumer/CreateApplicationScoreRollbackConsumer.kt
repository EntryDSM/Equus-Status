package hs.kr.equus.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.status.domain.status.service.DeleteStatusService
import hs.kr.equus.status.infrastructure.kafka.config.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CreateApplicationScoreRollbackConsumer (
    private val objectMapper: ObjectMapper,
    private val deleteStatusService: DeleteStatusService
) {

    @KafkaListener(
        topics = [KafkaTopics.CREATE_APPLICATION_SCORE_ROLLBACK],
        groupId = "create-score-status-rollback",
        containerFactory = "kafkaListenerContainerFactory",
    )
    fun createScoreRollback(message: String) {
        val receiptCode = objectMapper.readValue(message, Long::class.java)
        deleteStatusService.execute(receiptCode)
    }
}