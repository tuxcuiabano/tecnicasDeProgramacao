package com.example.model;


import com.example.client.dto.ModelDTO;
import com.google.gson.annotations.SerializedName;

public class Personagem extends ModelDTO {

    private String id;

    @SerializedName("description")
    private String descricao;
    
    private String imagem;



    public String getId() {
        return id;
    }

    public String getNome() {
        return super.getName();
    }

    public String getDescricao() {
        return descricao;
    }


    public String getImagem() {
        return imagem;
    }
    
}
