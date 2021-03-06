package us.kbase.taxonomyservice;

import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import us.kbase.auth.AuthToken;
import us.kbase.common.service.JsonClientCaller;
import us.kbase.common.service.JsonClientException;
import us.kbase.common.service.RpcContext;
import us.kbase.common.service.UnauthorizedException;

/**
 * <p>Original spec-file module name: taxonomy_service</p>
 * <pre>
 * A KBase module: taxonomy_service
 * This module serve as the taxonomy service in KBase.
 * </pre>
 */
public class TaxonomyServiceClient {
    private JsonClientCaller caller;
    private String serviceVersion = null;


    /** Constructs a client with a custom URL and no user credentials.
     * @param url the URL of the service.
     */
    public TaxonomyServiceClient(URL url) {
        caller = new JsonClientCaller(url);
    }
    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param token the user's authorization token.
     * @throws UnauthorizedException if the token is not valid.
     * @throws IOException if an IOException occurs when checking the token's
     * validity.
     */
    public TaxonomyServiceClient(URL url, AuthToken token) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, token);
    }

    /** Constructs a client with a custom URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public TaxonomyServiceClient(URL url, String user, String password) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password);
    }

    /** Constructs a client with a custom URL
     * and a custom authorization service URL.
     * @param url the URL of the service.
     * @param user the user name.
     * @param password the password for the user name.
     * @param auth the URL of the authorization server.
     * @throws UnauthorizedException if the credentials are not valid.
     * @throws IOException if an IOException occurs when checking the user's
     * credentials.
     */
    public TaxonomyServiceClient(URL url, String user, String password, URL auth) throws UnauthorizedException, IOException {
        caller = new JsonClientCaller(url, user, password, auth);
    }

    /** Get the token this client uses to communicate with the server.
     * @return the authorization token.
     */
    public AuthToken getToken() {
        return caller.getToken();
    }

    /** Get the URL of the service with which this client communicates.
     * @return the service URL.
     */
    public URL getURL() {
        return caller.getURL();
    }

    /** Set the timeout between establishing a connection to a server and
     * receiving a response. A value of zero or null implies no timeout.
     * @param milliseconds the milliseconds to wait before timing out when
     * attempting to read from a server.
     */
    public void setConnectionReadTimeOut(Integer milliseconds) {
        this.caller.setConnectionReadTimeOut(milliseconds);
    }

    /** Check if this client allows insecure http (vs https) connections.
     * @return true if insecure connections are allowed.
     */
    public boolean isInsecureHttpConnectionAllowed() {
        return caller.isInsecureHttpConnectionAllowed();
    }

    /** Deprecated. Use isInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public boolean isAuthAllowedForHttp() {
        return caller.isAuthAllowedForHttp();
    }

    /** Set whether insecure http (vs https) connections should be allowed by
     * this client.
     * @param allowed true to allow insecure connections. Default false
     */
    public void setIsInsecureHttpConnectionAllowed(boolean allowed) {
        caller.setInsecureHttpConnectionAllowed(allowed);
    }

    /** Deprecated. Use setIsInsecureHttpConnectionAllowed().
     * @deprecated
     */
    public void setAuthAllowedForHttp(boolean isAuthAllowedForHttp) {
        caller.setAuthAllowedForHttp(isAuthAllowedForHttp);
    }

    /** Set whether all SSL certificates, including self-signed certificates,
     * should be trusted.
     * @param trustAll true to trust all certificates. Default false.
     */
    public void setAllSSLCertificatesTrusted(final boolean trustAll) {
        caller.setAllSSLCertificatesTrusted(trustAll);
    }
    
    /** Check if this client trusts all SSL certificates, including
     * self-signed certificates.
     * @return true if all certificates are trusted.
     */
    public boolean isAllSSLCertificatesTrusted() {
        return caller.isAllSSLCertificatesTrusted();
    }
    /** Sets streaming mode on. In this case, the data will be streamed to
     * the server in chunks as it is read from disk rather than buffered in
     * memory. Many servers are not compatible with this feature.
     * @param streamRequest true to set streaming mode on, false otherwise.
     */
    public void setStreamingModeOn(boolean streamRequest) {
        caller.setStreamingModeOn(streamRequest);
    }

    /** Returns true if streaming mode is on.
     * @return true if streaming mode is on.
     */
    public boolean isStreamingModeOn() {
        return caller.isStreamingModeOn();
    }

    public void _setFileForNextRpcResponse(File f) {
        caller.setFileForNextRpcResponse(f);
    }

    public String getServiceVersion() {
        return this.serviceVersion;
    }

    public void setServiceVersion(String newValue) {
        this.serviceVersion = newValue;
    }

    /**
     * <p>Original spec-file function name: search_taxonomy</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.taxonomyservice.DropDownItemInputParams DropDownItemInputParams}
     * @return   parameter "output" of type {@link us.kbase.taxonomyservice.DropDownData DropDownData}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public DropDownData searchTaxonomy(DropDownItemInputParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<DropDownData>> retType = new TypeReference<List<DropDownData>>() {};
        List<DropDownData> res = caller.jsonrpcCall("taxonomy_service.search_taxonomy", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: create_taxonomy</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.taxonomyservice.CreateTaxonomyInputParams CreateTaxonomyInputParams}
     * @return   parameter "output" of type {@link us.kbase.taxonomyservice.CreateTaxonomyOut CreateTaxonomyOut}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public CreateTaxonomyOut createTaxonomy(CreateTaxonomyInputParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<CreateTaxonomyOut>> retType = new TypeReference<List<CreateTaxonomyOut>>() {};
        List<CreateTaxonomyOut> res = caller.jsonrpcCall("taxonomy_service.create_taxonomy", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_taxonomies_by_id</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.taxonomyservice.GetTaxonomiesIdInputParams GetTaxonomiesIdInputParams}
     * @return   parameter "output" of type {@link us.kbase.taxonomyservice.GetTaxonomiesOut GetTaxonomiesOut}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetTaxonomiesOut getTaxonomiesById(GetTaxonomiesIdInputParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetTaxonomiesOut>> retType = new TypeReference<List<GetTaxonomiesOut>>() {};
        List<GetTaxonomiesOut> res = caller.jsonrpcCall("taxonomy_service.get_taxonomies_by_id", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: change_taxa</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.taxonomyservice.ChangeTaxaInputParams ChangeTaxaInputParams}
     * @return   parameter "output" of type {@link us.kbase.taxonomyservice.ChangeTaxaOut ChangeTaxaOut}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public ChangeTaxaOut changeTaxa(ChangeTaxaInputParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<ChangeTaxaOut>> retType = new TypeReference<List<ChangeTaxaOut>>() {};
        List<ChangeTaxaOut> res = caller.jsonrpcCall("taxonomy_service.change_taxa", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    /**
     * <p>Original spec-file function name: get_genomes_for_taxonomy</p>
     * <pre>
     * </pre>
     * @param   params   instance of type {@link us.kbase.taxonomyservice.GetGenomesTaxonomyInputParams GetGenomesTaxonomyInputParams}
     * @return   parameter "output" of type {@link us.kbase.taxonomyservice.GetTaxonomiesOut GetTaxonomiesOut}
     * @throws IOException if an IO exception occurs
     * @throws JsonClientException if a JSON RPC exception occurs
     */
    public GetTaxonomiesOut getGenomesForTaxonomy(GetGenomesTaxonomyInputParams params, RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        args.add(params);
        TypeReference<List<GetTaxonomiesOut>> retType = new TypeReference<List<GetTaxonomiesOut>>() {};
        List<GetTaxonomiesOut> res = caller.jsonrpcCall("taxonomy_service.get_genomes_for_taxonomy", args, retType, true, true, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }

    public Map<String, Object> status(RpcContext... jsonRpcContext) throws IOException, JsonClientException {
        List<Object> args = new ArrayList<Object>();
        TypeReference<List<Map<String, Object>>> retType = new TypeReference<List<Map<String, Object>>>() {};
        List<Map<String, Object>> res = caller.jsonrpcCall("taxonomy_service.status", args, retType, true, false, jsonRpcContext, this.serviceVersion);
        return res.get(0);
    }
}
