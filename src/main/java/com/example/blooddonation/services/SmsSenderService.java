package com.example.blooddonation.services;

import com.example.blooddonation.domain.dtos.Appointment;
import com.example.blooddonation.domain.repositories.AppointmentRepository;
import com.twilio.Twilio;
import com.twilio.exception.TwilioException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SmsSenderService extends SenderService {
    private final AppointmentRepository appointmentRepository;


    public SmsSenderService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }


    @Override
    @Scheduled(cron = "0 24 13 * * *", zone = "Europe/Bucharest")
    @Async
    public void sendReminder() {
        List<Appointment> appointmentList = appointmentRepository.getAppointmentsByDate(
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        for (Appointment appointment : appointmentList) {
            if (appointment.getDonor().getSmsRemind()) {
                Twilio.init("AC059f338172b1b6e67cd83f2ef67efd6e", "9efa18397cbbb20797f46fd86dfaccf7");
                StringBuilder sb = new StringBuilder();

                sb.append("Hi, " + appointment.getDonor().getFirstName() + " " + appointment.getDonor().getLastName() + "\n\n");
                sb.append("You are receiving this email as a reminder of your appointment.\n\n");
                sb.append("Appointment date:  " + appointment.getDate() + "\n");
                sb.append("At location:  " + appointment.getLocation().getName() + ", " + appointment.getLocation().getAddress() + "\n\n");
                sb.append("Please follow your doctor's instructions regarding the process of donation.\n\n");
                sb.append("Have a nice day!");

                try {
                    Message message = Message.creator(
                            new PhoneNumber("+4" + appointment.getDonor().getPhoneNumber()),
                            new PhoneNumber("+13204336275"),
                            sb.toString()
                    ).create();
                    System.out.println("Message sent to " + appointment.getDonor().getFirstName());
                } catch (TwilioException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
