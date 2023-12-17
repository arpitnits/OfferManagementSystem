package com.hyperface.LLD.Services;

import com.hyperface.LLD.Utils.CommonUtils;
import com.hyperface.LLD.pojos.CustomerEligibilty;
import com.hyperface.LLD.pojos.Transaction;
import com.hyperface.LLD.pojos.TransactionEligibilty;
import com.hyperface.LLD.pojos.User;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

public class EligibilityService {

  UserService userService = new UserService();
  public boolean isTransactionEligible(TransactionEligibilty transactionEligibilty, Transaction transaction) {
    if(Objects.isNull(transactionEligibilty))
      return true;

    if(transaction.getTxnAmount() >= transactionEligibilty.getMinTransaction() && checkEligiblityCities(transaction, transactionEligibilty) && checkEligiblityMerchant(transaction, transactionEligibilty))
      return true;

    return false;
  }

  private boolean checkEligiblityCities(Transaction transaction, TransactionEligibilty transactionEligibilty) {
    if(Objects.nonNull(transactionEligibilty) && Objects.nonNull(transactionEligibilty.getEligibleCities())
      && !transactionEligibilty.getEligibleCities().isEmpty()) {
      return transactionEligibilty.getEligibleCities().contains(transaction.getCity());
    } else {
      return true;
    }
  }

  private boolean checkEligiblityMerchant(Transaction transaction, TransactionEligibilty transactionEligibilty) {
    if(Objects.nonNull(transactionEligibilty) && Objects.nonNull(transactionEligibilty.getEligibleMerchants())
      && !transactionEligibilty.getEligibleMerchants().isEmpty()) {
      return transactionEligibilty.getEligibleMerchants().contains(transaction.getMerchantId());
    } else {
      return true;
    }
  }



  public boolean isCustomerEligible(CustomerEligibilty customerEligibilty, User user) {
    if(Objects.isNull(customerEligibilty))
      return true;

    double usersWeekGMV = userService.getUserWeekGMV(user.getUuid());


    if(checkGenderEligibility(customerEligibilty, user) && checkAgeEligibility(customerEligibilty, user) && usersWeekGMV>=customerEligibilty.getWeekGMV())
      return true;

    return false;
  }

  private boolean checkAgeEligibility(CustomerEligibilty customerEligibilty, User user) {
    if(Objects.nonNull(customerEligibilty.getMinAge()) && customerEligibilty.getMinAge()>0) {
      int age = CommonUtils.calculateAge(LocalDate.now(), user.getDob().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
      return age>=customerEligibilty.getMinAge();
    } else {
      return true;
    }
  }


  private boolean checkGenderEligibility(CustomerEligibilty customerEligibilty, User user) {
    if(Objects.nonNull(customerEligibilty.getGender())) {
      return user.getGender().equals(customerEligibilty.getGender());
    } else {
      return true;
    }
  }
}
