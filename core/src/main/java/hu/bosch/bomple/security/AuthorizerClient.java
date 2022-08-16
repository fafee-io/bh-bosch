package hu.bosch.bomple.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "authorizer", configuration = AuthorizerClientConfig.class)
public interface AuthorizerClient {

    @RequestMapping(
            method = {RequestMethod.POST},
            value = {"/auth/refresh"})
    ResponseEntity<Void> autoLogin();

//    @RequestMapping(
//            method = RequestMethod.PUT,
//            value = "/authorizer/user/{userId}")
//    MasikDto updateByAdmin(
//            @PathVariable("userId") String userId,
//            @RequestBody ValamiDto userUpdateRequest);
}
