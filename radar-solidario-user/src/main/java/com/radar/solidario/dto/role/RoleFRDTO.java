package com.radar.solidario.dto.role;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.radar.solidario.dto.authentication.AuthenticationRDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleFRDTO implements Serializable {

	private static final long serialVersionUID = -7619822387652277936L;

	@NotNull(message = "O campo 'Id' é obrigatório")
	private Long id;

	@NotNull(message = "O campo 'Cargo' é obrigatório")
	@Size(max = 255, message = "O campo 'Cargo' deve conter no máximo 255 caracteres")
	private String name;

	@NotNull(message = "O campo 'Autenticação' é obrigatório")
	private List<AuthenticationRDTO> authentication;
}
