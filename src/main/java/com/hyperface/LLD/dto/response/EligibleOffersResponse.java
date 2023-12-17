package com.hyperface.LLD.dto.response;

import com.hyperface.LLD.pojos.Offers.Offer;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EligibleOffersResponse {

  private List<Offer> offers;

  ///private int discountAmount;

  //private int cashBackAmount;


}
