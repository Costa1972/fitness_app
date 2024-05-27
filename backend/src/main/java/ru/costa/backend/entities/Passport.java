package ru.costa.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Constantin Vashchuk
 */

@Entity
@Table(name = "passports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    private Long id;
    @Column(name = "series")
    private String series;
    @Column(name = "number")
    private String number;
    @Column(name = "data_of_issue")
    private String dateOfIssue;
}
