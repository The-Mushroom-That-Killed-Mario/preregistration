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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@EqualsAndHashCode(exclude = {
        "roles", "advancedAppointment"
})
@ToString(exclude = {
        "roles", "advancedAppointment"
})
@JsonIgnoreProperties({"roles, advancedAppointment"})
@Entity
@Table(name = "\"user\"")
@Cacheable("users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "created")
    @Builder.Default
    private LocalDateTime created = LocalDateTime.now();

    @Column(name = "changed")
    @Builder.Default
    private LocalDateTime changed = LocalDateTime.now();

    @Column(name = "deleted")
    private LocalDateTime deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "l_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @Builder.Default
    private Set<Role> roles = Collections.emptySet();

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
    private AdvancedAppointment advancedAppointment;
}