package hs.kr.equus.status.domain.status.domain.repository

import hs.kr.equus.status.domain.status.domain.Status
import org.springframework.data.repository.CrudRepository

interface StatusRepository : CrudRepository<Status, Long> {
    fun findByReceiptCode(receiptCode: Long): Status?
    fun deleteByReceiptCode(receiptCode: Long)
}
