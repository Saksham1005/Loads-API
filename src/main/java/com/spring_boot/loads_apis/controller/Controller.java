package com.spring_boot.loads_apis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring_boot.loads_apis.dao.Load_Dao;
import com.spring_boot.loads_apis.entity.Load;

@RestController
public class Controller {
	@Autowired
	private Load_Dao loadDao;

	@PostMapping("/load")
	public String createLoad(@RequestBody Load load) {
		
		return loadDao.addLoad(load);
	}
	
	@GetMapping("/load")
	public List<?> getLoads(@RequestParam("shipperId") String shipperId){
		return loadDao.getLoads(shipperId);
	}
	
	@GetMapping("/load/{loadId}")
	public List<?> getLoad(@PathVariable("loadId") String loadId){
		return loadDao.getLoad(loadId);
	}
	
	@PutMapping("/load/{loadId}")
	public void updateLoad(@PathVariable("loadId") String loadId, @RequestBody Load load) {
		loadDao.updateLoad(loadId, load);
	}
	
	@DeleteMapping("/load/{loadId}")
	public void deleteLoad(@PathVariable("loadId") String loadId) {
		 loadDao.deleteLoad(loadId);	
	}
	
}
