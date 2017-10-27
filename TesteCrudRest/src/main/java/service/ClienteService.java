package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import model.Cliente;
import utilitarios.DAOGenerico;

@Path("/cliente")
@ApplicationPath("/resource")
public class ClienteService extends Application {
	DAOGenerico dao = new DAOGenerico();

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Cliente> listar() {
		Cliente cl = new Cliente();
		cl.setNome("frank web");
		cl.setIdade(10);
		List<Cliente> lc = new ArrayList<Cliente>();
		lc.add(cl);

		System.out.println("Chamou: ");

		// return Response.status(200).entity("{nome:\"frank\"}").build();
		//return Response.status(200).entity(lc).build();
		return lc;
		// try {
		// List<Cliente> clientes = dao.lista(Cliente.class);
		// return clientes;
		// } catch (Exception e) {
		// throw new WebApplicationException(500);
		// }
	}

	@GET
	@Path("/hello")
	public Response hello() {
		return Response.status(200).entity("chamou.").build();

	}

	@GET
	@Path("/buscar/{id_cliente}")
	@Produces("application/json")
	public Cliente buscar(@PathParam("id_cliente") Long id_cliente) {
		try {
			Cliente cliente = (Cliente) dao.recupera(Cliente.class, id_cliente);

			return cliente;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/inserir")
	@Produces
	@Consumes("application/json")
	public Response cadastrar(Cliente objClinte) {
		System.out.println("Cliente: " + objClinte.getNome());


		 try {
		 dao.inserir(objClinte);
		 return Response.status(200).entity("cadastro realizado.").build();
		 } catch (Exception e) {
		 throw new WebApplicationException(500);
		 }
	}

	@PUT
	@Path("/alterar")
	@Consumes("application/json")
	public Response alterar(Cliente objClinte) {
		try {
			dao.salvar(objClinte);
			return Response.status(200).entity("cadastro alterado.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/excluir/{id_cliente}")
	public Response excluir(@PathParam("id_cliente") Long id_cliente) {
		try {
			Cliente objClinte = (Cliente) dao.recupera(Cliente.class, id_cliente);

			dao.excluir(objClinte);

			return Response.status(200).entity("cadastro excluído.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}