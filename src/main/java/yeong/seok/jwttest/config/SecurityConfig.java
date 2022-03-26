package yeong.seok.jwttest.config;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity  //기본적인 Web 보안을 활성화 하겠다는 어노테이션
//추가적인 설정을 위해서
//1.WebSecurityConfigurer implements 하거나
//2.WebSecurityAdapter를 extends 하여 설정
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * h2-console 접근을 위한 시큐리티 설정
     *
     * h2-console 접근은 spring security 관련 로직을 수행하지 않도록
     * configure(WebSecurity web)를 오버라이드한 메소드를 새롭게 추가
     */
    @Override
    public void configure(WebSecurity web){
        System.out.println("h2-console 시큐리티 설정 메서드 입니다.");
        web
                .ignoring()
                .antMatchers(
                  "/h2-console/**"
                            , "/favicon.ico"
                );
    }




    /** configure 메소드를 오버라이드 하여 /api/hi에 대한 접근이 인증없이
    *  접근 될 수 있도록 허용.*/
    @Override
    protected void configure(HttpSecurity http) throws Exception{

        /**
         * authorizeRequests()는 HttpServletRequest를 사용하는
         * 요청들에 대한 접근제한을 설정하겠다는 의미고
         * anyMatchers(path).permitAll()는
         * 해당 path로 들어오는 요청은 인증없이 접근을 허용하겠다는 의미.
         */

        /**
         * .anyRequest().authenticated()는 나머지 요청은 모두
         * 인증되어야 한다는 의미입니다.
         */
        http
                .authorizeRequests()
                .antMatchers("/api/hi").permitAll()
                .anyRequest().authenticated();
    }










}
