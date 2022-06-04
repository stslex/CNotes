package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.core_model.mapper.MapperNoteSize
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.GetRemoteNotesSizeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRemoteNotesSizeInteractorImpl(
    private val repository: ProfileRepository,
    private val mapper: MapperNoteSize
) : GetRemoteNotesSizeInteractor {

    override suspend fun invoke(): Flow<ValueState<Int>> = repository.getRemoteNotes().map {
        it.map(mapper)
    }
}