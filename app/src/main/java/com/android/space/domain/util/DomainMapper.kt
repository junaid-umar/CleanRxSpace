package com.android.space.domain.util

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(t: T): DomainModel
    fun mapFromDomainModel(domain: DomainModel): T
}