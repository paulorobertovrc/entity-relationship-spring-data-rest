package br.dev.pauloroberto.entity_relationship_spring_data_rest.model;

import br.dev.pauloroberto.entity_relationship_spring_data_rest.dto.RentalDto;
import br.dev.pauloroberto.entity_relationship_spring_data_rest.repository.CarRepository;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@ToString
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Fetch(FetchMode.SELECT)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION) // Não deleta o carro quando a locação for deletada
    @JoinColumn(name = "car_id")
    private Car car;

    @Fetch(FetchMode.SELECT)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.NO_ACTION) // Não deleta o cliente quando a locação for deletada
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column
    private String startDate;

    @Column
    private String endDate;

    public Rental(RentalDto rentalDto, Customer customer, CarRepository carRepository) {
        this.customer = customer;
        this.car = carRepository.findByLicenseNumber(rentalDto.licenseNumber());
        this.car.setAvailable(false);
        this.startDate = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(new Date());
    }
}
