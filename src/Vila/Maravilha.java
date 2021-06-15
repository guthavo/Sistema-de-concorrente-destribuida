package Vila;


import tela.Principal;

public class Maravilha {
	
	//Attributes
	private Principal principal;
	private Vila vila;
	private int tijolo;
	
	public Maravilha(Vila vila, Principal principal){
		this.vila = vila;
		this.principal = principal;
		this.tijolo = 0;
		this.principal.habilitarMaravilha();
		this.principal.mostrarMaravilha(this.tijolo);
	}
	

	public void produzirTijolo(){
		try {
			
			Thread.sleep(1000);
			this.tijolo += 1000;
			this.principal.mostrarMaravilha(this.tijolo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//Getters and Setters
	public int getTijolo(){
		return this.tijolo;
	}
	

}
