package com.ternbusty.gsoc.adapter.lsp

import com.ternbusty.gsoc.adapter.mapper.LspMapper
import com.ternbusty.gsoc.application.service.LspApplicationService
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.TextDocumentService
import java.util.concurrent.CompletableFuture

class TextDocumentServiceImpl(
    private val lspApplicationService: LspApplicationService
) : TextDocumentService {

    override fun didOpen(params: DidOpenTextDocumentParams?) {
        // TODO: NOT IMPLEMENTED
    }

    override fun didChange(params: DidChangeTextDocumentParams?) {
        // TODO: NOT IMPLEMENTED
    }

    override fun didSave(params: DidSaveTextDocumentParams?) {
        // TODO: NOT IMPLEMENTED
    }

    override fun didClose(params: DidCloseTextDocumentParams?) {
        // TODO: NOT IMPLEMENTED
    }

    override fun completion(params: CompletionParams?): CompletableFuture<Either<List<CompletionItem>, CompletionList>> {
        val domainItems = lspApplicationService.getCompletionItems()
        val lspItems = domainItems.map { LspMapper.toLspCompletionItem(it) }
        val list = CompletionList(false, lspItems)
        return CompletableFuture.completedFuture(Either.forRight(list))
    }
}
