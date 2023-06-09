package org.mushroom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "created")
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "changed")
    private LocalDateTime changed = LocalDateTime.now();

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @JsonIgnoreProperties("terminalService")
    @OneToOne(mappedBy = "service", fetch = FetchType.EAGER)
    private TerminalServices terminalService;

    @JsonIgnoreProperties("advancedAppointment")
    @OneToOne(mappedBy = "service", fetch = FetchType.EAGER)
    private AdvancedAppointment advancedAppointment;
}
