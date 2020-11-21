package com.example;



import org.json.JSONObject;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.MediaTracker;
import java.net.MalformedURLException;
import java.net.URL;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.example.client.*;
import com.example.client.dto.ResponseDTO;
import com.example.model.*;
import com.google.gson.Gson;


public class Main {

    public static void main(String[] args) throws MalformedURLException {
    	
    	
    	
        JFrame frame = new JFrame(); // cria frame 
        // seta preferencias do frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(580, 250);
   
    	
        // inicializa painel
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());

        // inicializa label
        JLabel lblImg = new JLabel(); 
        JLabel lblNom = new JLabel(); 
        JTextArea textDesc =  new JTextArea(10, 20);
        textDesc.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textDesc); 
        scrollPane.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(250, 250));
        
        StringBuilder sb = new StringBuilder();
        String desc = "";
        String nom = "";
        
    	 
        try {

            
         String nomePesquisa = JOptionPane.showInputDialog("Digite o nome do personagem");    
         ResponseDTO<Personagem> resposta = new PersonagemClient().buscar(nomePesquisa);
         
         
         
         if (resposta.getData().getResults().size()==0) {
        	 
             System.out.println("Quantidade de retorno: "+resposta.getData().getResults().size()+"\nNão há personagem com esse nome!");
             System.exit(1);
             
        	 
         }else 
         
         {
        	 
        	 
        	  System.out.println("Quantidade de retorno: "+resposta.getData().getResults().size());
        	 
        	 
         }

        
        	  String texto =   new Gson().toJson(resposta.getData().getResults().get(0));
         
   
         
        	  System.out.println("Resposta em formato Json = "+texto);
         

	         JSONObject obj = new JSONObject(texto);
	         
	         if (obj.has("name")) {
	        	 nom = obj.getString("name");
	        	    System.out.println("Nome = " + obj.getString("name")); //
	        	} else {
	        	    System.out.println("não possui nome");
	        	}
	         
	         
	         if (obj.has("description")) {
	        	 desc = obj.getString("description");
	     	    System.out.println("Descrição = " + obj.getString("description"));
	     	} else {
	     	    System.out.println("não possui descrição");
	     	}
	                
	         JSONObject foto = obj.getJSONObject("thumbnail");
	         for (String chave : foto.keySet()) {	 
	        	 sb.append(foto.get(chave));
	        	 if(chave.equals("path"))
	        	 sb.append("."); 
	         }
	         
	         System.out.println("Imagem = "+sb);
	           
	
	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        
	        
	
	        URL urlImg = new URL(sb.toString());
	        ImageIcon imgIcon = new ImageIcon(urlImg);
	        
	        Image image = imgIcon.getImage();
	        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);
	        imgIcon = new ImageIcon(newimg); 
	        
	       
	        
	        
	        // faz o preload da imagem
	        while(imgIcon.getImageLoadStatus() == MediaTracker.LOADING); 
	
	        // injeta o icone no label
	        lblImg.setIcon(imgIcon);
	        lblNom.setText(nom);
	        textDesc.setText(desc);
	        
	        
	        Font boldFontNom = new Font( "Serif", Font.BOLD, 28 );
	        lblNom.setFont(boldFontNom);
	
	
	        
	        // adicina o label no panel
	        p.add(lblNom);
	        p.add(lblImg);
	        p.add(textDesc);
	
	        frame.getContentPane().add(p);
	
	        // abre a janela (frame)
	        frame.setVisible(true);  
            

    }


}
