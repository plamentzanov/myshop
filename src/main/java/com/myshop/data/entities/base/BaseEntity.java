package com.myshop.data.entities.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseEntity {

   @Id
   @GeneratedValue(generator = "uuid-string")
   @GenericGenerator(name = "uuid-string", strategy = "org.hibernate.id.UUIDGenerator")
   @Column(name = "id", nullable = false, unique = true, updatable = false)
    private String id;
}
