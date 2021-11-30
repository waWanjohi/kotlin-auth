package corp.stickman.authjwt.controllers

import corp.stickman.authjwt.dtos.LoginDTO
import corp.stickman.authjwt.dtos.RegisterDTO
import corp.stickman.authjwt.messages.Message
import corp.stickman.authjwt.models.User
import corp.stickman.authjwt.services.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("api")
class AuthController(private val userService: UserService) {

    @PostMapping("register")
    fun register(@RequestBody body: RegisterDTO): ResponseEntity<Any> {
        val user = User()
        user.email = body.email
        user.firstName = body.firstName
        user.lastName = body.lastName
        user.password = body.password

        // Make sure that the email is a new email
        this.userService.findByEmail(user.email) ?: return ResponseEntity.ok(this.userService.save(user))

        return ResponseEntity.badRequest().body(Message("Another user with that email already exists"))
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginDTO, response: HttpServletResponse): ResponseEntity<Any> {

        // If email is incorrect
        val user = this.userService.findByEmail(email = body.email)
            ?: return ResponseEntity.badRequest().body(Message("email or password is incorrect"))

        // If password is incorrect
        if (!user.comparePasswords(body.password)) {
            return ResponseEntity.badRequest().body(Message("email or password is incorrect"))
        }


        val issuer = user.id.toString()

        // Generate a token from the user ID
        val jwt =
            Jwts.builder().setIssuer(issuer)
                .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))//one Day
                .signWith(SignatureAlgorithm.HS256, "some-secret").compact()

        // Create a cookie with the token value
        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true // Frontend can't access this cookie

        // Add the cookie to the http response
        response.addCookie(cookie)
        return ResponseEntity.ok(Message("Logged in!"))
    }

    @GetMapping("user")
    fun user(@CookieValue("jwt") jwt: String?): ResponseEntity<Any> {
        // Wrap in a try/catch in case the token is invalid
        try {

            if (jwt == null) {
                return ResponseEntity.status(401).body(Message("Not Authenticated!"))
            }

            val body = Jwts.parser().setSigningKey("some-secret") // Secret
                .parseClaimsJws(jwt) // Claims
                .body // Body

            return ResponseEntity.ok(this.userService.getUserByTheirId(body.issuer.toInt()))
        } catch (e: Exception) {
            return ResponseEntity.status(401).body(Message("Not authenticated"))
        }
    }


    @PostMapping("logout")
    fun logout(response: HttpServletResponse): ResponseEntity<Any> {
        val cookie = Cookie("jwt", "") // create a new cookie with the same name but set the value to ""
        cookie.maxAge = 0

        response.addCookie(cookie) // Add it to the response

        return ResponseEntity.ok(Message("Logged out!"))
    }
}