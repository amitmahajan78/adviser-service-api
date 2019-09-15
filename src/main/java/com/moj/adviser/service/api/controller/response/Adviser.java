package com.moj.adviser.service.api.controller.response;

import lombok.*;

import java.util.ArrayList;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Adviser {

    String adviserId;
    String name;
    String address;
    String contactNumber;
    String organizationType;
    ArrayList<String> categories;
}
