package com.ternbusty.gsoc.application.service

import com.ternbusty.gsoc.domain.usecase.CompletionUseCase

class LspApplicationService(private val completionUseCase: CompletionUseCase) {
    fun getCompletionItems() = completionUseCase.generateCompletionItems()
}
