/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleclientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente {
	public boolean valida=true;
	Socket sok=null;
DataInputStream in=null;
DataOutputStream ou=null;
        public int porta;
        public String ip;
	public Cliente(String ip, int porta){
            this.porta=porta;
            this.ip=ip;
		try {
			sok=new Socket(ip,porta);
			in=new DataInputStream(sok.getInputStream());
			ou=new DataOutputStream(sok.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		valida=false;	
		}	}
public void esTx(String tx){
	try {//Função de escrita texto, es-> escrita
		ou.writeUTF(tx);
	} catch (IOException e) {
		e.printStackTrace();
		valida=false;
	}}//Função de escrita numero, es-> escrita
public void esNm(double db){
	try {
		ou.writeDouble(db);
	} catch (IOException e) {
		e.printStackTrace();
		valida=false;
	}}
public Double lerNm(){
		try {//Função para ler Numero Ler
		return in.readDouble();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		valida=false;
	}	return null;}
public String lerTx(){
try {
	return	in.readUTF();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
valida=false;
}
return null;
}











}





