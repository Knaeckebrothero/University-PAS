package fra.uas.pas.camunda.service;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamundaUserFetcher {

    @Autowired
    private IdentityService identityService;

    public String getUserName(String userId) {
        User user = identityService.createUserQuery().userId(userId).singleResult();
        return user != null ? user.getFirstName() + " " + user.getLastName() : null;
    }

    public String getUserEmail(String userId) {
        User user = identityService.createUserQuery().userId(userId).singleResult();
        return user != null ? user.getEmail() : null;
    }
}
