package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.domain.abstraction.GetSyncNotesSizeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flowOf

class GetSyncNotesSizeInteractorImpl(
    private val repository: ProfileRepository,
    private val transformer: TransformerEqualTypeValues<List<NoteEntity>, Int>
) : GetSyncNotesSizeInteractor {

    override suspend fun invoke(): Flow<ValueState<Int>> = flowOf(repository.getLocalNotes())
        .combineTransform(repository.getRealtimeRemoteNotes()) { local, remote ->
            val result = local.transform(transformer, remote)
            emit(result)
        }
}