package com.springboot.config;

import org.springframework.context.annotation.Bean;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Bean
	static PasswordEncoder getPassword() {
		return new BCryptPasswordEncoder(); // Đối tượng BCryptPasswordEncoder để mã hóa mật khẩu
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("user1").password(getPassword().encode("123")).roles("GUEST").and()
		.withUser("user2").password(getPassword().encode("123")).roles("USER","GUEST").and()
		.withUser("user3").password(getPassword().encode("123")).roles("ADMIN","USER","GUEST");
	}

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    // Vô hiệu hóa bảo vệ CSRF và CORS để đơn giản hóa cấu hình, tùy vào yêu cầu của ứng dụng của bạn.
	    http.csrf().disable().cors().disable();

	    // Định nghĩa quy tắc kiểm soát truy cập
//	    http.authorizeRequests()
//	        .antMatchers("/home/index","/home/about","/auth/login/**").permitAll() // Cho phép truy cập công khai vào các URL này
//	        .anyRequest().authenticated(); // Yêu cầu xác thực cho tất cả các URL khác
	    
	    
	    //Phân quyền role
	    http.authorizeRequests()
	    .antMatchers("/home/admins").hasRole("ADMIN")
	    .antMatchers("/home/users").hasAnyRole("ADMIN","USER")
	    .antMatchers("/home/authenticated").authenticated()
	    .anyRequest().permitAll();
	    
	    
	    
	    // Điều khiển lỗi truy cập không đúng role
	    
	    http.exceptionHandling()
	    .accessDeniedPage("/auth/access/denied");

	    // Cấu hình đăng nhập bằng form
	    http.formLogin()
	        .loginPage("/auth/login/form") // Thiết lập URL của trang đăng nhập tùy chỉnh
	        .loginProcessingUrl("/auth/login") // Thiết lập URL xử lý đăng nhập khi người dùng gửi form đăng nhập
	        .defaultSuccessUrl("/auth/login/success", false) // Chuyển hướng đến URL này sau khi đăng nhập thành công
	        .failureUrl("/auth/login/error") // Chuyển hướng đến URL này sau khi đăng nhập thất bại
	        .usernameParameter("username") // Đặt tên tham số cho trường nhập tên đăng nhập
	        .passwordParameter("password"); // Đặt tên tham số cho trường nhập mật khẩu

	    // Cấu hình chức năng "Remember Me" để cho phép người dùng nhớ thông tin đăng nhập qua các phiên đăng nhập tiếp theo.
	    http.rememberMe()
	        .rememberMeParameter("remember"); // Đặt tên tham số cho checkbox "Nhớ tôi"

	    // Cấu hình đăng xuất
	    http.logout()
	        .logoutUrl("/auth/logoff") // Thiết lập URL xử lý đăng xuất
	        .logoutSuccessUrl("/auth/logoff/success"); // Chuyển hướng đến URL này sau khi đăng xuất thành công
	}

	
	

}
