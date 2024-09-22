package hs.kr.equus.status.infrastructure.kafka.producer

import hs.kr.equus.status.infrastructure.kafka.config.KafkaTopics
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class StatusProducer(
    private val createApplicationRollbackTemplate: KafkaTemplate<String, Any>
) {
    fun createApplicationStatusRollback(receiptCode: Long)  {
        createApplicationRollbackTemplate.send(
            KafkaTopics.CREATE_APPLICATION_STATUS_ROLLBACK,
            receiptCode
        )
    }
}