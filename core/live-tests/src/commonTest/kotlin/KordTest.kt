package dev.kord.core

import dev.kord.core.event.gateway.ReadyEvent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertFalse

internal class KordTest {
    @Test
    fun `Kord life cycle is correctly ended on shutdown`() = runTest {
        val kord = Kord.restOnly(testToken)
        val job = kord.on<ReadyEvent> {}
        kord.shutdown()
        assertFalse(job.isActive)
    }
}
