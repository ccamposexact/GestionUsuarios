package com.gestionusuario.app.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.PermisoService;

import static com.gestionusuario.app.enumerator.Identificadores.ACCESS_TOKEN_VALIDITY_SECONDS;

import static com.gestionusuario.app.enumerator.Identificadores.SIGNING_KEY;

import com.gestionusuario.app.enumerator.PermisosLista;


@Component
public class JwtTokenUtil implements Serializable{
	
	
	@Autowired
    private PermisoService permisoservice;
	
	@Autowired
	private PerfilService perfilservice;
	
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    
    

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(Usuario usuario) throws Exception {
        return doGenerateToken(usuario);
    }
    
    
    

    private String doGenerateToken(Usuario usuario) throws Exception {
    	
    	
    	String permisosID = permisoservice.ObtenerPermisosID(usuario.getIdUsuario());
    	List<String> permisosNombre = new ArrayList<String>();
        String[] permisosIDarray = permisosID.split(",");
        int[] intpermisosID = new int[permisosIDarray.length];
        for (int i = 0; i < permisosIDarray.length; i++) {
           String numberAsString = permisosIDarray[i];
           intpermisosID[i] = Integer.parseInt(numberAsString);
        }
        for (int i = 0; i < intpermisosID.length; i++) {
        	permisosNombre.add(permisoservice.ObtenerPermisosNombre(intpermisosID[i]));
        }

        Claims claims = Jwts.claims().setSubject(String.valueOf(usuario.getIdUsuario()));
        claims.put("idperfil", perfilservice.ObtenerPerfilID(usuario.getIdUsuario()));
        claims.put("perfil", perfilservice.ObtenerPerfil(usuario.getIdUsuario()));
        claims.put("permisos", permisosNombre);
        claims.put("matricula", usuario.getMatricula());
        claims.put("usuario", usuario.getNombre());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
