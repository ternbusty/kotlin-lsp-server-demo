package com.ternbusty.gsoc.server

import org.eclipse.lsp4j.*
import org.eclipse.lsp4j.jsonrpc.messages.Either
import org.eclipse.lsp4j.services.*
import java.util.concurrent.CompletableFuture

/**
 * A minimal Kotlin LSP server example using LSP4J.
 * Only the "initialize" request is properly handled here, and others are handled by simple logging.
 */
class KotlinLspServer : LanguageServer, LanguageClientAware {

    private var client: LanguageClient? = null

    /**
     * Called when the client sends the "initialize" request.
     * Here, we define what capabilities this server provides.
     */
    override fun initialize(params: InitializeParams?): CompletableFuture<InitializeResult> {
        val capabilities = ServerCapabilities().apply {
            textDocumentSync = Either.forRight(
                TextDocumentSyncOptions().apply {
                    openClose = true
                    change = TextDocumentSyncKind.Incremental
                }
            )
        }
        val result = InitializeResult(capabilities)
        return CompletableFuture.completedFuture(result)
    }

    /**
     * Provides the server with a reference to the LanguageClient.
     * The server can use this to send messages back to the client if needed.
     */
    override fun connect(client: LanguageClient?) {
        this.client = client
    }

    /**
     * Called when the client requests server shutdown (e.g., when VSCode is closing).
     * We return a CompletableFuture once the server has finished any shutdown tasks.
     */
    override fun shutdown(): CompletableFuture<Any> {
        return CompletableFuture.completedFuture(Unit)
    }

    /**
     * Called when the client sends an exit notification, indicating the session is fully terminated.
     * We can clean up resources here if needed.
     */
    override fun exit() {
        // TODO: NOT IMPLEMENTED
    }

    /**
     * Provides a TextDocumentService implementation that handles text document events:
     * didOpen, didChange, didClose, and didSave.
     */
    override fun getTextDocumentService(): TextDocumentService = object : TextDocumentService {
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

        /**
         * An example of how to handle completion requests (textDocument/completion).
         * We return a single dummy completion item.
         */
        override fun completion(params: CompletionParams?): CompletableFuture<Either<List<CompletionItem>, CompletionList>> {
            val item = CompletionItem("helloFromServer").apply {
                detail = "Dummy completion from KotlinLspServer"
            }
            val list = CompletionList(false, listOf(item))
            return CompletableFuture.completedFuture(Either.forRight(list))
        }
    }

    /**
     * Provides a WorkspaceService to handle workspace-related events,
     * such as folder changes and configuration changes.
     */
    override fun getWorkspaceService(): WorkspaceService = object : WorkspaceService {
        override fun didChangeWorkspaceFolders(params: DidChangeWorkspaceFoldersParams?) {
            // TODO: NOT IMPLEMENTED
        }

        override fun didChangeConfiguration(params: DidChangeConfigurationParams?) {
            // TODO: NOT IMPLEMENTED
        }

        override fun didChangeWatchedFiles(p0: DidChangeWatchedFilesParams?) {
            // TODO: NOT IMPLEMENTED
        }
    }
}
