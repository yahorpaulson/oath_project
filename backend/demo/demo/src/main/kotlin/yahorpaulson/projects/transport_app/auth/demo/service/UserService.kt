package yahorpaulson.projects.transport_app.auth.demo.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service

@Service
class UserService(
        @Value("\${custom.admin_name}")
        private val adminName: String
    ): OAuth2UserService<OAuth2UserRequest, OAuth2User> {

        override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User{


            val delegate = DefaultOAuth2UserService()

            val oAuth2User = delegate.loadUser(userRequest)

            val authorities = mutableListOf<GrantedAuthority>()

            val login = oAuth2User.getAttribute<String>("login")

            if (login == adminName) {
                authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
                authorities.add(SimpleGrantedAuthority("ROLE_USER"))
            } else {
                authorities.add(SimpleGrantedAuthority("ROLE_USER"))
            }

            return DefaultOAuth2User(
                    authorities,
                    oAuth2User.attributes,
                "login"


            )

        }
    }