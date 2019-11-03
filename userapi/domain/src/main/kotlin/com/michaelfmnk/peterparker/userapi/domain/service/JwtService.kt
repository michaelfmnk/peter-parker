package com.michaelfmnk.peterparker.userapi.domain.service

import com.michaelfmnk.peterparker.userapi.domain.model.entity.User
import com.michaelfmnk.peterparker.userapi.domain.plus
import com.michaelfmnk.peterparker.userapi.domain.property.AuthProperties
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import org.springframework.util.ResourceUtils
import java.nio.file.Files.readAllBytes
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.time.Duration
import java.time.temporal.ChronoUnit
import java.util.*
import javax.annotation.PostConstruct


@Component
class JwtService(
        private val authProperties: AuthProperties
) {

    lateinit var privateKey: PrivateKey

    companion object ClaimKeys {
        const val USER_ID = "userId"
        const val PHONE = "phone"
    }

    @PostConstruct
    fun init() {
        val privateKeyBytes = readAllBytes(ResourceUtils.getFile(authProperties.privateKeyPath).toPath())
        val privateSpec = PKCS8EncodedKeySpec(privateKeyBytes)
        privateKey = KeyFactory.getInstance("RSA").generatePrivate(privateSpec)
    }

    fun generateToken(user: User): String = createToken(createClaims(user), user.id)

    fun getUserIdFromToken(authToken: String) = getClaimFromToken(authToken) { (it[USER_ID] as String).toLong() }

    fun getPhoneFromToken(token: String): String = getClaimFromToken(token) { it[PHONE] as String }

    fun getExpirationDateFromToken(token: String): Date = getClaimFromToken(token) { it.expiration }

    fun isTokenValid(token: String, user: User): Boolean {
        val phone = getPhoneFromToken(token)
        return phone == user.phone
                && !isTokenExpired(token)
                && user.enabled
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return Date().after(expiration)
    }

    private fun <T> getClaimFromToken(token: String, resolver: (Claims) -> T) = resolver(getAllClaims(token))

    private fun getAllClaims(token: String): Claims = Jwts.parser()
            .setSigningKey(privateKey)
            .parseClaimsJws(token)
            .body

    private fun createToken(claims: Map<String, Any?>, userId: Long?) = Jwts.builder()
            .setClaims(claims)
            .setSubject(userId.toString())
            .setExpiration(Date() plus Duration.of(1, ChronoUnit.HOURS))
            .signWith(SignatureAlgorithm.RS256, privateKey)
            .compact()

    private fun createClaims(user: User): Map<String, Any?> = mutableMapOf(
            USER_ID to user.id.toString(),
            PHONE to user.phone
    )

}
