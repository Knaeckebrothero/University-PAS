package fra.uas.pas.delegate.date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fra.uas.pas.service.DateService;

@Component
public class CalculateSubmissionDate implements JavaDelegate {

    @Autowired
    private DateService dateService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Check if the user is a bachelor or master student
        if(execution.getVariable("abschlussart").equals("Bachelor")){
            // Set the submission date to 6 months
            execution.setVariable("abgabe_datum", dateService.calculateSubmissionDate(6));
        } else if (execution.getVariable("abschlussart").equals("Master")) {
            // Set the submission date to 12 months
            execution.setVariable("abgabe_datum", dateService.calculateSubmissionDate(12));
        } else {
            // Set the submission date to 0 months
            execution.setVariable("abgabe_datum", dateService.calculateSubmissionDate(0));
        }
    }
}
