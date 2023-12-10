package com.tanvoid0.tanquark.models.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

@Singleton
@RequiredArgsConstructor
public class MailService {
    private final Mailer mailer;
    private final ReactiveMailer reactiveMailer;

    public void sendMail() {
//        reactiveMailer.send(request.buildMail());
        mailer.send(Mail.withText("dev.tanveer.me@gmail.com"
                , "Greeting from Quarkus knowledgefactory",
                "Greeting from Quarkus knowledgefactory"));
    }
}
