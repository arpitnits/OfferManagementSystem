package com.hyperface.LLD.dto.response;

import io.vertx.core.json.JsonObject;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {

  private boolean success;

  private JsonObject data;
}
