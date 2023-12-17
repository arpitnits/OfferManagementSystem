package com.hyperface.LLD.dto.request;

import com.hyperface.LLD.pojos.Transaction;
import lombok.Getter;

@Getter
public class EligibleOffersRequest {

  private String uuid;
  private Transaction transaction;

}
