package mapping.mappers;

import mapping.dtos.vehiclesDTO;
import model.vehicles;
public class VehicleMapper {
    public static vehiclesDTO mapFromModel(vehicles vehicles){
        return new vehiclesDTO( vehicles.getType(), vehicles.getModel(), vehicles.getModelYear(), vehicles.getBrand(), vehicles.getPriceDay(), vehicles.getAvailability());

    }
    public static vehicles mapFromDTO(vehiclesDTO vehiclesDTO){
        return vehicles.builder()
                .type(vehiclesDTO.type())
                .model(vehiclesDTO.model())
                .modelYear(vehiclesDTO.modelYear())
                .brand(vehiclesDTO.brand())
                .priceDay(vehiclesDTO.priceDay())
                .availability(vehiclesDTO.availability())
                .build();
    }
}
