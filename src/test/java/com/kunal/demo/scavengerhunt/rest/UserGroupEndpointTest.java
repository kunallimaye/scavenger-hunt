package com.kunal.demo.scavengerhunt.rest;

import com.kunal.demo.scavengerhunt.entity.UserGroup;
import com.kunal.demo.scavengerhunt.rest.UserGroupEndpoint;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class UserGroupEndpointTest {
	@Inject
	private UserGroupEndpoint groupendpoint;

	@Deployment
	public static Archive<?> createDeployment() {
		return ShrinkWrap.create(WebArchive.class, "user-group-endpoint-test.war")
				.addClass(UserGroup.class)
				.addClass(UserGroupEndpoint.class)
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource("test-ds.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	public void testIsDeployed() {
		Assert.assertNotNull(groupendpoint);
	}
	
	@Test
	public void testInsertGroup(){
		UserGroup group = new UserGroup();
		group.setGroupName("grp1");
		group.setGroupDisplayName("Melbourne Demons");
		group.setDescription("Description for Melbourne Demons");
		
		Response response = groupendpoint.create(group);
		Assert.assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
	}
}
