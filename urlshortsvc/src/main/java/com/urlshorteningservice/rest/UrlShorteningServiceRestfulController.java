package com.urlshorteningservice.rest;

import java.net.URI;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.urlshorteningservice.bo.UrlShorteningService;

@Controller
@Path("")
public class UrlShorteningServiceRestfulController {

	@Autowired
	UrlShorteningService urlShorteningService;

	@Path("/shortenurl")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public Response shortenUrl(@QueryParam("url") String url,@Context ServletContext context){
		String resultUrl = "";
		resultUrl = urlShorteningService.shortenUrl(url);
		UriBuilder uriBuilder = UriBuilder.fromUri(URI.create(context.getContextPath()));
		uriBuilder.path("/result.jsp");
		uriBuilder.queryParam("result", resultUrl);
		URI uri = uriBuilder.build();
		return Response.seeOther(uri).build();
	}

	@Path("/{shortid}")
	@GET
	public Response redirectUrl(@Context HttpServletRequest request){
		String url = request.getRequestURL().toString();
		String redirectUrl = urlShorteningService.getRedirectUrl(url);
		if(redirectUrl!=null){
			URI redirectUri =URI.create(redirectUrl);
			if(redirectUri.getScheme()==null)
				redirectUri = URI.create("http://"+redirectUrl);
			return Response.temporaryRedirect(redirectUri).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}
}
