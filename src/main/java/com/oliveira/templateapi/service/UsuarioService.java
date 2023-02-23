package com.oliveira.templateapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oliveira.templateapi.exception.UsuarioCadastradoException;
import com.oliveira.templateapi.model.entity.Usuario;
import com.oliveira.templateapi.model.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    
	@Autowired
	private PasswordEncoder encoder;

    public Usuario salvar(Usuario usuario){
        boolean exists = repository.existsByUsername(usuario.getUsername());
        if(exists){
            throw new UsuarioCadastradoException(usuario.getUsername());
        }
        
        String encodedPassword = encoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPassword);
       // criptografarSenha(usuario);
        return repository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        Usuario usuario = repository
                            .findByUsername(username)
                            .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );

        return User
                .builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build()
                ;
    }
    
    public boolean authenticate(String username, String password) {
        Usuario usuario = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado.") );
        
        if (usuario != null) {
            return encoder.matches(password, usuario.getPassword());
        }
        return false;
    }

//	private void criptografarSenha(Usuario usuario) {
//		usuario.encodeSenha(encoder);
//	}
}
