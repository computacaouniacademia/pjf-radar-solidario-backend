package com.radar.solidario.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charity")
@Entity(name = "charity")
public class Charity implements Serializable {

	private static final long serialVersionUID = -8506472921384348180L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	@Size(max = 255, message = "O campo 'Entidade' deve conter no máximo 255 caracteres")
	private String name;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "address_charity", joinColumns = {
			@JoinColumn(name = "address_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "charity_id", referencedColumnName = "id") })
	private Address address;

	@ToString.Exclude
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "donate_charity", joinColumns = {
			@JoinColumn(name = "donate_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "charity_id", referencedColumnName = "id") })
	private Donate donate;
	
//	@ToString.Exclude
//	@OneToMany(mappedBy = "charity")
//	private List<User> user;
}
