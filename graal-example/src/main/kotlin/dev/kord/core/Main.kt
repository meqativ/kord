package dev.kord.core

import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent

suspend fun main(args: Array<String>) {
    val kord = Kord(args.firstOrNull() ?: error("token required")) {
        enableShutdownHook = true
    }

    kord.on<MessageCreateEvent> {
        if (message.author?.isBot == true) return@on
        if (message.content == "!ping") message.channel.createMessage("pong")
    }

    kord.login {
        presence { playing("!ping to pong") }

        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}
