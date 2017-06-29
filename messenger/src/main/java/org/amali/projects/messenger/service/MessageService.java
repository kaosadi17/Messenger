package org.amali.projects.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.amali.projects.messenger.database.DatabaseClass;
import org.amali.projects.messenger.model.*;

public class MessageService {
	
	private Map<Long,Message> messages = DatabaseClass.getMessages();
	
	public MessageService(){
		messages.put(1L, new Message(1,"Hello World","Amali"));
		messages.put(2L, new Message(2,"Hello Jersey","Osadi"));
	}
	
	public List<Message> getAllMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	public List<Message> getMessageByYear(int year){
		List<Message> messageList = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()){
			cal.setTime(message.getCreated());
			if(cal.get(Calendar.YEAR)== year){
				messageList.add(message);
			}
		}
		return messageList;
		
	}
	
	public List<Message> getMessageByPagn(int start, int size){
		ArrayList<Message> list = new ArrayList<Message>(messages.values());
		if(start+size > list.size()){
			return new ArrayList<Message>();
		}
		return list.subList(start, start+size);
		
	}
	
	public Message getMessage(long id){
		return messages.get(id);
	}
	
	public Message updateMessage(Message message){
		if(message.getId() <=0){
			return null;
		}
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size()+1);
		messages.put(message.getId(), message);
		return message;
	}
	
	public Message removeMessage(long id){
		return messages.remove(id);
	}
	
	

}
