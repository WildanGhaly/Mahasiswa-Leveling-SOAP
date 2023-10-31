package org.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.example.core.Database;

public class ChallengeDAOImpl implements ChallengeDAO {

    private Connection connection;

    public ChallengeDAOImpl() {
        Database db = new Database();
        this.connection = db.getConnection();
    }

    @Override
    public void insert(Challenge entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public Challenge findById(int id) {
        Challenge challenge = null;
        String sql = "SELECT * FROM challenge WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    challenge = new Challenge (
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("threshold")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return challenge;
    }

    @Override
    public List<Challenge> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

    @Override
    public void update(Challenge entity) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
