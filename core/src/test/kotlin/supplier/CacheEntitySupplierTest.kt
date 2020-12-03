package dev.kord.core.supplier

import dev.kord.common.entity.Snowflake
import dev.kord.core.ClientResources
import dev.kord.core.Kord
import dev.kord.core.cache.KordCacheBuilder
import dev.kord.core.gateway.MasterGateway
import dev.kord.gateway.Gateway
import dev.kord.gateway.Intents
import dev.kord.gateway.PrivilegedIntent
import dev.kord.rest.request.KtorRequestHandler
import dev.kord.rest.service.RestClient
import io.ktor.client.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

internal class CacheEntitySupplierTest {

    @Test
    @OptIn(PrivilegedIntent::class)
    fun `cache does not throw when accessing unregistered entities`(): Unit = runBlocking {
        val kord = Kord(
                ClientResources("", 0, HttpClient(), EntitySupplyStrategy.cache, Intents.all),
                KordCacheBuilder().build(),
                MasterGateway(mapOf(0 to Gateway.none())),
                RestClient(KtorRequestHandler("")),
                Snowflake(0),
                MutableSharedFlow(),
                Dispatchers.Default
        )

        kord.unsafe.guild(Snowflake(0)).regions.toList()
    }

}