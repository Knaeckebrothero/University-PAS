package fra.uas.pas.delegate.date;

import fra.uas.pas.service.DateService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RecalculateSubmissionDate implements JavaDelegate {

    @Autowired
    private DateService ds;

    @Override
    public void execute(DelegateExecution execution) {
        // Get the current extension period
        int extensionPeriod = (int) execution.getVariable("anzahl_verlaengerung");

        // Set the new submission date
        execution.setVariable("abgabe_datum",
                ds.calculateSubmissionDate(
                        extensionPeriod, execution.getVariable("abgabe_datum")));

        // Set new extension period
        execution.setVariable("preview_anzahl_verlaengerung",
                (int)execution.getVariable(
                "preview_anzahl_verlaengerung") - extensionPeriod);
    }
}
