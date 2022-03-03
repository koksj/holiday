package io.centilliard.application;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@ApplicationPath("/api/v1")
@OpenAPIDefinition(info = @Info(title = "Public Holiday Api", version = "v1"))
public class Holiday extends Application {

    public Holiday() {

    }

}