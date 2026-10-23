package com.taskmanagerlite.taskmanagerlite.workflow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taskmanagerlite.taskmanagerlite.workflow.entity.Workflow;

@Repository 
public interface WorkflowRepository extends JpaRepository<Workflow, Long>{
    
}
