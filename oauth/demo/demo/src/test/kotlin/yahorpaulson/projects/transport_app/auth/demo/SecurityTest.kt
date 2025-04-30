package yahorpaulson.projects.transport_app.auth.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import yahorpaulson.projects.transport_app.auth.demo.security.SecurityConfig



@WebMvcTest
@Import(SecurityConfig::class)
class SecurityTest(@Autowired val mockMvc: MockMvc) {

    @Test
    fun `unauthorized access to user should redirect to login`() {
        mockMvc.get("/user/home")
            .andExpect {
                status { is3xxRedirection() }
                redirectedUrlPattern("**/login")
            }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun `user can access user area`() {
        mockMvc.get("/user/home")
            .andExpect { status { isOk() } }
    }

    @Test
    @WithMockUser(roles = ["USER"])
    fun `user cannot access admin area`() {
        mockMvc.get("/admin/home")
            .andExpect { status { isForbidden() } }
    }

    @Test
    @WithMockUser(roles = ["ADMIN"])
    fun `admin can access admin area`() {
        mockMvc.get("/admin/home")
            .andExpect { status { isOk() } }
    }
}


