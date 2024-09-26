package hs.kr.equus.status.infrastructure.kafka.consumer.dto

import java.util.*

data class CreateApplicationEvent(
    val receiptCode: Long,
    val userId: UUID
)
