package fra.uas.pas.service;

import org.springframework.stereotype.Service;

@Service
public class DateService {

        public String calculateSubmissionDate(int months) {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            // Add the months to the current date
            LocalDate submissionDate = currentDate.plusMonths(months);
            // Return the submission date as a string
            return submissionDate.toString();
        }
}
