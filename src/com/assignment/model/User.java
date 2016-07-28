package com.assignment.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@PrimaryKey
	@Persistent
	Key id;
	

	@Persistent(defaultFetchGroup = "true", mappedBy = "user")
	List<TaskModel> tasks;
	
	public User(final Key id) {
		this.id = id;
	}
	
	public void addTask(final String taskname, final Date date) throws ParseException{
		TaskModel task = new TaskModel();
		task.setName(taskname);
		task.setCreatedDate(date);
		tasks.add(task);
	}
	public TaskModel getTask(final int index){
		return tasks.get(index);
	}
	public void deleteTask(final int index){
		tasks.remove(index);
	}
	public List<TaskModel> getTasks(){
		return tasks;
	}
}
