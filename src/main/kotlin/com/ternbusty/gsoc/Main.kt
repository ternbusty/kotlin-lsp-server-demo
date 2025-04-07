package com.ternbusty.gsoc

import com.ternbusty.gsoc.server.KotlinLspServer
import org.eclipse.lsp4j.launch.LSPLauncher

fun main() {
    try {
        val server = KotlinLspServer()
        val launcher = LSPLauncher.createServerLauncher(
            server,
            System.`in`,
            System.out
        )
        val client = launcher.remoteProxy
        server.connect(client)
        launcher.startListening()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
