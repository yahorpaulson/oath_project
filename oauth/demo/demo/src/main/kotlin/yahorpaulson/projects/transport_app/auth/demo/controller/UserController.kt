package yahorpaulson.projects.transport_app.auth.demo.controller

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class UserController {

    @GetMapping("/user/home")
    fun userHome(@AuthenticationPrincipal oauth2User: OAuth2User?, model: Model): String {
        model.addAttribute("username", oauth2User?.name)
        return "user/home"
    }
}