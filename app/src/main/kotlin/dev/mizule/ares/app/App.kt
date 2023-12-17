/*
 * This file is part of Ares, licensed under the MIT License.
 *
 * Copyright (c) 2023 powercas_gamer
 * Copyright (c) 2023 contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.mizule.ares.app

import com.varabyte.kotter.foundation.input.multilineInput
import com.varabyte.kotter.foundation.input.onInputEntered
import com.varabyte.kotter.foundation.input.runUntilInputEntered
import com.varabyte.kotter.foundation.session
import com.varabyte.kotter.foundation.text.textLine
import dev.mizule.ares.app.config.Config
import io.github.oshai.kotlinlogging.KotlinLogging

private val logger = KotlinLogging.logger {}

class App(private var config: Config) {

    fun start() {
        session {
            lateinit var message: String

            section {
                textLine("Please type a multiline chat message. ENTER to add newlines and CTRL-D to finish:")
                multilineInput()
            }.runUntilInputEntered() {
                onInputEntered {
                    message = input
                }
            }

            section {
                textLine()
                textLine("You typed: \"${message.replace("\n", "\\n")}\"")
                textLine()
            }.run()
        }
        logger.info { "Starting... ${Constants.VERSION} ${Constants.GIT_BRANCH} ${Constants.GIT_COMMIT}" }
    }

    fun stop() {
        logger.info { "Shutting down... ${config.example}" }
    }
}
