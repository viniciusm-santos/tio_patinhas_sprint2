package br.com.fiap.dao;

import br.com.fiap.factory.ConnectionFactory;
import br.com.fiap.model.CompanyAccount;
import br.com.fiap.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CompanyAccountDAO {
    private final Connection connection;
    private final UserDAO userDAO;

    public CompanyAccountDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
        userDAO = new UserDAO();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    private CompanyAccount parseCompanyAccount(ResultSet rs) throws SQLException {
        UUID id = UUID.fromString(rs.getString("id"));
        UUID ownerId = UUID.fromString(rs.getString("owner_id"));
        String companyName = rs.getString("company_name");

        User owner = userDAO.findById(ownerId);

        return new CompanyAccount(id, owner, companyName);
    }

    public void register(CompanyAccount companyAccount) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("INSERT INTO t_company_accounts (id,owner_id,company_name,company_identifier) VALUES (?,?,?,?)");

        stm.setString(1, companyAccount.getId().toString());
        stm.setString(2, companyAccount.getOwner().getId().toString());
        stm.setString(3, companyAccount.getCompanyName());
        stm.setString(4, companyAccount.getCompanyIdentifier());

        stm.executeUpdate();
    }

    public void update(CompanyAccount companyAccount) throws SQLException {

        PreparedStatement stm = connection.prepareStatement("UPDATE t_company_accounts SET owner_id = ?, company_name = ?, company_identifier = ? WHERE id = ?");
        stm.setString(1, companyAccount.getOwner().getId().toString());
        stm.setString(2, companyAccount.getCompanyName());
        stm.setString(3, companyAccount.getCompanyIdentifier());
        stm.setString(4, companyAccount.getId().toString());


        stm.executeUpdate();
    }

    public CompanyAccount findById(UUID id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_company_accounts WHERE id = ?");
        stm.setString(1, id.toString());

        ResultSet rs = stm.executeQuery();

        if (!rs.next()) {
            throw new SQLException("Company account not found");
        }

        return parseCompanyAccount(rs);
    }

    public List<CompanyAccount> getAll() throws SQLException {
        PreparedStatement stm = connection.prepareStatement("SELECT * FROM t_company_accounts");
        ResultSet rs = stm.executeQuery();
        List<CompanyAccount> companyAccounts = new ArrayList<>();

        while (rs.next()) {
            companyAccounts.add(parseCompanyAccount(rs));
        }

        return companyAccounts;
    }

    public void delete(UUID id) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM t_company_accounts WHERE id = ?");
        stm.setString(1, id.toString());

        int line = stm.executeUpdate();

        if (line == 0) {
            throw new SQLException("Company account not found to be removed.");
        }
    }
}
