package org.amali.projects.messenger.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.amali.projects.messenger.model.Profile;
import org.amali.projects.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<Profile> getAllProfiles(){
		return profileService.getAllProfiles();
	}
	
	@GET
	@Path("/{profileName}")
	public Profile getProfile(@PathParam ("profileName") String ProfileName){
		return profileService.getProfile(ProfileName);
		
	}
	
	@PUT
	@Path("/{profileName}")
	public Profile updateProfile(@PathParam ("profileName") String ProfileName,Profile profile){
		profile.setProfileName(ProfileName);
		profileService.updateProfile(profile);
		return profile;
	}
	
	@POST
	public Profile addProfile(Profile profile){
		profileService.addProfile(profile);
		return profile;
	}
	
	@DELETE
	@Path("/{profileName}")
	public void deleteProfile(@PathParam ("profileName") String profileName){
		profileService.removeProfile(profileName);
	}
	
	}
