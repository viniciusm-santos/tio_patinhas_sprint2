package br.com.fiap.view;

import br.com.fiap.dao.CompanyAccountDAO;
import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.CompanyAccount;
import br.com.fiap.model.User;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class UpdateCompanyAccountView {
    public static void execute(Scanner sc, UserDAO userDao, CompanyAccountDAO companyAccountDAO) throws SQLException {
        System.out.print("Enter user ID (UUID): ");
        UUID userId = UUID.fromString(sc.nextLine());

        System.out.print("Enter company ID (UUID): ");
        UUID companyId = UUID.fromString(sc.nextLine());

        User owner = userDao.findById(userId);

        if (owner == null) {
            System.out.println("User with given ID not found.");
            return;
        }

        System.out.print("Enter company name: ");
        String companyName = sc.nextLine();

        CompanyAccount companyAccount = new CompanyAccount(companyId, owner, companyName);

        companyAccountDAO.update(companyAccount);
        System.out.println("Company account updated: " + companyAccount);
    }
}
