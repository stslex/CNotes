package com.stslex.core_ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.stslex.core.AppDispatchers
import com.stslex.core.error.AppLogger
import com.stslex.core.error.ErrorState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.getScopeName

abstract class BaseViewModel(
    private val dispatchers: AppDispatchers
) : ViewModel() {

    private val _errorState: MutableStateFlow<ErrorState> = MutableStateFlow(ErrorState.Default)
    val errorState: StateFlow<ErrorState>
        get() = _errorState.primaryStateFlow(ErrorState.Default)

    val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            onErrorHandler(
                throwable = throwable,
                destination = coroutineContext.javaClass.simpleName
            )
        }

    val <T : Any> Flow<PagingData<T>>.pagingStateFlow: StateFlow<PagingData<T>>
        get() = cachedIn(viewModelScope)
            .primaryStateFlow(initialValue = PagingData.empty())

    private fun <T> Flow<T>.primaryStateFlow(initialValue: T): StateFlow<T> = catch { throwable ->
        onErrorHandler(
            throwable = throwable,
            destination = getScopeName().value
        )
    }
        .flowOn(dispatchers.io)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = initialValue
        )

    private fun onErrorHandler(
        throwable: Throwable,
        destination: String = getScopeName().value
    ) {
        _errorState.value = ErrorState.Error(throwable)
        AppLogger.logException(
            throwable = throwable,
            destination = AppLogger.getExceptionTag(javaClass.simpleName, destination)
        )
    }
}