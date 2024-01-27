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
       // The values are initialized by the process itself

        // Get the processing time of the thesis
        int bearbeitungszeit = (int) execution.getVariable("bearbeitungszeit_in_tagen");

        // Set the submission date based on the proivided processing time
        execution.setVariable("abgabe_datum",
                ds.calculateSubmissionDate(bearbeitungszeit, null));
    }
}