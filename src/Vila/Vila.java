package Vila;


import java.util.ArrayList;
import java.util.List;
import tela.Principal;

public class Vila {
	private Principal principal;
	private Prefeitura prefeitura;
	private List<Aldeao> aldeoes = new ArrayList<Aldeao>();
	private List<Fazenda> fazendas = new ArrayList<Fazenda>();
	private List<Mina> minas = new ArrayList<Mina>();
	private Templo templo;
	private Maravilha maravilha;
	
	
	public Vila(Principal principal) {
		this.principal = principal;
		this.prefeitura = new Prefeitura(this.principal,this);
		this.prefeitura.start();
		this.maravilha = new Maravilha(this, this.principal);
		
	}
	

	public int gerarIdAldeao() {
		return this.aldeoes.size();
	}
	
	public void addAldeoes(Aldeao aldeao) {
		this.aldeoes.add(aldeao);
	}
	
	public void addMina(Mina mina) {
		this.minas.add(mina);
	}
	
	public int gerarIdMina() {
		return this.minas.size();
	}
	
	public void addFazenda(Fazenda fazenda) {
		this.fazendas.add(fazenda);
	}

	public int gerarIdFazenda() {
		return this.fazendas.size();
	}
	
	
	
	public boolean verificaEPaga(int comida, int ouro, int oferenda) {
		if(this.prefeitura.getComida() >= comida && this.prefeitura.getOuro() >= ouro && this.prefeitura.getOferenda() >= oferenda) {
			this.prefeitura.retirarComida(comida);
			this.prefeitura.retirarOuro(ouro);
			this.prefeitura.retirarOferenda(oferenda);
			this.principal.mostrarComida(this.prefeitura.getComida());
			this.principal.mostrarOuro(this.prefeitura.getOuro());
			this.principal.mostrarOferendaFe(this.prefeitura.getOferenda());
			return true;
		}
		return false;
	}
	
	
//Getters and Setters
	public Aldeao getAldeao(int id) {
		return this.aldeoes.get(id);
	}
	
	public List<Aldeao> getListaAldeos() {
		return this.aldeoes;
	}
	

	public Fazenda getFazenda(int id) {
		return this.fazendas.get(id);
	}
	
	public List<Fazenda> getListaFazendas() {
		return this.fazendas;
	}
	

	public Mina getMina(int id) {
		return this.minas.get(id);
	}
	
	public List<Mina> getListaMinas() {
		return this.minas;
	}
	

	public void setTemplo(Templo t){
		this.templo = t;
	}
	
	public Templo getTemplo(){
		return this.templo;
	}
	

	public void setMaravilha(Maravilha m){
		this.maravilha = m;
	}
	
	public Maravilha getMaravilha(){
		return this.maravilha;
	}
	
	public Prefeitura getPrefeitura() {
		return this.prefeitura;
	}
}
