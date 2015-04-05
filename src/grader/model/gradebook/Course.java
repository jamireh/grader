package grader.model.gradebook;

import grader.model.items.Assignment;
import grader.model.items.Category;

import java.util.Collection;

public abstract class Course
{

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
	public Collection<Category> categories;

	/**
	*Collection of assignments associated with each category.
	*/
	public Collection<Assignment> assignments;

	/**
	*Add category to collection.
	*pre:
	*	!categories.contains(cate);
	*/
	public abstract void addCategory(Category cate);
}
