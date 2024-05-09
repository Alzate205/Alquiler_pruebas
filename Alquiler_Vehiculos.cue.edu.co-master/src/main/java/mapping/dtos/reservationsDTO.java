package mapping.dtos;

import lombok.Builder;
import java.util.Date;
@Builder
public record reservationsDTO (Date start_date, Date end_date, int idVehicle, int idUser, String status) {
}
