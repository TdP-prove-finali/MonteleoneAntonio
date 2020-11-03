package it.polito.tdp.trasportoRifiuti.model;

public class MezzoDiTrasporto implements Comparable<MezzoDiTrasporto>{
	
	private Integer id;
	private String trasportatore;
	private Integer capienza;
	
	public MezzoDiTrasporto(Integer id, String trasportatore, Integer capienza) {
		super();
		this.id = id;
		this.trasportatore = trasportatore;
		this.capienza = capienza;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTrasportatore() {
		return trasportatore;
	}

	public void setTrasportatore(String trasportatore) {
		this.trasportatore = trasportatore;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((trasportatore == null) ? 0 : trasportatore.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MezzoDiTrasporto other = (MezzoDiTrasporto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (trasportatore == null) {
			if (other.trasportatore != null)
				return false;
		} else if (!trasportatore.equals(other.trasportatore))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MezzoDiTrasporto [id=" + id + ", trasportatore=" + trasportatore + ", capienza=" + capienza + "]";
	}

	@Override
	public int compareTo(MezzoDiTrasporto o) {
		
		if(this.trasportatore.equals(o.trasportatore)) {
			return this.id-o.id;
		}else {
			return this.trasportatore.compareTo(o.trasportatore);
		}

	}
	
	
	

}
