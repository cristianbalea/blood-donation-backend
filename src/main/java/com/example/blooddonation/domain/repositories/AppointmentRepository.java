package com.example.blooddonation.domain.repositories;

import com.example.blooddonation.domain.dtos.Appointment;
import org.apache.tomcat.util.descriptor.web.ApplicationParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    @Query(value = "select * from appointments a where a.donor_id = :donorId", nativeQuery = true)
    List<Appointment> findAllByDonor_Id(@Param("donorId")UUID donorId);

    @Override
    void deleteById(UUID uuid);

    @Query(value = "select count(*) from appointments a where a.date = :date and a.location_id = :locationId", nativeQuery = true)
    int checkNumberOfAppointments(@Param("date") Date date, @Param("locationId") UUID locationId);

    @Query(value = "select cast(coalesce(case when count(*) < l.max_number_of_donors_per_day THEN 1 else 0 end, 1) as bit) " +
            "from locations l " +
            "         left join appointments a on l.id = a.location_id and a.date = :date " +
            "where a.location_id = :locationId " +
            "group by l.max_number_of_donors_per_day", nativeQuery = true)
    Boolean checkAvailableSpot(@Param("date") Date date, @Param("locationId") UUID locationId);

    List<Appointment> getAppointmentsByLocation_Id(UUID uuid);

    List<Appointment> getAppointmentsByLocation_IdAndDate(UUID uuid, Date date);

    @Transactional
    @Modifying
    @Query(value = "update appointments set confirmed = true where id = :id", nativeQuery = true)
    void confirmAppointment(@Param("id") UUID id);


    @Query(value = "select * from appointments a where a.location_id = :locationId LIMIT 3 offset :offset ", nativeQuery = true)
    List<Appointment> getAppointmentsLimit(@Param("locationId") UUID locationId, @Param("offset") int offset);
}
