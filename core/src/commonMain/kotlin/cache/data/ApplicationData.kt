package dev.kord.core.cache.data

import dev.kord.common.entity.*
import dev.kord.common.entity.optional.Optional
import dev.kord.common.entity.optional.OptionalSnowflake
import dev.kord.common.entity.optional.map
import dev.kord.common.entity.optional.mapNullable
import dev.kord.common.entity.optional.mapSnowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

public sealed interface BaseApplicationData {
    public val id: Snowflake
    public val name: String
    public val icon: String?
    public val description: String
    public val rpcOrigins: Optional<List<String>>
    public val termsOfServiceUrl: Optional<String>
    public val privacyPolicyUrl: Optional<String>
    public val ownerId: OptionalSnowflake
    public val verifyKey: String
    public val guildId: OptionalSnowflake
    public val primarySkuId: OptionalSnowflake
    public val slug: Optional<String>
    public val coverImage: Optional<String>
    public val flags: Optional<ApplicationFlags>
    public val tags: Optional<List<String>>
    public val installParams: Optional<InstallParams>
    public val customInstallUrl: Optional<String>
    public val roleConnectionsVerificationUrl: Optional<String?>
}

@Serializable
public data class ApplicationData(
    override val id: Snowflake,
    override val name: String,
    override val icon: String?,
    override val description: String,
    override val rpcOrigins: Optional<List<String>> = Optional.Missing(),
    val botPublic: Boolean,
    val botRequireCodeGrant: Boolean,
    val bot: Optional<UserData> = Optional.Missing(),
    override val termsOfServiceUrl: Optional<String> = Optional.Missing(),
    override val privacyPolicyUrl: Optional<String> = Optional.Missing(),
    override val ownerId: OptionalSnowflake = OptionalSnowflake.Missing,
    override val verifyKey: String,
    val team: TeamData?,
    override val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    val guild: Optional<PartialGuildData> = Optional.Missing(),
    override val primarySkuId: OptionalSnowflake = OptionalSnowflake.Missing,
    override val slug: Optional<String> = Optional.Missing(),
    override val coverImage: Optional<String> = Optional.Missing(),
    override val flags: Optional<ApplicationFlags> = Optional.Missing(),
    override val tags: Optional<List<String>> = Optional.Missing(),
    override val installParams: Optional<InstallParams> = Optional.Missing(),
    val integrationTypesConfig: Optional<Map<ApplicationIntegrationType, ApplicationIntegrationTypeConfig>> = Optional.Missing(),
    override val customInstallUrl: Optional<String> = Optional.Missing(),
    override val roleConnectionsVerificationUrl: Optional<String?> = Optional.Missing(),
) : BaseApplicationData {
    public companion object {

        public fun from(entity: DiscordApplication): ApplicationData = with(entity) {
            ApplicationData(
                id,
                name,
                icon,
                description,
                rpcOrigins,
                botPublic,
                botRequireCodeGrant,
                bot.map { UserData.from(it) },
                termsOfServiceUrl,
                privacyPolicyUrl,
                owner.mapSnowflake { it.id },
                verifyKey,
                team?.let { TeamData.from(it) },
                guildId,
                guild.map { PartialGuildData.from(it) },
                primarySkuId,
                slug,
                coverImage,
                flags,
                tags,
                installParams,
                integrationTypesConfig,
                customInstallUrl,
                roleConnectionsVerificationUrl,
            )
        }
    }
}


@Serializable
public data class PartialApplicationData(
    override val id: Snowflake,
    override val name: String,
    override val icon: String?,
    override val description: String,
    override val rpcOrigins: Optional<List<String>> = Optional.Missing(),
    override val termsOfServiceUrl: Optional<String> = Optional.Missing(),
    override val privacyPolicyUrl: Optional<String> = Optional.Missing(),
    override val ownerId: OptionalSnowflake = OptionalSnowflake.Missing,
    override val verifyKey: String,
    override val guildId: OptionalSnowflake = OptionalSnowflake.Missing,
    override val primarySkuId: OptionalSnowflake = OptionalSnowflake.Missing,
    override val slug: Optional<String> = Optional.Missing(),
    override val coverImage: Optional<String> = Optional.Missing(),
    override val flags: Optional<ApplicationFlags> = Optional.Missing(),
    override val tags: Optional<List<String>> = Optional.Missing(),
    override val installParams: Optional<InstallParams> = Optional.Missing(),
    override val customInstallUrl: Optional<String> = Optional.Missing(),
    override val roleConnectionsVerificationUrl: Optional<String?> = Optional.Missing(),
) : BaseApplicationData {
    public companion object {

        public fun from(entity: DiscordPartialApplication): PartialApplicationData = with(entity) {
            PartialApplicationData(
                id,
                name,
                icon,
                description,
                rpcOrigins,
                termsOfServiceUrl,
                privacyPolicyUrl,
                owner.mapSnowflake { it.id },
                verifyKey,
                guildId,
                primarySkuId,
                slug,
                coverImage,
                flags,
                tags,
                installParams,
                customInstallUrl,
                roleConnectionsVerificationUrl,
            )
        }
    }
}
