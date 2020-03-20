package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Address {
    String zipCode;
    String street;
    String numberHouse;
    String numberApartment;
    String entrance;
    String storey;
}
