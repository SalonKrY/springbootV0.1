package com.example.demo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Box;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import io.swagger.annotations.Api;

@Api(value="Mongodb-Controller类",tags= {"测试Mongodb接口"})
@RestController
@RequestMapping("/mongodb")
public class MongoController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@PostMapping("/save")
	public void save() {
		User user = new User();
		user.setId("1");
		user.setBirthday(new Date());
		user.setUserName("username");
		user.setPassword("123456");
		mongoTemplate.save(user);
	}
	
	@GetMapping("/getUserByName")
	public String getUserByName(String userName,int pageSize,int pageIndex) {
		Query query = new Query(Criteria.where("userName").is(userName)).skip( pageSize * ( pageIndex - 1)).limit(pageSize);
		User user = mongoTemplate.findOne(query, User.class);
		return JSON.toJSONString(user);
	}
	
	@PostMapping("/update")
	public String update(String id, String userName) {
		Query query = new Query(Criteria.where("id").is(id));
		Update update = new Update().set("userName", userName);
		UpdateResult result = mongoTemplate.updateFirst(query, update, User.class);
		return "更新个数为：" + result.getMatchedCount();
	}
	
	@PostMapping("/delete")
	public String delete(String id) {
		Query query = new Query(Criteria.where("id").is(id));
		DeleteResult result = mongoTemplate.remove(query, User.class);
		return "删除个数为：" + result.getDeletedCount();
	}
	
	@GetMapping("/circleQuery")
	public String circleQuery(){
		Circle circle = new Circle(30, 20, 20);
		Query query = new Query(Criteria.where("location").within(circle));
		List<User> users = mongoTemplate.find(query,User.class);
		return JSON.toJSONString(users);
	}
	
	@GetMapping("/circleSphereQuery")
	public String circleSphereQuery(){
		Circle circle = new Circle(30, 20, 20);
		Query query = new Query(Criteria.where("location").withinSphere(circle));
		List<User> users = mongoTemplate.find(query,User.class);
		return JSON.toJSONString(users);
	}
	
	@GetMapping("/rectangleQuery")
	public String rectangleQuery(){
		Box box = new Box(new Point(10,10),new Point(10,20));
		Query query = new Query(Criteria.where("location").within(box));
		List<User> users = mongoTemplate.find(query,User.class);
		return JSON.toJSONString(users);
	}
	
	@GetMapping("/nearbyQuery")
	public String nearbyQuery(){
		Point point = new Point(10,10);
		Query query = new Query(Criteria.where("location").near(point).maxDistance(20));
		List<User> users = mongoTemplate.find(query,User.class);
		return JSON.toJSONString(users);
	}
	
	@GetMapping("/nearestQuery")
	public String nearestQuery(){
		Point point = new Point(10,10);
		NearQuery query = NearQuery.near(point).maxDistance(new Distance(100000, Metrics.KILOMETERS));
		GeoResults<User> users = mongoTemplate.geoNear(query, User.class);
		return JSON.toJSONString(users);
	}
	
	@PostMapping("/createGeoData")
	public void createGeoData(){
		for (int i = 0; i < 10; i++) { 
			User user = new User();
			user.setId( i+ 1 + "");
			user.setBirthday(new Date());
			user.setUserName("username");
			user.setPassword("123456");
			Double location[] = new Double[]{Double.valueOf(Math.random() * 100), Double.valueOf(Math.random() * 100)};
			user.setLocation(location);
			mongoTemplate.insert(user);
		}
	}
}
