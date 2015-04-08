package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.items.Category;

import java.util.ArrayList;
import java.util.Collection;

public class Course
{
	public Course()
	{
		categories = new ArrayList<Category>();
		assignments = new ArrayList<Assignment>();
		System.out.println("gradebook.Course() called");
	}
	/**
	*Name of course.
	*/
	public String name;

	/**
	*Collectiion of sections of this course taught by the Instructor associated with this Gradebook.
	*/
	public Collection<Section> sections;

	/**
	*Collection of the various grade categories associated with this course.
	*Grade categories are fixed at the course level.  All categories must be represented in all sections.
	*/
	public ArrayList<Category> categories;

	/**
	*Collection of assignments associated with each category.
	*/
	public ArrayList<Assignment> assignments;

	/**
	*Add category to collection.
	*pre:
	*	!categories.contains(cate);
	*/
	public void add(Category cate)
	{
		System.out.println("gradebook.Course.add(Category) called");
		categories.add(cate);
	}

	public void add(Assignment assign)
	{
		System.out.println("gradebook.Course.add(Assignment) called");
		assignments.add(assign);
	}

	public void syncRoster()
	{
		System.out.println("gradebook.Course.syncRoster() called");
	}
}
