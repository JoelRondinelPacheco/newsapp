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
public class Image extends Base {
    private String mime;
    private String name;

    @Lob
    @Column(name = "content", columnDefinition="MEDIUMBLOB")
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
}
