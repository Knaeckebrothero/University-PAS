package fra.uas.pas.delegate.date;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import fra.uas.pas.service.DateService;

@Component
public class SetSubmissionDate implements JavaDelegate {

    @Autowired
    private DateService ds;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Check if the user is a bachelor or master student
        if(execution.getVariable("abschlussart").equals("Bachelor") ){
            // Set the submission date to 9 weeks
            execution.setVariable("abgabe_datum",
                    ds.calculateSubmissionDate(63, null));
            // Set the extension period to 6 weeks
            execution.setVariable("preview_anzahl_verlaengerung", 42);

        } else if (execution.getVariable("abschlussart").equals("Master")) {
            // Set the submission date to 22 weeks
            execution.setVariable("abgabe_datum",
                    ds.calculateSubmissionDate(154, null));
            // Set the extension period to 8 weeks
            execution.setVariable("preview_anzahl_verlaengerung", 56);

        } else {
            // Set the submission date to 0 days
            execution.setVariable("abgabe_datum",
                    ds.calculateSubmissionDate(0, null));
            // Set the extension period to 0 days
            execution.setVariable("preview_anzahl_verlaengerung", 0);
        }
    }
}
