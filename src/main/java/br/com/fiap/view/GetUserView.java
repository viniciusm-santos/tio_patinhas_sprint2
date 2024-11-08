package br.com.fiap.view;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class GetUserView {
    public static void getUserById(Scanner sc, UserDAO dao) throws SQLException {

        ListUserView.execute(dao);

        System.out.print("Enter user id: ");
        String id = sc.nextLine();
        UUID uuid = UUID.fromString(id);
        User user = dao.findById(uuid);
        System.out.println(user);
    }

    public static void getUserByEmail(Scanner sc, UserDAO dao) throws SQLException {

        ListUserView.execute(dao);

        System.out.print("Enter user email: ");
        String email = sc.nextLine();
        User user = dao.findByEmail(email);
        System.out.println(user);
    }
}
