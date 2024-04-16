package hs.kr.equus.status.domain.status.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Status(
    var isPrintsArrived: Boolean = false,
    var isSubmitted: Boolean = false,
    @Column(unique = true)
    var examCode: String? = null,
    var isFirstRoundPass: Boolean = false,
    var isSecondRoundPass: Boolean = false,
    val receiptCode: Long
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun cancelSubmit() {
        isSubmitted = false
    }
}
