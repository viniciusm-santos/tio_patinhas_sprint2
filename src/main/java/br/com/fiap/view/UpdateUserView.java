package br.com.fiap.view;

import br.com.fiap.dao.UserDAO;
import br.com.fiap.model.User;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class UpdateUserView {
    public static void execute(Scanner sc, UserDAO dao) throws SQLException {

        ListUserView.execute(dao);

        System.out.print("Enter user id: ");
        String id = sc.nextLine();
        UUID uuid = UUID.fromString(id);


        System.out.print("Enter user name: ");
        String name = sc.nextLine();

        System.out.print("Enter user email: ");
        String email = sc.nextLine();

        System.out.print("Enter user document: ");
        String document = sc.nextLine();

        User user = new User(uuid, name, email, document, true);

        dao.update(user);

        System.out.println(user);
    }
}
