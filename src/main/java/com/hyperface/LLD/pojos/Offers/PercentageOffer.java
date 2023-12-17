package com.hyperface.LLD.pojos.Offers;

import com.hyperface.LLD.pojos.Offers.Offer;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PercentageOffer extends Offer {

  private int percentage;
  private int offerLimit;
}
