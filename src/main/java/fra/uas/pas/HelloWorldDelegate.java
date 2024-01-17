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
        System.out.println("Vor änderung: " + execution.getVariable("test_task"));
        execution.setVariable("test_task", false);
        System.out.println("Nach änderung: " + execution.getVariable("test_task"));
        helloWorldService.sayHello();
    }
}

