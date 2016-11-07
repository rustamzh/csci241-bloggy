package repository;

import models.Approval;

import java.util.List;


public interface ApprovalRepository {
    List<Approval> getAllApprovals();
    boolean createApproval(int commentId);
    boolean deleteApproval(int approvalId);

}
