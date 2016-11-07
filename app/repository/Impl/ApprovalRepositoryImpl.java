package repository.Impl;

import models.Approval;
import repository.ApprovalRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ApprovalRepositoryImpl implements ApprovalRepository {
    private Connection conn;
    @Override
    public List<Approval> getAllApprovals() {
        List<Approval> list = new ArrayList<>();

        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("select * from approval");
            while(rs.next()){
                //int approvalId, int commentId
                list.add(new Approval(Integer.parseInt(rs.getString(1)),Integer.parseInt(rs.getString(2))));
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());

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
}
