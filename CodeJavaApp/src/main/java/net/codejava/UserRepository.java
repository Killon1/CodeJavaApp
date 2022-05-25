package net.codejava;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
    è un extend indiretto della classe crud
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmail(String email);

}
