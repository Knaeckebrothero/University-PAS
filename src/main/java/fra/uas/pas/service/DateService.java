package fra.uas.pas.service;

import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class DateService {

        public Date calculateSubmissionDate(int days, Object submissionDate) {

            Date newSubmissionDate;

            // Check if the submission date is null
            if (submissionDate == null) {
                // Assign the current date to the submission date
                newSubmissionDate = new Date();
            } else {
                // Convert the submission date to a date object
                newSubmissionDate = new Date((long) submissionDate);
            }

            // Add the days to the submission date
            newSubmissionDate.setTime(newSubmissionDate.getTime() +
                    (long) days * 24 * 60 * 60 * 1000);

            // Return the submission date
            return newSubmissionDate;
        }
}
