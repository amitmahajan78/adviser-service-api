package com.moj.adviser.service.api.domain;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Adviser {

    @Id
    Long Id;
    String name;
    String address1;
    String city;
    String postcode;
    String phone;
    String orgType;
    @OneToMany(mappedBy="adviserId",targetEntity=Category.class,
            fetch= FetchType.EAGER)
    List<Category> category;


}
