package service;

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

import model.Candidato;
import utilitarios.DAOGenerico;



@Path("/candidato")
@ApplicationPath("/resource")
public class CandidatoService extends Application{
	DAOGenerico dao = new DAOGenerico();
	
	
	@POST
	@Path("/inserir")
	@Produces
	@Consumes("application/json")
	public Response cadastrar(Candidato objCandidato)
	{
		try {
			dao.inserir(objCandidato);
			return Response.status(200).entity("cadastro realizado.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
		
	}
	
	@PUT
	@Path("/alterar")
	@Consumes("application/json")
	public Response alterar(Candidato objCandidato)
	{
		try {
		dao.salvar(objCandidato);
		return Response.status(200).entity("cadastro alterado.").build();
	} catch (Exception e) {
		throw new WebApplicationException(500);
	}
		
	}
	
	@DELETE
	@Path("/excluir/{id_candidato}")
	public Response excluir(@PathParam("id_candidato")Long id_candidato)
	{
		try {
			Candidato objCandidato = (Candidato) dao.recupera(Candidato.class, id_candidato);

			dao.excluir(objCandidato);

			return Response.status(200).entity("cadastro excluído.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
		
	}
	
	@GET
	@Path("/buscar/{id_candidato}")
	@Produces("application/json")
	public Candidato buscar(@PathParam("id_candidato") Long id_candidato)
	{
		try {
			Candidato candidato = (Candidato) dao.recupera(Candidato.class, id_candidato);

			return candidato;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
		
	}
	
	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Candidato> Listar()
	{
		try {
			List<Candidato> clientes = dao.lista(Candidato.class);
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
		
	}
	

}
