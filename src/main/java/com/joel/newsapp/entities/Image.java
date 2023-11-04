package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="images")
@ToString
public class Image {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid", strategy = "uuid2")
    private String id;

    private String mime;
    private String name;

    @Lob
    @Column(name = "contenido", columnDefinition="MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte[] contenido;
}
