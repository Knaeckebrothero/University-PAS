package fra.uas.pas.service;

import org.springframework.stereotype.Service;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

@Service
public class DateService {
    public String calculateSubmissionDate(int days, Object submissionDate) {

        Date newSubmissionDate;
        SimpleDateFormat sdf = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss'Z'");

        // Check if the submission date is null
        if (submissionDate == null) {
            // Assign the current date to the submission date
            newSubmissionDate = new Date();
        } else {
            // Parse the submission date from string to Date
            try {
                newSubmissionDate = sdf.parse((String) submissionDate);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }

        // Add the days to the submission date
        newSubmissionDate.setTime(newSubmissionDate.getTime() +
                (long) days * 24 * 60 * 60 * 1000);

        // Return the submission date
        return sdf.format(newSubmissionDate);
    }
}
