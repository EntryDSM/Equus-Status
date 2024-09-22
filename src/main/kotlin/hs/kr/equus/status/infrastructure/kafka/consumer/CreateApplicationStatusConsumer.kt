package hs.kr.equus.status.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.status.domain.status.service.CreateStatusService
import hs.kr.equus.status.domain.status.service.UpdateStatusService
import hs.kr.equus.status.infrastructure.kafka.config.KafkaTopics
import hs.kr.equus.status.infrastructure.kafka.producer.StatusProducer
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Recover
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Component

@Component
class CreateApplicationStatusConsumer(
    private val mapper: ObjectMapper,
    private val createStatusService: CreateStatusService,
    private val statusProducer: StatusProducer
) {

    @Retryable(
        value = [Exception::class],
        maxAttempts = 3,
        backoff = Backoff(delay = 100)
    )
    @KafkaListener(
        topics = [KafkaTopics.CREATE_APPLICATION],
        groupId = "create-status",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun createStatus(message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        createStatusService.execute(receiptCode)
    }

    @Recover
    fun recover(exception: Exception, message: String) {
        val receiptCode = mapper.readValue(message, Long::class.java)
        statusProducer.createApplicationStatusRollback(receiptCode)
    }
}