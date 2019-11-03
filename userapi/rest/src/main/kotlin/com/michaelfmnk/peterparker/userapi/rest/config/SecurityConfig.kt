package com.michaelfmnk.peterparker.userapi.rest.config

import com.michaelfmnk.peterparker.userapi.api.Api
import com.michaelfmnk.peterparker.userapi.domain.service.JwtService
import com.michaelfmnk.peterparker.userapi.domain.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val unauthorizedPage: UnauthorizedEntryPoint,
        private val jwtService: JwtService,
        private val userService: UserService
) : WebSecurityConfigurerAdapter() {

    private val excludedPaths = listOf(
            Api.VERSION,
            Api.BASE_PATH + Api.Auth.LOGIN,
            Api.BASE_PATH + Api.Auth.SIGN_UP
    )

    override fun configure(http: HttpSecurity) {
        http
                .cors().and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedPage).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(*excludedPaths.toTypedArray()).permitAll()
                .anyRequest().authenticated().and()
                .addFilterAfter(jwtFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .headers().cacheControl().disable()
    }

    private fun jwtFilter() = JwtFilter(jwtService, userService, excludedPaths)

}
