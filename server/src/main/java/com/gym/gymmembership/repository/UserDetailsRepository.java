package com.gym.gymmembership.repository;

import com.gym.gymmembership.domain.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Long> {

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE EXPIRATION_DATE >= ?1\n" +
            "AND EXPIRATION_DATE <= ?2\n" +
            "AND MEMBERSHIPTYPE_ID_FK LIKE (%?3%)\n" +
            "AND FIRST_NAME LIKE (%?4%);", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithAllParameters(
            LocalDate dateFrom,
            LocalDate dateTo,
            Long membershipTypeId,
            String firstNameSearch
    );

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE EXPIRATION_DATE >= ?1\n" +
            "AND MEMBERSHIPTYPE_ID_FK LIKE (%?2%)\n" +
            "AND FIRST_NAME LIKE (%?3%)", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithDateFrom(
            LocalDate dateFrom,
            Long membershipTypeId,
            String firstNameSearch
    );

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE EXPIRATION_DATE <= ?1\n" +
            "AND MEMBERSHIPTYPE_ID_FK LIKE (%?2%)\n" +
            "AND FIRST_NAME LIKE (%?3%)", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithDateTo(
            LocalDate dateTo,
            Long membershipTypeId,
            String firstNameSearch
    );

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE MEMBERSHIPTYPE_ID_FK LIKE (%?1%)\n" +
            "AND FIRST_NAME LIKE (%?2%);", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithoutDate(
            Long membershipTypeId,
            String firstNameSearch
    );


//    without membership type
@Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
        "WHERE EXPIRATION_DATE >= ?1\n" +
        "AND EXPIRATION_DATE <= ?2\n" +
        "AND FIRST_NAME LIKE (%?3%);", nativeQuery = true)
List<UserDetails> filterUserDetailsWithAllParametersExceptMembershipType(
        LocalDate dateFrom,
        LocalDate dateTo,
        String firstNameSearch
);

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE EXPIRATION_DATE >= ?1\n" +
            "AND FIRST_NAME LIKE (%?2%)", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithDateFromWithoutMembershipType(
            LocalDate dateFrom,
            String firstNameSearch
    );

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE EXPIRATION_DATE <= ?1\n" +
            "AND FIRST_NAME LIKE (%?2%)", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithDateToWithoutMembershipType(
            LocalDate dateTo,
            String firstNameSearch
    );

    @Query(value = "SELECT * FROM GYMMEMBERSHIP.USER\n" +
            "WHERE FIRST_NAME LIKE (%?1%);", nativeQuery = true)
    List<UserDetails> filterUserDetailsWithoutDateAndMembershipType(
            String firstNameSearch
    );
}
