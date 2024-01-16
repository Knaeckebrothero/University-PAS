package fra.uas.pas;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldDelegate implements JavaDelegate {

    @Autowired
    private HelloWorldService helloWorldService;

    @Override
    public void execute(DelegateExecution execution) {
        helloWorldService.sayHello();
    }
}

