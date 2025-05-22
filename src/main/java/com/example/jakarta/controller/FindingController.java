package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
import com.example.jakarta.service.impl.FindingService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Optional;

@Path("/findings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class FindingController {

    @Inject
    private FindingService findingService;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return Optional.ofNullable(findingService.getFinding(id))
                .map(finding -> Response.ok(finding).build())
                .orElseGet(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Знахідку з ID " + id + " не знайдено")
                        .build());
    }


    @POST
    public Response create(Finding finding, @Context UriInfo uriInfo) {
        return Optional.ofNullable(finding)
                .map(f -> {
                    Finding created = findingService.createFinding(f);
                    UriBuilder builder = uriInfo.getAbsolutePathBuilder().path(Long.toString(created.getId()));
                    return Response.created(builder.build()).entity(created).build();
                })
                .orElseGet(() -> Response
                        .status(Response.Status.BAD_REQUEST)
                        .entity("Недійсний запит: порожня знахідка")
                        .build());
    }


    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Finding finding) {
        return Optional.ofNullable(findingService.updateFinding(id, finding))
                .map(updated -> Response.ok(updated).build())
                .orElseGet(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Знахідку з ID " + id + " не знайдено для оновлення")
                        .build());
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return findingService.deleteFinding(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND)
                .entity("Знахідку з ID " + id + " не знайдено для видалення")
                .build();
    }


    @GET
    @Path("/search")
    public Response search(
            @QueryParam("keyword") String keyword,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        return Optional.ofNullable(findingService.searchByKeywordWithPagination(keyword, page, size))
                .filter(list -> !list.isEmpty())
                .map(list -> Response.ok(list).build())
                .orElseGet(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Нічого не знайдено за ключовим словом: " + keyword)
                        .build());
    }

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        return Optional.ofNullable(findingService.getAllFindings(page, size))
                .filter(list -> !list.isEmpty())
                .map(list -> Response.ok(list).build())
                .orElseGet(() -> Response
                        .status(Response.Status.NOT_FOUND)
                        .entity("Немає знайдених записів.")
                        .build());
    }

}
