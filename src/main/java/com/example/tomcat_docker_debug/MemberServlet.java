package com.example.tomcat_docker_debug;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

@Path("/members")
public class MemberServlet {

    @GET
    @Produces("text/html; charset=UTF-8")
    public Response getIndexPage() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("index.html");
        if (inputStream == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("File not found").build();
        }
        String htmlContent = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
        return Response.ok(htmlContent)
                .header("Content-Type", "text/html; charset=UTF-8")
                .build();
    }
}