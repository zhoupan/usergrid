/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.usergrid.rest.test.resource.endpoints.mgmt;

import org.apache.usergrid.rest.test.resource.endpoints.NamedResource;
import org.apache.usergrid.rest.test.resource.endpoints.UrlResource;
import org.apache.usergrid.rest.test.resource.model.ApiResponse;
import org.apache.usergrid.rest.test.resource.model.Organization;
import org.apache.usergrid.rest.test.resource.state.ClientContext;

import javax.ws.rs.core.MediaType;
import java.util.Map;


/**
 * This is for the Management endpoint.
 * Holds the information required for building and chaining organization objects to applications.
 * Should also contain the GET,PUT,POST,DELETE methods of functioning in here.
 */
public class OrganizationResource extends NamedResource {

    public OrganizationResource(final String name, final ClientContext context, final UrlResource parent) {
        super(name, context, parent);
    }

    public UsersResource users() {
        return new UsersResource(context, this);
    }

    public ApplicationsResource applications() {
        return new ApplicationsResource(context, this);
    }

    public Organization get() {
        ApiResponse rep = getTarget( true ).request()
            .accept(MediaType.APPLICATION_JSON)
            .get(ApiResponse.class);

        //TODO: not sure if this will work for multiple users.
        Organization org = new Organization(rep);
        org.setUserOwner(rep);
        return org;
    }

    // Doesn't return anything useful server side so this was made as a void. .
    public void put(Organization organization) {
        Map<String, Object> response = getTarget(true).request()
            .accept(MediaType.APPLICATION_JSON)
            .put( javax.ws.rs.client.Entity.json(organization), Organization.class);

    }

    public ApplicationResource app(){
        return new ApplicationResource(  context ,this );
    }

    public CredentialsResource credentials() {
        return new CredentialsResource(context, this);
    }

    public ApplicationsResource apps() {
        return new ApplicationsResource( context, this );
    }


    public ApplicationResource addToPath( String pathPart ) {
        return new ApplicationResource( pathPart, context, this );
    }

}
