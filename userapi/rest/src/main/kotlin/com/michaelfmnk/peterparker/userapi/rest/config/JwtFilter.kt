package com.michaelfmnk.peterparker.userapi.rest.config

import com.michaelfmnk.peterparker.userapi.domain.service.JwtService
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.UrlPathHelper
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

        val servletPath = UrlPathHelper().getPathWithinApplication(request)
        println("servlet path =" + servletPath)
        if (isPathMatchesExcludePath(servletPath)) {
            chain.doFilter(request, response)
            return
        }

        val token = request.getHeader("Authorization")
        token?.let { setAuthentication(it.substring(7)) }
        chain.doFilter(request, response)
    }

    private fun setAuthentication(token: String) {
        val userId = parseUserId(token)

        logger.info("checking authentication for user_id=$userId")
        if (userId != null && SecurityContextHolder.getContext().authentication == null) {
            val user = userService.findUserById(userId)
            if (jwtService.isTokenValid(token, user)) {
                logger.info("authenticated user $userId, setting security context")
                SecurityContextHolder.getContext().authentication = UserAuthentication(user)
            }
        }
    }

    private fun parseUserId(token: String): Long? = try {
        jwtService.getUserIdFromToken(token)
    } catch (e: RuntimeException) {
        logger.warn("an error occurred during getting user_id from token")
        null
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
