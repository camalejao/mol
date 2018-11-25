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
	private LocalDate dataInicio;
	
	@Column(name="data_fim")
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

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataIncio) {
		this.dataInicio = dataIncio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	public String toString() {
		String periodo = this.ano + "." + this.entrada.getSemestre();
		return periodo;
	}
}
