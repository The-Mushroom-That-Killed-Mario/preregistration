package org.mushroom.entity;

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
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {
        "breaks"
})
@ToString

@Entity
@Table(name = "week_day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_begin")
    private LocalTime timeBegin;
    @Column(name = "time_end")
    private LocalTime timeEnd;

    //Переделать в енум
    @Column(name = "day_of_week")
    private String day_of_week;


    @Column(name =  "created")
    private LocalDateTime created;

    @Column(name =  "changed")
    private LocalDateTime changed;

    @Column(name =  "is_actual")
    private boolean isActual;

    @ManyToMany(mappedBy = "scheduleDays", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("scheduleDays")
    private Set<Break> breaks = Collections.emptySet();

}
