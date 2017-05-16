/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.service;

import example.exceptiobn.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Assert;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

/**
 *
 * @author renan
 */
@Service
public class AccountService {

    @Autowired
    SecretService secretService;

    public String userName;
    
    private static final String BEARER_IDENTIFIER = "Bearer "; // space is important

    public JSONObject validaToken(HttpServletRequest req) throws JSONException {

        Assert.notNull(req);

        // get JWT as Authorization header
        String authorization = req.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith(BEARER_IDENTIFIER)) {
            throw new UnauthorizedException("Missing or invalid Authorization header with Bearer type.");
        }

        String jwt = authorization.substring(BEARER_IDENTIFIER.length());

        return validaToken(jwt);
    }
    
        public JSONObject validaToken(String jwt) throws JSONException {
            
        JSONObject ob = new JSONObject();

        try {
            
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKeyResolver(secretService.getSigningKeyResolver())
                    .parseClaimsJws(jwt);

            userName = jws.getBody().getIssuer();

            ob.put("usuario", userName);
            ob.put("jws", jws);
            
        } catch (JSONException ex) {
            Logger.getLogger(AccountService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ob;
    }

}
