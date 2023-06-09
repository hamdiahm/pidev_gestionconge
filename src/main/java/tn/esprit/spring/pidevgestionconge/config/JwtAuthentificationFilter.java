package tn.esprit.spring.pidevgestionconge.config;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthentificationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");       //EXTRACT JWT AUTH TOKEN FROM HEADER IN authHeader
        final String jwt;
        final String userEmail;                                                //FOR JWT SERVICE TO EXTRACT THE USER NAME (WE NEED TO CALL USER DETAIL SERVICE TO CHECK IF USER IN DB VIA JWT SERVICE)

        if(authHeader == null || !authHeader.startsWith(("Bearer ")) ){       //IF TOKEN DOES NOT EXIST OR THE TOKEN DOES NOT START WITH BEARER(jwt)
            filterChain.doFilter(request,response);                                  //PASS REQUEST TO NEXT FILTER
            return;
        }
        jwt = authHeader.substring(7);                               //EXTRACTING TOKEN FROM HEADER POSITION NUMBER 7 WITH SUBSTRING BEARER WITH SPACE THE COUNT IS 7

        userEmail = jwtService.extractUsername(jwt);                           //EXTRACT USERNAME FROM JWT

        if (userEmail!= null && SecurityContextHolder.getContext().getAuthentication()==null){      // OBJECT SECURITY CONTEXT HOLDER IS NULL MEANS USER IS NOT AUTHENTCATED
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);        //GETTING USER FROM DB
            if (jwtService.isTokenValid(jwt,userDetails)){                                          //IF TOKEN VALID + USERDETAILS FOUND IN DB
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(        //OBJECT NEEDED BY SECURITY CONTEXT HOLDER TO UPDATE IT
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(                                                               //GIVE THE AUTH TOKEN MORE DETAILS PARAM OBJECT OF TYPE WebAuthenticationDetailsSource
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);                    //UPDATE SECURITY CONTEXT HOLDER
            }
        }
        filterChain.doFilter(request,response);     //CALL FILTER CHAIN AND PASS TO NEXT FILTER
    }
}
