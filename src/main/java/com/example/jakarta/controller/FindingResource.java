package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
import com.example.jakarta.service.impl.FindingService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;

@Path("/findings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Stateless
public class FindingResource {

    @Inject
    private FindingService findingService;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        Finding finding = findingService.getFinding(id);
        if (finding == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(finding)
                .build();
    }

    @POST
    public Response create(Finding finding, @Context UriInfo uriInfo) {
        try {
            Finding created = findingService.createFinding(finding);
            UriBuilder builder = uriInfo.getAbsolutePathBuilder();
            builder.path(Long.toString(created.getId()));
            return Response
                    .created(builder.build())
                    .entity(created)
                    .build();
        } catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Finding finding) {
        Finding updated = findingService.updateFinding(id, finding);
        if (updated == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .ok(updated)
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = findingService.deleteFinding(id);
        if (!deleted) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .noContent()
                .build();
    }

    @GET
    @Path("/search")
    public Response search(
            @QueryParam("keyword") String keyword,
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        List<Finding> results = findingService.searchByKeywordWithPagination(keyword, page, size);
        return Response
                .ok(results)
                .build();
    }

    @GET
    public Response getAll(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("size") @DefaultValue("10") int size) {

        List<Finding> results = findingService.getAllFindings(page, size);
        return Response
                .ok(results)
                .build();
    }
}
