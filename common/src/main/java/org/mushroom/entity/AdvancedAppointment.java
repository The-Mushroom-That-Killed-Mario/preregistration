package org.mushroom.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "service","user","terminal"
})
@ToString(exclude = {
        "service","user","terminal"
})

@Entity
@Table(name = "advanced_appointment")
public class AdvancedAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "user_id")
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "terminal_id")
    private Terminal terminal;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name =  "service_id")
    private Service service;

    @Column(name =  "date")
    private LocalDate date;

    @Column(name =  "time_from")
    private LocalTime timeFrom;

    @Column(name =  "time_to")
    private LocalTime timeTo;

    @Column(name =  "created")
    private LocalDateTime created;

    @Column(name =  "changed")
    private LocalDateTime changed;

    @Column(name =  "is_actual")
    private boolean isActual;

}
