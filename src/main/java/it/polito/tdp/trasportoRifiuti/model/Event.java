package it.polito.tdp.trasportoRifiuti.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		NUOVA_REGISTRAZIONE,
		FINE_GIORNATA
	}
	
	private EventType type;
	private LocalDate data;
	private String ragSocTrasp;
	private String zonaRaccolta;
	private int peso;
	
	public Event(EventType type, LocalDate data, String ragSocTrasp, String zonaRaccolta, int peso) {
		super();
		this.type = type;
		this.data = data;
		this.ragSocTrasp = ragSocTrasp;
		this.zonaRaccolta = zonaRaccolta;
		this.peso = peso;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getRagSocTrasp() {
		return ragSocTrasp;
	}

	public void setRagSocTrasp(String ragSocTrasp) {
		this.ragSocTrasp = ragSocTrasp;
	}

	public String getZonaRaccolta() {
		return zonaRaccolta;
	}

	public void setZonaRaccolta(String zonaRaccolta) {
		this.zonaRaccolta = zonaRaccolta;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((ragSocTrasp == null) ? 0 : ragSocTrasp.hashCode());
		result = prime * result + ((zonaRaccolta == null) ? 0 : zonaRaccolta.hashCode());
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
		Event other = (Event) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (ragSocTrasp == null) {
			if (other.ragSocTrasp != null)
				return false;
		} else if (!ragSocTrasp.equals(other.ragSocTrasp))
			return false;
		if (zonaRaccolta == null) {
			if (other.zonaRaccolta != null)
				return false;
		} else if (!zonaRaccolta.equals(other.zonaRaccolta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [type=" + type + ", data=" + data + ", ragSocTrasp=" + ragSocTrasp + ", zonaRaccolta="
				+ zonaRaccolta + ", peso=" + peso + "]";
	}

	@Override
	public int compareTo(Event o) {
		if(this.data.equals(o.data)) {
			return -(this.peso-o.peso);
		}else {
			return this.data.compareTo(o.data);
		}
		
	}
	
	

}
