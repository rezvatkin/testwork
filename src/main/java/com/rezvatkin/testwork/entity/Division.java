package com.rezvatkin.testwork.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

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
    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dtFrom;
    @Column(name = "dt_till")
    private LocalDate dtTill;

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
