package com.example.ormi5projectteam4.aspect;

import com.example.ormi5projectteam4.annotation.Secured;
import com.example.ormi5projectteam4.domain.constant.Role;
import com.example.ormi5projectteam4.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

  @Autowired
  private AuthenticationService authenticationService;

  @Before("@within(secured) || @annotation(secured)")
  public void checkSecurity(Secured secured) {
    var authenticatedUser = authenticationService.getAuthenticatedUser();

    if (authenticatedUser.isEmpty()) {
      throw new SecurityException("Not authenticated");
    }

    var requiredRole = secured.role();

    boolean hasRole = false;
    if (authenticationService.hasRole(requiredRole)) {
      hasRole = true;
    }

    if (!hasRole) {
      throw new SecurityException("Access denied");
    }
  }
}
