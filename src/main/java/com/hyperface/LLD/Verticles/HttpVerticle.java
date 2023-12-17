package com.hyperface.LLD.Verticles;

import com.hyperface.LLD.Services.OfferService;
import com.hyperface.LLD.Services.UserService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import static com.hyperface.LLD.Constants.DataConstants.*;
import static com.hyperface.LLD.Constants.APIEndPoints.*;

public class HttpVerticle extends AbstractVerticle {

  UserService userService = new UserService();
  OfferService offerService = new OfferService();

  public void start(Promise<Void> startPromise) {
    Router router = Router.router(vertx);
    router.route().handler(BodyHandler.create());

    router.post(ADD_USER).consumes(APPLICATION_JSON).produces(APPLICATION_JSON).handler(rc -> apiHandler(rc, ADD_USER));
    router.post(ADD_OFFER).consumes(APPLICATION_JSON).produces(APPLICATION_JSON).handler(rc -> apiHandler(rc, ADD_OFFER));
    router.post(GET_OFFERS).consumes(APPLICATION_JSON).produces(APPLICATION_JSON).handler(rc -> apiHandler(rc, GET_OFFERS));

    createHttpServer(startPromise, router);
  }

  private void createHttpServer(Promise<Void> startPromise, Router router) {
    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8000, http -> {
        if (http.succeeded()) {
          System.out.println("HTTP server started on port 8000");
          startPromise.complete();
        } else {
          startPromise.fail(http.cause());
        }
    });
  }

  private void apiHandler(RoutingContext rc, String address) {

    JsonObject req = rc.body().asJsonObject();
    JsonObject response = new JsonObject();

    switch (address) {
      case ADD_USER:
        response = userService.addUser(req);
        break;
      case ADD_OFFER:
        response = offerService.addOffer(req);
        break;
      case GET_OFFERS:
        response = offerService.getEligibleOffers(req);
        break;

    }
    rc.end(response.toString());
  }
}
