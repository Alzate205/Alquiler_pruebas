package repository.vehiclesImpl;

import config.DatabaseConnection;
import model.vehicles;
import repository.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class vehicleRepositoryJDBC implements Repository<vehicles> {
    private Connection getConnection() throws SQLException {
        return DatabaseConnection.getInstance();
    }


    @Override
    public List<vehicles> list() {
        List<vehicles> users = new ArrayList<>();
        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicles")) {
            while (resultSet.next()) {
                vehicles vehicle = createVehicle(resultSet);
                users.add(vehicle);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar clientes", e);
        }
        return users;
    }

    @Override
    public vehicles byId(int id) {
        vehicles vehicle = null;
        try {
            String sql = "SELECT * FROM vehicles WHERE id = ?";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                vehicle = createVehicle(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar cliente por ID", e);
        }
        return vehicle;
    }

    @Override
    public void save(vehicles vehicle) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "INSERT INTO vehicles(type, model, modelYear, brand, priceDay, plate, availability) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getModelYear());
            preparedStatement.setString(4, vehicle.getBrand());
            preparedStatement.setInt(5, vehicle.getPriceDay());
            preparedStatement.setString(6, vehicle.getPlate());
            preparedStatement.setString(7, vehicle.getAvailability());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar cliente", e);
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(
                "DELETE FROM vehicles WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar vehiculo", e);
        }
    }

    @Override
    public void update(vehicles vehicle) {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement("UPDATE vehicles SET type = ?, model = ?, modelYear = ?, brand = ?, priceDay = ?, plate = ?, availability = ? WHERE id = ?")) {
            preparedStatement.setString(1, vehicle.getType());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getModelYear());
            preparedStatement.setString(4, vehicle.getBrand());
            preparedStatement.setInt(5, vehicle.getPriceDay());
            preparedStatement.setString(6, vehicle.getPlate());
            preparedStatement.setString(7, vehicle.getAvailability());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar vehiculo", e);
        }
    }

    private vehicles createVehicle(ResultSet resultSet) throws SQLException {
        vehicles vehicle = new vehicles();
        vehicle.setId(resultSet.getInt("id"));
        vehicle.setType(resultSet.getString("type"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setModelYear(resultSet.getInt("modelYear"));
        vehicle.setBrand(resultSet.getString("brand"));
        vehicle.setPriceDay(resultSet.getInt("PriceDay"));
        vehicle.setPlate(resultSet.getString("plate"));
        vehicle.setAvailability(resultSet.getString("Availability"));
        return vehicle;
    }
}
