package com.oliveira.templateapi.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(schema = "seguranca", name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "login")
    @NotEmpty(message = "{campo.login.obrigatorio}")
    private String username;

    @NotEmpty(message = "{campo.password.obrigatorio}")
    private String password;
    
	@Transient
	private boolean encoded;

//	public void encodeSenha(PasswordEncoder encoder) {
//		if (this.encoded) {
//			throw new IllegalStateException("Senha já está criptografada");
//		}
//		this.password = encoder.encode(this.password);
//	}
}
