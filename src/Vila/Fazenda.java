package Vila;

import java.util.ArrayList;
import java.util.List;
import tela.Principal;

public class Fazenda {
	private Principal principal;
	private Vila vila;
	private int id;
	private int qtdAldeoes;
	private int comida;
	private int nivel;
	private List<Integer> idAldeoes;
	

	public Fazenda(Vila vila, int id, Principal principal) {
		this.vila = vila;
		this.id = id;
		this.principal = principal;
		this.qtdAldeoes = 0;
		this.comida = 0;	
		this.nivel = 1;
		idAldeoes = new ArrayList<Integer>();
	}
	

// A��es da Fazenda
	public void cultivar(int nivelAldeao) {
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.comida += 10*nivelAldeao;
		System.out.println((nivelAldeao*10) + " unidades de comida produzidas");
	}
	
	public void transportar(int nivelAldeao) {
		if(this.comida >= 10*nivelAldeao) {
			try {
				Thread.sleep(2000/nivelAldeao);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
			this.comida -= 10*nivelAldeao;
			this.vila.getPrefeitura().receberComida(10*nivelAldeao);
			this.principal.mostrarComida(this.vila.getPrefeitura().getComida());
			System.out.println((nivelAldeao*10) + " unidades de comida transportadas");
				
		}else {
			System.out.println("Comida insuficinte");
		}
	}
	
	public void evoluir(){
		if(this.nivel == 1) {
			
				this.nivel = 2;
				
			} else {
				System.out.println("Ja esta evoluido");
			}
	}
	

	public void adionarNaLista(int id) {
		if(this.idAldeoes.isEmpty()) {
			
			this.idAldeoes.add(id+1);
			this.qtdAldeoes++;
			this.principal.mostrarFazenda(this.id + 1, stringListaId());
		}else{
			if(this.idAldeoes.size() <= 5*this.nivel) {
				
				this.idAldeoes.add(id+1);
				this.qtdAldeoes++;
				this.principal.mostrarFazenda(this.id + 1, stringListaId());
				
			}else {
				System.out.println("Quantidade limite de alde�es atingida");
			}
		}
	}
	
	public void retirarDaLista(int id) {
		int posicao = 0;
		int i = 0;
		boolean podeRemover = false;
		
		for (Integer integer : idAldeoes) {
			if(integer == id+1) {
				
				posicao = i;
				podeRemover = true;
			}
			i++;
	}
		System.out.println("Alde�o "+ posicao +" removido");
		if(podeRemover) {
			
			this.idAldeoes.remove(posicao);
			this.qtdAldeoes--;
			this.principal.mostrarFazenda(this.id + 1, stringListaId());
		}	
	}
	
	
	public String stringListaId() {
		String stringLista = "";
		
		if(this.idAldeoes.isEmpty() ){
			
			return "Sem alde�o";
		}else{
			for (Integer id : idAldeoes) {
				stringLista += " "+id;
			}
		}
		return stringLista;
	}
	
	public boolean verificaSeEstaNaFazenda(int id) {
		for (Integer idNaLista : idAldeoes) {
			if(idNaLista == id+1)
				
				return true;
		}
		return false;
	}
	
	
//Getters and Setters
	public int getQtdAldeoes() {
		return this.qtdAldeoes;
	}
	
	public int getComida() {
		return this.comida;
	}
	
	public int getIdFazenda() {
		return this.id;
	}
	
	public int getNivel() {
		return this.nivel;
	}
	
	public List<Integer> getListaId(){
		return this.idAldeoes;
	}
	

	
}
