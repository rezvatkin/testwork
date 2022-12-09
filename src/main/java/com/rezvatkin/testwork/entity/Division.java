package com.rezvatkin.testwork.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "division")
public class Division {
    @Id
    @Column(name = "division_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "division_name")
    private String name;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "dt_from")
    private LocalDate dtFrom;
    @Column(name = "dt_till")
    private LocalDate dtTill;

    // Из-за магии спринга в эту переменную некоректно парсилось значение, поэтому пришлось переназвать, очевидно
    // можно это отключить, но в рамках решения задачи я решил эту проблему тал.
    // Так же, насколько я понял NumericBooleanType не очень работает с Postgres поэтому bit я заменил на integer
    // Так же это значение, по моему мнению следует сделать неизменяемым, но в ТЗ этого не было, поэтому оставил так
    @Column(name = "is_system", columnDefinition = "INTEGER")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean badChoiceOfName;
    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime creationDate;
    @Column(name = "correction_date")
    private LocalDateTime correctionDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        var division = (Division) o;
        return id != null && Objects.equals(id, division.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
