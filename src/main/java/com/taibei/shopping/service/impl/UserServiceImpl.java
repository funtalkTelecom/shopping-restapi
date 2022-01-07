package com.taibei.shopping.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.taibei.shopping.config.NotFoundException;
import com.taibei.shopping.entity.Role;
import com.taibei.shopping.entity.SearchLog;
import com.taibei.shopping.entity.User;

import com.taibei.shopping.repository.RoleRepository;
import com.taibei.shopping.repository.SearchLogRepository;
import com.taibei.shopping.repository.UserRepository;
import com.taibei.shopping.service.UserService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
@author Xinpu Wang
*/
@Service @RequiredArgsConstructor @Transactional  @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

     @Autowired  private UserRepository userRepo;
     @Autowired  private RoleRepository roleRepo;
     @Autowired  private PasswordEncoder passwordEncoder;
	 @Autowired  private SearchLogRepository searchLogRepository;


    public Role findRoleById(Integer roleId){

        return  roleRepo.getOne(roleId);
    }

	public Slice<Object> findSearchLog(Pageable pageable){

		String userName= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		log.info("-------findSearchLog---------"+userName);
	    return this.searchLogRepository.findByUserNameOrderBySearchTimeDesc(userName,pageable);

	}

	public long deleteSearchKey(String searchKey){

		String userName= SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		return this.searchLogRepository.deleteByUserNameAndSearchKey(userName,searchKey);
	}

	public User save(User obj) {

	 	obj.setPassWord(passwordEncoder.encode(obj.getPassWord()));
	 	return userRepo.save(obj);

	 }

	 @Transactional
	 public List<User> saveAll(Iterable<User> list) {

	 	list.forEach(user -> user.setPassWord(passwordEncoder.encode(user.getPassWord())));
		 return userRepo.saveAll(list);

	 }

	 public User getOne(Integer id) {
		 return userRepo.getOne(id);
	 }


	 public User findById(Integer id) {
		 Optional<User> obj = userRepo.findById(id);
		 return obj.isPresent()?obj.get():null;
	 }


	 public void deleteById(Integer id) {
		 userRepo.deleteById(id);
	 }


	 @Transactional
	 public void deleteAll(List list) {
		 userRepo.deleteAll(list);
	 }


	 public void delete(User obj) {
		 userRepo.delete(obj);
	 }


	 public boolean existsById(Integer id) {
		 return userRepo.existsById(id);
	 }


	 public long count() {
		 return userRepo.count();
	 }


	 public List<User> findAll() {
		 return userRepo.findAll();
	 }


	 public List<User> findAll(User obj) {
		 List<User> list = userRepo.findAll(Example.of(obj));
		 return list==null||list.size()<1?null:list;
	 }


	 public List<User> findAll(Sort sort) {
		 return userRepo.findAll(sort);
	 }


	 public List<User> findAllById(Iterable<Integer> ids) {
		 return userRepo.findAllById(ids);
	 }


	 public List<User> findAll(Example<User> e) {
		 return userRepo.findAll(e);
	 }


	 public Page<User> findAll(Pageable pageable) {
		 return userRepo.findAll(pageable);
	 }


	 public List<User> findAll(Example<User> e, Sort sort) {
		return userRepo.findAll(e,sort);
	}


	 public Page<User> findAll(Example<User> e, Pageable page) {
		 return userRepo.findAll(e,page);
	 }


	 public Page<User> findAll(User obj, Pageable page) {
		 return userRepo.findAll(Example.of(obj),page);
	 }


	  public User findByUserName(String userName) {

	 	User  user= userRepo.findByUserName(userName);

	 	if (user ==null) throw new NotFoundException("user not be found.");

	 	return  user;
	 }




	public Page<User> findAll(Integer pageNo, Integer pageSize, String sortField, String sortDirection) {

		User user =new User("王新谱","123456","18611602701","qiluwxp@126.com");

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("username", match -> {match.caseSensitive(); })
				.withIgnorePaths("password");//忽略字段，即不管password是什么值都不加入查询条件

	 	Example<User> example =Example.of(user,matcher);

		Pageable pageable;
	 	if (sortField!=null && sortDirection!=null) {
			Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
					Sort.by(sortField).descending();
			 pageable=PageRequest.of(pageNo,pageSize,sort);
		}else
		    pageable=PageRequest.of(pageNo,pageSize);


		return userRepo.findAll(example,pageable);
	}


	public Page<User> findAll(Integer pageNo, Integer pageSize, String userName, int age) {

		Pageable pageable=PageRequest.of(pageNo,pageSize);

		return userRepo.findAll((root,criteriaQuery,criteriaBuilder)->{

			    List<Predicate> lstPredicates = new ArrayList<Predicate>();

			    Join<User, Order> join =  root.join("id", JoinType.LEFT);

			if(StringUtils.isNotBlank(userName))
				lstPredicates.add(criteriaBuilder.like(root.get("username"), "%"+ userName+"%"));

			if(age > 0)
				lstPredicates.add(criteriaBuilder.equal(root.get("age"), age));

			Predicate[] arrayPredicates = new Predicate[lstPredicates.size()];
			return criteriaBuilder.and(lstPredicates.toArray(arrayPredicates));

		},pageable);
	}



	@Override
	public Role addRole(Role role) {

	 	log.info("adding new role {} to the database.",role.getRoleName());
	 return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName,String roleName) {

		log.info("adding new role {} to user {}.",userName,roleName);

		User user=userRepo.findByUserName(userName);
		Role role =roleRepo.findByRoleName(roleName);

	 	user.getRoles().add(role);

	 	userRepo.save(user);

	}


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

	 	User  user=userRepo.findByUserName(userName);
	 	if (user== null){
	 		log.error("-----verification---user  not found in database.",userName);
	 		throw new UsernameNotFoundException("user name incorrect!");
		}else
			log.info("-----verification---user {} found in database.roles:{}",userName,user.getRoles());

		Collection<SimpleGrantedAuthority> authorities= new ArrayList<>();
		user.getRoles().forEach(role ->
				authorities.add(new SimpleGrantedAuthority(role.getRoleName()))
		  );
	 	return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassWord(),authorities);
	}

	@Override
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String  refreshToken;
		Map<String,String> responseMap = new HashMap<>();

		String authorizationHeader= request.getHeader(HttpHeaders.AUTHORIZATION);
		log.info("----refreshToken---received refreshToken:{}",authorizationHeader);

		if(authorizationHeader==null) {
			responseMap.put("errorMessage", "header not contain Authorization");
		}else if(!authorizationHeader.startsWith("Bearer:")){
			responseMap.put("errorMessage", "the Authorization in header not start with Bearer:");
		}else {

			// because of the same encryption algorithm for access_token and refresh_token,
			// access_token is also able to be used to refresh token if access_token is not expired.

			try {
				refreshToken = authorizationHeader.substring("Bearer:".length());

				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());

				JWTVerifier jwtVerifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);
				String userName = decodedJWT.getSubject();

				User user=userRepo.findByUserName(userName);

				String accessToken = JWT.create()
						.withSubject(user.getUserName())
						.withExpiresAt(new Date(System.currentTimeMillis()+30*60*1000))
						.withIssuer(request.getRequestURI())
						.withClaim("roles",user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
						.sign(algorithm);

				responseMap.put("accessToken",accessToken);
				responseMap.put("refreshToken",refreshToken);

			} catch (Exception e) {

				log.error("Error getting refreshToken :{}", e.getMessage());
				response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				responseMap.put("errorMessage",e.getMessage() != null ? e.getMessage():"invalid refreshToken.");

			}
		}

		response.setContentType(APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(),responseMap);

	}
}
