package org.amali.projects.messenger.database;

import java.util.HashMap;
import java.util.Map;

import org.amali.projects.messenger.model.*;

public class DatabaseClass {
	
	private static Map<Long,Message> messages = new HashMap<>();
	
	public static Map<Long,Message> getMessages(){
		return messages;
	}
	

}
