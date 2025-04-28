package yahorpaulson.projects.transport_app.auth.demo.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http

            .authorizeHttpRequests {
                it.requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
                    .anyRequest().authenticated()
            }
            .oauth2Login{
                oauth2 -> oauth2.defaultSuccessUrl("/", true)
            }
            .logout {
                logout -> logout.logoutSuccessUrl("/")
            }
        return http.build()
    }
}