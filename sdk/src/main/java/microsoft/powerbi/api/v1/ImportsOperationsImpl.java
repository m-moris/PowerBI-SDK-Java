/**
 * Code generated by Microsoft (R) AutoRest Code Generator 0.16.0.0
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package microsoft.powerbi.api.v1;

import com.google.common.reflect.TypeToken;
import com.microsoft.rest.ServiceCall;
import com.microsoft.rest.ServiceCallback;
import com.microsoft.rest.ServiceException;
import com.microsoft.rest.ServiceResponse;
import com.microsoft.rest.ServiceResponseBuilder;
import com.microsoft.rest.ServiceResponseCallback;
import com.microsoft.rest.Validator;
import java.io.IOException;
import microsoft.powerbi.api.v1.models.ImportInfo;
import microsoft.powerbi.api.v1.models.ImportModel;
import microsoft.powerbi.api.v1.models.ODataResponseListImport;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * An instance of this class provides access to all the operations defined
 * in ImportsOperations.
 */
public final class ImportsOperationsImpl implements ImportsOperations {
    /** The Retrofit service to perform REST calls. */
    private ImportsService service;
    /** The service client containing this operation class. */
    private PowerBIClient client;

    /**
     * Initializes an instance of ImportsOperations.
     *
     * @param retrofit the Retrofit instance built from a Retrofit Builder.
     * @param client the instance of the service client containing this operation class.
     */
    public ImportsOperationsImpl(Retrofit retrofit, PowerBIClient client) {
        this.service = retrofit.create(ImportsService.class);
        this.client = client;
    }

    /**
     * The interface defining all the services for ImportsOperations to be
     * used by Retrofit to perform actually REST calls.
     */
    interface ImportsService {
        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("v1.0/collections/{collectionName}/workspaces/{workspaceId}/imports")
        Call<ResponseBody> getImports(@Path("collectionName") String collectionName, @Path("workspaceId") String workspaceId);

        @Headers("Content-Type: application/json; charset=utf-8")
        @POST("v1.0/collections/{collectionName}/workspaces/{workspaceId}/imports")
        Call<ResponseBody> postImport(@Path("collectionName") String collectionName, @Path("workspaceId") String workspaceId, @Query("datasetDisplayName") String datasetDisplayName, @Query("nameConflict") String nameConflict, @Body ImportInfo importInfo);

        @Headers("Content-Type: application/json; charset=utf-8")
        @GET("v1.0/collections/{collectionName}/workspaces/{workspaceId}/imports/{importId}")
        Call<ResponseBody> getImportById(@Path("collectionName") String collectionName, @Path("workspaceId") String workspaceId, @Path("importId") String importId);

    }

    /**
     * Returns a list of imports for the specified workspace.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @throws ServiceException exception thrown from REST call
     * @throws IOException exception thrown from serialization/deserialization
     * @throws IllegalArgumentException exception thrown from invalid parameters
     * @return the ODataResponseListImport object wrapped in {@link ServiceResponse} if successful.
     */
    public ServiceResponse<ODataResponseListImport> getImports(String collectionName, String workspaceId) throws ServiceException, IOException, IllegalArgumentException {
        if (collectionName == null) {
            throw new IllegalArgumentException("Parameter collectionName is required and cannot be null.");
        }
        if (workspaceId == null) {
            throw new IllegalArgumentException("Parameter workspaceId is required and cannot be null.");
        }
        Call<ResponseBody> call = service.getImports(collectionName, workspaceId);
        return getImportsDelegate(call.execute());
    }

    /**
     * Returns a list of imports for the specified workspace.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if callback is null
     * @return the {@link Call} object
     */
    public ServiceCall getImportsAsync(String collectionName, String workspaceId, final ServiceCallback<ODataResponseListImport> serviceCallback) throws IllegalArgumentException {
        if (serviceCallback == null) {
            throw new IllegalArgumentException("ServiceCallback is required for async calls.");
        }
        if (collectionName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter collectionName is required and cannot be null."));
            return null;
        }
        if (workspaceId == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter workspaceId is required and cannot be null."));
            return null;
        }
        Call<ResponseBody> call = service.getImports(collectionName, workspaceId);
        final ServiceCall serviceCall = new ServiceCall(call);
        call.enqueue(new ServiceResponseCallback<ODataResponseListImport>(serviceCallback) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    serviceCallback.success(getImportsDelegate(response));
                } catch (ServiceException | IOException exception) {
                    serviceCallback.failure(exception);
                }
            }
        });
        return serviceCall;
    }

    private ServiceResponse<ODataResponseListImport> getImportsDelegate(Response<ResponseBody> response) throws ServiceException, IOException, IllegalArgumentException {
        return new ServiceResponseBuilder<ODataResponseListImport, ServiceException>(this.client.getMapperAdapter())
                .register(200, new TypeToken<ODataResponseListImport>() { }.getType())
                .build(response);
    }

    /**
     * Creates a new import using the specified import info.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param datasetDisplayName The display name of the dataset
     * @param importInfo The import to post
     * @throws ServiceException exception thrown from REST call
     * @throws IOException exception thrown from serialization/deserialization
     * @throws IllegalArgumentException exception thrown from invalid parameters
     * @return the ImportModel object wrapped in {@link ServiceResponse} if successful.
     */
    public ServiceResponse<ImportModel> postImport(String collectionName, String workspaceId, String datasetDisplayName, ImportInfo importInfo) throws ServiceException, IOException, IllegalArgumentException {
        if (collectionName == null) {
            throw new IllegalArgumentException("Parameter collectionName is required and cannot be null.");
        }
        if (workspaceId == null) {
            throw new IllegalArgumentException("Parameter workspaceId is required and cannot be null.");
        }
        if (datasetDisplayName == null) {
            throw new IllegalArgumentException("Parameter datasetDisplayName is required and cannot be null.");
        }
        if (importInfo == null) {
            throw new IllegalArgumentException("Parameter importInfo is required and cannot be null.");
        }
        Validator.validate(importInfo);
        final String nameConflict = null;
        Call<ResponseBody> call = service.postImport(collectionName, workspaceId, datasetDisplayName, nameConflict, importInfo);
        return postImportDelegate(call.execute());
    }

    /**
     * Creates a new import using the specified import info.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param datasetDisplayName The display name of the dataset
     * @param importInfo The import to post
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if callback is null
     * @return the {@link Call} object
     */
    public ServiceCall postImportAsync(String collectionName, String workspaceId, String datasetDisplayName, ImportInfo importInfo, final ServiceCallback<ImportModel> serviceCallback) throws IllegalArgumentException {
        if (serviceCallback == null) {
            throw new IllegalArgumentException("ServiceCallback is required for async calls.");
        }
        if (collectionName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter collectionName is required and cannot be null."));
            return null;
        }
        if (workspaceId == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter workspaceId is required and cannot be null."));
            return null;
        }
        if (datasetDisplayName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter datasetDisplayName is required and cannot be null."));
            return null;
        }
        if (importInfo == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter importInfo is required and cannot be null."));
            return null;
        }
        Validator.validate(importInfo, serviceCallback);
        final String nameConflict = null;
        Call<ResponseBody> call = service.postImport(collectionName, workspaceId, datasetDisplayName, nameConflict, importInfo);
        final ServiceCall serviceCall = new ServiceCall(call);
        call.enqueue(new ServiceResponseCallback<ImportModel>(serviceCallback) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    serviceCallback.success(postImportDelegate(response));
                } catch (ServiceException | IOException exception) {
                    serviceCallback.failure(exception);
                }
            }
        });
        return serviceCall;
    }

    /**
     * Creates a new import using the specified import info.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param datasetDisplayName The display name of the dataset
     * @param importInfo The import to post
     * @param nameConflict Determines what to do if a dataset with the same name already exists
     * @throws ServiceException exception thrown from REST call
     * @throws IOException exception thrown from serialization/deserialization
     * @throws IllegalArgumentException exception thrown from invalid parameters
     * @return the ImportModel object wrapped in {@link ServiceResponse} if successful.
     */
    public ServiceResponse<ImportModel> postImport(String collectionName, String workspaceId, String datasetDisplayName, ImportInfo importInfo, String nameConflict) throws ServiceException, IOException, IllegalArgumentException {
        if (collectionName == null) {
            throw new IllegalArgumentException("Parameter collectionName is required and cannot be null.");
        }
        if (workspaceId == null) {
            throw new IllegalArgumentException("Parameter workspaceId is required and cannot be null.");
        }
        if (datasetDisplayName == null) {
            throw new IllegalArgumentException("Parameter datasetDisplayName is required and cannot be null.");
        }
        if (importInfo == null) {
            throw new IllegalArgumentException("Parameter importInfo is required and cannot be null.");
        }
        Validator.validate(importInfo);
        Call<ResponseBody> call = service.postImport(collectionName, workspaceId, datasetDisplayName, nameConflict, importInfo);
        return postImportDelegate(call.execute());
    }

    /**
     * Creates a new import using the specified import info.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param datasetDisplayName The display name of the dataset
     * @param importInfo The import to post
     * @param nameConflict Determines what to do if a dataset with the same name already exists
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if callback is null
     * @return the {@link Call} object
     */
    public ServiceCall postImportAsync(String collectionName, String workspaceId, String datasetDisplayName, ImportInfo importInfo, String nameConflict, final ServiceCallback<ImportModel> serviceCallback) throws IllegalArgumentException {
        if (serviceCallback == null) {
            throw new IllegalArgumentException("ServiceCallback is required for async calls.");
        }
        if (collectionName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter collectionName is required and cannot be null."));
            return null;
        }
        if (workspaceId == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter workspaceId is required and cannot be null."));
            return null;
        }
        if (datasetDisplayName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter datasetDisplayName is required and cannot be null."));
            return null;
        }
        if (importInfo == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter importInfo is required and cannot be null."));
            return null;
        }
        Validator.validate(importInfo, serviceCallback);
        Call<ResponseBody> call = service.postImport(collectionName, workspaceId, datasetDisplayName, nameConflict, importInfo);
        final ServiceCall serviceCall = new ServiceCall(call);
        call.enqueue(new ServiceResponseCallback<ImportModel>(serviceCallback) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    serviceCallback.success(postImportDelegate(response));
                } catch (ServiceException | IOException exception) {
                    serviceCallback.failure(exception);
                }
            }
        });
        return serviceCall;
    }

    private ServiceResponse<ImportModel> postImportDelegate(Response<ResponseBody> response) throws ServiceException, IOException, IllegalArgumentException {
        return new ServiceResponseBuilder<ImportModel, ServiceException>(this.client.getMapperAdapter())
                .register(200, new TypeToken<ImportModel>() { }.getType())
                .build(response);
    }

    /**
     * Gets the import metadata for the specifed import id.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param importId The import id
     * @throws ServiceException exception thrown from REST call
     * @throws IOException exception thrown from serialization/deserialization
     * @throws IllegalArgumentException exception thrown from invalid parameters
     * @return the ImportModel object wrapped in {@link ServiceResponse} if successful.
     */
    public ServiceResponse<ImportModel> getImportById(String collectionName, String workspaceId, String importId) throws ServiceException, IOException, IllegalArgumentException {
        if (collectionName == null) {
            throw new IllegalArgumentException("Parameter collectionName is required and cannot be null.");
        }
        if (workspaceId == null) {
            throw new IllegalArgumentException("Parameter workspaceId is required and cannot be null.");
        }
        if (importId == null) {
            throw new IllegalArgumentException("Parameter importId is required and cannot be null.");
        }
        Call<ResponseBody> call = service.getImportById(collectionName, workspaceId, importId);
        return getImportByIdDelegate(call.execute());
    }

    /**
     * Gets the import metadata for the specifed import id.
     *
     * @param collectionName The workspace collection name
     * @param workspaceId The workspace id
     * @param importId The import id
     * @param serviceCallback the async ServiceCallback to handle successful and failed responses.
     * @throws IllegalArgumentException thrown if callback is null
     * @return the {@link Call} object
     */
    public ServiceCall getImportByIdAsync(String collectionName, String workspaceId, String importId, final ServiceCallback<ImportModel> serviceCallback) throws IllegalArgumentException {
        if (serviceCallback == null) {
            throw new IllegalArgumentException("ServiceCallback is required for async calls.");
        }
        if (collectionName == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter collectionName is required and cannot be null."));
            return null;
        }
        if (workspaceId == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter workspaceId is required and cannot be null."));
            return null;
        }
        if (importId == null) {
            serviceCallback.failure(new IllegalArgumentException("Parameter importId is required and cannot be null."));
            return null;
        }
        Call<ResponseBody> call = service.getImportById(collectionName, workspaceId, importId);
        final ServiceCall serviceCall = new ServiceCall(call);
        call.enqueue(new ServiceResponseCallback<ImportModel>(serviceCallback) {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    serviceCallback.success(getImportByIdDelegate(response));
                } catch (ServiceException | IOException exception) {
                    serviceCallback.failure(exception);
                }
            }
        });
        return serviceCall;
    }

    private ServiceResponse<ImportModel> getImportByIdDelegate(Response<ResponseBody> response) throws ServiceException, IOException, IllegalArgumentException {
        return new ServiceResponseBuilder<ImportModel, ServiceException>(this.client.getMapperAdapter())
                .register(200, new TypeToken<ImportModel>() { }.getType())
                .build(response);
    }

}
