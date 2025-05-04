package com.example.jakarta.controller;

import com.example.jakarta.model.Finding;
import com.example.jakarta.service.impl.FindingService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
    public Finding get(@PathParam("id") Long id) {
        return findingService.getFinding(id);
    }

    @POST
    public Finding create(Finding finding) {
        return findingService.createFinding(finding);
    }

    @PATCH
    @Path("/{id}")
    public Finding update(@PathParam("id") Long id, Finding finding) {
        return findingService.updateFinding(id, finding);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        findingService.deleteFinding(id);
    }

    @GET
    @Path("/search/{keyword}")
    public List<Finding> searchByKeyword(@PathParam("keyword") String keyword) {
        return findingService.searchByKeyword(keyword);
    }
}
