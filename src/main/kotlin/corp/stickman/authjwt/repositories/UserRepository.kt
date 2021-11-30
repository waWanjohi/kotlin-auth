package corp.stickman.authjwt.repositories

import corp.stickman.authjwt.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByEmail(email: String): User? // ? -> returns user or null
}