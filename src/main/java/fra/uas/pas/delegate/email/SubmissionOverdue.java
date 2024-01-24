package fra.uas.pas.delegate.email;

import fra.uas.pas.camunda.CamundaUserFetcher;
import fra.uas.pas.service.EmailService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.camunda.bpm.engine.IdentityService;

@Component
public class SubmissionOverdue implements JavaDelegate {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TaskService taskService;

    // Define the fetcher to get information about the user
    @Autowired
    private CamundaUserFetcher fetcher;

    @Autowired
    IdentityService identityService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Get the relevant task to fetch the assignee
        Task task = taskService.createTaskQuery().executionId(execution.getId()).taskDefinitionKey("antrag_stellen").singleResult();

        // Email Subject
        String subject = "Abgabedatum Überschritten";

        // Email Content
        String content = "Sehr geehrter " + fetcher.getUserName() + ",\n\n"
                + "leider haben Sie das Abgabedatum " + execution.getVariable("abgabe_datum") + "nicht " +
                "eingehalten.\n"
                + "Sie sind somit leider durchgefallen.\n\n\n"
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
