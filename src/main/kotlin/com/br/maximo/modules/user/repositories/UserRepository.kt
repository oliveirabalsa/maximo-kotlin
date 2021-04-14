package com.br.maximo.modules.user.repositories

import com.br.maximo.modules.user.entities.User
import com.br.maximo.modules.user.enum.UserTypeEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?
    fun findByType(type: UserTypeEnum?): List<User>?
}