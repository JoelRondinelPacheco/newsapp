package com.joel.newsapp.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;


@Entity(name = "images")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image extends Base {
    private String mime;
    private String name;

    @Lob
    @Column(name = "content", columnDefinition="MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
