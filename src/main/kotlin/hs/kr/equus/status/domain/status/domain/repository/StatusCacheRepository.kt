package hs.kr.equus.status.domain.status.domain.repository

import hs.kr.equus.status.domain.status.domain.StatusCache
import org.springframework.data.repository.CrudRepository

interface StatusCacheRepository : CrudRepository<StatusCache, Long>
