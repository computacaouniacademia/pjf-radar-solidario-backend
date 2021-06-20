package com.radar.solidario.dto.authentication;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.annotation.Password;
import com.radar.solidario.constant.AuthenticationRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRDTO implements Serializable {

	private static final long serialVersionUID = 2932728917392452591L;

	@NotNull(message = "O campo 'Email' é obrigatório")
	@Size(min = 6, max = 80, message = "O campo 'Email' deve conter entre 6 e 80 caracteres")
	@Email(message = "O campo 'Email' é inválido")
	private String email;

	@Size(min = 8, max = 40, message = "O campo 'Senha' deve conter entre 8 a 40 caracteres")
	@Password
	private String password;

	@NotNull(message = "O campo 'Função' é obrigatório")
	@Enumerated(EnumType.STRING)
	private AuthenticationRole role;
}