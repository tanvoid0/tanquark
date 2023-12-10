package com.tanvoid0.tanquark.models.mail;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class NewMailRequestVO {

    @NotEmpty
    private String to;

    private String from;

    private String subject;

    private String body;

//    private Mail mail;

//    public Mail buildMail() {
//        final Mail mail = new Mail();
//        mail.setFrom(from);
//        mail.setTo(List.of(to));
//        mail.setSubject(subject);
//        mail.setText(body);
//        this.mail = mail;
//        return mail;
//    }

//    public void addAttachment(final String fileName, final File file, final String contentType) {
//        if (this.mail == null) {
//            this.buildMail();
//        }
//        mail.addAttachment(fileName, file, contentType);
//    }
}
