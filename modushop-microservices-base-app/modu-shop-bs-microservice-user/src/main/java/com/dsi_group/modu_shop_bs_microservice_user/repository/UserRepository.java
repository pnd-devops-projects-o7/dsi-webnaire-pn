package com.dsi_group.modu_shop_bs_microservice_user.repository;

import com.dsi_group.modu_shop_bs_microservice_user.domain.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Query(nativeQuery = true, value = """
            SELECT *
            FROM users_db.users AS usr
            WHERE usr.email=:email
            """)
    List<UserEntity> findUserByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = """
            SELECT *
            FROM users_db.users AS usr
                        WHERE usr.phone_number=:phoneNumber
            """)
    UserEntity findUserByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}
