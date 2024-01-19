package fra.uas.pas.delegate;

import fra.uas.pas.service.EmailService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailDelegate implements JavaDelegate {

    @Autowired
    private EmailService emailService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        /*
        String recipient = (String) execution.getVariable("recipient");
        String subject = (String) execution.getVariable("subject");
        String message = (String) execution.getVariable("message");
        */
        //recipient, subject, message
        emailService.sendSimpleMessage();
    }
}
