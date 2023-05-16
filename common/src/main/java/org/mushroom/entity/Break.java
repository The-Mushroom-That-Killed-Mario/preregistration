package org.mushroom.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@EqualsAndHashCode(exclude = {
        "scheduleDays"
})
@ToString(exclude = {
        "scheduleDays"
})

@Entity
@Table(name = "break")
public class Break {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "from_time")
    private LocalTime fromTime;
    @Column(name = "to_time")
    private LocalTime toTime;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "changed")
    private LocalDateTime changed;
    @Column(name = "is_actual")
    private boolean isActual;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "l_breaks",
            joinColumns = @JoinColumn(name = "break_id"),
            inverseJoinColumns = @JoinColumn(name = "week_day_schedule_id")
    )
    @JsonIgnoreProperties("breaks")
    private Set<DaySchedule> scheduleDays = Collections.emptySet();
}
