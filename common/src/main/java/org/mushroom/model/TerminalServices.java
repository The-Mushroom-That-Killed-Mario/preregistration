package org.mushroom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString(
        exclude = {
                "scheduleDays"
        }
)
@EqualsAndHashCode(
        exclude = {
                "scheduleDays"
        }
)

@Entity
@Table(name = "terminal_services")
public class TerminalServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //основная сторона связи
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "l_week_days_schedule",
            joinColumns = @JoinColumn(name = "terminal_services_id"),
            inverseJoinColumns = @JoinColumn(name = "week_day_schedule_id")
    )
    private Set<DaySchedule> scheduleDays = Collections.emptySet();

    @OneToMany(mappedBy = "terminalService", fetch = FetchType.EAGER)
    private Set<CalendarOutDays> outDays;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "terminal_id")
    private Terminal terminal;

    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "changed")
    private LocalDateTime changed;
    @Column(name = "is_actual")
    private boolean isActual;

}
