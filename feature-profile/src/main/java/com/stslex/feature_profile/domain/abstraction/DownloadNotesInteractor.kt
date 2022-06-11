package com.stslex.feature_profile.domain.abstraction

import com.stslex.core.ValueState

interface DownloadNotesInteractor {
    suspend operator fun invoke(): ValueState<Unit>
}