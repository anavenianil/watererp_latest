package com.callippus.water.erp.repository;

import com.callippus.water.erp.domain.ReversalDetails;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the ReversalDetails entity.
 */
public interface ReversalDetailsRepository extends JpaRepository<ReversalDetails,Long> {

    @Query("select reversalDetails from ReversalDetails reversalDetails where reversalDetails.user.login = ?#{principal.username}")
    List<ReversalDetails> findByUserIsCurrentUser();

}
