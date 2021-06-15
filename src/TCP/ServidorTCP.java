package TCP;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServidorTCP extends Thread {
	private Socket conexao;
	private List<ObjectOutputStream> saidas;

	private ServidorTCP(Socket conexao, List<ObjectOutputStream> saidas) {
		System.out.println("Cliente conectado: "+ conexao.getInetAddress().getHostAddress());
		this.conexao = conexao;
		this.saidas = saidas;
	}

	public static void main(String[] args) {
		System.out.println("Servidor levantado...");
		List<ObjectOutputStream> saidas = new ArrayList<ObjectOutputStream>();
		try {
			@SuppressWarnings("resource")
			ServerSocket servidor = new ServerSocket(12345);
			while (true) {
				Socket conexao = servidor.accept();
				(new ServidorTCP(conexao, saidas)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		DateFormat formato = new SimpleDateFormat("HH:mm");
		try {
			ObjectInputStream entrada = new ObjectInputStream(this.conexao.getInputStream());
			ObjectOutputStream saida = new ObjectOutputStream(this.conexao.getOutputStream());
			synchronized (this.saidas) {
				this.saidas.add(saida);
			}
			String mensagem = (String) entrada.readObject();
			super.setName(mensagem);
			try {
				while (!(mensagem = (String) entrada.readObject()).equals("CMD|DESCONECTAR")) {
					System.out.println("dentro --> ["+ mensagem +"]");
					synchronized (this.saidas) {
						String msg = super.getName() +"("+ formato.format(new Date()) +"): "+ mensagem;
						for (ObjectOutputStream saida2 : this.saidas)
							saida2.writeObject(msg);
					}
				}
				System.out.println("fora --> ["+ mensagem +"]");
			} catch (SocketException e) {} 
			synchronized (this.saidas) {
				this.saidas.remove(saida);
			}
			saida.close();
			entrada.close();
			System.out.println("Cliente desconectado: "+ conexao.getInetAddress().getHostAddress());
			this.conexao.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}