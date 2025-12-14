package com.sp.common.feature_address.repository;

import com.sp.common.feature_address.modal.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address , Long> {

    @Query("Select * from address where " +
            "street = :street and " +
            "city = :city and " +
            "state = :state and " +
            "zip = :zip and " +
            "country = :country and " +
            "phone = :phone and " +
            "email = :email "
    )
    Address findAddressByDetails(
            @Param("street")  String street,
            @Param("city")    String city ,
            @Param("state")   String state,
            @Param("zip")     String zip,
            @Param("country") String country,
            @Param("phone")   String phone,
            @Param("email")   String email
    );
}
