package fra.uas.pas.delegate.email;

import fra.uas.pas.camunda.CamundaUserFetcher;
import fra.uas.pas.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.camunda.bpm.engine.IdentityService;

@Component
public class MissingInformation implements JavaDelegate {

    @Autowired
    private EmailService emailService;

    // Define the fetcher to get information about the user
    @Autowired
    private CamundaUserFetcher fetcher;

    @Autowired
    private IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Email Subject
        String subject = "Fehlende Informationen";

        // Email Content
        String content = "Sehr geehrter " + fetcher.getUserName() + ",\n\n"
                + "leider fehlen noch folgende Informationen:\n"
                + execution.getVariable("infos_nachreichen_pruefungsamt") + "\n\n"
                + "Bitte reichen Sie diese Informationen nach.\n\n\n"
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
