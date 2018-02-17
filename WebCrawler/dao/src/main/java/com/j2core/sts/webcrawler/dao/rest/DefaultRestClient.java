package com.j2core.sts.webcrawler.dao.rest;

import com.j2core.sts.webcrawler.dao.model.dto.FinalInformationDto;
import com.j2core.sts.webcrawler.dao.model.dto.TaskResultDto;
import com.j2core.sts.webcrawler.dao.model.informationdto.NodeData;
import com.j2core.sts.webcrawler.dao.model.informationdto.UrlData;
import com.j2core.sts.webcrawler.dao.model.userdto.SecurityToken;
import com.j2core.sts.webcrawler.dao.model.userdto.UserData;
import org.apache.log4j.Logger;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The class is default rest client interface implementation
 */
public class DefaultRestClient implements RestClient{

    private final static Logger LOGGER = Logger.getLogger(DefaultRestClient.class); // class for save logs information
    private static final String REST_URL = "http://localhost:8080/TaskService/API";
    private ClientConfig configuration = null;


    public DefaultRestClient(){

        this.configuration = new ClientConfig();
        this.configuration.property(ClientProperties.CONNECT_TIMEOUT, 10000);
        this.configuration.property(ClientProperties.READ_TIMEOUT, 10000);

    }

    private Client getJerseyClient() {

        return ClientBuilder.newClient(configuration);
    }


    @Override
    public SecurityToken getSecurityToken(UserData user){

        SecurityToken token = null;
        Client jerseyClient = getJerseyClient();

        try {

            token = jerseyClient.target(REST_URL).path("auth/get").request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(user, MediaType.APPLICATION_JSON)).readEntity(SecurityToken.class);

        }catch (Exception ex){

            LOGGER.error(" Get security token exception: " + ex);
        }

        jerseyClient.close();
        return token;

    }

    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "NP_NULL_ON_SOME_PATH", justification = "Exception detail hide")
    public NodeData getNodeData(int nodeId, SecurityToken token) throws Exception {

        String path = "node/get/" + String.valueOf(nodeId);
        Client jerseyClient = getJerseyClient();
        NodeData nodeData = null;

        Response response = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                post(Entity.entity(token, MediaType.APPLICATION_JSON));

        try {

            nodeData = response.readEntity(NodeData.class);

        }catch (Exception e){

            LOGGER.error(" Get node data exception: " + e);

        }

        jerseyClient.close();
        return nodeData;
    }

    @Override
    public Integer getNodeStatus(int nodeId, SecurityToken token){

        Integer status = -1;
        String path = "node/status/" + String.valueOf(nodeId);
        Client jerseyClient = getJerseyClient();

        try {

            status = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(token, MediaType.APPLICATION_JSON)).readEntity(Integer.class);

        }catch (Exception ex){

            LOGGER.error(" Get node status exception: " + ex);
        }

        jerseyClient.close();
        return status;
    }

    @Override
    public boolean changeWorkFlag(int nodeId, int status, SecurityToken token){

        boolean result = false;
        String path = "node/flag/" + String.valueOf(nodeId) + "/" + String.valueOf(status);
        Client jerseyClient = getJerseyClient();

        try {

            result = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(token, MediaType.APPLICATION_JSON)).readEntity(Boolean.class);

        }catch (Exception ex){

            LOGGER.error(" Change work flag exception: " + ex);
        }

        jerseyClient.close();
        return result;

    }

    @Override
    public Integer createNode(String nodeName, SecurityToken token){

        Integer result = -1;
        String path = "node/new/" + nodeName;
        Client jerseyClient = getJerseyClient();

        try {

            result = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(token, MediaType.APPLICATION_JSON)).readEntity(Integer.class);

        }catch (Exception ex){

            LOGGER.error(" Create new node exception: " + ex);
        }

        jerseyClient.close();
        return result;
    }

    @Override
    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings(value = "SIC_INNER_SHOULD_BE_STATIC_ANON", justification = "Exception detail hide")
    public List<UrlData> getTask(int nodeId, int amountTask, SecurityToken token) {

        String path = "task/get/" + String.valueOf(nodeId) + "/" + String.valueOf(amountTask);
        Client jerseyClient = getJerseyClient();
        List<UrlData> tasks;

        try {
            Response response = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(token, MediaType.APPLICATION_JSON));

            tasks = response.readEntity(new GenericType<List<UrlData>>() {});

        }catch (Exception ex){

            LOGGER.error(" Get new Tasks exception: " + ex);
            tasks = new LinkedList<>();
        }

        jerseyClient.close();
        return tasks;

    }


    @Override
    public boolean saveTaskResult(TaskResultDto taskResult) {

        Client jerseyClient = getJerseyClient();

        boolean result = false;

        try {

            result = jerseyClient.target(REST_URL).path("task/saveResult").request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(taskResult, MediaType.APPLICATION_JSON)).readEntity(Boolean.class);

        }catch (Exception ex){

            LOGGER.error(" Save task result exception: " + ex);
        }

        jerseyClient.close();
        return result;

    }


    @Override
    public boolean finalSaveInformation(FinalInformationDto finalInfo, int nodeId) {

        String path = "/task/finalSave/" + nodeId;
        Client jerseyClient = getJerseyClient();
        boolean result = false;

        try {

            result = jerseyClient.target(REST_URL).path(path).request(MediaType.APPLICATION_JSON).
                    post(Entity.entity(finalInfo, MediaType.APPLICATION_JSON)).readEntity(Boolean.class);

        }catch (Exception ex){

            LOGGER.error(" Final save information exception: " + ex);
        }

        jerseyClient.close();

        return result;

    }
}
