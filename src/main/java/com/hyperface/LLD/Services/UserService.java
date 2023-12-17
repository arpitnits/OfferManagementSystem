package com.hyperface.LLD.Services;

import com.hyperface.LLD.Exceptions.UserServiceException;
import com.hyperface.LLD.Utils.CommonUtils;
import com.hyperface.LLD.pojos.Transaction;
import com.hyperface.LLD.pojos.User;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class UserService {

  static Map<String, User> userMap = new HashMap<>();
  public JsonObject addUser(JsonObject request) {
    User user = request.mapTo(User.class);
    user.setUuid(CommonUtils.generateUUID());
    userMap.put(user.getUuid(), user);
    System.out.println("Successfully added user with uuid: " + user.getUuid());

    return CommonUtils.getSuccessResponse(user);
  }

  public User getUser(String uuid) {
    if(userMap.isEmpty() || !userMap.containsKey(uuid)) {
      System.out.println("User doesn't exist");
      return null;
    }
    return userMap.get(uuid);
  }

  public void addTransaction(String uuid, Transaction transaction) {

    User user =  getUser(uuid);
    if(Objects.isNull(user.getTransactions()))
      user.setTransactions(new ArrayList<>());
    user.getTransactions().add(transaction);
  }

  public double getUserWeekGMV(String uuid) {
    User user = getUser(uuid);
    double gmv = 0.0;
    for (Transaction transaction : user.getTransactions()) {
      if(CommonUtils.getDays(transaction.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now()) <=7) {
        gmv+= transaction.getTxnAmount();
      }
    }
    return gmv;
  }
}
