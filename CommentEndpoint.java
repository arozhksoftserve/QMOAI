package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.comment.CommentDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * Endpoint service for comments, extends AbstractWebEndpoint. Provides CRUD operations for comments.
 */
public class CommentEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String COMMENTS_END = "/comments";
    private static final String COMMENTS_RESOURCE_END = "/comments/{commentID}";

    /**
     * Constructor to initialize specification for the requests.
     * @param specification the specification for the requests
     */
    public CommentEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new comment and returns the created comment data.
     * @param commentDto the data for the new comment to be created
     * @return the created comment data
     */
    public CommentDto create(CommentDto commentDto) {
        return create(commentDto, HttpStatus.CREATED)
            .extract().as(CommentDto.class);
    }

    /**
     * Creates a new comment and returns the response.
     * @param commentDto the data for the new comment to be created
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse create(CommentDto commentDto, HttpStatus status) {
        LOGGER.info("Create new Comment");
        return post(
            this.specification,
            COMMENTS_END,
            commentDto)
            .statusCode(status.getCode());
    }

    /**
     * Updates a comment with the specified id and returns the updated comment data.
     * @param id the id of the comment to be updated
     * @param commentDto the new data for the comment
     * @return the updated comment data
     */
    public CommentDto update(int id, CommentDto commentDto) {
        return update(commentDto, id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }

    /**
     * Updates a comment with the specified id and returns the response.
     * @param commentDto the new data for the comment
     * @param id the id of the comment to be updated
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse update(CommentDto commentDto, int id, HttpStatus status) {
        LOGGER.info("Update Comment by id [{}]", id);
        return put(
            this.specification,
            COMMENTS_RESOURCE_END,
            commentDto,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a comment with the specified id and returns the comment data.
     * @param id the id of the comment to be retrieved
     * @return the comment data
     */
    public CommentDto getById(int id) {
        return getById(id, HttpStatus.OK)
            .extract().as(CommentDto.class);
    }

    /**
     * Retrieves a comment with the specified id and returns the response.
     * @param id the id of the comment to be retrieved
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse getById(int id, HttpStatus status) {
        LOGGER.info("Get Comment by id [{}]", id);
        return get(
            this.specification,
            COMMENTS_RESOURCE_END,
            String.valueOf(id))
            .statusCode(status.getCode());
    }

    /**
     * Retrieves all comments and returns a list of comment data.
     * @return a list of all comment data
     */
    public List<CommentDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(CommentDto[].class));
    }

    /**
     * Retrieves all comments and returns the response.
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Comments");
        ValidatableResponse response = get(this.specification, COMMENTS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
