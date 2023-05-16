//package org.mushroom.entity;
//
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "service")
//public class Service {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "name")
//    private String name;
//    @Column(name = "duration")
//    private Integer duration;
//    @Column(name = "created")
//    private LocalDateTime created;
//    @Column(name = "changed")
//    private LocalDateTime changed;
//    @Column(name = "deleted")
//    private LocalDateTime deleted;
//
//    @OneToOne(mappedBy = "service", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = false)
//    @JsonManagedReference
//    private AdvancedAppointment advancedAppointment;
//}
