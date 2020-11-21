package com.example.client;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.client.dto.ResponseDTO;
import com.example.model.Personagem;

public class PersonagemClient extends ClientApi<Personagem> {

    public PersonagemClient() throws IOException {
    }

    public ResponseDTO<Personagem> buscar(String nome) throws IOException, NoSuchAlgorithmException {
        
    	Map<String, String> params = new HashMap<>();
      
        params.put("name", nome);

        return super.buscar(Collections.singletonList("characters"), params);
    }
}
