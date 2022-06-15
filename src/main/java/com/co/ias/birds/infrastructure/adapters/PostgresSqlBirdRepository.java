package com.co.ias.birds.infrastructure.adapters;

import com.co.ias.birds.application.domain.Bird;
import com.co.ias.birds.application.domain.valueObjs.BirdId;
import com.co.ias.birds.application.ports.output.BirdRepository;
import com.co.ias.birds.infrastructure.models.BirdDAO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PostgresSqlBirdRepository implements BirdRepository {
    private final DataSource dataSource;

    public PostgresSqlBirdRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void store(Bird bird) {
        String sql="INSERT INTO birds (common_name,scientific_name,zone_name,confirmed_quantity) values ( ?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,bird.getCommonName().getValue());
            preparedStatement.setString(2,bird.getScientificName().getValue());
            preparedStatement.setString(3,bird.getZoneName().getValue());
            preparedStatement.setInt(4,bird.getConfirmedQuantity().getValue());
            preparedStatement.execute();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("! Error in the query database ",exception);
        }
    }

    @Override
    public Optional<Bird> get(BirdId birdId) {
        String sql = "SELECT * FROM birds where id= ? ";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,birdId.getValue().intValue());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                BirdDAO birdDAO = BirdDAO.fromResulSet(resultSet);
                Bird bird = birdDAO.toDomain();
                return Optional.of(bird);
            }
            else{
                return Optional.empty();
            }
        }catch (SQLException exe){
            throw new RuntimeException("Error in the query ",exe);
        }
    }


    @Override
    public void update(Bird bird) {
        String sql="UPDATE birds set common_name = ? ,scientific_name= ?, zone_name = ?, confirmed_quantity = ? where id = ?";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,bird.getCommonName().getValue());
            preparedStatement.setString(2,bird.getScientificName().getValue());
            preparedStatement.setString(3,bird.getZoneName().getValue());
            preparedStatement.setInt(4,bird.getConfirmedQuantity().getValue());
            preparedStatement.setInt(5,bird.getId().getValue().intValue());
            preparedStatement.executeUpdate();
        }
        catch (SQLException exception){
            System.out.println(exception.getMessage());
            throw new RuntimeException("! Error in the query database ",exception);
        }
    }

    @Override
    public Boolean delete(BirdId birdId) {
        String sql = "DELETE FROM birds where id = ?";
        try(Connection connection = dataSource.getConnection();PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,birdId.getValue().intValue());
            preparedStatement.execute();
            return true;
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            throw new RuntimeException("Error in the query ",ex);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Error in the query ",e);
        }
    }
}

