package com.myfitnessbag.order.core.data.source.remote

import com.myfitnessbag.order.core.domain.model.AuthCredMethod
import com.myfitnessbag.order.core.domain.model.AuthCredentials
import com.myfitnessbag.order.core.util.constants.AppConst
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.headers
import io.ktor.client.utils.EmptyContent.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json


class HttpClientFactory(
    private val engine: HttpClientEngine,
    private val additionalHeaders: Map<String, String> = emptyMap(),
    private val credentials: AuthCredentials? = null
) {
    fun create(
    ): HttpClient {
        return HttpClient(engine) {
            credentials?.let {
                install(Auth) {
                    basic {
                        credentials {
                            BasicAuthCredentials(
                                username = it.key,
                                password = it.secret,
                            )

                        }
                    }
                }

            }


            install(Logging) {
                level = LogLevel.ALL
            }
            install(
                ContentNegotiation,
            ) {
                json(
                    json = Json {
                        ignoreUnknownKeys = true
                        encodeDefaults = true
                    }
                )
            }
            defaultRequest {
                headers {
                    additionalHeaders.forEach { (key, value) ->
                        append(key, value)
                    }
                }
            }
        }
    }


}
