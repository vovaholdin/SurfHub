package com.goldin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surfs")
@Getter
@Setter
public class Surf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type_surf")
    private String typeSurf;
    private String price;
    private String description;
    @OneToMany(mappedBy = "surf")
    private List<CartItem> surfs;
}
