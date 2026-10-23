package com.taskmanagerlite.taskmanagerlite.workflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateWorkflowRequest(
    @NotBlank(message = "A name must be assigned to the work")
    @Size(max = 150, message = "Name cannot exceed 150 characters")
    String name,
    
    @Size(max = 1000, message = "Description cannot exceed 150 characters")
    String description

    
) {}