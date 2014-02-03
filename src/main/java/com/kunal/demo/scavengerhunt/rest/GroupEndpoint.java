package com.kunal.demo.scavengerhunt.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import com.kunal.demo.scavengerhunt.entity.Group;

/**
 * 
 */
@Stateless
@Path("/groups")
public class GroupEndpoint
{
   @PersistenceContext(unitName = "forge-default")
   private EntityManager em;

   @POST
   @Consumes("application/json")
   public Response create(Group entity)
   {
      em.persist(entity);
      return Response.created(UriBuilder.fromResource(GroupEndpoint.class).path(String.valueOf(entity.getId())).build()).build();
   }

   @DELETE
   @Path("/{id:[0-9][0-9]*}")
   public Response deleteById(@PathParam("id") Long id)
   {
      Group entity = em.find(Group.class, id);
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      em.remove(entity);
      return Response.noContent().build();
   }

   @GET
   @Path("/{id:[0-9][0-9]*}")
   @Produces("application/json")
   public Response findById(@PathParam("id") Long id)
   {
      TypedQuery<Group> findByIdQuery = em.createQuery("SELECT DISTINCT g FROM Group g WHERE g.id = :entityId ORDER BY g.id", Group.class);
      findByIdQuery.setParameter("entityId", id);
      Group entity;
      try
      {
         entity = findByIdQuery.getSingleResult();
      }
      catch (NoResultException nre)
      {
         entity = null;
      }
      if (entity == null)
      {
         return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(entity).build();
   }

   @GET
   @Produces("application/json")
   public List<Group> listAll()
   {
      final List<Group> results = em.createQuery("SELECT DISTINCT g FROM Group g ORDER BY g.id", Group.class).getResultList();
      return results;
   }

   @PUT
   @Path("/{id:[0-9][0-9]*}")
   @Consumes("application/json")
   public Response update(Group entity)
   {
      entity = em.merge(entity);
      return Response.noContent().build();
   }
}