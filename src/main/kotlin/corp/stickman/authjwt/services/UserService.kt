package corp.stickman.authjwt.services

import corp.stickman.authjwt.models.User
import corp.stickman.authjwt.repositories.UserRepository
import org.springframework.stereotype.Service


@Service
class UserService(private val userRepository: UserRepository) {

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String): User? {
        return this.userRepository.findByEmail(email)
    }

    fun getUserByTheirId(id: Int): User {
        return this.userRepository.getById(id)
    }
}