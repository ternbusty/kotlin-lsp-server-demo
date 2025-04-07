package com.ternbusty.gsoc.domain.usecase

import com.ternbusty.gsoc.domain.model.CompletionItemEntity

/**
 * Use case for generating completion items. (Returning dummy data)
 *
 * This use case is responsible for generating completion items that can be used in the editor.
 * It can be extended to include more complex logic, such as fetching data from a database or an API.
 */
class CompletionUseCase {
    fun generateCompletionItems(): List<CompletionItemEntity> {
        return listOf(
            CompletionItemEntity("helloFromServer", "Dummy completion from KotlinLspServer")
        )
    }
}
