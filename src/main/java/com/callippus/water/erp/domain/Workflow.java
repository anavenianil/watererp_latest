package com.callippus.water.erp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Workflow.
 */
@Entity
@Table(name = "workflow")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Workflow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stage_id")
    private Integer stageId;
    
    @ManyToOne
    @JoinColumn(name = "workflow_master_id")
    private WorkflowMaster workflowMaster;

    @ManyToOne
    @JoinColumn(name = "relative_from_role_id")
    private WorkflowRelations relativeFromRole;

    @ManyToOne
    @JoinColumn(name = "absolute_from_role_id")
    private OrgRoleInstance absoluteFromRole;

    @ManyToOne
    @JoinColumn(name = "relationship_type_id")
    private WorkflowRelationships relationshipType;

    @ManyToOne
    @JoinColumn(name = "relative_to_role_id")
    private WorkflowRelations relativeToRole;

    @ManyToOne
    @JoinColumn(name = "absolute_to_role_id")
    private OrgRoleInstance absoluteToRole;

    @ManyToOne
    @JoinColumn(name = "escalation_relationship_type_id")
    private WorkflowRelationships escalationRelationshipType;

    @ManyToOne
    @JoinColumn(name = "relative_escalation_to_id")
    private WorkflowRelations relativeEscalationTo;

    @ManyToOne
    @JoinColumn(name = "absolute_escalation_to_id")
    private OrgRoleInstance absoluteEscalationTo;

    @ManyToOne
    @JoinColumn(name = "workflow_stage_master_id")
    private WorkflowStageMaster workflowStageMaster;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStageId() {
        return stageId;
    }
    
    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public WorkflowMaster getWorkflowMaster() {
        return workflowMaster;
    }

    public void setWorkflowMaster(WorkflowMaster workflowMaster) {
        this.workflowMaster = workflowMaster;
    }

    public WorkflowRelations getRelativeFromRole() {
        return relativeFromRole;
    }

    public void setRelativeFromRole(WorkflowRelations workflowRelations) {
        this.relativeFromRole = workflowRelations;
    }

    public OrgRoleInstance getAbsoluteFromRole() {
        return absoluteFromRole;
    }

    public void setAbsoluteFromRole(OrgRoleInstance orgRoleInstance) {
        this.absoluteFromRole = orgRoleInstance;
    }

    public WorkflowRelationships getRelationshipType() {
        return relationshipType;
    }

    public void setRelationshipType(WorkflowRelationships workflowRelationships) {
        this.relationshipType = workflowRelationships;
    }

    public WorkflowRelations getRelativeToRole() {
        return relativeToRole;
    }

    public void setRelativeToRole(WorkflowRelations workflowRelations) {
        this.relativeToRole = workflowRelations;
    }

    public OrgRoleInstance getAbsoluteToRole() {
        return absoluteToRole;
    }

    public void setAbsoluteToRole(OrgRoleInstance orgRoleInstance) {
        this.absoluteToRole = orgRoleInstance;
    }

    public WorkflowRelationships getEscalationRelationshipType() {
        return escalationRelationshipType;
    }

    public void setEscalationRelationshipType(WorkflowRelationships workflowRelationships) {
        this.escalationRelationshipType = workflowRelationships;
    }

    public WorkflowRelations getRelativeEscalationTo() {
        return relativeEscalationTo;
    }

    public void setRelativeEscalationTo(WorkflowRelations workflowRelations) {
        this.relativeEscalationTo = workflowRelations;
    }

    public OrgRoleInstance getAbsoluteEscalationTo() {
        return absoluteEscalationTo;
    }

    public void setAbsoluteEscalationTo(OrgRoleInstance orgRoleInstance) {
        this.absoluteEscalationTo = orgRoleInstance;
    }

    public WorkflowStageMaster getWorkflowStageMaster() {
        return workflowStageMaster;
    }

    public void setWorkflowStageMaster(WorkflowStageMaster workflowStageMaster) {
        this.workflowStageMaster = workflowStageMaster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Workflow workflow = (Workflow) o;
        if(workflow.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, workflow.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Workflow{" +
            "id=" + id +
            ", stageId='" + stageId + "'" +
            '}';
    }
}
