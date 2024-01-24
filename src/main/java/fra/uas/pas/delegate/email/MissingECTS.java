package fra.uas.pas.delegate.email;

import fra.uas.pas.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fra.uas.pas.camunda.CamundaUserFetcher;
import org.camunda.bpm.engine.IdentityService;

@Component
public class MissingECTS implements JavaDelegate {

    @Autowired
    private EmailService emailService;

    // Define the fetcher to get information about the user
    @Autowired
    private CamundaUserFetcher fetcher;

    @Autowired
    IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Email Subject
        String subject = "Fehlende ECTS";

        // Email Content
        String content = "Sehr geehrter " + fetcher.getUserName() + ",\n\n"
                + "leider fehlen ihnen die notwendigen ECTS Punkte.\n\n\n"
                + "Mit Freundlichen Grüßen,\n\n"
                + "Ihr Prüfungsamt";

        // Send the email
        emailService.sendSimpleMessage(
                fetcher.getUserEmail(),
                subject,
                content
        );
    }
}
