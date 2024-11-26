package com.daturism.taller3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;

    @Column
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "item", cascade = CascadeType.REMOVE)
    private List<Paquete> paqueteList;
}
