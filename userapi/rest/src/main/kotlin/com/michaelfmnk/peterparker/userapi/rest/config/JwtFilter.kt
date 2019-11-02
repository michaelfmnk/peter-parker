package com.michaelfmnk.peterparker.userapi.rest.config

import com.michaelfmnk.peterparker.userapi.domain.service.JwtService
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import java.util.regex.Pattern
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtFilter(
        private val jwtService: JwtService,
        private val userService: UserService,
        private val excludedPaths: List<String>
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest,
                                  response: HttpServletResponse,
                                  chain: FilterChain) {
        if (isPathMatchesExcludePath(request.servletPath)) {
            chain.doFilter(request, response)
        } else {
            val token = request.getHeader("Authorization")
            var userId: Long? = null
            try {
                userId = token?.let {
                    jwtService.getUserIdFromToken(token)
                }
            } catch (e: IllegalArgumentException) {
                logger.warn("an error occurred during getting user_id from token")
            } catch (e: ExpiredJwtException) {
                logger.warn("the token is expired and not valid anymore")
            }

            logger.info(String.format("checking authentication for user_id=%s", userId))
            if (userId != null && Objects.isNull(SecurityContextHolder.getContext().authentication)) {
                val user = userService.findUserById(userId)
                if (jwtService.isTokenValid(token, user)) {
                    val authentication = UserAuthentication(user)
                    logger.info(String.format("authenticated user %s, setting security context", userId))
                    SecurityContextHolder.getContext().authentication = authentication
                }
            }
            chain.doFilter(request, response)
        }
    }

    private fun isPathMatchesExcludePath(servletPath: String): Boolean {
        val urlPatterns = getExcludedPatterns(excludedPaths)
        if (null == urlPatterns) {
            return false
        } else {
            val var3 = urlPatterns.iterator()

            var pattern: Pattern
            do {
                if (!var3.hasNext()) {
                    return false
                }

                pattern = var3.next()
            } while (!pattern.matcher(servletPath.toLowerCase()).matches())

            return true
        }
    }

    private fun getExcludedPatterns(excludeUrls: Collection<String>?): List<Pattern>? {
        return if (null != excludeUrls && excludeUrls.isNotEmpty()) {
            val excludedUrlPatterns = ArrayList<Pattern>()
            val var3 = excludeUrls.iterator()

            while (var3.hasNext()) {
                excludedUrlPatterns.add(Pattern.compile(var3.next()))
            }

            excludedUrlPatterns
        } else {
            Collections.emptyList()
        }
    }
}
