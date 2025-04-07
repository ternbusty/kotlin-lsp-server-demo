package com.ternbusty.gsoc.adapter.mapper

import com.ternbusty.gsoc.domain.model.CompletionItemEntity
import org.eclipse.lsp4j.CompletionItem

object LspMapper {
    fun toLspCompletionItem(entity: CompletionItemEntity): CompletionItem {
        return CompletionItem(entity.label).apply {
            detail = entity.detail
        }
    }
}
