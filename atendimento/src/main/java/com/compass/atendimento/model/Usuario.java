package com.compass.atendimento.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

	@Document
	public class Usuario implements UserDetails {
		private static final long serialVersionUID = 1L;

		@Id
		private String id;
		
		private String email;
		private String senha;
		
		
		
		public Usuario() {
			
		}
		
		public Usuario(String email, String senha, List<Perfil> perfis) {
			super();
			this.email = email;
			this.senha = senha;
			this.perfis = perfis;
		}

		public Usuario(String id, String email, String senha, List<Perfil> perfis) {
			this.id = id;
			this.email = email;
			this.senha = senha;
			this.perfis = perfis;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		
		@DBRef
		private List<Perfil> perfis = new ArrayList<>();
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return this.perfis;
		}

		@Override
		public String getPassword() {
			return this.senha;
		}

		@Override
		public String getUsername() {
			return this.email;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	
}

