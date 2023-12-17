package com.hyperface.LLD.pojos;

import com.hyperface.LLD.enums.City;
import com.hyperface.LLD.enums.Merchant;
import lombok.Getter;

import java.util.List;

@Getter
public class TransactionEligibilty {

  private int minTransaction;

  private List<City> eligibleCities;

  private List<Merchant> eligibleMerchants;
}
