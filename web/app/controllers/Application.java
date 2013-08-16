package controllers;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * Manage a database of computers
 */
public class Application extends Controller {

    /**
     * This result directly redirect to application home.
     */
    public static Result GO_HOME = redirect(
        routes.ComputerController.list(0, "name", "asc", "")
    );
    
    /**
     * Handle default path requests, redirect to computers list
     */
    public static Result index() {
        return GO_HOME;
    }
}
            
