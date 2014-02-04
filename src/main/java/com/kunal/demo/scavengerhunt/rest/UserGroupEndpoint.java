package com.kunal.demo.scavengerhunt.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import com.kunal.demo.scavengerhunt.entity.UserGroup;

/**
 * 
 */
@Stateless
@Path("/usergroups")
public class UserGroupEndpoint {
	@Inject
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(UserGroup entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(UserGroupEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		UserGroup entity = em.find(UserGroup.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<UserGroup> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT u FROM UserGroup u WHERE u.id = :entityId ORDER BY u.id",
						UserGroup.class);
		findByIdQuery.setParameter("entityId", id);
		UserGroup entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<UserGroup> listAll() {
		final List<UserGroup> results = em.createQuery(
				"SELECT DISTINCT u FROM UserGroup u ORDER BY u.id",
				UserGroup.class).getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(UserGroup entity) {
		entity = em.merge(entity);
		return Response.noContent().build();
	}
}