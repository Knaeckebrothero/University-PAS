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
        // Get the type of the thesis
        String abschlussart = execution.getVariable("abschlussart").toString();

        // Set the submission date and the extension period depending on the type of the thesis
        switch (abschlussart) {
            case "Bachelor":

                System.out.println("SetSubmissionDate: " + execution.getVariable("abgabe_datum"));
                System.out.println(ds.calculateSubmissionDate(63, null));


                // Set the submission date to 9 weeks
                execution.setVariable("abgabe_datum",
                        ds.calculateSubmissionDate(63, null));
                // Set the extension period to 6 weeks
                execution.setVariable("preview_anzahl_verlaengerung", 42);

                System.out.println("SetSubmissionDate: " + execution.getVariable("abgabe_datum"));

                break;
            case "Master 3. Semester":
            case "Master 4. Semester":
                // Set the submission date to 22 weeks
                execution.setVariable("abgabe_datum",
                        ds.calculateSubmissionDate(154, null));
                // Set the extension period to 8 weeks
                execution.setVariable("preview_anzahl_verlaengerung", 56);
                break;
            default:
                // Set the submission date to 0 days
                execution.setVariable("abgabe_datum",
                        ds.calculateSubmissionDate(0, null));
                // Set the extension period to 0 days
                execution.setVariable("preview_anzahl_verlaengerung", 0);
                break;
        }
    }
}