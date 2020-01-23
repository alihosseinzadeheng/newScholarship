package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.DashboardBySupervisorUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DashboardBySupervisorUseCaseImpl implements DashboardBySupervisorUseCase {

    @Override
    public void dashboard(){

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if(loginUser.getRole().equalsIgnoreCase("supervisor")){
            System.out.println("Dear supervisor, ");
            System.out.println("Requested By Student: "+ getCount("RequestedByStudent"));
            System.out.println("Accepted By Your role: "+ getCount("AcceptedBySupervisor"));
            System.out.println("Rejected By Your role: "+ getCount("RejectedBySupervisor"));
        }

    }

    private int getCount(String status){
        int x=0;
        Connection connection = null;
        try {
            connection = DatabaseConfig.getDatabaseConnection();

            // sql
            String sql = "select Count(*) as count from scholarship where status =?";

            // execute
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                x = resultSet.getInt("count");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return x;
    }

}
