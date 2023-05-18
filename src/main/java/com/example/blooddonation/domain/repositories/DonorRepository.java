package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DonorRepository extends JpaRepository<Donor, UUID> {
    @Override
    Optional<Donor> findById(UUID uuid);

    @Transactional
    @Modifying
    @Query(value = "update users set sms_remind = :smsRemind where id = :uuid", nativeQuery = true)
    void setSmsReminder(UUID uuid, Boolean smsRemind);

    @Transactional
    @Modifying
    @Query(value = "update users set mail_remind = :mailRemind where id = :uuid", nativeQuery = true)
    void setMailReminder(UUID uuid, Boolean mailRemind);
}
