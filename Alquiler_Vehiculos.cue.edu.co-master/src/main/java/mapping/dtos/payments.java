package mapping.dtos;

import lombok.Builder;
import java.util.Date;
@Builder
public record payments (Date paymentDate, int paymentValue, int idReservation, String status){
}
