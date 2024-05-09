package model;

import lombok.*;
import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class reservations {
    private int id;
    private Date start_Date;
    private Date end_Date;
    private int idVehicle;
    private int idUser;
    private String status;

}
