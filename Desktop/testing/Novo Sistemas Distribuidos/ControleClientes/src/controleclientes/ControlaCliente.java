/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleclientes;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlaCliente {

    String ip = "192.168.137.1";
    String ip2 = "localhost";

    int porta = 3314;
    int porta2 = 3313;
    boolean continua = true;
    Cliente1 cli;
    Cliente cli2;

    public ControlaCliente() {
        try {
            String conf[] = new Ler().AbrirS("config.jpc").split("\n");

            for (int cont = 0; cont < conf.length; cont++) {
                System.out.println("Tentando em "+conf[cont].split(";")[0]+"\n"+
                     conf[cont].split(";")[1]);
                new iniciaClienteGeral(conf[cont].split(";")[0],
                        Integer.parseInt(conf[cont].split(";")[1])).start();
            }

        } catch (Exception e) {
        }

    }

    class iniciaClienteGeral extends Thread {

        int portag;
        String ipg;

        public iniciaClienteGeral(String ipg, int portag) {
            this.portag = portag;
            this.ipg = ipg;
        }

        public void run() {
            while (continua) {

                Cliente1 cliL = new Cliente1(ipg, portag);
                if (cliL.valida) {
                    new usoDeDadosGeral(cliL).start();
                    System.out.println("Cliente Conectado " + portag + " " + ipg);
                    break;
                }
                System.out.println("Cliente não conectado!!" + portag + " " + ipg);

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    int contE = 0;

    class usoDeDadosGeral extends Thread {

        Cliente1 clig = null;
        int contL=0;

        public usoDeDadosGeral(Cliente1 clig) {
            this.clig = clig;
            contL = contE++;
        }

        public void run() {
            String tx, oldTx = null;
            while (continua) {
                
                if (clig != null) {
                     tx = clig.lerTx();
                     
                     if (tx != null) {

                        oldTx = tx;
                    }
                    
                    if (tx == null) {

                        break;
                    }
                    System.out.println("Conectado no Servidor, Cliente - "
                            + contL+ "- Valor lido "+tx);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControlaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
		//System.gc();

            }
            System.out.println("Morreu " + oldTx);
            
            new iniciaClienteGeral(clig.ip, clig.porta).start();
            clig = null;
        }

    }

    class iniciaCliente extends Thread {

        int portal;
        int porta2;

        public iniciaCliente(int porta, int porta2) {
            portal = porta;
            this.porta2 = porta2;
        }

        public void run() {
            while (continua) {
                if (cli == null) {
                    cli = new Cliente1(ip, portal);
                    System.out.println("Cliente Conectado " + portal);
                }
                if (cli2 == null) {
                    cli2 = new Cliente1(ip2, porta2);
                    System.out.println("Cliente Conectado " + porta2);
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    class usoDeDados extends Thread {

        public void run() {
            while (continua) {
                if (cli != null) {
                    String tx = cli.lerTx();
                    System.out.println(tx);
                    if (tx == null) {
                        cli = null;
                    }
                    System.out.println("Finalizando Envio Cli1 "
                            + contE++);
                }
                if (cli2 != null) {
                    String tx = cli2.lerTx();
                    System.out.println(tx);
                    if (tx == null) {
                        cli2 = null;
                    }

                    System.out.println("Finalizando Envio Cli2 "
                            + contE++);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControlaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
		//System.gc();

            }
        }

    }

    public static void main(String args[]) {

        new ControlaCliente();
    }
}
