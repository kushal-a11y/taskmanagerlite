package com.taskmanagerlite.taskmanagerlite.workflow.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.taskmanagerlite.taskmanagerlite.workflow.dto.CreateWorkflowRequest;
import com.taskmanagerlite.taskmanagerlite.workflow.dto.WorkflowResponse;
import com.taskmanagerlite.taskmanagerlite.workflow.entity.Workflow;
import com.taskmanagerlite.taskmanagerlite.workflow.repository.WorkflowRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor  
public class WorkflowService {
    
    private final WorkflowRepository workflowRepository;

    @Transactional 
    public WorkflowResponse createWorkflow(CreateWorkflowRequest createWorkflowRequest){
        Workflow workflow = new Workflow();

        workflow.setName(createWorkflowRequest.name());
        workflow.setDescription(createWorkflowRequest.description());

        Workflow neWorkflow =  workflowRepository.save(workflow);
        
        return WorkflowResponse.from(neWorkflow);
    }

    @Transactional(readOnly = true)
    public WorkflowResponse getWorkflow(long id){
        Workflow workflow = workflowRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Workflow not found with id: " + id
                ));

        return WorkflowResponse.from(workflow);
    }

    @Transactional(readOnly = true)
    public List<WorkflowResponse> getAllWorkflows(){
        return workflowRepository.findAll()
                .stream()
                .map(WorkflowResponse::from)
                .toList();
    }
}
