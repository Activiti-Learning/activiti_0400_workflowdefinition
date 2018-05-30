package com.ymd.learn;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;

public class Main {

    public static void main(String[] args) {

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = engine.getRepositoryService();

//        Deployment deploy = repositoryService.createDeployment().addClasspathResource("vacation.bpmn").deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1").singleResult();

        RuntimeService runtimeService = engine.getRuntimeService();

        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinition.getKey());

        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId("2501").singleResult();

        TaskService taskService = engine.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        //System.out.println("task = " + task.getId() + task.getName());
        taskService.complete(task.getId());

        engine.close();
        System.exit(0);

    }
}

