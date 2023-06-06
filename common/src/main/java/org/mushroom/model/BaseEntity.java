//package org.mushroom.model;
//
//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.MappedSuperclass;
//import java.time.LocalDateTime;
//
//@MappedSuperclass
//@Data
//public abstract class BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "created")
//    private final LocalDateTime created = LocalDateTime.now();
//    @Column(name = "changed")
//    private LocalDateTime changed;
//    @Column(name = "deleted")
//    private LocalDateTime deleted;
//}
