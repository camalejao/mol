package mol.model.curso;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import mol.model.Entidade;

@Entity
@Table(name="t_periodo")
public class Periodo extends Entidade {
	
	private int ano;
	
	@Enumerated(EnumType.ORDINAL)
	private EntradaPeriodo entrada;
	
	@Column(name="data_inicio")
    //@Temporal(TemporalType.DATE)
	private LocalDate dataIncio;
	
	@Column(name="data_fim")
    //@Temporal(TemporalType.DATE)
	private LocalDate dataFim;
	
	
	
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public EntradaPeriodo getEntrada() {
		return entrada;
	}

	public void setEntrada(EntradaPeriodo entrada) {
		this.entrada = entrada;
	}

	public LocalDate getDataIncio() {
		return dataIncio;
	}

	public void setDataIncio(LocalDate dataIncio) {
		this.dataIncio = dataIncio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
}
