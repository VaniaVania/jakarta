package com.example.jakarta.controller;

import com.example.jakarta.error.ErrorMessages;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
        return loadHtmlPage("index.html", ErrorMessages.FILE_NOT_FOUND.getMessage());
    }

    @GET
    @Path("/{id}")
    @Produces("text/html; charset=UTF-8")
    public Response getMemberPage(@PathParam("id") String id) {
        return loadHtmlPage("members/member" + id + ".html", ErrorMessages.MEMBER_NOT_FOUND.getMessage());
    }

    private Response loadHtmlPage(String resourcePath, String notFoundMessage) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(notFoundMessage)
                    .build();
        }

        String htmlContent = new Scanner(inputStream, StandardCharsets.UTF_8)
                .useDelimiter("\\A")
                .next();

        return Response.ok(htmlContent)
                .header("Content-Type", "text/html; charset=UTF-8")
                .build();
    }
}