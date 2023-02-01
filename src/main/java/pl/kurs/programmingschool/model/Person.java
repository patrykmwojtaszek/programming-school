package pl.kurs.programmingschool.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
//@Data
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Person {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}
