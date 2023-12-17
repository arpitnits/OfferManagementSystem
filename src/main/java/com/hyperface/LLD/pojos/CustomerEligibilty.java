package com.hyperface.LLD.pojos;


import com.hyperface.LLD.enums.Gender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerEligibilty {

  private Integer minAge;
  private Gender gender;
  private int weekGMV;
}
