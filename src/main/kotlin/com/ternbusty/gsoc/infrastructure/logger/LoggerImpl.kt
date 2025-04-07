package com.ternbusty.gsoc.infrastructure.logger

import org.slf4j.LoggerFactory

object LoggerImpl {
    private val logger = LoggerFactory.getLogger(LoggerImpl::class.java)

    fun log(message: String) {
        logger.info(message)
    }
}
