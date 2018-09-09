package jsheargold.spring_data_jpa_bulk_techniques.common;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CarAutoId extends AbstractCar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public CarAutoId() {
    }

    public CarAutoId(String make, String model, String registration, int year, String colour, double value) {
        super(make, model, registration, year, colour, value);
    }
}
