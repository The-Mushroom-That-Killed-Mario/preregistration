package org.mushroom.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString(
        exclude = {
                "scheduleDays","outDays","terminal","service"
        }
)
@EqualsAndHashCode(
        exclude = {
                "scheduleDays","outDays","terminal","service"
        }
)
@JsonIgnoreProperties({"terminalService"})

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
    @Builder.Default
    private Set<DaySchedule> scheduleDays = Collections.emptySet();

    @OneToMany(mappedBy = "terminalService", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<CalendarOutDays> outDays = Collections.emptySet();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    @JsonBackReference
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "terminal_id")
    @JsonBackReference
    private Terminal terminal;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "changed")
    @Builder.Default
    private LocalDateTime changed = LocalDateTime.now();

    @Column(name = "is_actual")
    @Builder.Default
    private boolean isActual = true;

}
