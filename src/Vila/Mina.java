package Vila;

import java.util.ArrayList;
import java.util.List;
import tela.Principal;

public class Mina {
 	private Principal principal;
	private Vila vila;
	private int id;
	private int qtdAldeoes;
	private int ouro;
	private int nivel;
	private List<Integer> idAldeoes;
	

	public Mina(Vila vila, int id, Principal principal){
		this.vila = vila;
		this.id = id;
		this.principal = principal;
		this.qtdAldeoes = 0;
		this.ouro = 0;
		this.nivel = 1;
		idAldeoes = new ArrayList<Integer>();
	}
	

// Ações da Mina
	public void minerar(int nivelAldeao){
		
		try {
			Thread.sleep(2000);
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.ouro += 5 * nivelAldeao;
		System.out.println((nivelAldeao*5) + " unidades de ouro produzidas");
		
	}
	
	public void transportar(int nivelAldeao){
		if(this.ouro >= 5*nivelAldeao) {
			try {
				Thread.sleep(3000/nivelAldeao);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
				
			this.ouro -= 5*nivelAldeao;
			this.vila.getPrefeitura().receberOuro(5*nivelAldeao);
			this.principal.mostrarOuro(this.vila.getPrefeitura().getOuro());
			System.out.println((nivelAldeao*5) + " unidades de ouro transportadas");
				
		}else {
			System.out.println("Ouro insuficinte");
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
			this.principal.mostrarMinaOuro(this.id + 1, stringListaId());
		}else{
			if(this.idAldeoes.size() <= 5*this.nivel) {
				
				this.idAldeoes.add(id+1);
				this.qtdAldeoes++;
				this.principal.mostrarMinaOuro(this.id + 1, stringListaId());
			}else {
				System.out.println("Quantidade limite de aldeões atingida");
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
		System.out.println("Aldeão "+ posicao +" removido");
		if(podeRemover) {
			
			this.idAldeoes.remove(posicao);
			this.qtdAldeoes--;
			this.principal.mostrarMinaOuro(this.id + 1, stringListaId());
		}	
	}
	
	public String stringListaId() {
		String stringLista = "";
		
		if(this.idAldeoes.isEmpty() ){
			
			return "Sem aldeão";
		}else{
			for (Integer id : idAldeoes) {
				stringLista += " "+id;
			}
		}
		return stringLista;
	}
	

	public boolean verificaSeEstaNaMina(int id) {
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
	
	public int getOuro() {
		return this.ouro;
	}
	
	public int getIdMina(){
		return this.id;
	}
	
	public int getNivel(){
		return this.nivel;
	}
	
}
