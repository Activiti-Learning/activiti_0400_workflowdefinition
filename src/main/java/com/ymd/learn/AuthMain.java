package com.ymd.learn;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.identity.User;
import org.activiti.engine.repository.ProcessDefinition;

import java.util.List;
import java.util.UUID;

public class AuthMain {

    public static void main(String[] args) {

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

        IdentityService identityService = engine.getIdentityService();

        //User and Group related operation are attribute to IdentityService

        User user = identityService.newUser(UUID.randomUUID().toString());

        user.setFirstName("Michael");
        user.setLastName("Yao");
        user.setPassword("123456");

        identityService.saveUser(user);

        RepositoryService repositoryService = engine.getRepositoryService();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1").singleResult();

        repositoryService.addCandidateStarterUser(processDefinition.getId(), user.getId());


        List<ProcessDefinition> definitionList = repositoryService.createProcessDefinitionQuery().startableByUser(user.getId()).list();

       




        engine.close();
        System.exit(0);
    }
}
