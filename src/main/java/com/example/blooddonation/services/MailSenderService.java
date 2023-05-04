package com.example.blooddonation.services;

import com.example.blooddonation.domain.dtos.Appointment;
import com.example.blooddonation.domain.repositories.AppointmentRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import java.sql.Date;
import java.util.List;

@Service
public class MailSenderService extends SenderService {
    private final JavaMailSender mailSender;
    private final AppointmentRepository appointmentRepository;


    public MailSenderService(JavaMailSender mailSender, AppointmentRepository appointmentRepository) {
        this.mailSender = mailSender;
        this.appointmentRepository = appointmentRepository;
    }

    @Async
    public void sendConfirmation(Appointment appointment) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("blood.donation.demo@gmail.com");

        message.setTo(appointment.getDonor().getEmail());
        message.setSubject("Appointment confirmation");

        StringBuilder sb = new StringBuilder();

        sb.append("Hi, " + appointment.getDonor().getFirstName() + " " + appointment.getDonor().getLastName() + "\n\n");
        sb.append("You are receiving this email as a confirmation of your appointment.\n\n");
        sb.append("Appointment's date: " + appointment.getDate() + "\n");
        sb.append("At location: " + appointment.getLocation().getName() + ", " + appointment.getLocation().getAddress() + "\n\n");
        sb.append("Please follow your doctor's instructions regarding the process of donation.\n\n");
        sb.append("Have a nice day!");

        message.setText(sb.toString());

        mailSender.send(message);
    }

    @Override
    @Scheduled(cron = "0 41 12 * * *", zone = "Europe/Bucharest")
    @Async
    public void sendReminder() {
        List<Appointment> appointmentList = appointmentRepository.getAppointmentsByDate(
                new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        for (Appointment appointment : appointmentList) {
            if(appointment.getDonor().getSmsRemind()) {
                // TODO
                System.out.println("Message sent to " + appointment.getDonor().getFirstName());
            }
            if(appointment.getDonor().getMailRemind()) {
                SimpleMailMessage message = new SimpleMailMessage();

                message.setFrom("blood.donation.demo@gmail.com");

                message.setTo(appointment.getDonor().getEmail());
                message.setSubject("Appointment reminder");
                StringBuilder sb = new StringBuilder();

                sb.append("Hi, " + appointment.getDonor().getFirstName() + " " + appointment.getDonor().getLastName() + "\n\n");
                sb.append("You are receiving this email as a reminder of your appointment.\n\n");
                sb.append("Appointment date:  " + appointment.getDate() + "\n");
                sb.append("At location:  " + appointment.getLocation().getName() + ", " + appointment.getLocation().getAddress() + "\n\n");
                sb.append("Please follow your doctor's instructions regarding the process of donation.\n\n");
                sb.append("Have a nice day!");

                message.setText(sb.toString());

                mailSender.send(message);
            }
        }
    }
}
