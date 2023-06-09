package org.mushroom.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
        "breaks", "terminalServices"
})
@ToString(exclude = {
        "breaks", "terminalServices"
})

@Entity
@Table(name = "week_day_schedule")
public class DaySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_begin")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime timeBegin;

    @Column(name = "time_end")
    private LocalTime timeEnd;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

@Column(name = "changed")
    private LocalDateTime changed = LocalDateTime.now();

    @Column(name = "is_actual")
    private boolean isActual;

    //Основная сторона связи
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "l_breaks",
            joinColumns = @JoinColumn(name = "week_day_schedule_id"),
            inverseJoinColumns = @JoinColumn(name = "break_id")
    )
    @JsonIgnoreProperties("scheduleDays")
    private Set<Break> breaks = Collections.emptySet();

    //Зависимая сторона связи
    @ManyToMany(mappedBy = "scheduleDays", fetch = FetchType.EAGER)
    private Set<TerminalServices> terminalServices = Collections.emptySet();

}
