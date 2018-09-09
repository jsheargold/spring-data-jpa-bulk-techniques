package jsheargold.spring_data_jpa_bulk_techniques.common;

import jsheargold.spring_data_jpa_bulk_techniques.jparepository_manualid.IdGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarManualId extends AbstractCar implements Persistable<Integer> {

    @Id
    private Integer id;

    public CarManualId() {
    }

    public CarManualId(String make, String model, String registration, int year, String colour, double value) {
        super(make, model, registration, year, colour, value);
        this.id = IdGenerator.getNextId();
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return true;
    }
}
