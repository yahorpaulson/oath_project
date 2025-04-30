package yahorpaulson.projects.transport_app.auth.demo.security


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.slf4j.LoggerFactory
import yahorpaulson.projects.transport_app.auth.demo.service.UserService

@Configuration
class SecurityConfig (private val userService: UserService) {

    private val logger = LoggerFactory.getLogger(SecurityConfig::class.java)

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http

            .authorizeHttpRequests {
                it.requestMatchers("/", "/login", "/css/**", "/js/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/user/**").hasRole("USER")
                    .anyRequest().authenticated()
            }
            .oauth2Login {
                it.userInfoEndpoint { userInfo ->
                    userInfo.userService(userService)
                    logger.info("WE ARE HERE")

                }
                it.defaultSuccessUrl("/", true)
            }

            .logout { logout ->
                logout.logoutSuccessUrl("/")
            }
        return http.build()
    }

}