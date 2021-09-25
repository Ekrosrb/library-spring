package com.ekros.libraryspring.dao;

import com.ekros.libraryspring.model.entity.Token;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Optional;

public interface TokenRepo extends CrudRepository<Token, Long> {
    Optional<Token> findByUuid(String uuid);

    @Modifying
    @Query(
            nativeQuery = true,
            value = "DELETE t, u FROM tokens t INNER JOIN users u WHERE t.time < ?1 and t.user_id = u.id"
    )
    void deleteOldAll(Timestamp timestamp);
}
