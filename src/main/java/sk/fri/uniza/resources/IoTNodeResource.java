package sk.fri.uniza.resources;

import io.dropwizard.hibernate.UnitOfWork;
import io.swagger.annotations.*;
import sk.fri.uniza.db.IotNodeDAO;
import sk.fri.uniza.model.IotNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

// Určené na konfigurovanie swagger dokumentačného nástroja
@SwaggerDefinition(
        info = @Info(
                description = "HouseHold data",
                version = "V1.0.0",
                title = "HouseHold service",
                contact = @Contact(
                        name = "Martin Húdik",
                        email = "martin.hudik@fri.uniza.sk"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP}

)
@Api("/iotnode") // Swagger
@Path("/iotnode") // Koreňová adresa kolekcie koncových bodov
// pre prístup k zdrojom domácností // Súčasť JAX-RS
@Produces(MediaType.APPLICATION_JSON)// Výstupné dáta sú vo forme JSON //JAX-RS
@Consumes(MediaType.APPLICATION_JSON) //Vstupné dáta sú vo forme JSON //JAX-RS
public class IoTNodeResource {

    private IotNodeDAO iotNodeDAO;

    public IoTNodeResource(IotNodeDAO iotNodeDAO) {
        this.iotNodeDAO = iotNodeDAO;
    }

    @POST // JAX-RS
    @UnitOfWork //Otvorí novú hibernate session //Dropwizzard
    @ApiOperation(value = "Pridá nové dáta") // Swagger
    public IotNode createIotNode(IotNode iotNode) {
       return iotNodeDAO.create(iotNode);
    }

    @PUT /*JAX-RS*/
    @Path("{id}") /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Úprava existujúcej domácnosti")
    public IotNode updateIotNode(IotNode iotNode) {
        return iotNodeDAO.update(iotNode);
    }

    @GET /*JAX-RS*/
    @Path("{id}") /*JAX-RS*/
    @UnitOfWork //Otvorí novú hibernate session // Dropwizard
    @ApiOperation(value = "Úprava existujúcej domácnosti")
    public IotNode findIotNode(@PathParam("id") Long id) {
        return iotNodeDAO.findById(id);
    }

    @GET
    @UnitOfWork //Otvorí novú hibernate session
    @ApiOperation(value = "Zoznam všetkých domácnosti")
    public List<IotNode> allIotNodes() {
        return iotNodeDAO.allIotNodes();
    }

}
