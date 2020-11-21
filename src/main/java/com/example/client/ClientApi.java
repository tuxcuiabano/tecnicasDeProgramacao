package com.example.client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.client.dto.ResponseDTO;
import com.example.config.ApiConfig;
import com.example.helper.StringHashHelper;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class ClientApi<T> {

    private final GsonBuilder gsonBuilder;
    private final Type collectionType;
    private final ApiConfig apiConfig;

    protected abstract ResponseDTO<T> buscar(String filtro) throws IOException, NoSuchAlgorithmException;

    public ClientApi() throws IOException {
        this.collectionType = new TypeToken<ResponseDTO<T>>(){}.getType();
        this.gsonBuilder = new GsonBuilder();
        this.apiConfig = new ApiConfig();
    }

    public ResponseDTO<T> buscar(List<String> paths, Map<String, String> params) throws IOException, NoSuchAlgorithmException {
        Long timeStamp = System.currentTimeMillis();

        params.put("ts", timeStamp.toString());
        params.put("apikey", this.apiConfig.getKey());
        params.put("hash", StringHashHelper.hashMD5(timeStamp + this.apiConfig.getSecret() + this.apiConfig.getKey()));

        HttpUrl.Builder urlBuilder = this.buildUrl(paths, params);

        Request request = new Request.Builder().url(urlBuilder.build()).build();


        try (Response response = new OkHttpClient().newCall(request).execute()) {

            String resposta = Objects.requireNonNull(response.body()).string();
            ResponseDTO<T> responseDTO = this.gsonBuilder.create().fromJson(resposta, this.collectionType);
          

            return responseDTO;
        }
    }

    private HttpUrl.Builder buildUrl(List<String> paths, Map<String, String> params){
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(this.apiConfig.getUrl())).newBuilder();

        paths.forEach(urlBuilder::addEncodedPathSegment);
        params.forEach(urlBuilder::addEncodedQueryParameter);

        return urlBuilder;
    }


}
