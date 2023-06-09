package org.mushroom.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Entity
@Table(name = "service")
@EqualsAndHashCode(exclude = {
        "advancedAppointment", "terminalService"
})
@ToString(exclude = {
        "advancedAppointment", "terminalService"
})
@JsonIgnoreProperties({"advancedAppointment", "terminalService"})
@Cacheable("services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "changed")
    @Builder.Default
    private LocalDateTime changed = LocalDateTime.now();

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @OneToMany(mappedBy = "service", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<TerminalServices> terminalServices = Collections.emptySet();

    @JsonIgnoreProperties("advancedAppointment")
    @OneToOne(mappedBy = "service", fetch = FetchType.EAGER)
    private AdvancedAppointment advancedAppointment;
}
