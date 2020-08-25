package com.github.gabrielbabler;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @GET
    public List<Product> findAll() {
        return Product.listAll();
    }

    @POST
    @Transactional
    public void createNewProduct(ProductRequest request) {
        Product p = new Product();
        p.name = request.name;
        p.value = request.value;
        p.persist();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public void updateProduct(
            @PathParam("id") Long id, ProductRequest request) {
        Optional<Product> p = Product.findByIdOptional(id);

        if(p.isPresent()) {
            Product product = p.get();
            product.name = request.name;
            product.value = request.value;
            product.persist();
        } else {
            throw new NotFoundException();
        }
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void deleteProduct(
            @PathParam("id") Long id) {
        Optional<Product> p = Product.findByIdOptional(id);

        p.ifPresentOrElse(Product::delete, () -> {
            throw new NotFoundException();
        });
    }

}
