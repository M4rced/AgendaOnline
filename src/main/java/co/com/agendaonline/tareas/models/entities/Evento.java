package co.com.agendaonline.tareas.models.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="evento")
public class Evento implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotBlank
	@Column(name="titulo")
	private String titulo;
	
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechainicio")
	private LocalDate fechaInicio;
	
	
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="fechafin")
	private LocalDate fechaFin;
	
	@NotBlank
	@Column(name="horas")
	private Integer horas;
	
	@NotBlank
	@Column(name="descripcion")
	private String descripcion;
	
	@NotBlank
	@Column(name="nivel_prioridad")
	private String nivelPrioridad;
	
	@Column(name="Lugar")
	private String asignada;

	private String foto1;

	private String foto2;
	
	public Evento() {
		super();
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public LocalDate getFechaInicio() {
		return fechaInicio;
	}



	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}



	public LocalDate getFechaFin() {
		return fechaFin;
	}



	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}



	public Integer getHoras() {
		return horas;
	}



	public void setHoras(Integer horas) {
		this.horas = horas;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getNivelPrioridad() {
		return nivelPrioridad;
	}



	public void setNivelPrioridad(String nivelPrioridad) {
		this.nivelPrioridad = nivelPrioridad;
	}



	public String getAsignada() {
		return asignada;
	}



	public void setAsignada(String asignada) {
		this.asignada = asignada;
	}



	public String getFoto1() {
		return foto1;
	}



	public void setFoto1(String foto1) {
		this.foto1 = foto1;
	}



	public String getFoto2() {
		return foto2;
	}



	public void setFoto2(String foto2) {
		this.foto2 = foto2;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return Objects.equals(id, other.getId());
	}
	

}

/*** creado por M4rced and G3ors ***/
