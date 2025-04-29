package yahorpaulson.projects.transport_app.auth.demo.controller

import org.springframework.ui.Model
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {

    @GetMapping("/")
    fun index(@AuthenticationPrincipal oauth2User: OAuth2User?, model: Model): String{
        if(oauth2User != null){
            model.addAttribute("username", oauth2User.getAttribute<String>("login"))

        } else {
            model.addAttribute("username", null)
        }
        return "index"
    }

}