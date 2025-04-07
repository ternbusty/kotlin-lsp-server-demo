package com.ternbusty.gsoc.server

import com.ternbusty.gsoc.adapter.lsp.TextDocumentServiceImpl
import com.ternbusty.gsoc.application.service.LspApplicationService
import com.ternbusty.gsoc.domain.usecase.CompletionUseCase
import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.LanguageClient
import org.eclipse.lsp4j.services.LanguageClientAware
import org.eclipse.lsp4j.services.LanguageServer
import org.eclipse.lsp4j.services.WorkspaceService
import java.util.concurrent.CompletableFuture

class KotlinLspServer : LanguageServer, LanguageClientAware {

    private var client: LanguageClient? = null

    private val completionUseCase = CompletionUseCase()
    private val lspApplicationService = LspApplicationService(completionUseCase)
    private val textDocumentService = TextDocumentServiceImpl(lspApplicationService)

    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        val capabilities = ServerCapabilities().apply {
            textDocumentSync = Either.forRight(
                TextDocumentSyncOptions().apply {
                    openClose = true
                    change = TextDocumentSyncKind.Incremental
                }
            )
            completionProvider = CompletionOptions().apply {
                resolveProvider = false
                triggerCharacters = listOf(".", "\"", "'")
            }
        }
        val result = InitializeResult(capabilities)
        return CompletableFuture.completedFuture(result)
    }

    override fun connect(client: LanguageClient?) {
        this.client = client
    }

    override fun shutdown(): CompletableFuture<Any> {
        return CompletableFuture.completedFuture(Unit)
    }

    override fun exit() {
        // TODO: NOT IMPLEMENTED
    }

    override fun getTextDocumentService(): org.eclipse.lsp4j.services.TextDocumentService = textDocumentService

    override fun getWorkspaceService(): WorkspaceService = object : WorkspaceService {
        override fun didChangeWorkspaceFolders(params: DidChangeWorkspaceFoldersParams?) {
            // TODO: NOT IMPLEMENTED
        }

        override fun didChangeConfiguration(params: DidChangeConfigurationParams?) {
            // TODO: NOT IMPLEMENTED
        }

        override fun didChangeWatchedFiles(params: DidChangeWatchedFilesParams?) {
            // TODO: NOT IMPLEMENTED
        }
    }
}
