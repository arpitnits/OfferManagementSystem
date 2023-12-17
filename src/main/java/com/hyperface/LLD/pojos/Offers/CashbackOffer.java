package com.hyperface.LLD.pojos.Offers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashbackOffer extends Offer {

  private int cashBackAmount;

  private int cashBackLimit;
}
