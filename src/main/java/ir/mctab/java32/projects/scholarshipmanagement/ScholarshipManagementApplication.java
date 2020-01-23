package ir.mctab.java32.projects.scholarshipmanagement;

import ir.mctab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.mctab.java32.projects.scholarshipmanagement.features.Display;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LogoutUseCaseImpl;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogoutUseCase;
import ir.mctab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.mctab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

public class ScholarshipManagementApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String command = "";
        loginMethod(scanner, command);
        while (!command.equals("exit")) {
        if(AuthenticationService.getInstance().getLoginUser()!=null) {

                if (AuthenticationService.getInstance().getLoginUser().getRole().equals("supervisor")) {
                    Display.suplist();
                    System.out.println("what do you want? ");
                    command = scanner.nextLine();
                    // find scholarship by supervisor
                    if (command.equals("suplist")) {
                        FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                                = new FindScholarshipBySupervisorUseCaseImpl();

                        List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                                .listScholarships();
                        for (int i = 0; i < scholarships.size(); i++) {
                            System.out.println(scholarships.get(i));
                        }
                    }
                    // acceptBySupervisor
                    if (command.equals("supaccept")) {
                        AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                                = new AcceptScholarshipBySupervisorUseCaseImpl();
                        System.out.println("Scholarship Id: ");
                        String scholarshipId = scanner.nextLine();
                        acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                        System.out.println("Done.");
                    }
                    // rejectBySupervisor
                    if (command.equals("supreject")) {
                        RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase
                                = new RejectScholarshipBySupervisorUseCaseImpl();
                        System.out.println("Scholarship Id: ");
                        String scholarshipId = scanner.nextLine();
                        rejectScholarshipBySupervisorUseCase.reject(Long.parseLong(scholarshipId));
                        System.out.println("Done.");
                    }

                    //DashboardBySupervisor
                    if (command.equals("supdash")) {
                        DashboardBySupervisorUseCase dashboardBySupervisorUseCase
                                = new DashboardBySupervisorUseCaseImpl();
                        dashboardBySupervisorUseCase.dashboard();

                    }
                    //log out
                    if (command.equals("logout")) {
                        LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                        logoutUseCase.logout();
                    }
                }else if (AuthenticationService.getInstance().getLoginUser().getRole().equals("manager")) {
                    Display.manlist();
                    System.out.println("what do you want? ");
                    command = scanner.nextLine();
                    // findScholarshipByManager
                    if (command.equals("manlist")) {
                        FindScholarshipByManagerUseCase findScholarshipByManagerUseCase = new FindScholarshipByManagerUseCaseImpl();

                        List<Scholarship> scholarships = findScholarshipByManagerUseCase.listScholarships();
                        for (int i = 0; i < scholarships.size(); i++) {
                            System.out.println(scholarships.get(i));
                        }
                    }

                    // acceptByManager
                    if (command.equals("manaccept")) {
                        AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase
                                = new AcceptScholarshipByManagerUseCaseImpl();
                        System.out.println("Scholarship Id: ");
                        String scholarshipId = scanner.nextLine();
                        acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                        System.out.println("Done.");
                    }

                    // rejectByManager

                    if (command.equals("manreject")) {
                        RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase
                                = new RejectScholarshipByManagerUseCaseImpl();

                        System.out.println("Scholarship Id: ");
                        String scholarshipId = scanner.nextLine();
                        rejectScholarshipByManagerUseCase.reject(Long.parseLong(scholarshipId));
                        System.out.println("Done.");
                    }

                    //DashboardByManager
                    if (command.equals("mandash")) {
                        DashboardByManagerUseCase dashboardByManagerUseCase
                                = new DashboardByManagerUseCaseImpl();
                        dashboardByManagerUseCase.dashboard();

                    }
                    //log out
                    if (command.equals("logout")) {
                        LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                        logoutUseCase.logout();
                    }

                } else if (AuthenticationService.getInstance().getLoginUser().getRole().equals("student")) {
                    Display.stulist();
                    System.out.println("what do you want? ");
                    command = scanner.nextLine();
                    // findScholarshipByStudent

                    if (command.equals("stulist")) {
                        FindScholarshipByStudentUseCase findScholarshipByStudentUseCase = new FindScholarshipByStudentUseCaseImpl();
                        System.out.println("Scholarship Id: ");
                        String scholarshipId = scanner.nextLine();
                        Scholarship scholarship = findScholarshipByStudentUseCase.find(Long.parseLong(scholarshipId));
                        System.out.println(scholarship.toString());
                    }
                    //requestByStudent
                    if (command.equals("request")) {
                        RequestScholarshipByStudentUseCase requestScholarshipByStudentUseCase
                                = new RequestScholarshipByStudentUseCaseImpl();

                        boolean x = requestScholarshipByStudentUseCase.request();
                        if (x) {
                            System.out.println("Done.");
                        }
                    }
                    //log out
                    if (command.equals("logout")) {
                        LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                        logoutUseCase.logout();
                    }
                }else if (AuthenticationService.getInstance().getLoginUser().getRole().equals("university")) {
                    Display.unilist();
                    System.out.println("what do you want? ");
                    command = scanner.nextLine();
                    //findScholarshipByUniversity
                    if (command.equals("unilist")) {
                        FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase
                                = new FindScholarshipByUniversityUseCaseImpl();
                        List<Scholarship> scholarships = findScholarshipByUniversityUseCase.listScholarships();
                        for (int i = 0; i < scholarships.size(); i++) {
                            System.out.println(scholarships.get(i));
                        }
                    }
                    //log out
                    if (command.equals("logout")) {
                        LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                        logoutUseCase.logout();
                    }
                }

            if(AuthenticationService.getInstance().getLoginUser()==null) loginMethod(scanner, command);

            }

        }

    }

    private static void loginMethod(Scanner scanner, String command) {
        while (! command.equals("exit")) {
            System.out.println("what do you want? ");
            System.out.println("you can do these: \"login\" or \"exit\". ");
            command = scanner.nextLine();
            // Login
            if (command.equals("login")) {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                User user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println(" Login successful by " + user.getRole());
                }
            }
            if(command.equalsIgnoreCase("exit")) System.exit(1);
            if(AuthenticationService.getInstance().getLoginUser()!=null) command="exit";
        }
    }
}

