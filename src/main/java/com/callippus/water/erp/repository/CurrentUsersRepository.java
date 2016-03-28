package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.CurrentUsers;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the CurrentUsers entity.
 */
public interface CurrentUsersRepository extends JpaRepository<CurrentUsers,Long> {

}
