package com.ironman.book.controller;

import com.ironman.book.dto.publisher.*;
import com.ironman.book.exception.ExceptionResponse;
import com.ironman.book.service.PublisherService;
import com.ironman.book.util.HttpStatusCode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import static jakarta.ws.rs.core.Response.Status;

@OpenAPIDefinition(
        info = @Info(
                title = "Publisher API",
                version = "1.0.0",
                description = "API for managing publishers in the Book application"
        ),
        tags = @Tag(
                name = "Publisher",
                description = "Operations related to publishers"
        )
)

@RequiredArgsConstructor
@ApplicationScoped
@Path("/publishers")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PublisherController {

    private final PublisherService publisherService;

    @Operation(
            summary = "Get all publishers",
            description = "Retrieve a list of all publishers"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successful retrieval of publishers",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = PublisherBriefResponse.class,
                            type = SchemaType.ARRAY
                    )
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GET
    public Response findAll() {
        return Response
                .status(Status.OK)
                .entity(publisherService.findAll())
                .build();
    }


    @Operation(
            summary = "Get publisher by ID",
            description = "Retrieve a publisher by its unique ID"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successful retrieval of publisher by ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PublisherDetailResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher not found with the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        return Response
                .status(Status.OK)
                .entity(publisherService.findById(id))
                .build();
    }


    @Operation(
            summary = "Create a new publisher",
            description = "Create a new publisher with the provided details"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.CREATED,
            description = "Publisher created successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PublisherResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data provided",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @POST
    public Response create(@Valid @NotNull PublisherRequest publisherRequest) {
        return Response
                .status(Status.CREATED)
                .entity(publisherService.create(publisherRequest))
                .build();
    }


    @Operation(
            summary = "Update an existing publisher",
            description = "Update the details of an existing publisher by its ID"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Publisher updated successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PublisherResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.BAD_REQUEST,
            description = "Invalid input data provided",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher not found with the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, @Valid @NotNull PublisherRequest publisherRequest) {
        return Response
                .status(Status.OK)
                .entity(publisherService.update(id, publisherRequest))
                .build();
    }


    @Operation(
            summary = "Disable a publisher by ID",
            description = "Disable a publisher using its unique ID"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Publisher disabled successfully",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = PublisherResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.NOT_FOUND,
            description = "Publisher not found with the given ID",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @DELETE
    @Path("/{id}")
    public Response disableById(@PathParam("id") Integer id) {
        return Response
                .status(Status.OK)
                .entity(publisherService.disableById(id))
                .build();
    }


    @Operation(
            summary = "Get all publishers by name",
            description = "Retrieve a list of all publishers matching the given name"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successful retrieval of publishers by name",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = PublisherBriefResponse.class,
                            type = SchemaType.ARRAY
                    )
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GET
    @Path("/search")
    public Response findAllByName(@QueryParam("name") String name) {
        return Response
                .status(Status.OK)
                .entity(publisherService.findAllByName(name))
                .build();
    }


    @Operation(
            summary = "Search and paginate publishers",
            description = "Search publishers based on filter criteria and paginate the results"
    )
    @Tag(name = "Publisher")
    @APIResponse(
            responseCode = HttpStatusCode.OK,
            description = "Successful retrieval of paginated publishers",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(
                            implementation = PublisherDetailResponse.class
                    )
            )
    )
    @APIResponse(
            responseCode = HttpStatusCode.INTERNAL_SERVER_ERROR,
            description = "Internal server error occurred",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ExceptionResponse.class)
            )
    )
    @GET
    @Path("/paginate")
    public Response searchAndPaginate(@BeanParam PublisherPageFilterQuery filterQuery) {
        return Response
                .status(Status.OK)
                .entity(publisherService.searchAndPaginate(filterQuery))
                .build();
    }

}
