package com.pe.bcp.challenge.api.seguridad.controlador;



import com.pe.bcp.challenge.api.seguridad.JWT;
import com.pe.bcp.challenge.api.seguridad.PBKDF2Encoder;
import com.pe.bcp.challenge.api.seguridad.dto.AuthRequest;
import com.pe.bcp.challenge.api.seguridad.dto.AuthResponse;
import com.pe.bcp.challenge.api.seguridad.servicio.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class AutenticationApiRest {

    @Autowired
    private JWT jwt;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private UserService userService;


    @PostMapping(value = "/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest request){
        return userService.findByUserName(request.getUsername()).map((userDetails) -> {
            if(passwordEncoder.encode(request.getPassword()).equals(userDetails.getPassword())){
                return ResponseEntity.ok(new AuthResponse(jwt.generateToken(userDetails)));
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }


}
