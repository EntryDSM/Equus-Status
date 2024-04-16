package hs.kr.equus.status.infrastructure.kafka.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kafka")
class KafkaProperty(
    val serverAddress: String
)
