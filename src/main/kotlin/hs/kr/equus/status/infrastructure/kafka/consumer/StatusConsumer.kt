package hs.kr.equus.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.status.domain.status.service.CreateStatusService
import hs.kr.equus.status.domain.status.service.DeleteStatusService
import hs.kr.equus.status.domain.status.service.UpdateStatusService
import hs.kr.equus.status.infrastructure.kafka.config.KafkaTopics
import hs.kr.equus.status.infrastructure.kafka.consumer.dto.CreateApplicationEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class StatusConsumer(
    private val mapper: ObjectMapper,
    private val createStatusService: CreateStatusService,
    private val updateStatusService: UpdateStatusService,
    private val deleteStatusService: DeleteStatusService
) {
    @KafkaListener(
        topics = [KafkaTopics.CREATE_APPLICATION],
        groupId = "create-status",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun createStatus(message: String) {
        val createApplicationEvent = mapper.readValue(message, CreateApplicationEvent::class.java)
        createStatusService.execute(createApplicationEvent.receiptCode)
    }

    @KafkaListener(
        topics = [KafkaTopics.SUBMIT_APPLICATION_FINAL],
        groupId = "update-status",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun updateStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        updateStatusService.execute(receiptCode)
    }

    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-status",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun deleteStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        deleteStatusService.execute(receiptCode)
    }
}
