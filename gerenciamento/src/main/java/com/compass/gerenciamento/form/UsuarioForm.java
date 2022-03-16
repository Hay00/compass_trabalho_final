package com.compass.gerenciamento.form;

import com.compass.gerenciamento.model.Usuario;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotNull;

@Setter
public class UsuarioForm {
	@NotNull
	private String email;
	@NotNull
	private String senha;

	public Usuario convert() {
		Usuario usuario = new Usuario();
		usuario.setEmail(this.email);
		// usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		usuario.setSenha(this.senha);
		return usuario;
	}
}
