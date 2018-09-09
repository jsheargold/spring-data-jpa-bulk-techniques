package jsheargold.spring_data_jpa_bulk_techniques.common;

import javax.persistence.*;

@MappedSuperclass
public class AbstractCar {

    private String make;
    private String model;
    private String registration;
    private Integer year;
    private String colour;
    private Double value;

    public AbstractCar() {

    }

    public AbstractCar(String make, String model, String registration, int year, String colour, double value) {
        this.make = make;
        this.model = model;
        this.registration = registration;
        this.year = year;
        this.colour = colour;
        this.value = value;
    }

}
