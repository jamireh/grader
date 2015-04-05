package grader.model.file;

import grader.model.gradebook.Gradebook;
import grader.model.people.Person;

/**
 * The WorkSpace class is a singleton contains all the information for the
 * current user session, including the open Gradebook, active user,
 * undo/redo history, and the clipboard.
 */
public abstract class WorkSpace {
	/**
	 * The currently open Gradebook.
	 */
	public static Gradebook gradebook;

	/**
	 * The currently logged in user.
	 */
	public static Person user;

	/**
	 * The previous Gradebook for undoing.
	 * Will be null if no changes have been made this session.
	 * This will reset to null when undo is called, since history only goes back
	 * one operation.
	 */
	public static Gradebook prevGradebook;

	/**
	 * The future Gradebook for redoing.
	 * Will be null if undo has not been called.
	 * This will reset to null when redo is called, since history only goes back
	 * one operation.
	 */
	public static Gradebook futureGradebook;

	/**
	 * The value stored in the clipboard.
	 * Will be null if cut/copy have not been called.
	 */
	public static String clipboard;

	/**
	 * The contents of the currently selected item.
	 */
	public static String selectedContext;
}
