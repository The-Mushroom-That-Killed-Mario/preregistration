package org.mushroom.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "calendar_out_days")
public class CalendarOutDays {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "changed")
    private LocalDateTime changed;
    @Column(name = "is_actual")
    private boolean isActual;


    @ManyToOne
    @JoinColumn(name = "terminal_services_id")
    private TerminalServices terminalService;

}

