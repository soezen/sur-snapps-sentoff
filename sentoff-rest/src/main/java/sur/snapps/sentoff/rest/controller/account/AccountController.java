package sur.snapps.sentoff.rest.controller.account;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.account.Balance;
import sur.snapps.sentoff.domain.repo.AccountRepository;
import sur.snapps.sentoff.rest.resources.EntityResource;
import sur.snapps.sentoff.rest.resources.EntityResourceAssembler;

@Api
@Component
@Path("/account")
public class AccountController {

	@Autowired
	private AccountRepository repo;
	
	private EntityResourceAssembler<Account> resourceAssembler = new AccountResourceAssembler(getClass());
	
	// TODO testing
	// TODO add paging
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAccounts() {
		List<Account> accounts = repo.findAll();
		List<EntityResource<Account>> resources = resourceAssembler.toResources(accounts);
		return Response.ok(resources).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountById(@PathParam("id") String id) {
		Account account = repo.findById(Integer.valueOf(id));
		if (account == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(resourceAssembler.toResource(account)).build();
	}
	
	@GET
	@Path("/{id}/balances")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountBalances(@PathParam("id") String id) {
		Account account = repo.findById(Integer.valueOf(id));
		if (account == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok().build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(@Valid Account account) {
		Number id = repo.save(account);
		if (id == null) {
			return Response.serverError().build();
		}
		return getAccountById(id.toString());
	}

	@POST
	@Path("/{id}/balances")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAccountBalance(@PathParam("id") String id, @Valid Balance balance) {
		Account account = repo.findById(Integer.valueOf(id));
		if (account == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		balance.setAccountId(account.getId());
		repo.save(balance);
		return Response.ok().build();
	}
		
	
	class AccountResourceAssembler extends EntityResourceAssembler<Account> {

		@SuppressWarnings("unchecked")
		public AccountResourceAssembler(Class<?> controllerClass) {
			super(controllerClass, ((Class<EntityResource<Account>>) new EntityResource<Account>().getClass()));
		}
		
	}
}
