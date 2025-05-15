package com.sstinternaltools.sstinternal_tools.security.controller.Implementation;

import com.sstinternaltools.sstinternal_tools.security.service.interfaces.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthControllerImpl {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    /* ────────────────────────────────────────────────────────────
     * OAuth success landing page
     * (Your CustomOAuth2SuccessHandler already does the heavy lifting:
     *   - registers the user if needed
     *   - sets secure cookies
     *   - redirects here if you want one, or straight to "/" otherwise.)
     * Feel free to replace the body with a static HTML file if preferred.
     * ──────────────────────────────────────────────────────────── */
    @GetMapping(value = "/oauth-success", produces = MediaType.TEXT_HTML_VALUE)
    public String oauthSuccess() {
        return """
               <html>
                 <head>
                   <meta http-equiv="refresh" content="0; URL='/'"/>
                 </head>
                 <body>
                   <p>Login successful. Redirecting&hellip;</p>
                 </body>
               </html>
               """;
    }

    /* ────────────────────────────────────────────────────────────
     * Refresh access token
     * ──────────────────────────────────────────────────────────── */
    @GetMapping("/refresh")
    public ResponseEntity<Map<String, String>> rotateRefreshToken(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String headerToken,
            @CookieValue(value = "refreshToken", required = false) String cookieToken) {

        String token = headerToken != null ? headerToken : cookieToken;

        Map<String, String> tokens = authService.rotateRefreshToken(token);

        // For header-only clients you can still return the tokens in JSON
        // For SPA + cookies you might not need to return anything
        return ResponseEntity.ok(tokens);
    }

    /* ────────────────────────────────────────────────────────────
     * Logout
     * ──────────────────────────────────────────────────────────── */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String headerToken,
            @CookieValue(value = "refreshToken", required = false) String cookieToken,
            HttpServletResponse response) {

        String token = headerToken != null ? headerToken : cookieToken;
        authService.logout(token);

        /* Clear cookies on client */
        ResponseCookie expiredAccess = ResponseCookie.from("accessToken", "")
                .path("/api/").maxAge(0).httpOnly(true).secure(true).build();
        ResponseCookie expiredRefresh = ResponseCookie.from("refreshToken", "")
                .path("/auth/refresh").maxAge(0).httpOnly(true).secure(true).build();

        response.addHeader(HttpHeaders.SET_COOKIE, expiredAccess.toString());
        response.addHeader(HttpHeaders.SET_COOKIE, expiredRefresh.toString());

        return ResponseEntity.ok("✅ User logged out successfully");
    }

    /* ────────────────────────────────────────────────────────────
     * Optional helper:  whoami / health check
     * (Runs under /auth/, so still public; remove if you don't need it.)
     * ──────────────────────────────────────────────────────────── */
    @GetMapping("/ping")
    public ResponseEntity<String> ping(HttpServletRequest req) {
        return ResponseEntity.ok("Auth service is up — " + req.getRemoteAddr());
    }
}
