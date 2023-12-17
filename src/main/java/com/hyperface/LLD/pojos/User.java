package com.hyperface.LLD.pojos;

import com.hyperface.LLD.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class User {

  private String uuid;
  private String userName;
  private Gender gender;
  private Date dob;
  private List<Transaction> transactions;
}
