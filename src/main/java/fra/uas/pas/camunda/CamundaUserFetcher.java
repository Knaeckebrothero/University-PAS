package fra.uas.pas.camunda;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.stereotype.Component;

@Component
public class CamundaUserFetcher {

    public String getUserName() {
        return "demo student"; // TODO: Change this to fetch the matrikelnummer and fetch user based on that
    }

    public String getUserEmail() {
        return "Overlygenericaddress@pm.me"; // TODO: Change this to fetch the matrikelnummer and append @studmail
    }
}
