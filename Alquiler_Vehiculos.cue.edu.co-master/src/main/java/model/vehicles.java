package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class vehicles {
    private int id;
    private String type;
    private String model;
    private int modelYear;
    private String brand;
    private int priceDay;
    private String plate;
    private String availability;

}
