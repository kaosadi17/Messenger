package org.amali.projects.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.amali.projects.messenger.model.Message;
import org.amali.projects.messenger.service.MessageService;



@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
	
	MessageService messageService =  new MessageService();

	@GET
	public List<Message> getAllMessage(@QueryParam("year") int year,
										@QueryParam("start") int start,
										@QueryParam("size") int size){
		if(year >0){
			return messageService.getMessageByYear(year);
		}
		if(start >= 0 && size >= 0){
			return messageService.getMessageByPagn(start, size);
		}
		
		return messageService.getAllMessages();
		
	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam ("messageId") long id, @Context UriInfo uriinfo){
		Message message = messageService.getMessage(id);
		String uri = getUrlForSelf(uriinfo, message);
		String uri1 = getUrlForProfile(uriinfo, message);
		String uri2 = getUrlForComments(uriinfo, message);
		message.addLink(uri, "self");
		message.addLink(uri1, "profile");
		message.addLink(uri2, "comments");
		return message;
		
	}

	private String getUrlForComments(UriInfo uriinfo, Message message) {
		URI uri = uriinfo.getAbsolutePathBuilder().
				path(MessageResource.class).
				path(MessageResource.class,"getCommentResource").
				path(CommentResource.class).resolveTemplate("messageId", message.getId()).build();
		return uri.toString();
	}

	private String getUrlForSelf(UriInfo uriinfo, Message message) {
		String uri = uriinfo.getAbsolutePathBuilder().path(MessageResource.class).path(Long.toString(message.getId())).build().toString();
		return uri;
	}
	
	private String getUrlForProfile(UriInfo uriinfo, Message message) {
		String uri = uriinfo.getAbsolutePathBuilder().path(ProfileResource.class).path(message.getAuthor()).build().toString();
		return uri;
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uriino){
		
		Message newMessage =  messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriino.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
		//return Response.status(Status.CREATED).entity(newMessage).build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam ("messageId") long id, Message message){
		message.setId(id);
		messageService.updateMessage(message);
		return message;
		
	}
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam ("messageId") long id){
		messageService.removeMessage(id);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}	
	
}


