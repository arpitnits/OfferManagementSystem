package com.hyperface.LLD.pojos.Offers;

import com.hyperface.LLD.enums.OfferType;
import com.hyperface.LLD.pojos.CustomerEligibilty;
import com.hyperface.LLD.pojos.TransactionEligibilty;
import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Offer {

  private String id;
  private CustomerEligibilty customerEligibilty;
  private TransactionEligibilty transactionEligibilty;

  private OfferType type;
}

/*
{
  "offerType" : 1,
  "
}
 */
