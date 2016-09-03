package com.callippus.water.erp.repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.callippus.water.erp.domain.User;
import com.callippus.water.erp.web.rest.dto.ManagedUserDTO;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(ZonedDateTime dateTime);

    Optional<User> findOneByResetKey(String resetKey);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);//this method was by default commented

    Optional<User> findOneById(Long userId);

    @Override
    void delete(User t);
    
    @Query("Select u From User u")
    List<User> findAllUsers();
    
    User findById(long parseLong);
    //User findOneByLogin(String login);

}
