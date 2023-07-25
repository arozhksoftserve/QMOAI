package com.softserve.taf.services.placeholder.endpoints;

import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.softserve.taf.models.enums.HttpStatus;
import com.softserve.taf.models.placeholder.user.UserDto;
import com.softserve.taf.services.common.AbstractWebEndpoint;

/**
 * Endpoint service for users, extends AbstractWebEndpoint. Provides CRUD operations for users.
 */
public class UserEndpoint extends AbstractWebEndpoint {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USERS_END = "/users";
    private static final String USERS_RESOURCE_END = "/users/{userID}";

    /**
     * Constructor to initialize specification for the requests.
     * @param specification the specification for the requests
     */
    public UserEndpoint(RequestSpecification specification) {
        super(specification);
    }

    /**
     * Creates a new user and returns the created user data.
     * @param userDto the data for the new user to be created
     * @return the created user data
     */
    public UserDto create(UserDto userDto) {
        return create(userDto, HttpStatus.CREATED)
            .extract().as(UserDto.class);
    }

    /**
     * Creates a new user and returns the response.
     * @param userDto the data for the new user to be created
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse create(UserDto userDto, HttpStatus status) {
        LOGGER.info("Create new User");
        return post(
            this.specification,
            USERS_END,
            userDto)
            .statusCode(status.getCode());
    }

    /**
     * Updates a user with the specified id and returns the updated user data.
     * @param id the id of the user to be updated
     * @param userDto the new data for the user
     * @return the updated user data
     */
    public UserDto update(int id, UserDto userDto) {
        return update(userDto, id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Updates a user with the specified id and returns the response.
     * @param userDto the new data for the user
     * @param id the id of the user to be updated
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse update(UserDto userDto, int id, HttpStatus status) {
        LOGGER.info("Update User by id [{}]", id);
        return put(
            this.specification,
            USERS_RESOURCE_END,
            userDto,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves a user with the specified id and returns the user data.
     * @param id the id of the user to be retrieved
     * @return the user data
     */
    public UserDto getById(String id) {
        return getById(id, HttpStatus.OK)
            .extract().as(UserDto.class);
    }

    /**
     * Retrieves a user with the specified id and returns the response.
     * @param id the id of the user to be retrieved
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse getById(String id, HttpStatus status) {
        LOGGER.info("Get User by id [{}]", id);
        return get(
            this.specification,
            USERS_RESOURCE_END,
            id)
            .statusCode(status.getCode());
    }

    /**
     * Retrieves all users and returns a list of user data.
     * @return a list of all user data
     */
    public List<UserDto> getAll() {
        return List.of(getAll(HttpStatus.OK).extract().as(UserDto[].class));
    }

    /**
     * Retrieves all users and returns the response.
     * @param status the expected status of the response
     * @return the response of the request
     */
    public ValidatableResponse getAll(HttpStatus status) {
        LOGGER.info("Get all Users");
        ValidatableResponse response = get(this.specification, USERS_END);
        response.statusCode(status.getCode());
        return response;
    }
}
