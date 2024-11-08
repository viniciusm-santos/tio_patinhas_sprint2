package br.com.fiap.view;

import br.com.fiap.dao.CompanyAccountDAO;
import br.com.fiap.model.CompanyAccount;

import java.sql.SQLException;

import java.util.Scanner;
import java.util.UUID;

public class GetCompanyAccountView {
    public static void execute(Scanner sc, CompanyAccountDAO companyAccountDAO) throws SQLException {
        ListCompanyAccountView.execute(companyAccountDAO);

        System.out.print("Enter account id: ");
        String id = sc.nextLine();
        UUID uuid = UUID.fromString(id);
        CompanyAccount companyAccount = companyAccountDAO.findById(uuid);
        System.out.println(companyAccount);
    }
}
