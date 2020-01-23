package ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.mctab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.mctab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {
    public boolean request() {

        User loginUser = AuthenticationService.getInstance().getLoginUser();
        if (loginUser != null) {
            if (loginUser.getRole().equalsIgnoreCase("student")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter id:");
                Long id = scanner.nextLong();
                System.out.println("Enter name:");
                String name = scanner.next();
                System.out.println("Enter Family:");
                String family = scanner.next();
                System.out.println("Enter National Code:");
                String nationalCode = scanner.next();
                System.out.println("Enter name of your Last University:");
                String lastUni = scanner.next();
                System.out.println("Enter your Last Degree:");
                String lastDegree = scanner.next();
                System.out.println("Enter your Last Field:");
                String lastField = scanner.next();
                System.out.println("Enter your Last Score:");
                Float lastScore = scanner.nextFloat();
                System.out.println("Which university you want to apply:");
                String applyUni = scanner.next();
                System.out.println("Which Degree you wish to get:");
                String applyDegree = scanner.next();
                System.out.println("Which field you want to apply:");
                String applyField = scanner.next();
                System.out.println("Enter Apply Date:");
                String applyDate = scanner.next();
                String status = "RequestedByStudent";
                Scholarship scholarship = new Scholarship(id,status,name,family,nationalCode,lastUni,
                        lastDegree,lastField,lastScore,applyUni,applyDegree,applyField,applyDate);

                Connection connection=null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    String sql = "INSERT INTO scholarship(id,status, name, family, nationalCode, lastUni, " +
                            "lastDegree, lastField, lastScore, applyUni, applyDegree, applyField, " +
                            "applyDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setLong(1,scholarship.getId());
                    preparedStatement.setString(2,scholarship.getStatus());
                    preparedStatement.setString(3,scholarship.getName());
                    preparedStatement.setString(4,scholarship.getFamily());
                    preparedStatement.setString(5,scholarship.getNationalCode());
                    preparedStatement.setString(6,scholarship.getLastUni());
                    preparedStatement.setString(7,scholarship.getLastDegree());
                    preparedStatement.setString(8,scholarship.getLastField());
                    preparedStatement.setFloat(9,scholarship.getLastScore());
                    preparedStatement.setString(10,scholarship.getApplyUni());
                    preparedStatement.setString(11,scholarship.getApplyDegree());
                    preparedStatement.setString(12,scholarship.getApplyField());
                    preparedStatement.setString(13,scholarship.getApplyDate());
                    preparedStatement.executeUpdate();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return false;

    }


}
