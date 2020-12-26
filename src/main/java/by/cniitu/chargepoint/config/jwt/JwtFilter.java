package by.cniitu.chargepoint.config.jwt;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    // TODO use it when we will use tokens in every request
    // private final JwtProvider jwtProvider;
    // private final UserService userService;

    // public JwtFilter(JwtProvider jwtProvider, UserService userService) {
    //     this.jwtProvider = jwtProvider;
    //     // this.userService = userService;
    // }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        // TODO use it when we will use tokens in every request
        // String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        // if (token != null && jwtProvider.validateToken(token)) {
        //     String userLogin = jwtProvider.getLoginFromToken(token);
        //     User user;
        //     if(userLogin.contains("@")){
        //         user = userService.findUserByEmail(userLogin);
        //     } else {
        //         user = userService.findUserByPhoneNumber(userLogin);
        //     }
        //     UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null);
        //     SecurityContextHolder.getContext().setAuthentication(auth);
        // }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    // TODO use it when we will use tokens in every request
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
