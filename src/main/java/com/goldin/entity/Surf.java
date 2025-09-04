package com.goldin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surfs")
public class Surf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "type_surf")
    private String typeSurf;
    private String price;
    @OneToMany(mappedBy = "surf")
    private List<CartItem> surfs;




}
