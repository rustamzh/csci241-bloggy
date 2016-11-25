package repository.Impl;

import models.Approval;
import repository.ApprovalRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.Database;
import database.DatabaseJDBC;


public class ApprovalRepositoryImpl implements ApprovalRepository {
	private Database database = DatabaseJDBC.getInstance();
	private Connection conn = database.getConnection();
	
    @Override
    public List<Approval> getAllApprovalsforTable() {
        List<Approval> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select a.approvalId, a.commentId, p.title, c.user_nickname, c.body from (approval as a join comment as c on a.commentId=c.commentId) join post as p on c.post_postId=p.postid ORDER BY a.approvalId DESC");
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Approval(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2)),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

        }

        return list;
    }



    @Override
    public boolean createApproval(int commentId) {
        String str = "insert into approval (commentId) values ("+commentId+")";
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deleteApproval(int approvalId) {
        String str = "delete from approval where approvalId="+approvalId;
        int res = getRes(str);
        return res==1;
    }

    @Override
    public boolean deleteAllApprovals() {
        String str = "delete from approval where approvalId<>-1";
        int res = getRes(str);
        return res==1;
    }

    private int getRes(String str) {
        int res=0;
        Statement stm;
        try{
            stm = conn.createStatement();
            res=stm.executeUpdate(str);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return res;
    }
}
