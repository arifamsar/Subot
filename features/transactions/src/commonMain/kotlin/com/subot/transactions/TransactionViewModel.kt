package com.subot.transactions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subot.core.data.service.UserPreferences
import com.subot.core.domain.result.ApiResult
import com.subot.core.domain.usecase.GetInvoicesUseCase
import com.subot.core.domain.usecase.GetPaymentHistoryUseCase
import com.subot.core.domain.usecase.RequestSnapTokenUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TransactionViewModel(
    private val getInvoicesUseCase: GetInvoicesUseCase,
    private val getPaymentHistoryUseCase: GetPaymentHistoryUseCase,
    private val requestSnapTokenUseCase: RequestSnapTokenUseCase,
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            userPreferences.userRoleFlow().collectLatest { role ->
                _uiState.update { it.copy(userRole = role) }
            }
        }
        onEvent(TransactionEvent.LoadInvoices)
        onEvent(TransactionEvent.LoadPaymentHistory)
    }

    fun onEvent(event: TransactionEvent) {
        when (event) {
            is TransactionEvent.LoadInvoices -> loadInvoices()
            is TransactionEvent.LoadPaymentHistory -> loadPaymentHistory()
            is TransactionEvent.RequestSnapToken -> requestSnapToken(event.tagihanId)
            is TransactionEvent.Refresh -> {
                loadInvoices()
                loadPaymentHistory()
            }
            is TransactionEvent.ClearSnapToken -> _uiState.update { it.copy(snapToken = null) }
        }
    }

    private fun loadInvoices() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = getInvoicesUseCase()) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            invoices = result.data,
                            error = null
                        ) 
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = result.message
                        ) 
                    }
                }
                is ApiResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun loadPaymentHistory() {
        viewModelScope.launch {
            // We don't set isLoading here if invoices are already loading to avoid multiple shimmers
            when (val result = getPaymentHistoryUseCase()) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            paymentHistory = result.data.items,
                        ) 
                    }
                }
                is ApiResult.Error -> {
                    // Fail silently or handle error if needed
                }
                is ApiResult.Loading -> {}
            }
        }
    }

    private fun requestSnapToken(tagihanId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = requestSnapTokenUseCase(tagihanId)) {
                is ApiResult.Success -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            snapToken = result.data,
                            error = null
                        ) 
                    }
                }
                is ApiResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            error = result.message
                        ) 
                    }
                }
                is ApiResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
