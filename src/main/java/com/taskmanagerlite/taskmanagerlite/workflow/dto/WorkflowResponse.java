package com.taskmanagerlite.taskmanagerlite.workflow.dto;

import java.time.LocalDateTime;

import com.taskmanagerlite.taskmanagerlite.workflow.entity.Workflow;
import com.taskmanagerlite.taskmanagerlite.workflow.entity.WorkflowStatus;

public record WorkflowResponse(
        Long id,
        String name,
        String description,
        WorkflowStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static WorkflowResponse from(Workflow workflow) {
        return new WorkflowResponse(
                workflow.getId(),
                workflow.getName(),
                workflow.getDescription(),
                workflow.getStatus(),
                workflow.getCreatedAt(),
                workflow.getUpdatedAt()
        );
    }
}
