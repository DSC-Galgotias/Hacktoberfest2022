package com.github.danishjamal104.notes.data.mapper

import com.github.danishjamal104.notes.data.entity.cache.UserCacheEntity
import com.github.danishjamal104.notes.data.mapper.generic.AbstractMapper
import com.github.danishjamal104.notes.data.model.User
import javax.inject.Inject

class UserMapper
@Inject
constructor():  AbstractMapper<UserCacheEntity, User>() {

    override fun mapFromEntity(entity: UserCacheEntity): User {
        return User(entity.id, entity.username, entity.email)
    }

    override fun mapToEntity(domainModel: User): UserCacheEntity {
        return UserCacheEntity(domainModel.id, domainModel.username, domainModel.email)
    }
}