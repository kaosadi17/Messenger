package org.amali.projects.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.amali.projects.messenger.database.DatabaseClass;
import org.amali.projects.messenger.model.Profile;

public class ProfileService {
	
	private Map<String,Profile> profiles = DatabaseClass.getProfiles();
	
	public ProfileService(){
		profiles.put("Amali",new Profile("Amali",1L,"Amali","Osadi"));
		profiles.put("Nishani", new Profile("Nishani",2L,"Nishani","Fernando"));
	}
	
	public List<Profile> getAllProfiles(){
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public Profile updateProfile(Profile profile){
		if(profile.getProfileName().isEmpty()){
			return null;
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public void removeProfile(String profileName){
		profiles.remove(profileName);
	}
}

