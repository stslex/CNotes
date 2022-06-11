package com.stslex.feature_profile.domain.realisation

import com.stslex.core.ValueState
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.transformer.TransformerEqualTypeValues
import com.stslex.feature_profile.data.ProfileRepositoryOld
import com.stslex.feature_profile.domain.abstraction.GetSyncNotesSizeInteractor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combineTransform

class GetSyncNotesSizeInteractorImpl(
    private val repository: ProfileRepositoryOld,
    private val transformer: TransformerEqualTypeValues<List<NoteEntity>, Int>
) : GetSyncNotesSizeInteractor {

    override suspend fun invoke(): Flow<ValueState<Int>> = repository.getLocalNotes()
        .combineTransform(repository.getRemoteNotes()) { local, remote ->
            val result = local.transform(transformer, remote)
            emit(result)
        }
}