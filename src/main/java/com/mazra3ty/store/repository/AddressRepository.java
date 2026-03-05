package com.mazra3ty.store.repository;

import com.mazra3ty.store.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


    Optional<Address> findByIdAndIsActiveTrue(Long id);

    List<Address> findAllByIsActiveTrue();
}
