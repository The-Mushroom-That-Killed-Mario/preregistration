package org.mushroom.model;

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
@Table(name = "terminal")
public class Terminal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "physical_address")
    private String physicalAddress;
    @Column(name = "port")
    private short port;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "changed")
    private LocalDateTime changed;
    @Column(name = "deleted")
    private LocalDateTime deleted;

    @OneToOne(mappedBy = "terminal", fetch = FetchType.EAGER)
    private TerminalServices terminalService;

    @OneToOne(mappedBy = "terminal", fetch = FetchType.EAGER)
    private AdvancedAppointment advancedAppointment;
}
