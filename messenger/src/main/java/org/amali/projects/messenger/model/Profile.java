package org.amali.projects.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Profile {
	
	private String profileName;
	private long id;
	private String firstName;
	private String lastName;
	
	public Profile(){
		
	}
	
	public Profile(String profileName,long id, String firstName, String lastName){
		this.profileName = profileName;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getProfileName() {
		return profileName;
	}
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	

}
