package dev.kord.rest.json

import dev.kord.common.entity.DiscordAuditLog
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlin.test.Test

class AuditLogResponseTest {

    @Test
    fun `AuditLogResponseSerialization serialization`() = runTest {

        val json = file("auditlog")

        @Suppress("UNUSED_VARIABLE")
        val log = Json.decodeFromString(DiscordAuditLog.serializer(), json)


    }

}
