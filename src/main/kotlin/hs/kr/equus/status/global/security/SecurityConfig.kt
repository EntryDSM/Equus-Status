package hs.kr.equus.status.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.status.global.security.jwt.UserRole
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val objectMapper: ObjectMapper
) {
    companion object {
        const val ADMIN_ROLE = "ADMIN"
    }

    @Bean
    protected fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf()
            .disable()
            .cors()
            .and()
            .formLogin()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        http.authorizeRequests()
            .antMatchers("/")
            .permitAll()
            .antMatchers("/internal/**")
            .hasRole(UserRole.ROOT.name)
            .antMatchers("/admin")
            .hasAnyRole(UserRole.ADMIN.name)
            .anyRequest()
            .authenticated()
            .and()
            .apply(FilterConfig(objectMapper))

        return http.build()
    }
}
