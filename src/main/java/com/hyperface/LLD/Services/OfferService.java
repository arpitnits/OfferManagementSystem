package com.hyperface.LLD.Services;

import com.hyperface.LLD.Utils.CommonUtils;
import com.hyperface.LLD.dto.request.EligibleOffersRequest;
import com.hyperface.LLD.dto.response.EligibleOffersResponse;
import com.hyperface.LLD.pojos.*;
import com.hyperface.LLD.pojos.Offers.CashbackOffer;
import com.hyperface.LLD.pojos.Offers.FixedOffer;
import com.hyperface.LLD.pojos.Offers.Offer;
import com.hyperface.LLD.pojos.Offers.PercentageOffer;
import io.vertx.core.json.JsonObject;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import static com.hyperface.LLD.Constants.DataConstants.OFFER_TYPE;
import static com.hyperface.LLD.enums.OfferType.*;

public class OfferService {


  UserService userService = new UserService();
  EligibilityService eligibilityService = new EligibilityService();
  public static Map<String, Offer> offerMap = new HashMap<>();
  public JsonObject addOffer(JsonObject req) {
    Offer offer;
    String offerType = req.getString(OFFER_TYPE);

    if(FIXED.name().equals(offerType)) {
      offer = req.mapTo(FixedOffer.class);
    } else if(PERCENTAGE.name().equals(offerType)) {
      offer = req.mapTo(PercentageOffer.class);
    } else if(CASHBACK.name().equals(offerType)) {
      offer = req.mapTo(CashbackOffer.class);
    } else {
      System.out.println("INVALID offerType:" + offerType);
      return CommonUtils.getFailureResponse();
    }

    offer.setId(CommonUtils.generateUUID());
    offerMap.put(offer.getId(), offer);

    System.out.println("Successfully added offer for offerId: " + offer.getId());

    return CommonUtils.getSuccessResponse(offer);
  }

  public JsonObject getEligibleOffers(JsonObject req) {
    EligibleOffersRequest eligibleOffersRequest = req.mapTo(EligibleOffersRequest.class);
    User user = userService.getUser(eligibleOffersRequest.getUuid());
    if(Objects.isNull(user)) {
      return CommonUtils.getFailureResponse();
    }

    userService.addTransaction(user.getUuid(), eligibleOffersRequest.getTransaction());

    List<Offer> eligibleOffers = new ArrayList<>();
    for (Map.Entry<String, Offer> offerEntry : offerMap.entrySet()) {
      Offer offer = offerEntry.getValue();
      if(eligibilityService.isCustomerEligible(offer.getCustomerEligibilty(), user) &&
        eligibilityService.isTransactionEligible(offer.getTransactionEligibilty(), eligibleOffersRequest.getTransaction()))

        eligibleOffers.add(offer);
    }

    if(eligibleOffers.size()==0)
      System.out.println("No eligible offers");
    else {
      System.out.println("Eligible Offers");
      for(Offer eligibleOffer : eligibleOffers) {
        if(FIXED.equals(eligibleOffer.getType())) {
          FixedOffer fixedOffer = (FixedOffer) eligibleOffer;
          System.out.println("offerId: "+ fixedOffer.getId() + " | FLAT " + fixedOffer.getOfferAmount() + " Rs OFF\n" +
            "discountAmount: " + fixedOffer.getOfferAmount());
        } if(PERCENTAGE.equals(eligibleOffer.getType())) {
          PercentageOffer percentageOffer = (PercentageOffer) eligibleOffer;
          System.out.println("offerId: "+ percentageOffer.getId() + " | Get " + percentageOffer.getPercentage() + "% Off \n" + "discountAmount: " +
            Math.min(percentageOffer.getOfferLimit(), (percentageOffer.getPercentage())*(eligibleOffersRequest.getTransaction().getTxnAmount()/100)) + " Rs");
        }
      }
    }

    EligibleOffersResponse eligibleOffersResponse = new EligibleOffersResponse();
    eligibleOffersResponse.setOffers(eligibleOffers);
    return CommonUtils.getSuccessResponse(eligibleOffersResponse);
  }


}
