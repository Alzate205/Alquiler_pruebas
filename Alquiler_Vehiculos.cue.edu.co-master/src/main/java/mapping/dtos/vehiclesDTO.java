package mapping.dtos;

import lombok.Builder;

@Builder
public record vehiclesDTO(String type, String model, int modelYear, String brand, int priceDay, String availability ) {
}
