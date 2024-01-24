package fra.uas.pas.delegate;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckExternalSupervisor  implements JavaDelegate {

    @Autowired
    private TaskService taskService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        // Get the task for the second supervisor
        Task task = taskService.createTaskQuery().processInstanceId(
        execution.getProcessInstanceId()).taskDefinitionKey(
                "arbeit_bestaetigen_2").singleResult();

        // Wait for the task to be created
        do {
            try {
                Thread.sleep(1000);
                // Get the task for the second supervisor
                task = taskService.createTaskQuery().processInstanceId(
                        execution.getProcessInstanceId()).taskDefinitionKey(
                                "arbeit_bestaetigen_2").singleResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while(task == null);

        // Check if the second supervisor is an external one and skip the task if so
        if (execution.getVariable("zweitbetreuer").equals("extern")){
            // Set the variable for the second supervisor
            execution.setVariable("betreuung2", "bestaetigen");

            // Set the task to completed
            taskService.complete(task.getId());
        }
    }
}
