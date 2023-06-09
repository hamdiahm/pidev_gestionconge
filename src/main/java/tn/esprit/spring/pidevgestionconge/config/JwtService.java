package tn.esprit.spring.pidevgestionconge.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY="3F442A472D4B6150645367566B59703373367639792442264529482B4D625165";  //SECRET KEY TO SIGN TOKEN


    public  String extractUsername(String token) {      //EXTRACT USERNAME WITH EXTRACT ONE CLAIM (SUBJECT IS USERNAME IN OUR CASE EMAIL)
        return extractClaim(token,Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){    //EXTRACT ONE CLAIM (EXAMPLE WE NEED ONLY USERNAME)
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){   //GENERATE TOKEN WITHOUT EXTRA CLAIMS
        return generateToken(new HashMap<>(),userDetails);
    }

    public String generateToken(        //GENERATE TOKEN WITH EXTRA CLAIMS
            Map<String, Object> extracClaims,
            UserDetails userDetails
    ){
        return Jwts
                .builder()
                .setClaims(extracClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();                     //GENERATE RETURN TOKEN
    }

    public boolean isTokenValid(String token,UserDetails userDetails){      //VALIDATE A TOKEN
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token) {      //CHECK IF TOKEN EXPIRED
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {      // EXTRACTING EXPIRATION = EXTRA CLAIM
        return extractClaim(token,Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){      //EXTRACT ALL CLAIMS
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())              //SIGN KEY TO DECODE THE TOKEN
                .build()                                    //BUILD OBJECT
                .parseClaimsJws(token)
                .getBody();                                 //GET THE CLAIMS WITHIN THE TOKEN
    }

    private Key getSignInKey() {
        byte [] keyBytes = Decoders.BASE64.decode(SECRET_KEY);      //OBJECT OF TYPE KEYBYTES AND DECODDE SECRET KEY
        return Keys.hmacShaKeyFor(keyBytes);                        //HMAC ALGORITHM
    }


}
