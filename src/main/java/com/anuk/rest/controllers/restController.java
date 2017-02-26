package com.anuk.rest.controllers;
 
import java.util.List;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
import com.anuk.rest.model.Employee;
import com.anuk.rest.dao.EmployeeDAO;

 
@RestController
public class restController {
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public String hello(){
        return "Rest Hello World using Spring";
    }
    
    @RequestMapping(value="/employees",method=RequestMethod.GET)
    public ResponseEntity<List<Employee>> getEmployees() {
        EmployeeDAO dao = new EmployeeDAO();
        List<Employee> employees = dao.getEmployees();
        for(Employee emp : employees)
            System.out.println(emp.getName());
        if(employees.isEmpty())
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
 
    
    @RequestMapping(value="/addemployee",method=RequestMethod.POST)
    public ResponseEntity<Void> addEmployee(@RequestBody Employee emp){
        emp.setName(emp.getName());
        emp.setAge(emp.getAge());
                
        EmployeeDAO dao = new EmployeeDAO();
        dao.addEmployee(emp);
        
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/deleteemployee/{id}",method=RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.deleteEmployee(id);
        if(count==0){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }
    
    @RequestMapping(value="/updateemployee/{id}",method=RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") int id, @RequestBody Employee emp){
        EmployeeDAO dao = new EmployeeDAO();
        int count = dao.updateEmployee(id,emp);
        if(count==0){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(HttpStatus.OK);
    }
}
 