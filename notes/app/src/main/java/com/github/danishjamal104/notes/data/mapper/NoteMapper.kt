package com.github.danishjamal104.notes.data.mapper

import com.github.danishjamal104.notes.data.entity.cache.NoteCacheEntity
import com.github.danishjamal104.notes.data.mapper.generic.AbstractMapper
import com.github.danishjamal104.notes.data.model.Note
import javax.inject.Inject

class NoteMapper
@Inject
constructor(): AbstractMapper<NoteCacheEntity, Note>() {

    override fun mapFromEntity(entity: NoteCacheEntity): Note {
        return Note(entity.id, entity.userId, entity.value, entity.timestamp)
    }

    override fun mapToEntity(domainModel: Note): NoteCacheEntity {
        return NoteCacheEntity(domainModel.userId,
            domainModel.value, domainModel.timestamp)
    }
}