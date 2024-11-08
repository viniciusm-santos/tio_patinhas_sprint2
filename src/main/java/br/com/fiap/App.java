package br.com.fiap;

import br.com.fiap.dao.*;
import br.com.fiap.model.User;
import br.com.fiap.view.*;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        UserDAO userDao;
        CompanyAccountDAO companyAccountDao;
        CryptoAssetDAO cryptoAssetDao;
        TransactionDAO transactionDao;
        WalletDAO walletDAO;

        try {
            userDao = new UserDAO();
            companyAccountDao = new CompanyAccountDAO();
            cryptoAssetDao = new CryptoAssetDAO();
            transactionDao = new TransactionDAO();
            walletDAO = new WalletDAO();


            int option;

            do {
                menu();
                option = sc.nextInt();
                sc.nextLine();

                switch (option) {
                    case 1:
                        CreateUserView.execute(sc, userDao);
                        break;
                    case 2:
                        CreateCompanyAccountView.execute(sc, userDao, companyAccountDao);
                        break;
                    case 3:
                        CreateCryptoAssetView.execute(sc, cryptoAssetDao);
                        break;
                    case 4:
                        CreateWalletView.execute(sc, companyAccountDao, cryptoAssetDao, walletDAO);
                        break;
                    case 5:
                        CreateTransactionView.execute(sc, walletDAO, transactionDao);
                        break;
                    case 6:
                       ListUserView.execute(userDao);
                        break;
                    case 7:
                        ListCompanyAccountView.execute(companyAccountDao);
                        break;
                    case 8:
                        ListCryptoAssetView.execute(cryptoAssetDao);
                        break;
                    case 9:
                        ListWalletView.execute(walletDAO);
                        break;
                    case 10:
                        ListTransactionView.execute(transactionDao);
                        break;
                    case 11:
                        DeleteUserView.execute(sc, userDao);
                        break;
                    case 12:
                        DeleteCompanyAccountView.execute(sc, companyAccountDao);
                        break;
                    case 13:
                        DeleteCryptoAssetView.execute(sc, cryptoAssetDao);
                        break;
                    case 14:
                        DeleteWalletView.execute(sc, walletDAO);
                        break;
                    case 15:
                        DeleteTransactionsView.execute(sc, transactionDao);
                        break;
                    case 16:
                        UpdateCompanyAccountView.execute(sc, userDao,companyAccountDao);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } while (option != 0);
            userDao.closeConnection();
            companyAccountDao.closeConnection();
            cryptoAssetDao.closeConnection();
            transactionDao.closeConnection();
            walletDAO.closeConnection();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database" + e.getMessage());
        }
    }

    private static void menu() {
        System.out.println("Menu:");
        System.out.println("1. Create User");
        System.out.println("2. Create Company Account");
        System.out.println("3. Create Crypto Asset");
        System.out.println("4. Create Wallet");
        System.out.println("5. Create Transaction");
        System.out.println("6. List Users");
        System.out.println("7. List Company Accounts");
        System.out.println("8. List CryptoAssets");
        System.out.println("9. List Wallets");
        System.out.println("10. List Transactions");
        System.out.println("11. Delete User");
        System.out.println("12. Delete Company Accounts");
        System.out.println("13. Delete CryptoAssets");
        System.out.println("14. Delete Wallets");
        System.out.println("15. Delete Transactions");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }
}
