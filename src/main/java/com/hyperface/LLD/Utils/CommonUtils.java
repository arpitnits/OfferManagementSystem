package com.hyperface.LLD.Utils;

import com.hyperface.LLD.dto.response.BaseResponse;
import io.vertx.core.json.JsonObject;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public abstract class CommonUtils {

  public static String generateUUID() {
    return UUID.randomUUID().toString();
  }

  public static <T> JsonObject getSuccessResponse(T data) {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setSuccess(true);
    baseResponse.setData(JsonObject.mapFrom(data));

    return JsonObject.mapFrom(baseResponse);
  }

  public static <T> JsonObject getFailureResponse() {
    BaseResponse baseResponse = new BaseResponse();
    baseResponse.setSuccess(false);

    return JsonObject.mapFrom(baseResponse);
  }

  public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
    if ((birthDate != null) && (currentDate != null)) {
      return Period.between(currentDate, birthDate).getYears();
    } else {
      return 0;
    }
  }

  public static int getDays(LocalDate txnDate, LocalDate currentDate) {
    if ((txnDate != null) && (currentDate != null)) {
      return Period.between(currentDate, txnDate).getDays();
    } else {
      return 0;
    }
  }
}
