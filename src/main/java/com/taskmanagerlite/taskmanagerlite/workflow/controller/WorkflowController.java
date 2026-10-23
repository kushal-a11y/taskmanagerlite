package com.taskmanagerlite.taskmanagerlite.workflow.controller;

import com.taskmanagerlite.taskmanagerlite.workflow.dto.CreateWorkflowRequest;
import com.taskmanagerlite.taskmanagerlite.workflow.dto.WorkflowResponse;
import com.taskmanagerlite.taskmanagerlite.workflow.service.WorkflowService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/workflows")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @PostMapping
    public ResponseEntity<WorkflowResponse> createWorkflow(
            @Valid @RequestBody CreateWorkflowRequest request) {

        WorkflowResponse response =
                workflowService.createWorkflow(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public WorkflowResponse getWorkflow(@PathVariable Long id) {
        return workflowService.getWorkflow(id);
    }

    @GetMapping
    public List<WorkflowResponse> getAllWorkflows() {
        return workflowService.getAllWorkflows();
    }
}