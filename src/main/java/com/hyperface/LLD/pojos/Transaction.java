package com.hyperface.LLD.pojos;

import com.hyperface.LLD.enums.City;
import com.hyperface.LLD.enums.Merchant;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Transaction {

  public Merchant merchantId;
  private String merchantName;
  private Date transactionDate;

  private City city;

  private double txnAmount;
}
