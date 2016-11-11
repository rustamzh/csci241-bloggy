package repository.Impl;

import models.Approval;
import repository.ApprovalRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ruszh on 09-Nov-16.
 */
public class ApprovalTestingRepositoryImpl implements ApprovalRepository {
    List<Approval> list = new LinkedList<>();
    boolean firsttime=true;

    public void defaults(){

    }

    //int approvalId, int commentId, String title, String user_nickname, String comment_body
    @Override
    public List<Approval> getAllApprovalsforTable() {
        if(firsttime){
            list.add(new Approval(11,23,"First post","user1","Welcome to the internet"));
            list.add(new Approval(13,23,"First post","user1","Welcome to the internet1"));
            list.add(new Approval(12,23,"First post","user1","Welcome to the internet2"));
            list.add(new Approval(14,23,"First post","user1","Welcome to the internet3"));
            list.add(new Approval(12,23,"First post","user1","Welcome to the internet4"));
            firsttime=false;
        }
        return list;
    }

    @Override
    public boolean createApproval(int commentId) {
        return false;
    }

    @Override
    public boolean deleteApproval(int approvalId) {
        return false;
    }

    @Override
    public boolean deleteAllApprovals() {
        list.clear();
        return true;
    }
}
